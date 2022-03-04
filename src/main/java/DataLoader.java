import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {

    public void loadGame(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        Game.setGameBoard((scan.nextLine()), 0, 0);
        Game.setGameBoard((scan.nextLine()), 0, 1);
        Game.setGameBoard((scan.nextLine()), 0, 2);
        Game.setGameBoard((scan.nextLine()), 1, 0);
        Game.setGameBoard((scan.nextLine()), 1, 1);
        Game.setGameBoard((scan.nextLine()), 1, 2);
        Game.setGameBoard((scan.nextLine()), 2, 0);
        Game.setGameBoard((scan.nextLine()), 2, 1);
        Game.setGameBoard((scan.nextLine()), 2, 2);
        Game.setIsPlayerOneTurn(Boolean.parseBoolean(scan.nextLine()));
        Game.setCounter(Integer.parseInt(scan.nextLine()));
        String gameMode = scan.nextLine();
        switch (gameMode){
            case "PVP":
                Game.setState(GAMESTATE.PVP);
                break;
            case "PVE":
                Game.setState(GAMESTATE.PVE);
                break;
        }
        scan.close();
    }

    public void loadRanking() throws FileNotFoundException {
        File ranking = new File("C:/Users/Tomek/IdeaProjects/Tic-Tac-Toe/src/main/resources/ranking.txt");
        Scanner scan = new Scanner(ranking);
        Game.setPlayerOneWins(Integer.parseInt(scan.nextLine()));
        Game.setPlayerTwoWins(Integer.parseInt(scan.nextLine()));
        Game.setMachineWins(Integer.parseInt(scan.nextLine()));
        scan.close();
    }
}
