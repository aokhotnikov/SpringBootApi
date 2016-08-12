package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "values_characters")
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private long char_id;

    protected Value(){}

    public Value(String name, long char_id) {
        this.name = name;
        this.char_id = char_id;
    }

    public Value(String name, long char_id, Set<Tovar> tovars) {
        this.name = name;
        this.char_id = char_id;
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

    public long getChar_id() {
        return char_id;
    }

    public void setChar_id(long char_id) {
        this.char_id = char_id;
    }

    @Override
    public String toString() {
        return String.format("[id=%d, name='%s', char_id=%d]", id, name, char_id);
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "values")
    @JsonBackReference
    private Set<Tovar> tovars;

    public Set<Tovar> getTovars() {
        return tovars;
    }

    public void setTovars(Set<Tovar> tovars) {
        this.tovars = tovars;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "char_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Character character;

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}
