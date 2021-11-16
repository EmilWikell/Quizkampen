import ClientLogic.QuestionClass;

public class QuestionGenerator {

    String usedQuestion;
    private String category;
    public QuestionClass generateQuestion(String category){
        //currentQuestion = randomizar ettt random index i databas
        //if(!usedQuestion.contains(currentQuestion)
        //om falsk generera nytt currentquestion
        this.category = category;
        String question = "";
        String altOne = "";
        String altTwo = "";
        String altThree = "";
        String answer = "";
        return new QuestionClass(question, altOne, altTwo, altThree, answer);
    }
}
