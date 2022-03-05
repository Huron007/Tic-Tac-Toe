import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {

    Game game;

    public DataSaver(Game game){
        this.game = game;
    }

    public void saveGame(File file){
        try {
            FileWriter gameSaver = new FileWriter(file.getAbsolutePath() + ".txt");
            String gameSave = game.getGameBoard()[0][0] + "\n" + game.getGameBoard()[0][1] + "\n" + game.getGameBoard()[0][2] + "\n" +
                              game.getGameBoard()[1][0] + "\n" + game.getGameBoard()[1][1] + "\n" + game.getGameBoard()[1][2] + "\n" +
                              game.getGameBoard()[2][0] + "\n" + game.getGameBoard()[2][1] + "\n" + game.getGameBoard()[2][2] + "\n" +
                              game.isPlayerOneTurn() + "\n" + game.getCounter() + "\n" + game.getState();
            gameSaver.write(gameSave);
            gameSaver.close();
        } catch (IOException e){
            System.out.println("Wystapil blad");
            e.printStackTrace();
        }
    }

    public void saveRanking(){
        try {
            File ranking = new File(".", "ranking.txt");
            ranking.createNewFile();
            FileWriter rankingWriter = new FileWriter(ranking);
            String rankingContent = game.getPlayerOneWins() + "\n" + game.getPlayerTwoWins() + "\n" + game.getMachineWins();
            rankingWriter.write(rankingContent);
            rankingWriter.close();
        } catch (IOException e){
            System.out.println("Wystapil blad");
            e.printStackTrace();
        }
    }
}
