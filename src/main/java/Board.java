import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Board implements ActionListener {

    private static final int rows = 3;
    private static final int cols = 3;
    private int[][] winnerFields;
    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JPanel mainMenuPanel = new JPanel();
    private final JPanel newGamePanel = new JPanel();
    private final JPanel gameTextPanel = new JPanel();
    private final JPanel gameButtonsPanel = new JPanel();
    private final JPanel gameOptionsPanel = new JPanel();
    private final JPanel rankingPanel = new JPanel();
    private final JLabel mainMenuTextField = new JLabel();
    private final JLabel newGameTextField = new JLabel();
    private final static JLabel gameTextField = new JLabel();
    private final JLabel[] rankingTextField = new JLabel[3];
    private final JButton[] mainMenuButtons = new JButton[4];
    private final static JButton[][] gameButtons = new JButton[rows][cols];
    private final JButton[] newGameButtons = new JButton[3];
    private final JButton[] gameOptionsButtons = new JButton[2];
    private final JButton rankingReturnButton = new JButton();
    private final JFileChooser fileChooser = new JFileChooser();
    private final Game game = new Game();
    private final DataSaver dataSaver = new DataSaver();
    private final DataLoader dataLoader = new DataLoader();

    public Board() throws IOException {

        //Main Menu Panel Setup
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBounds(0, 0, 800, 800);
        mainMenuPanel.setBackground(new Color(24, 99, 203, 189));
        mainMenuPanel.setVisible(true);
        mainMenuTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        mainMenuTextField.setText("Tic Tac Toe Game");
        mainMenuTextField.setBounds(30, 50, 800, 100);
        mainMenuPanel.add(mainMenuTextField);
        for (int i = 0; i < 4; i++) {
            mainMenuButtons[i] = new JButton();
            mainMenuPanel.add(mainMenuButtons[i]);
            mainMenuButtons[i].setForeground(new Color(1, 2, 1, 184));
            mainMenuButtons[i].setBorder(BorderFactory.createEtchedBorder());
            mainMenuButtons[i].setFont(new Font("MS Gothic", Font.BOLD, 18));
            mainMenuButtons[i].setFocusable(false);
            mainMenuButtons[i].addActionListener(this);
        }
        mainMenuButtons[0].setText("New Game");
        mainMenuButtons[0].setBounds(285, 300, 200, 50);
        mainMenuButtons[1].setText("Load Game");
        mainMenuButtons[1].setBounds(285, 375, 200, 50);
        mainMenuButtons[2].setText("Rankings");
        mainMenuButtons[2].setBounds(285, 450, 200, 50);
        mainMenuButtons[3].setText("Quit");
        mainMenuButtons[3].setBounds(285, 525, 200, 50);
        fileChooser.setCurrentDirectory(new File("."));

        //New Game Panel Setup
        newGamePanel.setLayout(null);
        newGamePanel.setBounds(0, 0, 800, 800);
        newGamePanel.setBackground(new Color(24, 99, 203, 189));
        newGamePanel.setVisible(false);
        newGameTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        newGameTextField.setText("Select Game Mode");
        newGameTextField.setBounds(30, 50, 800, 100);
        newGamePanel.add(newGameTextField);
        for (int i = 0; i < 3; i++) {
            newGameButtons[i] = new JButton();
            newGamePanel.add(newGameButtons[i]);
            newGameButtons[i].setForeground(new Color(1, 2, 1, 184));
            newGameButtons[i].setBorder(BorderFactory.createEtchedBorder());
            newGameButtons[i].setFont(new Font("MS Gothic", Font.BOLD, 18));
            newGameButtons[i].setFocusable(false);
            newGameButtons[i].addActionListener(this);
        }
        newGameButtons[0].setText("Player Vs Machine");
        newGameButtons[0].setBounds(285, 300, 200, 50);
        newGameButtons[1].setText("Player Vs Player");
        newGameButtons[1].setBounds(285, 375, 200, 50);
        newGameButtons[2].setText("Return");
        newGameButtons[2].setBounds(285, 450, 200, 50);

        //Ranking Setup
        rankingPanel.setLayout(null);
        rankingPanel.setBounds(0, 0, 800, 800);
        rankingPanel.setBackground(new Color(24, 99, 203, 189));
        rankingPanel.setVisible(false);
        for (int i = 0; i < 3; i++) {
            rankingTextField[i] = new JLabel();
            rankingPanel.add(rankingTextField[i]);
            rankingTextField[i].setFont(new Font("MS Gothic", Font.BOLD, 36));
        }
        rankingTextField[0].setBounds(250, 125, 800, 200);
        rankingTextField[1].setBounds(250, 200, 800, 200);
        rankingTextField[2].setBounds(250, 275, 800, 200);
        rankingReturnButton.setForeground(new Color(1, 2, 1, 184));
        rankingReturnButton.setBorder(BorderFactory.createEtchedBorder());
        rankingReturnButton.setFont(new Font("MS Gothic", Font.BOLD, 18));
        rankingReturnButton.setFocusable(false);
        rankingReturnButton.addActionListener(this);
        rankingReturnButton.setText("Return");
        rankingReturnButton.setBounds(285, 650, 200, 50);
        rankingPanel.add(rankingReturnButton);

        //Game Setup
        gameTextField.setBackground(new Color(25, 2, 2, 255));
        gameTextField.setForeground(new Color(8, 255, 0, 109));
        gameTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 75));
        gameTextField.setHorizontalAlignment(JLabel.CENTER);
        gameTextField.setText("Tic-Tac-Toe");
        gameTextField.setOpaque(true);

        gameTextPanel.setLayout(new BorderLayout());
        gameTextPanel.setBounds(0, 0, 800, 100);

        gameButtonsPanel.setLayout(new GridLayout(3, 3));
        gameButtonsPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                gameButtons[i][k] = new JButton();
                gameButtonsPanel.add(gameButtons[i][k]);
                gameButtons[i][k].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 120));
                gameButtons[i][k].setFocusable(false);
                gameButtons[i][k].addActionListener(this);
            }
        }

        //Game Bottom Panel Setup
        gameOptionsPanel.setLayout(new GridLayout(1, 2));
        gameOptionsPanel.setBackground(new Color(25, 2, 2, 255));
        for (int i = 0; i < 2; i++) {
            gameOptionsButtons[i] = new JButton();
            gameOptionsPanel.add(gameOptionsButtons[i]);
            gameOptionsButtons[i].setForeground(new Color(1, 2, 1, 184));
            gameOptionsButtons[i].setBorder(BorderFactory.createEtchedBorder());
            gameOptionsButtons[i].setFont(new Font("MS Gothic", Font.BOLD, 18));
            gameOptionsButtons[i].setFocusable(false);
            gameOptionsButtons[i].addActionListener(this);
        }
        gameOptionsButtons[0].setText("Save Game");
        gameOptionsButtons[1].setText("Main Menu");

        gameTextPanel.add(gameTextField);
        gameButtonsPanel.setVisible(false);
        gameTextPanel.setVisible(false);
        gameOptionsPanel.setVisible(false);

        //Frame Setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(mainMenuPanel);
        frame.add(newGamePanel);
        frame.add(rankingPanel);
        frame.add(gameTextPanel, BorderLayout.NORTH);
        frame.add(gameButtonsPanel, BorderLayout.CENTER);
        frame.add(gameOptionsPanel, BorderLayout.SOUTH);
    }

    public static void setGameTextField(String string) {
        gameTextField.setText(string);
    }

    public static void resetGameButtons() {
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                gameButtons[i][k].setEnabled(true);
                gameButtons[i][k].setText("");
                gameButtons[i][k].setBackground(Color.lightGray);
            }
        }
    }

    public static void disableGameButtons(boolean draw) {
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                gameButtons[i][k].setEnabled(false);
                if (draw) {
                    gameButtons[i][k].setBackground(Color.GRAY);
                }
            }
        }
    }

    public void update(String[][] board, boolean gameOver, boolean draw) {
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                gameButtons[i][k].setText(board[i][k]);
                if (board[i][k].equals(Game.X_SYMBOL)) {
                    gameButtons[i][k].setForeground(new Color(255, 0, 0));
                } else if (board[i][k].equals(Game.O_SYMBOL)) {
                    gameButtons[i][k].setForeground(new Color(0, 0, 255));
                }
            }
        }
        gameOptionsButtons[0].setEnabled(true);
        if (gameOver && !draw) {
            winnerFields = game.getWinnerFields();
            gameButtons[winnerFields[0][0]][winnerFields[0][1]].setBackground(Color.GREEN);
            gameButtons[winnerFields[1][0]][winnerFields[1][1]].setBackground(Color.GREEN);
            gameButtons[winnerFields[2][0]][winnerFields[2][1]].setBackground(Color.GREEN);
            gameTextField.setText(game.getWhoWon() + " wins!");
            gameOptionsButtons[0].setEnabled(false);
            disableGameButtons(draw);
        } else if (gameOver && draw) {
            disableGameButtons(draw);
            gameOptionsButtons[0].setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //New Game
        if (e.getSource() == mainMenuButtons[0]) {
            mainMenuPanel.setVisible(false);
            newGamePanel.setVisible(true);
            gameTextField.setText("Tic-Tac-Toe");
        }

        //Load Game
        if (e.getSource() == mainMenuButtons[1]) {

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    dataLoader.loadGame(selectedFile, game);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                if (game.isIsPlayerOneTurn()) {
                    gameTextField.setText("O turn");
                } else {
                    gameTextField.setText("X Turn");
                }
                mainMenuPanel.setVisible(false);
                gameButtonsPanel.setVisible(true);
                gameTextPanel.setVisible(true);
                gameOptionsPanel.setVisible(true);
                update(game.getGameBoard(), game.isGameOver(), game.getDraw());
            }
        }

        //Player Vs Machine
        if (e.getSource() == newGameButtons[0]) {
            newGamePanel.setVisible(false);
            gameButtonsPanel.setVisible(true);
            gameTextPanel.setVisible(true);
            gameOptionsPanel.setVisible(true);
            resetGameButtons();
            game.gameSetup(GameState.PVE);
        }

        //Player Vs Player
        if (e.getSource() == newGameButtons[1]) {
            newGamePanel.setVisible(false);
            gameButtonsPanel.setVisible(true);
            gameTextPanel.setVisible(true);
            gameOptionsPanel.setVisible(true);
            resetGameButtons();
            game.gameSetup(GameState.PVP);
        }

        //Rankings
        if (e.getSource() == mainMenuButtons[2]) {
            mainMenuPanel.setVisible(false);
            rankingPanel.setVisible(true);
            try {
                dataSaver.saveRanking(game);
                dataLoader.loadRanking(game);
                rankingTextField[0].setText("Player 'X': " + game.getPlayerOneWins());
                rankingTextField[1].setText("Player 'O': " + game.getPlayerTwoWins());
                rankingTextField[2].setText("Machine: " + game.getMachineWins());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        //Save Game
        if (e.getSource() == gameOptionsButtons[0]) {

            int returnValue = fileChooser.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                dataSaver.saveGame(selectedFile, game);
            }
        }

        //Quit
        if (e.getSource() == mainMenuButtons[3]) {
            System.exit(0);
        }

        //Return to Main Menu
        if (e.getSource() == newGameButtons[2]) {
            mainMenuPanel.setVisible(true);
            newGamePanel.setVisible(false);
        }

        //Return from ranking screen to main menu
        if (e.getSource() == rankingReturnButton) {
            mainMenuPanel.setVisible(true);
            rankingPanel.setVisible(false);
        }

        //Return to Main Menu from game screen
        if (e.getSource() == gameOptionsButtons[1]) {
            mainMenuPanel.setVisible(true);
            gameButtonsPanel.setVisible(false);
            gameTextPanel.setVisible(false);
            gameOptionsPanel.setVisible(false);
            resetGameButtons();
            game.reset();
        }

        //Game Buttons
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                if (e.getSource() == gameButtons[i][k]) {
                    game.validMove(i, k);
                    update(game.getGameBoard(), game.isGameOver(), game.getDraw());
                }
            }
        }
    }
}