import java.util.Random;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Simulator {
    
    private boolean end;
    private Random random;
    private Match match;
    private boolean servingFirst;
    private Player lastServing;
    private Player winner;
    private int currentSet;
    private int currentGame;
    private List<Player> players;
    private Display display;

    private Timer timer;
    private TimerTask timerTask;
    private static final int TIMER_DELAY_MS = 300;

    public Simulator(Player player1, Player player2, String tournamentName, int totalSets, int probabilityPlayer1) {
        this.match = new Match(player1, player2, tournamentName, totalSets, probabilityPlayer1);
        this.end = false;
        this.servingFirst = true;
        this.random = new Random();
        this.currentSet = 0;
        this.currentGame = this.match.getSet(currentSet).addNewGame();
        this.players = this.match.getPlayers();
        this.display = new Display(this.match);
        whoServe();
    }

    /* Principal functions */

    /* Empieza el partido, crea el timer y lanza la tarea de jugar puntos periodicamente */
    public void playMatch() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run(){
                playPoint();
            }
        };
        timer.schedule(timerTask, 0, TIMER_DELAY_MS);
    }

    /* Juega el punto, y sigue la corriente del partido. Llama a las funciones de
     * Game, de Set y de Display para el control y la continuidad del partido.
     */
    public void playPoint() {
        if(!match.getMatchEnd()) {

            this.winner = whoWonPoint();
            match.getSet(currentSet).getGame(currentGame).addPointsPlayer(this.winner);

            if(match.getSet(currentSet).getGame(currentGame).getGameEnd() ) {

                match.getSet(currentSet).getGame(currentGame).setWinnerGame(this.winner);
                match.getSet(currentSet).addScore(this.winner);
                if(!match.getSet(currentSet).getSetEnd()) {
                    currentGame = match.getSet(currentSet).addNewGame();
                    if(match.getSet(currentSet).getTiebreak()) {
                        match.getSet(currentSet).getGame(currentGame).setTiebreak();
                    }
                    whoServe();
                }
                else {
                    match.addScore(this.winner);
                    currentSet = match.addNewSet();
                    currentGame = match.getSet(currentSet).addNewGame();
                    whoServe();
                }
            }

            display.show(match.getSet(currentSet).getGame(currentGame).getScore(),
                match.getSet(currentSet).getGame(currentGame).getServing(),
                 match.getSet(currentSet).getGame(currentGame).getAdvantage(),
                 match.getSet(currentSet).getGame(currentGame).getTiebreak());
            
        }
        else {
            this.end = true;
            match.setWinner(this.winner);
            display.showWinner(this.winner);
            timer.cancel();
        }

    }

    /* Elige el jugador que saca en cada Game. Empezando aleatoriamente la primera vez */
    private void whoServe() {
        if(this.servingFirst){
            int randomServe = random.nextInt(2);
            match.getSet(currentSet).getGame(currentGame).setServing(this.players.get(randomServe));
            this.lastServing = this.players.get(randomServe);
            this.servingFirst = false;
        } 
        else {
            if(this.lastServing.getId() == 0) {
                match.getSet(currentSet).getGame(currentGame).setServing(players.get(1));
                this.lastServing = this.players.get(1);
            }
            else {
                match.getSet(currentSet).getGame(currentGame).setServing(players.get(0));
                this.lastServing = this.players.get(0);
            }
        }
    }

    /* Elige quien gano el punto teniendo en cuenta las probabilidades seteadas */
    public Player whoWonPoint () {
        double rand = random.nextDouble()*100;
        int probabilityPlayer1 = match.getProbabiltyPlayer1();
        if(rand < probabilityPlayer1) {
            System.out.println("player 1 won");
            return match.getPlayers().get(0);
        }
        else {
            System.out.println("player 2 won");
            return match.getPlayers().get(1);
        }
    }

    /* Getters */

    public boolean getEnd() {
        return this.end;
    }


    
}
