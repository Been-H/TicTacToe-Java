import java.util.List;

public class TTTPVSC extends TTTPVDC implements Cloneable {

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private boolean firstMove = true;

    private void copyBoardFromInstance(TTTBase prevInstance) {
        board = prevInstance.board;
    }

    @Override
    protected void handleComputerMove() {
        if (firstMove) {
            board[0][0] = currentPlayer;
            return;
        } else {
            MinimaxReturn result = minimax(this, true, 8);
        }
    }

    private int staticEval() {
        if (checkForWin()) {
            if (currentPlayer == Player.X) {
                return 10;
            } else {
                return -10;
            }
        } return 0;
    }


    private MinimaxReturn minimax(TTTPVSC game, boolean isMax, int depth) {
        if (depth == 0 || checkForWin() || checkForTie()) {
            return new MinimaxReturn(game.staticEval(), game);
        }

        List<int[]> childPositions = findOpenPositions();

        if (isMax) {
            TTTPVSC bestGame;
            int maxEval = -10000;
            for (int[] childPosition: childPositions) {
                board[childPosition[0]][childPosition[1]] = Player.X;
                MinimaxReturn returnValues = minimax(this, false, depth - 1);
                int eval = returnValues.getEval();
                if (eval >= maxEval) {
                    maxEval = eval;
                }

            }
        } else {
            TTTPVSC bestGame;
            int minEval = 10000;
        }


        return new MinimaxReturn(10, this);
    }

}
