import java.util.ArrayList;
import java.util.List;

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
    
    /* Principal functions */

    /* Controla que jugador tiene advantage cuando se esta en deuce, y marca el fin del Game cuando alguno gana */
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

    /* Suma un punto al jugador ganador y llama a las funciones de chequeo */
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

    /* Chequea si se ha terminado el Game */
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

    /* Chequea si el Game esta en deuce */
    private void gameDeuce() {
        if(!tiebreak) {
            setDeuce(this.score.stream().allMatch(score-> score == POINTS_TO_DEUCE));
        }
    }

    /* Setters */
    public void setTiebreak(){
        this.tiebreak = true;
    }

    public void setServing(Player player) {
        this.serving = player;
    }
    
    public void setDeuce(boolean deuce) {
        this.deuce = deuce;
    }

    public void setWinnerGame(Player player) {
        this.winner = player;
    }

    private void setGameEnd(){
        this.gameEnd = true;
    }

    /* Getters */
    public Player getServing() {
        return this.serving;
    }

    public List<Boolean> getAdvantage() {
        return this.advantage;
    }
    
    public List<Integer> getScore() {
        return this.score;
    }

    public boolean getDeuce() {
        return this.deuce;
    }

    public Player getWinnerGame() {
        return this.winner;
    }

    public boolean getGameEnd(){
        return this.gameEnd;
    }

    public boolean getTiebreak(){
        return this.tiebreak;
    }

}
