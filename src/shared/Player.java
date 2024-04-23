package shared;

public class Player {
    private int playerID;
    private String username;
    private String fullName;
    private String password;
    private byte[] pfp;
    private int points;

    public Player(){}
    public Player(int playerID, String username, String fullName, String password, byte[] pfp, int points) {
        this.playerID = playerID;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.pfp = pfp;
        this.points = points;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPfp() {
        return pfp;
    }

    public void setPfp(byte[] pfp) {
        this.pfp = pfp;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object obj) {
        Player another = (Player) obj;
        return this.username.equals(another.getUsername());
    }

    /**
     * The pfp blob and password will not be printed for security purposes
     * @return
     */
    @Override
    public String toString() {
        return playerID +", "+ username + ", " + fullName + ", " + points;
    }
}
