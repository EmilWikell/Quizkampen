import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    public Database(){
        String tempLine;
        String path = "C:\\Users\\rober\\IdeaProjects\\Quizkampen\\src\\questions.txt";
        List<String> categories = new ArrayList();

        try {
            BufferedReader buffIn = new BufferedReader(new FileReader(path));
            while((tempLine = buffIn.readLine()) !=null) {
                if(tempLine.charAt(0) == '!') {
                    categories.add(tempLine.substring(1));
                }

                System.out.println(tempLine);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not read from file");
        }
        for (String s: categories) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Database database = new Database();
    }
}


