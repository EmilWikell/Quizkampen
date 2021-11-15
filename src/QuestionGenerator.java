public class QuestionGenerator {
    public QuestionClass generateQuestion(){
        String question = "";
        String altOne = "";
        String altTwo = "";
        String altThree = "";
        String answer = "";
        return new QuestionClass(question, altOne, altTwo, altThree, answer);
    }
}
