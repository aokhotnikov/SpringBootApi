package com.store.controller;

import com.store.model.Category;
import com.store.model.Character;
import com.store.model.Tovar;
import com.store.service.CategoryRepository;

import javax.annotation.Resource;

import com.store.service.TovarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Resource(name="tovarRepository")
    private TovarRepository trepository;



    /**------------------------GET-------------------------
     * The method returns an object "Category" for the requested field "id"
     * @param cat_id
     * @return "Category" object
     */
    @GetMapping(value = "/category/{cat_id}")
    public Category getCategory(@PathVariable long cat_id){
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category;
        }
        return null;
    }

    /**------------------------POST-------------------------
     * The method creates a new category
     * @param name
     * @param parent_id
     */
    @PostMapping(value = "/category")
    public void postCategory(String name, @RequestParam(value="parent_id", defaultValue = "0") long parent_id) {

        Category category = new Category(name, parent_id);
        repository.save(category);
    }

    /**--------------------------PUT------------------------
     * The method editing category
     * @param cat_id
     * @param name
     * @param parent_id
     */
    @PutMapping(value = "/category/{cat_id}")
    public void putCategory(@PathVariable long cat_id, String name, long parent_id) {

        Category category = repository.findOne(cat_id);
        category.setName(name);
        category.setParent_id(parent_id);
        repository.save(category);
    }

    /**-----------------------DELETE------------------------
     * The method removes the category
     * @param cat_id
     * @return true or false
     */
    @DeleteMapping(value = "/category/{cat_id}")
    public boolean delCategory(@PathVariable long cat_id) {

        if ((cat_id > 0) && (repository.exists(cat_id))) {
            repository.delete(cat_id);
            System.out.println(String.format("Delete category id = %d", cat_id));
            return true;
        }
        return false;
    }



    /**------------------GET ALL CATEGORIES----------------------
     * The method returns all categories
     * @return An iterator of objects "Category"
     */
    @CrossOrigin(origins = "http://reactjs.dev")
    @GetMapping(value = "/categories")
    public Iterable<Category> getCaregories() {
        return repository.findAll();
    }


    /**-----------GET TOVARS BY CATEGORY AND VALUES -------------
     * The method returns the tovars for a given category and entered the characteristics values
     * @param cat_id
     * @param values
     * @return List of objects "Tovar" or null
     */
    @GetMapping(value = "/category/{cat_id}/tovars")
    public List<Tovar> getTovars(@PathVariable long cat_id, long[] values) {
        if (cat_id > 0) {

            if (values == null) {
                Category category = repository.findOne(cat_id);
                return category.getTovars();
            }

            return trepository.findByValue(cat_id, values, values.length);
        }
        return null;
    }

    /**------------------GET CHARACTERS BY CATEGORY----------------------
     * The method returns the characteristics of the category
     * @param cat_id
     * @return List of objects "Character" or null
     */
    @GetMapping(value = "/category/{cat_id}/characters")
    public List<Character> getCharacters(@PathVariable long cat_id) {
        if (cat_id > 0) {
            Category category = repository.findOne(cat_id);
            return category.getCharacters();
        }
        return null;
    }
}
