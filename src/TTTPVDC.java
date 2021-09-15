import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TTTPVDC extends TTTBase {

    private Random rand = new Random();

    protected List<int[]> findOpenPositions() {
        List<int[]> possiblePositions = new ArrayList<int[]>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int[] position = {row, col};
                if (board[row][col] == Player.NONE) {
                    possiblePositions.add(position);
                }
            }
        }

        return possiblePositions;
    }

    protected void handleComputerMove() {
        List<int[]> possiblePositions = findOpenPositions();

        if (possiblePositions.size() == 0) {
            return;
        }

        int positionIndex = rand.nextInt((possiblePositions.size() - 1) + 0) + 0;
        System.out.println(positionIndex);
        int[] chosenPosition = possiblePositions.get(positionIndex);
        board[chosenPosition[0]][chosenPosition[1]] = currentPlayer;
    }

    @Override
    public void game() {
        super.game();
        while (true) {



            handleComputerMove();
            displayBoard();

            if (checkForWin()) {
                System.out.println("Player " + currentPlayer.asChar + " Wins!");
                return;
            }
            if (checkForTie()) {
                System.out.println("It's a tie!");
                return;
            }
            currentPlayer = currentPlayer.reversePlayer();

            handleMove(currentPlayer);

            if (checkForWin()) {
                displayBoard();
                System.out.println("Player " + currentPlayer.asChar + " Wins!");
                return;
            }
            if (checkForTie()) {
                System.out.println("It's a tie!");
                displayBoard();
                return;
            }
            currentPlayer = currentPlayer.reversePlayer();

        }
    }

}
