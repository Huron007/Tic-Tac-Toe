import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {

    public void saveGame(File file, Game game){
        try {
            FileWriter gameSaver = new FileWriter(file.getAbsolutePath() + ".txt");
            String gameSave = "";
            for(int i = 0; i < game.getGameBoard().length; i++){
                gameSave += game.getGameBoard()[i][0] + "\n" + game.getGameBoard()[i][1] + "\n" + game.getGameBoard()[i][2] + "\n";
                if(i == 2){
                    gameSave += game.isPlayerOneTurn() + "\n" + game.getCounter() + "\n" + game.getState();
                }
            }
            gameSaver.write(gameSave);
            gameSaver.close();
        } catch (IOException e){
            System.out.println("Wystapil blad");
            e.printStackTrace();
        }
    }

    public void saveRanking(Game game){
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
