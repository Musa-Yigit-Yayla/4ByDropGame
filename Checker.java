package MyTetrisGamePack;

public class Checker {

    public static void main(String[] args) {
        int[][] array = {{0,0,0,0,0,0},
                        {1,1,0,0,0,0},
                        {1,1,0,0,0,0},
                        {1,2,1,0,0,0,0},
                        {1,2,0,1,0,0,0},
                        {1,2,2,1,2,0,0}};
        if(Checker.isConsecutiveColumn(array, 4, 0, 1)){
            System.out.println("Check column is invoked on element 1, returned true");
        }
        if(Checker.isConsecutiveRow(array[2], 4, 0)){
            System.out.println("Check row is invoked on element 0, returned true");
        }
        if(Checker.checkDiagonally(array, 4, 1)){
            System.out.println("Check diagonally is invoked on element 1, returned " + Checker.checkDiagonally(array, 4, 1));
        }
        System.out.println("Finish");
    }    
        /**
     * 
     * @param value
     * @param csCount denotes consecutive count to be checked
     * @return
     *
    public static void printConsecutive(int[][] value,int csCount){
        //print rows
        System.out.printf("********************************\nCons. Rows%15s%20s","Cons.Columns","Cons Diagonally?\n");
        System.out.println("********************************");
        String[] rowString = new String[value.length];
        String[] columnString = new String[value[0].length];
        for(int i = 0; i < value.length;i++){
            if(isConsecutiveRow(value[i], csCount)){
                rowString[i] = "Row" + (i + 1);
            }
        }
        for(int i = 0; i < value[0].length; i++){
            if(isConsecutiveColumn(value, csCount, i)){
                columnString[i] = "Column" + (i + 1);
            }
        }
        String diagonalString;
        if(checkDiagonally(value, csCount)){
            diagonalString = "Yes";
        }
        else{
            diagonalString = "No";
        }
        int bigger = Math.max(rowString.length,columnString.length);
        for(int i = 0; i < bigger; i++){
            //print row
            if(i < rowString.length){
                if(rowString[i] != null)
                System.out.printf("%6s",rowString[i]);
                else
                System.out.print("     ");
            }
            //print column
            if(i < columnString.length){
                if(columnString[i] != null){
                    System.out.printf("%18s",columnString[i]);
                }
                else{
                    System.out.print("          ");
                }
            }
            if(i == 0){
                System.out.printf("%10s",diagonalString);
            }
            System.out.println();
        }*/

    public static boolean isConsecutiveRow(int[] array, int csCount, int element){ //element denotes what to be checked
        int count = 0;
        for(int i = 0; i <= array.length - 1;i++){ // < ü <= yaptım
            if(count == csCount - 1){
                return true;
            }
            if(i == array.length - 1){//SORUN OLABİLİR
                break;
            }
            if(array[i] == array[i + 1] && array[i] ==  element){
                count++;
            }
            else{
                count = 0;
            }
        }
        return false;
    }
    public static boolean isConsecutiveColumn(int[][] array, int csCount,int columnIndex, int element){
        int count = 0;
        for(int i = 0; i < array.length - 1; i++){
            {
                if(array[i][columnIndex] == array[i + 1][columnIndex] && array[i][columnIndex] == element ){
                count++;
                }
                else{
                    count = 0;
                }
            }
            if(count == csCount - 1){
                return true;
            }
        }

        return false;
        
    }
    public static boolean checkDiagonally(int[][] array, int csCount, int element){
        int rowLength = array.length;
        int columnLength = array[0].length;
            //check major diagonal
            //check from column and diagonal
            for(int columnIndex = 0; columnIndex < csCount; columnIndex++){ //<= olabilir
                int initialColumnIndex = columnIndex;
                int count = 0;
                for(int rowIndex = 0; (rowIndex < rowLength - 1) && (columnIndex < columnLength - 1); rowIndex++, columnIndex++){ //-1 LERİ KALDIRMAN GEREKEBİLİR
                    if(count == csCount - 1){
                        System.out.println("There is a major diagonal match starting from column index " + columnIndex); //WILL BE USED WHEN GUI IS IMPLEMENTED
                        return true;
                    }
                    if(array[rowIndex][columnIndex] == array[rowIndex + 1][columnIndex + 1] && array[rowIndex][columnIndex] == element){
                        count++;
                    }
                    else{
                        count = 0;
                    }
                }
                columnIndex = initialColumnIndex;
            }
            //check from row
            for(int rowIndex = 1; rowLength - rowIndex >= csCount; rowIndex++){
                int initialRowIndex = rowIndex;
                int count = 0;
                for(int columnIndex = 0;(rowIndex < rowLength - 1) && (columnIndex < columnLength - 1); rowIndex++, columnIndex++){
                    if(count == csCount - 1){
                        System.out.println("There is a major diagonal match starting from row index " + rowIndex);
                        return true;
                    }
                    if(array[rowIndex][columnIndex] == array[rowIndex + 1][columnIndex + 1] && array[rowIndex][columnIndex] == element){
                        count++;
                    }
                    else{
                        count = 0;
                    }

                }
            }
            //check sub diagonal
            for(int rowIndex = rowLength - 1;rowLength - rowIndex <= csCount; rowIndex--){
                int initialRowIndex = rowIndex;
                int count = 0;
                for(int columnIndex = 0; (rowIndex >= 1) && (columnIndex) < columnLength; columnIndex++,rowIndex-- ){
                    if(count == csCount - 1){
                        System.out.println("There is a sub diagonal match starting from row index " + rowIndex);
                        return true;
                    }
                    if(array[rowIndex][columnIndex] == array[rowIndex - 1][columnIndex + 1] && array[rowIndex][columnIndex] == element){
                        count++;
                    }
                    else{
                        count = 0;
                    }
                }
                rowIndex = initialRowIndex;
            }
            for(int columnIndex = 1; columnLength - columnIndex >= csCount; columnIndex++){//SORU NBURADA OLABILIR
                int initialColumnIndex = columnIndex;
                int count = 0;
                for(int rowIndex = rowLength - 1;(rowIndex > 0) && (columnIndex) < columnLength - 1; rowIndex--,columnIndex++){ //(rowIndex >= 0 dı), (columnIndex) < columnLength conditionu böyleydi
                    if(count == csCount - 1){
                        System.out.println("There is a sub diagonal match starting from column index " + columnIndex);
                        return true;
                    }
                    if(array[rowIndex][columnIndex] == array[rowIndex - 1][columnIndex + 1] && array[rowIndex][columnIndex] == element){
                        count++;
                    }
                    else{
                        count = 0;
                    }
                }
                columnIndex = initialColumnIndex;
            }
        return false;
    }
}
