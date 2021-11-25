package DispatchClasses;

import java.io.Serializable;

public class CategoryClass implements Serializable {
    String categories = "Choose category";

    public void addToList(String string){
        categories += "-" + string ; // will give us 4 categories that client side will split
    }

    @Override
    public String toString() {
        return categories;
    }
}
