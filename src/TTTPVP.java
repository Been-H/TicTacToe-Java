public class TTTPVP extends TTTBase {

    @Override
    public void game() {
        super.game();
        while (true) {

            handleMove(currentPlayer);
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

        }
    }

}
