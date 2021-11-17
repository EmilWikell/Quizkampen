package DispatchClasses;

import DAO.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryHandler {

    private List<String> listOfCategories;

    public CategoryHandler(DAO dao){
        this.listOfCategories = dao.getListOfCategories();
    }

    public CategoryClass getSmallListOfCategories(){
        CategoryClass categories = new CategoryClass();
        Collections.shuffle(listOfCategories);
        for (int i = 0; i < 4; i++) {               // we always have 4 categories to choose from
            categories.addToList(listOfCategories.get(i));
        }
        return categories;
    }

    public void removeChosenCategory(String chosenCategory){
      //  listOfCategories.removeIf(c -> c.equals(chosenCategory)); kan ersätta foreach loopen, behöver förstå först
        for (String c: listOfCategories) {
            if (c.equals(chosenCategory)){
                listOfCategories.remove(c);
            }
        }
    }
}
