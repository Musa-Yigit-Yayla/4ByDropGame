package MyTetrisGamePack;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Initialization process
        final int NUMBER_OF_COLUMNS = 7;
        final int NUMBER_OF_ROWS = 6;
        final int NUMBER_OF_TIMES = 4; // denotes number of times that the balls have to be lined in order to win the game
        System.out.println("Enter player1's name and a char representing the color respectively: ");
        Scanner input = new Scanner(System.in);
        Player player1 = new Player(input.next(),input.next().charAt(0));
        System.out.println("Enter player2's name and a char representing the color respectively: ");
        Player player2 = new Player(input.next(),input.next().charAt(0));

        int[][] numericMatrix = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char[][] charMatrix = new char[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
            
        boolean gameCondition = true; //Should the game continue ?
        boolean isDraw = false;
        int count = 0; //Counts the number of iterations on game loop, will be useful to determine the players' turn
        matrixPrinter(charMatrix); // print the matrix once before the game starts
        //CORE GAME LOOP
        while(gameCondition){
            
            //RECEIVING INPUT AND UPDATING THE MATRIXES
            if(count % 2 == 0){//player1's turn
            System.out.println("Player1's turn, decide a column from (0," + (NUMBER_OF_COLUMNS - 1) + ")");
            player1.setChoice(input.nextInt());
            boolean isValidInput = update(numericMatrix, player1, charMatrix); //update the game
                if(!isValidInput){
                    System.out.println("Invalid input, column " + player1.getChoice() + " is already filled.");
                    count -= 2; // burda sorun olabilir
                }
                else{
                    count++;
                }
            }
            //player2's turn
            else{
                System.out.println("Playe2's turn, decide a column from (0," + (NUMBER_OF_COLUMNS - 1) + ")");
            player2.setChoice(input.nextInt());
            boolean isValidInput = update(numericMatrix, player2, charMatrix); //update the game
                if(!isValidInput){
                    System.out.println("Invalid input, column " + player2.getChoice() + " is already filled.");
                    count -= 2; // burda sorun olabilir
                }
                else{
                    count++;
                }
            }

            //PRINTMATRIX
            matrixPrinter(charMatrix);

            //Check whether the game is over

            //Check for player1
            //Check rows
            for(int i = 0; i < NUMBER_OF_ROWS; i++){
                if(Checker.isConsecutiveRow(numericMatrix[i], NUMBER_OF_TIMES, player1.getNumericColor())){
                    player1.setPlayerWon(true);
                    gameCondition = false; //game is over, break out of the loop
                }
            }
            //Check columns
            for(int i = 0; i < NUMBER_OF_COLUMNS; i++){
                if(Checker.isConsecutiveColumn(numericMatrix, NUMBER_OF_TIMES, i, player1.getNumericColor())){
                    player1.setPlayerWon(true);
                    gameCondition = false;
                }
            }
            //Check diagonally
            if(Checker.checkDiagonally(numericMatrix, NUMBER_OF_TIMES, player1.getNumericColor())){
                player1.setPlayerWon(true);
                gameCondition = false;
            }

            //Check for player2
            //Check rows
            for(int i = 0; i < NUMBER_OF_ROWS; i++){
                if(Checker.isConsecutiveRow(numericMatrix[i], NUMBER_OF_TIMES, player2.getNumericColor())){
                    player2.setPlayerWon(true);
                    gameCondition = false; //game is over, break out of the loop
                }
            }
            //Check columns
            for(int i = 0; i < NUMBER_OF_COLUMNS; i++){
                if(Checker.isConsecutiveColumn(numericMatrix, NUMBER_OF_TIMES, i, player2.getNumericColor())){
                    player2.setPlayerWon(true);
                    gameCondition = false;
                }
            }
            //Check diagonally
            if(Checker.checkDiagonally(numericMatrix, NUMBER_OF_TIMES, player2.getNumericColor())){
                player2.setPlayerWon(true);
                gameCondition = false;
            }
            //If the top row is filled and no one won, game is a draw
            if(isTopRowFilled(numericMatrix[0]) && (!player1.didPlayetWon() && !player2.didPlayetWon())){
                isDraw = true;
            }
        }
        //Print out who won
        if(player1.didPlayetWon())
            System.out.println("Player1 won!");
        else if(isDraw){
            System.out.println("Game is a draw!");
        }
        else{
            System.out.println("Player2 won!");
        }
    }
    /**
     * Updates the game matrix
     * @param result
     * @param player
     * @returns false when the column player picked is filled, will be useful to validate input
     */
    public static boolean update(int[][] result, Player player,char[][] charMatrix){
        int rowIndex = getRowIndex(result, player.getChoice());
        if(rowIndex == -1){
            return false;
        }
        result[rowIndex][player.getChoice()] = player.getNumericColor();
        //matrixPrinter(result); //numeric matrix printle
        charMatrix[rowIndex][player.getChoice()] = player.getCharColor(); // updates the char matrix (could be problem here)
        return true;
    }
    /**
     * @param columnIndex: player's choice
     * @return -1 if the column is filled, otherwise returns the rowIndex
     */
    public static int getRowIndex(int[][] result, int columnIndex){
        if(result[0][columnIndex] != 0){
            return -1;
        }
        int lastIndexOfZero = 0;
        for(int i = 0; i < result.length; i++){
            if(result[i][columnIndex] == 0){
                lastIndexOfZero = i;
            }
        }
        return lastIndexOfZero;
    }
    //Prints the matrix
    public static void matrixPrinter(char[][] charMatrix){
        int difference = 1;
        for(int i = 0; i < charMatrix.length; i++){
            for(int j = 0; j < charMatrix[i].length * 2 + 1; j++){
                if(j % 2 == 0){
                    System.out.print("|");
                }
                else{
                    if(charMatrix[i][j - difference] == '\u0000'){
                        System.out.print(" ");
                    }
                    else{
                        System.out.print(charMatrix[i][j - difference]);
                    }
                    difference++;
                }
                
            }
            difference = 1; // problem olabilir ilk hali 0 dı
            System.out.println();
        }
        for(int i = 0; i < charMatrix[0].length * 2 + 1; i++){
            System.out.print("*");
        }
        System.out.println();
    }
    public static void matrixPrinter(int[][] numericMatrix){
        int difference = 1;
        for(int i = 0; i < numericMatrix.length; i++){
            for(int j = 0; j < numericMatrix[i].length * 2 + 1; j++){
                if(j % 2 == 0){
                    System.out.print("|");
                }
                else{
                        System.out.print(numericMatrix[i][j - difference]);
                    
                    difference++;
                }
                
            }
            difference = 1; // problem olabilir ilk hali 0 dı
            System.out.println();
        }
    }
    public static boolean isTopRowFilled(int[] topRow){
        for(int i = 0; i < topRow.length; i++){
            if(topRow[i] == 0){
                return false;
            }
        }
        return true;
    }
}
