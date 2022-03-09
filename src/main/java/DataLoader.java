import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {


    public void loadGame(File file, Game game) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        for(int i = 0; i < game.getGameBoard().length; i++){
            game.setGameBoard((scan.nextLine()), i, 0);
            game.setGameBoard((scan.nextLine()), i, 1);
            game.setGameBoard((scan.nextLine()), i, 2);
        }
        game.setIsPlayerOneTurn(Boolean.parseBoolean(scan.nextLine()));
        game.setCounter(Integer.parseInt(scan.nextLine()));
        String gameMode = scan.nextLine();
        switch (gameMode){
            case "PVP":
                game.setState(GameState.PVP);
                break;
            case "PVE":
                game.setState(GameState.PVE);
                break;
        }
        scan.close();
    }

    public void loadRanking(Game game) throws FileNotFoundException {
        File ranking = new File(".","ranking.txt");
        Scanner scan = new Scanner(ranking);
        game.setPlayerOneWins(Integer.parseInt(scan.nextLine()));
        game.setPlayerTwoWins(Integer.parseInt(scan.nextLine()));
        game.setMachineWins(Integer.parseInt(scan.nextLine()));
        scan.close();
    }
}
