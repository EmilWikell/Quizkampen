package DispatchClasses;

public class ScoreClass {

    private int firstTot;
    private int firstRound;
    private int oppTot;
    private int oppRound;

    public ScoreClass(int firstTot, int firstRound, int oppTot, int oppRound) {
        this.firstTot = firstTot;
        this.firstRound = firstRound;
        this.oppTot = oppTot;
        this.oppRound = oppRound;
    }

    @Override
    public String toString() {
        return firstTot +","+ firstRound +","+ oppTot +","+ oppRound;
    }
}
