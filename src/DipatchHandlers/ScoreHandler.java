package DipatchHandlers;

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
    public void resetTotal(){
       scoreTotal = 0;
   }

    public Object getWinner(int oppScore) {
       String condition;
       if(scoreTotal > oppScore){
           condition = "You Won";
       }
       else if(scoreTotal == oppScore){
           condition = "Game Tied";
       }
       else{
           condition = "You Lost";
       }
       return new WinClass(condition);
    }
}
