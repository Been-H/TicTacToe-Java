public class MinimaxReturn {

    private int bestEval;
    private TTTPVSC board;

    public MinimaxReturn(int bestEval, TTTPVSC board){
        this.bestEval = bestEval;
        this.board = board;
    }

    public int getBestEval() {
        return bestEval;
    }

    public TTTBase getBoard() {
        return board;
    }
}
