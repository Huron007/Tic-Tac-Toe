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
    public final String xSymbol = "X";
    public final String oSymbol = "O";
    private String whoWon = "";
    private final int maxNumberOfTurns = 9;
    private int playerOneWins = 0;
    private int playerTwoWins = 0;
    private int machineWins = 0;
    private boolean isPlayerOneTurn = true;
    private boolean gameOver = false;
    private GameState state = GameState.PVE;
    private final Random rnd = new Random();
    private final DataSaver dataSaver = new DataSaver(this);

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
                    gameBoard[row][col] = xSymbol;
                    isPlayerOneTurn = false;
                    Board.setGameTextField(oSymbol + " turn");
                    check(xSymbol);
                } else {
                    gameBoard[row][col] = oSymbol;
                    isPlayerOneTurn = true;
                    Board.setGameTextField(xSymbol + " turn");
                    check(oSymbol);
                }
                if (draw) {
                    draw();
                }
            } else if (state == GameState.PVE) {
                if (isPlayerOneTurn) {
                    gameBoard[row][col] = xSymbol;
                    isPlayerOneTurn = false;
                    counter++;
                    isDraw();
                    Board.setGameTextField(xSymbol + " turn");
                    check(xSymbol);
                    if (draw) {
                        draw();
                    } else if(!gameOver && counter != maxNumberOfTurns){
                        machineMove();
                    }
                }
            }
        }
    }

    public void machineMove(){
        rollRow = rnd.nextInt(gameBoard.length);
        rollCol = rnd.nextInt(gameBoard.length);
        while (gameBoard[rollRow][rollCol].equals(xSymbol) || gameBoard[rollRow][rollCol].equals(oSymbol)){
            rollRow = rnd.nextInt(gameBoard.length);
            rollCol = rnd.nextInt(gameBoard.length);
        }
        if (!isPlayerOneTurn) {
            if (gameBoard[rollRow][rollCol].equals("")) {
                gameBoard[rollRow][rollCol] = oSymbol;
                isPlayerOneTurn = true;
                check(oSymbol);
                counter++;
                isDraw();
                if (draw) {
                    draw();
                }
            }
        }
    }

    public void check(String move){
        if (gameBoard[0][0].equals(move) &&
            gameBoard[0][1].equals(move) &&
            gameBoard[0][2].equals(move)){
            whoWins(0,0,0, 1, 0, 2, move);
        }
        if (gameBoard[1][0].equals(move) &&
            gameBoard[1][1].equals(move) &&
            gameBoard[1][2].equals(move)){
            whoWins(1,0,1, 1, 1, 2, move);
        }
        if (gameBoard[2][0].equals(move) &&
            gameBoard[2][1].equals(move) &&
            gameBoard[2][2].equals(move)){
            whoWins(2,0,2, 1, 2, 2, move);
        }
        if (gameBoard[0][0].equals(move) &&
            gameBoard[1][0].equals(move) &&
            gameBoard[2][0].equals(move)){
            whoWins(0,0,1, 0, 2, 0, move);
        }
        if (gameBoard[0][1].equals(move) &&
            gameBoard[1][1].equals(move) &&
            gameBoard[2][1].equals(move)){
            whoWins(0,1,1, 1, 2, 1, move);
        }
        if (gameBoard[0][2].equals(move) &&
            gameBoard[1][2].equals(move) &&
            gameBoard[2][2].equals(move)){
            whoWins(0,2,1, 2, 2, 2, move);
        }
        if (gameBoard[0][0].equals(move) &&
            gameBoard[1][1].equals(move) &&
            gameBoard[2][2].equals(move)){
            whoWins(0,0,1, 1, 2, 2, move);
        }
        if (gameBoard[0][2].equals(move) &&
            gameBoard[1][1].equals(move) &&
            gameBoard[2][0].equals(move)){
            whoWins(0,2,1, 1, 2, 0, move);
        }
    }

    public void whoWins(int rowA, int colA, int rowB, int colB, int rowC, int colC, String winner){
        if(winner.equals(xSymbol)){
            playerOneWins++;
            whoWon = xSymbol;
        }
        if(state == GameState.PVP && winner.equals(oSymbol)){
            playerTwoWins++;
            whoWon = oSymbol;
        }
        if(state == GameState.PVE && winner.equals(oSymbol)){
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
        if(counter == maxNumberOfTurns){
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

