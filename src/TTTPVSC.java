public class TTTPVSC extends TTTPVDC {

    private boolean firstMove = true;

    private void copyBoardFromInstance(TTTBase prevInstance) {
        board = prevInstance.board;
    }

    @Override
    protected void handleComputerMove() {
        if (firstMove) {
            board[0][0] = currentPlayer;
            return;
        }
    }

    private int staticEval(Player currentPlayer) {
        if (checkForWin()) {
            if (currentPlayer == Player.X) {
                return 10;
            } else {
                return -10;
            }
        } return 0;
    }


}
