package DAO;

import DispatchClasses.QuestionClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAO {

    private String path = "src/DAO/questions.txt";
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

    public DAO() {
        String tempLine;
        String currentCategory = "";
        try {
            buffIn = new BufferedReader(new FileReader(path));
            while ((tempLine = buffIn.readLine()) != null) {
                if (tempLine.charAt(0) == '!') {
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

    public List<String> getListOfCategories() {
        return listOfCategories;
    }

    private QuestionClass addQuestionToList(String tempLine) throws IOException {
        return new QuestionClass(tempLine, buffIn.readLine(), buffIn.readLine(), buffIn.readLine(), buffIn.readLine());
    }

    public List<QuestionClass> getQuestions(int amountOfQuestions, String currentCategory) throws IOException,NullPointerException{
        List<QuestionClass> chosenQuestions = new ArrayList<>();
        switch (currentCategory) {
            case "ENTERTAINMENT" ->createList(ENTERTAINMENT, chosenQuestions,amountOfQuestions);
            case "GEOGRAPHY" -> createList(GEOGRAPHY, chosenQuestions,amountOfQuestions);
            case "SPORTS" -> createList(SPORTS, chosenQuestions,amountOfQuestions);
            case "HISTORY" -> createList(HISTORY, chosenQuestions,amountOfQuestions);
            case "IT" -> createList(IT, chosenQuestions,amountOfQuestions);
            case "LITERATURE" -> createList(LITERATURE, chosenQuestions,amountOfQuestions);
            case "ART" -> createList(ART, chosenQuestions,amountOfQuestions);
            case "SCIENCE" -> createList(SCIENCE, chosenQuestions,amountOfQuestions);
            case "ANIMALS" -> createList(ANIMALS, chosenQuestions,amountOfQuestions);
            case "CAPITALS" -> createList(CAPITALS, chosenQuestions,amountOfQuestions);
        }
        return chosenQuestions;
    }

    private List<QuestionClass> createList(List<QuestionClass> currentList,
                                           List<QuestionClass> chosenQuestions, int amountOfQuestions) {
        Collections.shuffle(currentList);
        for (int i = 0; i < amountOfQuestions; i++) {
            chosenQuestions.add(currentList.get(i));
        }
        return chosenQuestions;
    }
}
