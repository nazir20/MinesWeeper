package mnEgemen;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int row, column;
        System.out.println("Welcome to MinesWeeper Game!");
        System.out.println("Please enter the coordinates you want to play!");
        System.out.print("Row: ");
        row = scanner.nextInt();
        System.out.print("Column: ");
        column = scanner.nextInt();
        MinesWeeper minesWeeper = new MinesWeeper(row, column);
        minesWeeper.run();

    }
}
