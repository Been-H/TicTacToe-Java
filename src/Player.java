public enum Player {
    X('X'), O('O'), NONE('*');

    char asChar;

    Player(char asChar) {
        this.asChar = asChar;
    }

    public Player reversePlayer() {
        switch (this) {
            case X:
                return O;
            case O:
                return X;
        }
        return X; //why do I need this
    }
}
