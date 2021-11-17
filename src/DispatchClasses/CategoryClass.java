package DispatchClasses;

import java.io.Serializable;

public class CategoryClass implements Serializable {
    String categories = "Choose category";

    public void addToList(String string){
        categories += "," + string ; // make sure to only use first four elements in split
    }

    @Override
    public String toString() {
        return categories;
    }
}
