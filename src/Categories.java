import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Categories {
    public static void main(String[] args) {
        new Categories();
    }

    private String path = "C:\\Users\\emils\\IdeaProjects\\OOP & JAVA\\Sprint5\\Quizkampen\\src\\questions.txt";
    private List<QuestionClass> ENTERTAINMENT = new ArrayList<>();
    private List<QuestionClass> GEOGRAPHY = new ArrayList<>();
    private List<QuestionClass> SPORTS = new ArrayList<>();
    private List<QuestionClass> HISTORY = new ArrayList<>();
    private List<QuestionClass> IT = new ArrayList<>();
    private BufferedReader buffIn;
 
    public Categories(){
        String tempLine;
        String currentCategory ="";
        try {
            buffIn = new BufferedReader(new FileReader(path));
            while ((tempLine = buffIn.readLine()) != null){
                if(tempLine.charAt(0) == '!'){
                    currentCategory = tempLine.substring(1);
                    tempLine = buffIn.readLine();
                }
                if (currentCategory.equals("ENTERTAINMENT")){
                    ENTERTAINMENT.add(addQuestionToList(tempLine));
                }else if(currentCategory.equals("GEOGRAPHY")){
                    GEOGRAPHY.add(addQuestionToList(tempLine));
                }else if(currentCategory.equals("SPORTS")){
                    SPORTS.add(addQuestionToList(tempLine));
                }else if (currentCategory.equals("HISTORY")){
                    HISTORY.add(addQuestionToList(tempLine));
                }else if(currentCategory.equals("IT")){
                    IT.add(addQuestionToList(tempLine));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private QuestionClass addQuestionToList(String tempLine) throws IOException {
        return new QuestionClass(tempLine, buffIn.readLine(), buffIn.readLine(), buffIn.readLine(), buffIn.readLine());
    }
    public List<QuestionClass> getQuestions(int amountOfQuestions,String currentCategory){
        List<QuestionClass> chosenQuestions = new ArrayList<>();
        if (currentCategory.equals("ENTERTAINMENT")){
            
        }else if(currentCategory.equals("GEOGRAPHY")){
        }else if(currentCategory.equals("SPORTS")){
        }else if (currentCategory.equals("HISTORY")){
        }else if(currentCategory.equals("IT")){
    }

}
