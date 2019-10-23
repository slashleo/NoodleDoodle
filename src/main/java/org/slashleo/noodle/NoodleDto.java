package org.slashleo.noodle;

import javax.persistence.*;

@Entity
@Cacheable
@Table(name = "NOODLE")
public class NoodleDto {

    /**
     * userID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private int id;

    /**
     * Name of person.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Carried food by person.
     */
    @Column(name = "food", nullable = false)
    private String food;

    /**
     * Is the carried food vegan?
     */
    @Column(name = "vegan", nullable = false)
    private Boolean vegan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }
}
