import java.util.ArrayList;
import java.util.List;

public class Match {
    
    private List<Player> players;
    private List<Set> sets;
    private Player winner;
    private String tournamentName;
    private int probabilityPlayer1;
    private int probabilityPlayer2;
    private int matchTotalSets;
    private List<Integer> score;
    private boolean matchEnd;

    public Match(Player player1, Player player2, String tournamentName, int totalSets, int probabilityPlayer1) {
        this.players = new ArrayList<>();
        this.matchTotalSets = getTotalSets(totalSets);
        this.sets = new ArrayList<>();
        addNewSet();
        players.add(player1);
        players.add(player2);
        setTournamentName(tournamentName);
        setProbabiltyPlayer1(probabilityPlayer1);
        setProbabiltyPlayer2(100 - probabilityPlayer1);
        this.score = new ArrayList<>();
        score.add(0);
        score.add(0);
        matchEnd = false;
    }
    
    /* Principal functions */
    
    /* Chequea si se ha terminado el partido */
    private void matchEnd() {
        this.score.forEach(score -> {if(score == this.matchTotalSets) {
            setMatchEnd();
        } });
    }
    
    /* Agrega un set ganado al jugador correspondiente */
    public void addScore(Player player) {
        this.score.set(player.getId(), this.score.get(player.getId()) + 1);
        matchEnd();
    }
    
    /* Crea y agrega un set nuevo */
    public int addNewSet() {
        Set set = new Set();
        this.sets.add(set);
        return this.sets.lastIndexOf(set);
    }

    /* Setters */
    public void setWinner(Player player) {
        this.winner = player;
    }
    
    private void setMatchEnd(){
        this.matchEnd = true;
    }
    
    public void setTournamentName (String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setProbabiltyPlayer1 (int probabilityPlayer1) {
        this.probabilityPlayer1 = probabilityPlayer1;
    }

    public void setProbabiltyPlayer2 (int probabilityPlayer2) {
        this.probabilityPlayer2 = probabilityPlayer2;
    }
    
    /* Getters */

    public String getTournament(){
        return this.tournamentName;
    }

    public Player getWinner(){
        return this.winner;
    }

    public List<Integer> getScore() {
        return this.score;
    } 

    private int getTotalSets (int totalSets) {
        if(totalSets == 3){
            return 2;
        }
        if(totalSets == 5){
            return 5;
        }
        return 0;
    }

    public boolean getMatchEnd() {
        return this.matchEnd;
    }

    public List<Set> getAllSets() {
        return this.sets;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Set getSet(int currentSet) {
        return this.sets.get(currentSet);
    }

    public int getProbabiltyPlayer1 () {
        return this.probabilityPlayer1;
    }

    public int getProbabiltyPlayer2 () {
        return this.probabilityPlayer2;
    }

}
