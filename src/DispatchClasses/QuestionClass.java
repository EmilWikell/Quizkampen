package DispatchClasses;

import java.io.Serializable;

public class QuestionClass implements Serializable {
    private String question;
    private String altOne;
    private String altTwo;
    private String altThree;
    private String answer;

    public QuestionClass(String question, String altOne, String altTwo, String altThree, String answer) {
        this.question = question;
        this.altOne = altOne;
        this.altTwo = altTwo;
        this.altThree = altThree;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }
    public String getAltOne() {
        return altOne;
    }
    public String getAltTwo() {
        return altTwo;
    }
    public String getAltThree() {
        return altThree;
    }
    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString(){
        return (question +","+ altOne +","+ altTwo +","+ altThree +","+ answer);
    }
}
