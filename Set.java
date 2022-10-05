import java.util.ArrayList;
import java.util.List;

public class Set {
    
    public static final int GAMES_TO_SET = 6;
    
    private List<Game> games;
    private List<Integer> score;
    private Player winner;
    private boolean setEnd;
    private boolean tiebreak;
    
    public Set() {
        this.games = new ArrayList<>();
        this.score = new ArrayList<>();
        score.add(0);
        score.add(0);
        setEnd = false;
        tiebreak = false;
    }
    
    /*Principal functions */

    /* Chequea si se juega tiebreak */
    private void Tiebreak() {
        if(this.score.stream().allMatch(score-> score == GAMES_TO_SET)){
            setTiebreak();
        };
    }

    /* Suma un game al set del jugador ganador */
    public void addScore(Player player) {
        if(tiebreak){
            this.score.set(player.getId(), this.score.get(player.getId()) + 1);
            setSetEnd();
        }
        else {
            this.score.set(player.getId(), this.score.get(player.getId()) + 1);
            Tiebreak();
            setEnd();
        }
    }

    /* Chequea si se ha terminado el set */
    private void setEnd() {
        this.score.forEach(score -> {if(score >= GAMES_TO_SET) {
            if(this.score.get(this.score.size()-2) >= (this.score.get(this.score.size()-1) + 2)) {
                setSetEnd();
            }
            if(this.score.get(this.score.size()-1) >= (this.score.get(this.score.size()-2) + 2)) {
                setSetEnd();
            }
        } });
    }

    /* Crea e incorpora un nuevo Game al Set */
    public int addNewGame() {
        Game game = new Game();
        this.games.add(game);
        return this.games.lastIndexOf(game);
    }
    
    /* Setters */
    public void setTiebreak(){
        this.tiebreak = true;
    }
    
    private void setSetEnd() {
        this.setEnd = true;
    }
    
    public void setWinnerSet(Player player) {
        this.winner = player;
    }
    
    /* Getters */
    public boolean getTiebreak() {
        return this.tiebreak;
    }
    
    public boolean getSetEnd() {
        return this.setEnd;
    }
    
    public List<Integer> getScore() {
        return this.score;
    }

    public List<Game> getGames() {
        return this.games;
    }
    
    public Player getWinnerSet() {
        return this.winner;
    }

    public Game getGame(int currentGame) {
        return this.games.get(currentGame);
    }


}
