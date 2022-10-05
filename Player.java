public class Player {

    private String name;
    private int playerId;
    
    public Player(String name, int playerId) {
        setName(name);
        setId(playerId);
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.playerId;
    }
    
}

