package MyTetrisGamePack;
import java.util.Scanner;
public class TicTacToe {
    public static void main(String[] args) {
        //INITIALIZATION PROCESS
        final int NUMBER_OF_COLUMNS = 3;
        final int NUMBER_OF_ROWS = 3;
        final int NUMBER_OF_TIMES = 3;

        int[][] numericMatrix = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char[][] charMatrix = new char[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

        Player player1 = new Player("Player1",'X');
        Player player2 = new Player("Player2",'O');
        
        
        boolean gameCondition = true;
        boolean isDraw = false;

        //Core Game Loop
        int count = 0;
        //Print the charMatrix before the game starts
        Main.matrixPrinter(charMatrix);
        while(gameCondition){
            if(count % 2 == 0){
                receiveInput(player1,numericMatrix,charMatrix);

                //Check whether player1 won
                //Check column
                for(int i = 0; i < numericMatrix[0].length; i++){
                    if(Checker.isConsecutiveColumn(numericMatrix, NUMBER_OF_TIMES, i, player1.getNumericColor())){
                        player1.setPlayerWon(true);
                        gameCondition = false;
                    }
                }
                //Check row
                for(int i = 0; i < numericMatrix.length; i++){
                    if(Checker.isConsecutiveRow(numericMatrix[i], NUMBER_OF_TIMES, player1.getNumericColor())){
                        player1.setPlayerWon(true);
                        gameCondition = false;
                    }
                }
                //Check diagonally
                if((checkDiagonally(player1,numericMatrix))){
                    player1.setPlayerWon(true);
                    gameCondition = false;
                }
            }
            else{
                receiveInput(player2,numericMatrix,charMatrix);

                //Check whether player2 won
                //Check column
                for(int i = 0; i < numericMatrix[0].length; i++){
                    if(Checker.isConsecutiveColumn(numericMatrix, NUMBER_OF_TIMES, i, player2.getNumericColor())){
                        player2.setPlayerWon(true);
                        gameCondition = false;
                    }
                }
                //Check row
                for(int i = 0; i < numericMatrix.length; i++){
                    if(Checker.isConsecutiveRow(numericMatrix[i], NUMBER_OF_TIMES, player2.getNumericColor())){
                        player2.setPlayerWon(true);
                        gameCondition = false;
                    }
                }
                //Check diagonally
                if(checkDiagonally(player2,numericMatrix)){
                    player2.setPlayerWon(true);
                    gameCondition = false;
                }
            }
            //Check whether the game is a draw
            if(isMatrixFilled(numericMatrix) && (!player1.didPlayetWon() && !player2.didPlayetWon())){
                isDraw = true;
                gameCondition = false;  
            }
            if(isDraw){
                System.out.println("Game is a draw!");
            }
            else if(player1.didPlayetWon()){
                System.out.println(player1.getName() + " won!");
            }
            else if(player2.didPlayetWon()){
                System.out.println(player2.getName() + " won!");
            }
            
            count++;
        }
    }
    public static void receiveInput(Player player, int[][] numericMatrix,char[][] charMatrix){ //numericMatrix
        Scanner input = new Scanner(System.in);
        do{
            System.out.println(player.getName() + " decide where to put your element, starting from (0,0): ");
            int x = input.nextInt();
            int y = input.nextInt();
            if(numericMatrix[x][y] == 0){
                numericMatrix[x][y] = player.getNumericColor();
                charMatrix[x][y] = player.getCharColor();
                break;
            }
            System.out.println("(" + x + "," + y + ") is already filled, try again." );
        }while(true);
        //Print matrix
        Main.matrixPrinter(charMatrix);
    }
    public static boolean isMatrixFilled(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkDiagonally(Player player, int[][] numericMatrix){
        //Major diagonal
        boolean majorMatch = true;
        for(int i = 0; i < numericMatrix.length; i++){
           if(numericMatrix[i][i] != player.getNumericColor()){
               majorMatch = false;
           }
        }
        if(majorMatch){
            return true;
        }
        //Sub diagonal
        for(int i = 0; i < numericMatrix.length; i++){
            if(numericMatrix[i][numericMatrix.length -1 - i] != player.getNumericColor()){
                return false;
            }
        }
        return true;
    }
}
