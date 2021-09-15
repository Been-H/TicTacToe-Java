import java.util.List;

public class TTTPVSC extends TTTPVDC {

    private boolean firstMove = true;

    private TTTPVSC copyBoardFromInstance() {
        TTTPVSC copiedGame = new TTTPVSC();
        copiedGame.board = copyBoardCurrentInstance();
        copiedGame.currentPlayer = currentPlayer;
        return copiedGame;
    }

    private Player[][] copyBoardCurrentInstance () {
        Player[][] newBoard = new Player[3][3];
        for (int row = 3; row < 3; row++) {
            for (int col = 3; col < 3; col++) {
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }

    @Override
    protected void handleComputerMove() {
        if (firstMove) {
            board[0][0] = currentPlayer;
            firstMove = false;
        } else {
            MinimaxReturn result = minimax(this, true, 8);
            TTTPVSC chosenGame = result.getBoard();
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
        if (checkForWin() || checkForTie()) {
            return new MinimaxReturn(game.staticEval(), game);
        }

        List<int[]> childPositions = findOpenPositions();

        if (isMax) {
            TTTPVSC bestGame = copyBoardFromInstance();
            int maxEval = -10000;
            for (int[] childPosition: childPositions) {
                board[childPosition[0]][childPosition[1]] = Player.X;
                TTTPVSC newGame = copyBoardFromInstance();
                MinimaxReturn returnValues = minimax(newGame, false, depth - 1);
                int eval = returnValues.getEval();
                if (eval >= maxEval) {
                    bestGame = copyBoardFromInstance();
                    maxEval = eval;
                }
                board[childPosition[0]][childPosition[1]] = Player.NONE;
            }
            return new MinimaxReturn(maxEval, bestGame);
        }

        else {
            TTTPVSC bestGame = copyBoardFromInstance();
            int minEval = -10000;
            for (int[] childPosition: childPositions) {
                board[childPosition[0]][childPosition[1]] = Player.O;
                TTTPVSC newGame = copyBoardFromInstance();
                MinimaxReturn returnValues = minimax(newGame, true, depth - 1);
                int eval = returnValues.getEval();
                if (eval <= minEval) {
                    bestGame = copyBoardFromInstance();
                    minEval = eval;
                }
                board[childPosition[0]][childPosition[1]] = Player.NONE;
            }
            return new MinimaxReturn(minEval, bestGame);
        }

    }

}
