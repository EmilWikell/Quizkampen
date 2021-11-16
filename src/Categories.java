import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
                switch (currentCategory) {
                    case "ENTERTAINMENT" -> ENTERTAINMENT.add(addQuestionToList(tempLine));
                    case "GEOGRAPHY" -> GEOGRAPHY.add(addQuestionToList(tempLine));
                    case "SPORTS" -> SPORTS.add(addQuestionToList(tempLine));
                    case "HISTORY" -> HISTORY.add(addQuestionToList(tempLine));
                    case "IT" -> IT.add(addQuestionToList(tempLine));
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

        switch (currentCategory) {
            case "ENTERTAINMENT" -> {
                Collections.shuffle(ENTERTAINMENT);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(ENTERTAINMENT.get(i));
                }
            }case "GEOGRAPHY" -> {
                Collections.shuffle(GEOGRAPHY);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(GEOGRAPHY.get(i));
                }
            }case "SPORTS" -> {
                Collections.shuffle(SPORTS);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(SPORTS.get(i));
                }
            }case "HISTORY" -> {
                Collections.shuffle(HISTORY);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(HISTORY.get(i));
                }
            }case "IT" -> {
                Collections.shuffle(IT);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(IT.get(i));
                }
            }
        }
        return chosenQuestions;
    }

}
