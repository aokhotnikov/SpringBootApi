package com.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tovar")
public class Tovar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int available;
    private double price;
    private int garanty;
    private long owner_id;


    protected Tovar() {}

    public Tovar(String name, int available, double price, int garanty, long owner_id) {
        this.name = name;
        this.available = available;
        this.price = price;
        this.garanty = garanty;
        this.owner_id = owner_id;
    }

    public Tovar(long id, String name, int available, double price, int garanty) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.price = price;
        this.garanty = garanty;
    }

    public Tovar(String name, int available, double price, int garanty, List<Category> categories) {
        this.name = name;
        this.available = available;
        this.price = price;
        this.garanty = garanty;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }

    public int getGaranty() {
        return garanty;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGaranty(int garanty) {
        this.garanty = garanty;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return String.format(
                "А вот и товар: [id=%d, name='%s', garanty=%d]",
                id, name, garanty);
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tovars")
    @JsonBackReference
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @ManyToMany
    @JoinTable(name = "tovar_characters",
            joinColumns = @JoinColumn(name = "tovar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "val_id", referencedColumnName = "id")
    )
    private List<Value> values;

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Owner owner;

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
