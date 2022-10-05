import java.util.ArrayList;
import java.util.List;

public class Set {
    
    public static final int GAMES_TO_SET = 6;
    // public static final int GAMES_TO_TIEBREAK = 5;
    
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

    public boolean getTiebreak() {
        return this.tiebreak;
    }

    private void Tiebreak() {
        if(this.score.stream().allMatch(score-> score == GAMES_TO_SET)){
            setTiebreak();
        };
    }

    public void setTiebreak(){
        this.tiebreak = true;
    }

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

    private void setSetEnd() {
        this.setEnd = true;
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
    
    public void addGameToSet (Game game) {
        this.games.add(game);
    }

    public int addNewGame() {
        Game game = new Game();
        this.games.add(game);
        return this.games.lastIndexOf(game);
    }

    public void setWinnerSet(Player player) {
        this.winner = player;
    }

    public Player getWinnerSet() {
        return this.winner;
    }

    public Game getGame(int currentGame) {
        return this.games.get(currentGame);
    }

    public void displayScore() {
        System.out.print("Games : ");
        this.score.forEach(score -> {System.out.print(score + " ");});
        System.out.println("");
    }


}
