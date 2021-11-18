package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import DispatchClasses.QuestionClass;

public class DAO {
    public static void main(String[] args) {
        new DAO();
    }

    private String path = "Quizkampen/src/DAO/questions.txt";
    private List<QuestionClass> ENTERTAINMENT = new ArrayList<>();
    private List<QuestionClass> GEOGRAPHY = new ArrayList<>();
    private List<QuestionClass> SPORTS = new ArrayList<>();
    private List<QuestionClass> HISTORY = new ArrayList<>();
    private List<QuestionClass> IT = new ArrayList<>();
    private List<QuestionClass> LITERATURE = new ArrayList<>();
    private List<QuestionClass> ART = new ArrayList<>();
    private List<QuestionClass> SCIENCE = new ArrayList<>();
    private List<QuestionClass> ANIMALS = new ArrayList<>();
    private List<QuestionClass> CAPITALS = new ArrayList<>();
    List<String> listOfCategories = new ArrayList<>();
    private BufferedReader buffIn;
 
    public DAO(){
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
                    case "LITERATURE" -> LITERATURE.add(addQuestionToList(tempLine));
                    case "ART" -> ART.add(addQuestionToList(tempLine));
                    case "SCIENCE" -> SCIENCE.add(addQuestionToList(tempLine));
                    case "ANIMALS" -> ANIMALS.add(addQuestionToList(tempLine));
                    case "CAPITALS" -> CAPITALS.add(addQuestionToList(tempLine));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listOfCategories.add("ENTERTAINMENT");
        listOfCategories.add("GEOGRAPHY");
        listOfCategories.add("SPORTS");
        listOfCategories.add("HISTORY");
        listOfCategories.add("IT");
        listOfCategories.add("LITERATURE");
        listOfCategories.add("ART");
        listOfCategories.add("SCIENCE");
        listOfCategories.add("ANIMALS");
        listOfCategories.add("CAPITALS");
    }
    public List<String> getListOfCategories(){
        return listOfCategories;
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
            }case "LITERATURE" -> {
                Collections.shuffle(LITERATURE);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(LITERATURE.get(i));
                }
            }case "ART" -> {
                Collections.shuffle(ART);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(ART.get(i));
                }
            }case "SCIENCE" -> {
                Collections.shuffle(SCIENCE);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(SCIENCE.get(i));
                }
            }case "ANIMALS" -> {
                Collections.shuffle(ANIMALS);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(ANIMALS.get(i));
                }
            }case "CAPITALS" -> {
                Collections.shuffle(CAPITALS);
                for (int i = 0; i < amountOfQuestions; i++) {
                    chosenQuestions.add(CAPITALS.get(i));
                }
            }
        }
        return chosenQuestions;
    }
}
