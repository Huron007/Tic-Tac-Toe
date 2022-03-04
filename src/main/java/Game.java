import java.util.Random;

public class Game {

    private static String[][] gameBoard = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };

    private static int[][] winnerFields;

    private static int counter = 0;
    private int rollRow;
    private int rollCol;
    private boolean draw;
    public static final String xSymbol = "X";
    public static final String oSymbol = "O";
    private String whoWon = "";
    private int maxNumberOfTurns = 9;
    private static int playerOneWins = 0;
    private static int playerTwoWins = 0;
    private static int machineWins = 0;
    private static boolean isPlayerOneTurn = true;
    private boolean gameOver = false;
    private static GAMESTATE state = GAMESTATE.PVE;
    private Random rnd = new Random();
    private DataSaver dataSaver = new DataSaver();

    public void gameSetup(GAMESTATE gamestate){
        counter = 0;
        state = gamestate;
        gameOver = false;
        isPlayerOneTurn = true;
        draw = false;
    }

    public void validMove(int row, int col) {
        if (gameBoard[row][col].equals("")) {
            if (state == GAMESTATE.PVP) {
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
            } else if (state == GAMESTATE.PVE) {
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
        if(state == GAMESTATE.PVP && winner.equals(oSymbol)){
            playerTwoWins++;
            whoWon = oSymbol;
        }
        if(state == GAMESTATE.PVE && winner.equals(oSymbol)){
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
        winnerFields = null;
    }

    public static int[][] getWinnerFields() {
        return winnerFields;
    }

    public static String[][] getGameBoard() {
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

    public static GAMESTATE getState() {
        return state;
    }

    public static int getCounter() {
        return counter;
    }

    public static boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public static int getPlayerOneWins() {
        return playerOneWins;
    }

    public static int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public static int getMachineWins() {
        return machineWins;
    }

    public String getWhoWon() {
        return whoWon;
    }

    public static boolean isIsPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public static void setPlayerOneWins(int playerOneWins) {
        Game.playerOneWins = playerOneWins;
    }

    public static void setPlayerTwoWins(int playerTwoWins) {
        Game.playerTwoWins = playerTwoWins;
    }

    public static void setMachineWins(int machineWins) {
        Game.machineWins = machineWins;
    }

    public static void setGameBoard(String gameBoard, int row, int col) {
        Game.gameBoard[row][col] = gameBoard;
    }

    public static void setCounter(int counter) {
        Game.counter = counter;
    }

    public static void setIsPlayerOneTurn(boolean isPlayerOneTurn) {
        Game.isPlayerOneTurn = isPlayerOneTurn;
    }

    public static void setState(GAMESTATE state) {
        Game.state = state;
    }
}

