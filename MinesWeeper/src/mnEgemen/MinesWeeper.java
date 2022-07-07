/*
*
*
@mineField_game_with_Java
*
*
 */
package mnEgemen; // mnEgemen -> Mohammad Nazir Sharifi (Egemen)
import java.util.Random;
import java.util.Scanner;
public class MinesWeeper {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public int rowNumber, colNumber, size, success = 0;
    public int[][] map;
    public int[][] board;
    public boolean game = true;

    public MinesWeeper(int rowNumber, int colNumber){
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new int[rowNumber][colNumber];
        this.board = new int[rowNumber][colNumber];
        this.size = rowNumber*colNumber;
    }

    public void run(){
        int row, column;
        prepareGame();
        /*
        printMap(map);
         */
        System.out.println("Game Loading...");
        while (game){
            printMap(board);
            System.out.print("Row: ");
            row = scanner.nextInt();
            System.out.print("Column: ");
            column = scanner.nextInt();
            if(row < 0 || row >= rowNumber){
                System.out.println("Invalid Row Coordinate!");
                continue;
            }
            if(column < 0 || column >= colNumber){
                System.out.println("Invalid Column Coordinate!");
                continue;
            }

            if(map[row][column] != -1){
                check(row, column);
                success++;
                if(success == (size - (size/4))){
                    System.out.println("You Successfully Finished The Game!");
                    break;
                }
            }else{
                game = false;
                System.out.println("You detonated a mine!");
                System.out.println("Game Over!");

            }
        }

    }

    public void check(int row, int column){
        if(map[row][column] == 0){
            if((row > 0) && (map[row-1][column] == -1)){
                board[row][column]++;
            }
            if(( column < colNumber-1) && (map[row][column+1] == -1)){
                board[row][column]++;
            }
            if(( row < rowNumber-1) && (map[row+1][column] == -1)){
                board[row][column]++;
            }
            if((column > 0) && (map[row][column-1] == -1)){
                board[row][column]++;
            }
            if(board[row][column] == 0){
                board[row][column] = -2;
            }
        }
    }

    public void prepareGame(){
        int randRow, randColumn, count = 0;
        while (count != (size/4)){
            randRow = random.nextInt(rowNumber);
            randColumn = random.nextInt(colNumber);
            if(map[randRow][randColumn] != -1){
                map[randRow][randColumn] = -1;
                count++;
            }
        }
    }

    public void printMap(int[][] arr){
        for(int i = 0; i< arr.length; i++){
            for(int j = 0; j<arr[0].length; j++){
                if(arr[i][j] >= 0){
                    System.out.print(" ");
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
