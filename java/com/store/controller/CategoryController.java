package com.store.controller;

import com.store.model.Category;
import com.store.model.Character;
import com.store.model.Tovar;
import com.store.service.CategoryRepository;
import java.sql.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    private DataSource dataSource;

    //------------------GET----------------------
    @GetMapping(value = "/category/{cat_id}")
    public Category getCategory(@PathVariable long cat_id){
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category;
        }
        return null;
    }

    //------------------POST----------------------
    @PostMapping(value = "/category")
    public void postCategory(String name, @RequestParam(value="parent_id", defaultValue = "0") long parent_id) {

        Category category = new Category(name, parent_id);
        repository.save(category);
    }

    //------------------PUT----------------------
    @PutMapping(value = "/category/{cat_id}")
    public void putCategory(@PathVariable long cat_id, String name, long parent_id) {

        Category category = repository.findOne(cat_id);
        category.setName(name);
        category.setParent_id(parent_id);
        repository.save(category);
    }

    //------------------DELETE----------------------
    @DeleteMapping(value = "/category/{cat_id}")
    public boolean delCategory(@PathVariable long cat_id) {

        if ((cat_id > 0) && (repository.exists(cat_id))) {
            repository.delete(cat_id);
            System.out.println(String.format("Delete category id = %d", cat_id));
            return true;
        }
        return false;
    }



    //------------------GET ALL CATEGORIES----------------------
    @GetMapping(value = "/categories")
    public Iterable<Category> getCaregories() {
        return repository.findAll();
    }

    //------------------GET TOVARS BY CATEGORY----------------------
    @GetMapping(value = "/category/{cat_id}/tovars")
    public List<Tovar> getTovars(@PathVariable long cat_id) {
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category.getTovars();
        }
        return null;
    }

    //------------------GET TOVARS BY CATEGORY AND VALUES----------------------
    @GetMapping(value = "/category/{cat_id}/tovars/{val_id}")
    public List<Tovar> getTovarsByValue(@PathVariable long cat_id, @PathVariable long val_id, String mas) {
        System.out.println(mas);
        if ((cat_id > 0) && (val_id > 0)) {
            List<Tovar> tovars = new ArrayList<>();
            String sql = " SELECT t.* from tovar t" +
                    " left join category_tovar ct on t.id = ct.tovar_id" +
                    " left join category cat on cat.id = ct.cat_id" +
                    " left join tovar_characters tc on t.id = tc.tovar_id" +
                    " left join values_characters vc on vc.id = tc.val_id" +
                    " left join characters c on c.id = vc.char_id" +
                    " where cat.id = ? and vc.id = ?";

            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/market","root", "fbi");
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, cat_id);
                ps.setLong(2, val_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    long id = rs.getLong("ID");
                    String name = rs.getString("NAME");
                    int available = rs.getInt("AVAILABLE");
                    double price = rs.getDouble("PRICE");
                    int garanty = rs.getInt("GARANTY");
                    Tovar tovar = new Tovar(id, name, available, price, garanty);
                    tovars.add(tovar);
                }
                //System.out.println(tovars.toString());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
            return tovars;
        }
        return null;
    }

    //------------------GET CHARACTERS BY CATEGORY----------------------
    @GetMapping(value = "/category/{cat_id}/characters")
    public List<Character> getCharacters(@PathVariable long cat_id) {
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category.getCharacters();
        }
        return null;
    }
}
