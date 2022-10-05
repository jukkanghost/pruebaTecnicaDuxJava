import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Player playerOne;
    private Player playerTwo;
    private String tournament;
    private Simulator simulator;
    private int probabilityPlayer1;
    private int totalSets;

    public Menu(){
        this.scanner = new Scanner(System.in);
    }

    public void launchSimulator() {
        getInput();

        simulator = new Simulator(this.playerOne, this.playerTwo, this.tournament, this.totalSets, probabilityPlayer1);
        simulator.playMatch();
       
    }

    public void revenge() {
        if(scanner.hasNext("y|n")) {
            String revenge = scanner.next();
            if(revenge.equals("y")){
                simulator = new Simulator(this.playerOne, this.playerTwo, this.tournament, this.totalSets, probabilityPlayer1);
                simulator.playMatch();
            }
        }
    }

    private void getInput() {
        System.out.println("Enter player 1:");
        if(scanner.hasNext("[A-Za-z0-9]*")) {
            String player1 = scanner.nextLine();
            this.playerOne = new Player(player1, 0);
        }
        System.out.println("Enter player 2:");
        if(scanner.hasNext("[A-Za-z0-9]*")) {
            String player2 = scanner.nextLine();
            this.playerTwo = new Player(player2, 1);
        }
        System.out.println("Enter tournament name:");
        if(scanner.hasNext("[A-Za-z0-9]*")) {
            String tournament = scanner.nextLine();
            this.tournament = tournament;
        }
        System.out.println("Enter sets to play:");
        if(scanner.hasNextInt()) {
            int totalSets = scanner.nextInt();
            while(totalSets != 3 && totalSets != 5){
                System.out.println("Please Enter 3 or 5 sets to play");
                totalSets = scanner.nextInt();
            }
            this.totalSets = totalSets;
        }
        System.out.println("Enter probability player1:");
        if(scanner.hasNextInt()) {
            int probabilityPlayer1 = scanner.nextInt();
            while(!(probabilityPlayer1 >= 1 && probabilityPlayer1 <= 99)){
                System.out.println("Please Enter a probability between 1 and 100");
                probabilityPlayer1 = scanner.nextInt();
            }
            this.probabilityPlayer1 = probabilityPlayer1;
        }
    }
}
    
    
