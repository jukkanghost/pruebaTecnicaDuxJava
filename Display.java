import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Display {

    private Match match;
    Map<Integer,String> pointsDisplay;
    Map<Integer,String> tiebreakDisplay;

    public Display(Match match) {
        this.match = match;
        pointsDisplay = createPointsDisplay();
        tiebreakDisplay = createTiebreakDisplay();
        
    }

    /*Mapas que traducen el puntaje usado en la simulacion a convenio de tenis */

    private static Map<Integer, String> createPointsDisplay() {
        Map<Integer,String> myMap = new HashMap<Integer,String>();
        myMap.put(0, "0");
        myMap.put(15, "15");
        myMap.put(30, "30");
        myMap.put(45, "40");
        return myMap;
    }

    private static Map<Integer, String> createTiebreakDisplay() {
        Map<Integer,String> myMap = new HashMap<Integer,String>();
        myMap.put(0, "0");
        myMap.put(15, "1");
        myMap.put(30, "2");
        myMap.put(45, "3");
        myMap.put(60, "4");
        myMap.put(75, "5");
        myMap.put(90, "6");
        myMap.put(105, "7");
        return myMap;
    }

    /*Funcion que muestra el ganador del partido y pregunta por revancha */

    public void showWinner(Player winner) {
        System.out.println("----------------------------------------------------------------------------------------");
        Formatter formatter = new Formatter();
        formatter.format("%s %30s\n", "Ganador", winner.getName());
        formatter.format("%s", "Revancha(y/n):");
        System.out.println(formatter);
        System.out.println("----------------------------------------------------------------------------------------");
        formatter.close();
    }

    /*Funcion que muestra por consola las estadisticas de partido */

    public void show(List<Integer> currentGame, Player serving, List<Boolean> advantage, boolean tiebreak) {
        List<Player> players = match.getPlayers();
        List<Set> sets = match.getAllSets();
        System.out.println("----------------------------------------------------------------------------------------");
        Formatter formatter = new Formatter();
        formatter.format("%s %30s\n", "Tournament", match.getTournament());
        formatter.format("%s ", "Jugadores");
        for (Player player : players) {
            formatter.format("%30s", player.getName());
        }
        formatter.format("\n", "");
        for (Set set : sets) {
            formatter.format("%5s", set.getScore().get(0));
        }
        if(tiebreak){
            formatter.format("%5s\n", tiebreakDisplay.get(currentGame.get(0)));
        } else {
            formatter.format("%5s\n", pointsDisplay.get(currentGame.get(0)));
        }
        for (Set set : sets) {
            formatter.format("%5s", set.getScore().get(1));
        }
        if(tiebreak){
            formatter.format("%5s\n", tiebreakDisplay.get(currentGame.get(1)));
        } else {
            formatter.format("%5s\n", pointsDisplay.get(currentGame.get(1)));
        }
        formatter.format("%s %20s\n", "Serving", serving.getName());
        String advantagePlayer = "";
        if(advantage.get(0).equals(true)) {
            advantagePlayer = players.get(0).getName();
        }
        if(advantage.get(1).equals(true)) {
            advantagePlayer = players.get(1).getName();
        }
        formatter.format("%s %20s", "Advantage", advantagePlayer);
        System.out.println(formatter);
        System.out.println("----------------------------------------------------------------------------------------\n");
        advantagePlayer = "";
        formatter.close();
    }

}
