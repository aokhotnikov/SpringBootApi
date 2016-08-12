package com.store.service;

import com.store.model.Tovar;
import java.util.List;


public abstract class TovarService implements TovarRepository{

    private static List<Tovar> tovars;

    public Tovar findByName(String name){

        for(Tovar tovar : tovars){
            if(tovar.getName().equalsIgnoreCase(name)){
                return tovar;
            }
        }
        return null;
    }

}
