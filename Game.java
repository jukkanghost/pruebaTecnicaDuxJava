import java.util.ArrayList;
import java.util.List;

import javax.print.FlavorException;

public class Game {
    
    public static final int POINTS_TO_DEUCE = 45;
    public static final int POINTS_STEP = 15;
    public static final int POINTS_TO_GAME = 60;
    public static final int POINTS_TO_TIEBREAK = 105;
    public static final int POINTS_TO_ADVANTAGE_TIEBREAK = 90;


    private boolean deuce;
    private boolean tiebreak;
    private Player winner;
    private Player serving;
    private List<Integer> score;
    private List<Boolean> advantage;
    private boolean gameEnd;


    public Game() {
        setDeuce(false);
        this.winner = null;
        this.gameEnd = false;
        this.tiebreak = false;
        score = new ArrayList<>(2);
        score.add(0);
        score.add(0);
        advantage = new ArrayList<>(2);
        advantage.add(false);
        advantage.add(false);
    }

    public void setTiebreak(){
        this.tiebreak = true;
    }

    public void setServing(Player player) {
        this.serving = player;
    }

    public Player getServing() {
        return this.serving;
    }

    public List<Boolean> getAdvantage() {
        return this.advantage;
    }

    public void setDeuce(boolean deuce) {
        this.deuce = deuce;
    }

    public List<Integer> getScore() {
        return this.score;
    }

    public void setAdvantage(Player player) {
        if(this.advantage.get(player.getId())) {
            setGameEnd();
        }
        else {
            this.advantage.set(player.getId(), true);
            if (this.advantage.stream().allMatch(advantage-> advantage.equals(true))) {
                for(Boolean ad : advantage) {
                    ad = false;
                }
            }
        }
    }

    public void addPointsPlayer(Player player) {
            if(tiebreak){
                this.score.set(player.getId(), this.score.get(player.getId()) + POINTS_STEP);
                gameEnd();
            }
            if(deuce){
                setAdvantage(player);
            }
            else {
                this.score.set(player.getId(), this.score.get(player.getId()) + POINTS_STEP);
                gameDeuce();
                gameEnd(); 
            }
    }

    public void displayScore() {
        System.out.print("Score : ");
        this.score.forEach(score -> {System.out.print(score + " ");});
        System.out.println("");

    }

    public void displayAdvantage() {
        System.out.print("Advantage : ");
        this.advantage.forEach(advantage -> {System.out.print(advantage + " ");});
        System.out.println("");
    }

    public boolean getDeuce() {
        return this.deuce;
    }

    public void setWinnerGame(Player player) {
        this.winner = player;
    }

    public Player getWinnerGame() {
        return this.winner;
    }

    private void gameEnd() {
        if(!tiebreak) {
            this.score.forEach(score -> {if(score == POINTS_TO_GAME) {
                setGameEnd();
            } });
        }
        else {
            this.score.forEach(score -> {if(score >= POINTS_TO_TIEBREAK) {
                if(this.score.get(this.score.size()-2) >= (this.score.get(this.score.size()-1) + 2)) {
                    setGameEnd();
                }
                if(this.score.get(this.score.size()-1) >= (this.score.get(this.score.size()-2) + 2)) {
                    setGameEnd();
                }
            } });
        }
    }

    private void gameDeuce() {
        if(!tiebreak) {
            setDeuce(this.score.stream().allMatch(score-> score == POINTS_TO_DEUCE));
        }
    }

    private void setGameEnd(){
        this.gameEnd = true;
    }

    public boolean getGameEnd(){
        return this.gameEnd;
    }

    public boolean getTiebreak(){
        return this.tiebreak;
    }

   

}
