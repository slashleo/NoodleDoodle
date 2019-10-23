package org.slashleo.noodle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of Noodle DAO Interface
 */
@RequestScoped
public class NoodleDaoImpl implements NoodleDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(NoodleDaoImpl.class);

    @Inject
    EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public NoodleDto create(final NoodleDto noodleDto) {
        NoodleDto newNoodleDto = new NoodleDto();
        newNoodleDto.setName(noodleDto.getName());
        newNoodleDto.setFood(noodleDto.getFood());
        newNoodleDto.setVegan(noodleDto.getVegan());

        entityManager.persist(newNoodleDto);

        return newNoodleDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NoodleDto read(final int id) {
        return entityManager.find(NoodleDto.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NoodleDto> readAll() {
        List<NoodleDto> noodleDtoList = null;
        TypedQuery<NoodleDto> query = entityManager.createQuery("from NoodleDto", NoodleDto.class);

        // TODO Is this try-catch block good? Should I apply this to the other DaoImpl methods as well?
        try {
            noodleDtoList = query.getResultList();
        }
        catch(PersistenceException e) {
            LOGGER.debug("Error in NoodleDaoImpl.readAll() " + e);
        }

        return noodleDtoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public NoodleDto update(final NoodleDto noodleDto) {
        return entityManager.merge(noodleDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(final NoodleDto noodleDto) {
        NoodleDto deleteNoodleDto = entityManager.merge(noodleDto);
        entityManager.remove(deleteNoodleDto);
    }
}
