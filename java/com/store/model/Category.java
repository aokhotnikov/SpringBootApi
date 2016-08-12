package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private long parent_id;

    protected Category() {}

    public Category(String name, long parent_id) {
        this.name = name;
        this.parent_id = parent_id;
    }

    public Category(String name, int parent_id, List<Tovar> tovars) {
        this.name = name;
        this.parent_id = parent_id;
        this.tovars = tovars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return String.format("[id=%d, name='%s', parent_id=%d]", id, name, parent_id);
    }


    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "category_tovar",
            joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tovar_id", referencedColumnName = "id")
    )
    private List<Tovar> tovars;

    public List<Tovar> getTovars() {
        return tovars;
    }

    public void setTovars(List<Tovar> tovars) {
        this.tovars = tovars;
    }


    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "category_characters",
            joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "char_id", referencedColumnName = "id")
    )
    private List<Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

}
