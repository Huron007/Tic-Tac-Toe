import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {

    public void saveGame(File file){
        try {
            FileWriter gameSaver = new FileWriter(file.getAbsolutePath() + ".txt");
            String gameSave = Game.getGameBoard()[0][0] + "\n" + Game.getGameBoard()[0][1] + "\n" + Game.getGameBoard()[0][2] + "\n" +
                              Game.getGameBoard()[1][0] + "\n" + Game.getGameBoard()[1][1] + "\n" + Game.getGameBoard()[1][2] + "\n" +
                              Game.getGameBoard()[2][0] + "\n" + Game.getGameBoard()[2][1] + "\n" + Game.getGameBoard()[2][2] + "\n" +
                              Game.isPlayerOneTurn() + "\n" + Game.getCounter() + "\n" + Game.getState();
            gameSaver.write(gameSave);
            gameSaver.close();
        } catch (IOException e){
            System.out.println("Wystapil blad");
            e.printStackTrace();
        }
    }

    public void saveRanking(){
        try {
            FileWriter rankingWriter = new FileWriter("C:/Users/Tomek/IdeaProjects/Tic-Tac-Toe/src/main/resources/ranking.txt");
            String rankingContent = Game.getPlayerOneWins() + "\n" + Game.getPlayerTwoWins() + "\n" + Game.getMachineWins();
            rankingWriter.write(rankingContent);
            rankingWriter.close();
        } catch (IOException e){
            System.out.println("Wystapil blad");
            e.printStackTrace();
        }
    }
}
