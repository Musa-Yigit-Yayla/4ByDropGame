package MyTetrisGamePack;

public class Player {
    private static int playerCount;
    private String name;
    private char color; // denotes the color of this player's ball
    private boolean didWin; // denotes whether the player won or not
    private int numericColor; // 1 if this is the player1 2 if player2
    private int choice; // column index, namely the choice determining ball to be thrown
    public Player(String name, char color){
        this.name = name;
        this.color = color;
        this.numericColor = ++playerCount;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public char getCharColor(){
        return this.color;
    }
    public void setCharColor(char color){
        this.color = color;
    }
    public boolean didPlayetWon(){
        return didWin;
    }
    public void setPlayerWon(boolean didWin){
        this.didWin = didWin;
    }
    public int getNumericColor(){
        return numericColor;
    }

    //This is how the user controls where to throw the ball
    public void setChoice(int choice){//choice denotes column index
        this.choice = choice;
    }
    public int getChoice(){
        return this.choice;
    }
}
