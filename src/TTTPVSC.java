import java.util.List;

public class TTTPVSC extends TTTPVDC {

    private boolean firstMove = true;

    private TTTPVSC copyBoardFromInstance(TTTBase prevInstance) {
        TTTPVSC copiedGame = new TTTPVSC();
        copiedGame.board = prevInstance.board;
        copiedGame.currentPlayer = prevInstance.currentPlayer;
        return copiedGame;
    }

    @Override
    protected void handleComputerMove() {
        if (firstMove) {
            board[0][0] = currentPlayer;
            firstMove = false;
        } else {
            MinimaxReturn result = minimax(this, true, 8);
            TTTPVSC chosenGame = result.getBoard();
            chosenGame.displayBoard();
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] != chosenGame.board[row][col]) {
                        board[row][col] = Player.X;
                    }
                }
            }
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
            TTTPVSC bestGame = this;
            int maxEval = -10000;
            for (int[] childPosition: childPositions) {
                board[childPosition[0]][childPosition[1]] = Player.X;
                MinimaxReturn returnValues = minimax(this, false, depth - 1);
                int eval = returnValues.getEval();
                if (eval >= maxEval) {
                    bestGame = copyBoardFromInstance(this);
                    maxEval = eval;
                }
                game.board[childPosition[0]][childPosition[1]] = Player.NONE;
            }
            return new MinimaxReturn(maxEval, bestGame);
        }

        else {
            TTTPVSC bestGame = this;
            int minEval = 10000;
            for (int[] childPosition: childPositions) {
                board[childPosition[0]][childPosition[1]] = Player.O;
                MinimaxReturn returnValues = minimax(this, false, depth - 1);
                int eval = returnValues.getEval();
                if (eval <= minEval) {
                    bestGame = copyBoardFromInstance(this);
                    minEval = eval;
                }
                game.board[childPosition[0]][childPosition[1]] = Player.NONE;
            }
            return new MinimaxReturn(minEval, bestGame);
        }

    }

}
