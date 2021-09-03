import java.util.Scanner;
import java.util.Arrays;

public class TTTBase {

    protected Player[][] board = new Player[3][3];
    protected Player currentPlayer;
    protected int[][] diagonal1 = {{0, 0}, {1, 1}, {2, 2}};
    protected int[][] diagonal2 = {{0, 2}, {1, 1}, {2, 0}};

    Scanner input = new Scanner(System.in);

    protected void setup() {
        createBoard();
        currentPlayer = Player.X;
    }

    private void createBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = Player.NONE;
            }
        }
    }

    protected void displayBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(board[row][column].asChar);
                if (column != 2) {
                    System.out.print(" | ");
                }
            }
            if (row != 2) {
                System.out.println("\n-   -   -");
            }
        }
        System.out.println();
    }

    protected void handleMove(Player player) {
        int[] position = getMove(player);
        System.out.println(Arrays.toString(position));
        board[position[0]][position[1]] = currentPlayer;
    }

    private int[] getMove(Player player) {
        while (true) {
            System.out.println("Player " + player + ", " + "Where would you like to place your marker? ");
            int position = input.nextInt() - 1;
            int[] convertedPosition = convertPosition(position);
            if (isValid(convertedPosition)) {
                return convertedPosition;
            }
            System.out.println("Invalid input");
        }
    }

    private int[] convertPosition(int position) {
        int column = position % 3;
        int row;

        if (position < 3) {
            row = 0;
        } else if (position < 6) {
            row = 1;
        } else {
            row = 2;
        }

        int[] convertedPosition = {row, column};
        return convertedPosition;
    }

    private boolean isValid (int[] position) {
        if (board[position[0]][position[1]].asChar != '*') {
            return false;
        } return true;
    }

    protected boolean checkForWin() {
        if (checkRowColWin() || checkDiagonalWin()) {
            return true;
        }
        return false;
    }

    private boolean checkRowColWin() {
        Player rowPlayerCheck;
        Player columnPlayerCheck;
        boolean rowWin;
        boolean columnWin;

        for (int row = 0; row < 3; row ++) {
            rowWin = true;
            rowPlayerCheck = board[row][0];
            columnWin = true;
            columnPlayerCheck = board[0][row];

            for (int column = 0; column < 3; column ++) {
                if (board[row][column] != rowPlayerCheck || board[row][column] == Player.NONE) {
                    rowWin = false;
                }
                if (board[column][row] != columnPlayerCheck || board[column][row] == Player.NONE) {
                    columnWin = false;
                }
            }

            if (rowWin || columnWin) {

                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        if (checkDiagonal(diagonal1) || checkDiagonal(diagonal2)) {
            return true;
        } return false;
    }

    private boolean checkDiagonal(int[][] diagonal) {
        boolean diagonalWin = true;
        int[] startPosition = diagonal[0];
        Player diagonalPlayerCheck = board[startPosition[0]][startPosition[1]];

        for (int[] position : diagonal) {
            Player positionToCheck = board[position[0]][position[1]];
            if (positionToCheck != diagonalPlayerCheck || positionToCheck == Player.NONE) {
                diagonalWin = false;
            }
        }

        return diagonalWin;
    }

    protected boolean checkForTie() {
        boolean isTie = true;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == Player.NONE) {
                    isTie = false;
                }
            }
        }
        return isTie;
    }

    public void game() {
        setup();
        displayBoard();
    }
}
