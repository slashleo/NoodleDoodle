package org.slashleo.noodle;

import java.util.List;

public interface NoodleDao {

    /**
     * Creates a new Noodle entity.
     * @param noodleDto Noodle DTO
     * @return Created Noodle Entity DTO
     */
    NoodleDto create(NoodleDto noodleDto);

    /**
     * Reads one Entity from datasource.
     * @param id primary key of entity to be read
     * @return Noodle Entity DTO
     */
    NoodleDto read(int id);

    /**
     * Retrieves all Noodles Entities from datasource.
     * @return List of Noodle Entity DTOs
     */
    List<NoodleDto> readAll();

    /**
     * Update Noodle Entity.
     * @param noodleDto Noodle DTO
     * @return Updated Noodle Entity DTO
     */
    NoodleDto update(NoodleDto noodleDto);

    /**
     * Deletes a Noodle Entity.
     * @param noodleDto Noodle DTO
     */
    void delete(NoodleDto noodleDto);
}
