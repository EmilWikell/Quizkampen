package DipatchHandlers;

import DispatchClasses.ScoreClass;
public class ScoreHandler{

private int scoreTotal = 0;
private int scoreThisRound = 0;


   public void increaseScore(){
       int scoreEarned = 0;
       scoreEarned += 1;
       scoreTotal += scoreEarned;
       scoreThisRound += scoreEarned;
   }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public int getScoreThisRound() {
        return scoreThisRound;
    }
    public void resetRound(){
       scoreThisRound = 0;
    }
}
