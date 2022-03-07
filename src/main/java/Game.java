import java.util.Random;

public class Game {

    private String[][] gameBoard = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };

    private int[][] winnerFields;
    private int counter = 0;
    private int rollRow;
    private int rollCol;
    private boolean draw;
    public final static String X_SYMBOL = "X";
    public final static String O_SYMBOL = "O";
    private String whoWon = "";
    private final static int MAX_NUMBER_OF_TURNS = 9;
    private int playerOneWins = 0;
    private int playerTwoWins = 0;
    private int machineWins = 0;
    private boolean isPlayerOneTurn = true;
    private boolean gameOver = false;
    private GameState state = GameState.PVE;
    private final Random rnd = new Random();

    public void gameSetup(GameState gamestate){
        counter = 0;
        state = gamestate;
        gameOver = false;
        isPlayerOneTurn = true;
        draw = false;
    }

    public void validMove(int row, int col) {
        if (gameBoard[row][col].equals("")) {
            if (state == GameState.PVP) {
                counter++;
                isDraw();
                if (isPlayerOneTurn) {
                    gameBoard[row][col] = X_SYMBOL;
                    isPlayerOneTurn = false;
                    Board.setGameTextField(O_SYMBOL + " turn");
                    check(X_SYMBOL);
                } else {
                    gameBoard[row][col] = O_SYMBOL;
                    isPlayerOneTurn = true;
                    Board.setGameTextField(X_SYMBOL + " turn");
                    check(O_SYMBOL);
                }
                if (draw) {
                    draw();
                }
            } else if (state == GameState.PVE) {
                if (isPlayerOneTurn) {
                    gameBoard[row][col] = X_SYMBOL;
                    isPlayerOneTurn = false;
                    counter++;
                    isDraw();
                    Board.setGameTextField(X_SYMBOL + " turn");
                    check(X_SYMBOL);
                    if (draw) {
                        draw();
                    } else if(!gameOver && counter != MAX_NUMBER_OF_TURNS){
                        machineMove();
                    }
                }
            }
        }
    }

    public void machineMove(){
        rollRow = rnd.nextInt(gameBoard.length);
        rollCol = rnd.nextInt(gameBoard.length);
        while (gameBoard[rollRow][rollCol].equals(X_SYMBOL) || gameBoard[rollRow][rollCol].equals(O_SYMBOL)){
            rollRow = rnd.nextInt(gameBoard.length);
            rollCol = rnd.nextInt(gameBoard.length);
        }
        if (!isPlayerOneTurn) {
            if (gameBoard[rollRow][rollCol].equals("")) {
                gameBoard[rollRow][rollCol] = O_SYMBOL;
                isPlayerOneTurn = true;
                check(O_SYMBOL);
                counter++;
                isDraw();
                if (draw) {
                    draw();
                }
            }
        }
    }

    public void check(String figure){
        for(int i = 0; i < 3; i++){
            checkWinCombination(0,i,1, i, 2, i, figure);
            checkWinCombination(i,0,i, 1, i, 2, figure);
        }
        checkWinCombination(0,0,1, 1, 2, 2, figure);
        checkWinCombination(0,2,1, 1, 2, 0, figure);
    }

    public void checkWinCombination(int x1, int y1, int x2, int y2, int x3, int y3, String figure) {
        if (gameBoard[x1][y1].equals(figure) &&
            gameBoard[x2][y2].equals(figure) &&
            gameBoard[x3][y3].equals(figure)){
            whoWins(x1, y1, x2, y2, x3, y3, figure);
        }
    }

    public void whoWins(int rowA, int colA, int rowB, int colB, int rowC, int colC, String winner){
        if(winner.equals(X_SYMBOL)){
            playerOneWins++;
            whoWon = X_SYMBOL;
        }
        if(state == GameState.PVP && winner.equals(O_SYMBOL)){
            playerTwoWins++;
            whoWon = O_SYMBOL;
        }
        if(state == GameState.PVE && winner.equals(O_SYMBOL)){
            machineWins++;
            whoWon = "Machine";
        }
        gameOver = true;
        draw = false;
        winnerFields = new int[][]{
                {rowA, colA},
                {rowB, colB},
                {rowC, colC}
        };
        DataSaver dataSaver = new DataSaver(this);
        dataSaver.saveRanking();
    }

    public void draw(){
        gameOver = true;
        Board.disableGameButtons(draw);
        Board.setGameTextField("Draw");
    }

    public void reset(){
        gameBoard = new String[][] {
                {"", "", ""},
                {"", "", ""},
                {"", "", ""}
        };
        gameOver = false;
        draw = false;
    }

    public  int[][] getWinnerFields() {
        return winnerFields;
    }

    public  String[][] getGameBoard() {
        return gameBoard;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void isDraw(){
        if(counter == MAX_NUMBER_OF_TURNS){
            draw = true;
        }
    }

    public boolean getDraw(){
        return draw;
    }

    public GameState getState() {
        return state;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public  int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public  int getMachineWins() {
        return machineWins;
    }

    public String getWhoWon() {
        return whoWon;
    }

    public  boolean isIsPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public  void setPlayerOneWins(int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public  void setPlayerTwoWins(int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    public  void setMachineWins(int machineWins) {
        this.machineWins = machineWins;
    }

    public  void setGameBoard(String gameBoard, int row, int col) {
        this.gameBoard[row][col] = gameBoard;
    }

    public  void setCounter(int counter) {
        this.counter = counter;
    }

    public  void setIsPlayerOneTurn(boolean isPlayerOneTurn) {
        this.isPlayerOneTurn = isPlayerOneTurn;
    }

    public  void setState(GameState state) {
        this.state = state;
    }
}

