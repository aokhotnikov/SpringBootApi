package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    protected  Owner(){}

    public Owner(String name) {
        this.name = name;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @JsonBackReference
    private Set<Tovar> tovars;

    public Set<Tovar> getTovars() {
        return this.tovars;
    }

    public void setTovars(Set<Tovar> tovars) {
        this.tovars = tovars;
    }
}
