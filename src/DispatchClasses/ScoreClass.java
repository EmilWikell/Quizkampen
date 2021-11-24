package DispatchClasses;

import java.io.Serializable;

public class ScoreClass implements Serializable {

    String oppName;
    String score;
    private int firstTot;
    private int firstRound;
    private int oppTot;
    private int oppRound;

    public ScoreClass(String oppName, int firstTot, int firstRound, int oppTot, int oppRound) {
        this.oppName = oppName;
        score = "Score";
        this.firstTot = firstTot;
        this.firstRound = firstRound;
        this.oppTot = oppTot;
        this.oppRound = oppRound;
    }

    @Override
    public String toString() {
        return oppName + "-" + firstTot +"-"+  firstRound +"-"+ oppTot +"-"+ oppRound;
    }
}
