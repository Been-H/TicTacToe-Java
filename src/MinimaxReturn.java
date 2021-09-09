public class MinimaxReturn {

    private int eval;
    private TTTPVSC board;

    public MinimaxReturn(int eval, TTTPVSC board){
        this.eval = eval;
        this.board = board;
    }

    public int getEval() {
        return eval;
    }

    public TTTPVSC getBoard() {
        return board;
    }
}
