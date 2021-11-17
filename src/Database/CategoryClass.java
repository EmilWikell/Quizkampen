package Database;

import DAO.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryClass {

    private List<String> listOfCategories;

    public CategoryClass(DAO dao){
        this.listOfCategories = dao.getListOfCategories();
    }

    public List<String> getSmallListOfCategories(){
        List<String> smallListOfCategories = new ArrayList<>();
        Collections.shuffle(listOfCategories);
        for (int i = 0; i < 4; i++) {               // we always have 4 categories to choose from
            smallListOfCategories.add((listOfCategories.get(i)));
        }
        return smallListOfCategories;
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
