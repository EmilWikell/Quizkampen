package DipatchHandlers;

import DispatchClasses.LoseClass;
import DispatchClasses.TieClass;
import DispatchClasses.WinClass;

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

    public Object getWinner(int oppScore) {
       if(scoreTotal > oppScore){
           return new WinClass();
       }
       else if(scoreTotal == oppScore){
           return new TieClass();
       }
       else{
           return new LoseClass();
       }
    }
}
