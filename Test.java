package MyTetrisGamePack;

public class Test {
    public static void main(String[] args) {
        int[][] array = {{0,0,0,0,0,0},
                        {0,0,0,0,0,0},
                        {1,1,0,0,0,0},
                        {1,2,0,0,0,0,0},
                        {1,2,0,0,0,0,0},
                        {1,2,2,1,2,0,0}};
        /*if(Checker.isConsecutiveColumn(array, 4, 0, 2)){
            System.out.println("Check column is invoked on element 2, returned true");
        }
        if(Checker.isConsecutiveRow(array[2], 4, 1)){
            System.out.println("Check row is invoked on element 1, returned true");
        }
        if(Checker.checkDiagonally(array, 4, 1)){
            System.out.println("Check diagonally is invoked on element 1, returned " + Checker.checkDiagonally(array, 4, 1));
        }*/
        int columnIndex = 0;
        int count = 0;
        int csCount = 4;
        int element =  1;
        for(int i = 0; i < array.length - 1; i++){
            if(array[i][columnIndex] == array[i + 1][columnIndex] && array[i][columnIndex] == element ){
                count++;
            }
            if(count == csCount - 1){
                System.out.println("match");
            }
            else{
                count = 0;
            }
        }

        System.out.println("No match");
    }    
}
