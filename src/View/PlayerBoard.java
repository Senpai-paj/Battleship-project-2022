package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author Johannes Rosengren Systemutvecklare HT22 (datorID: an6380)
 * @author Victor Pirojoc Systemutvecklare HT22 (datorID: an4721)
 *
 * This class is responsible for managing the GUI part of the game board
 */
public class PlayerBoard extends JFrame implements ActionListener {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private JButton save;
    private JButton quit;
    private JButton stats;
    private final Controller controller;
    private boolean gameWon;
    private final JButton[][] buttonsLeft = new JButton[10][10];
    private final JButton[][] buttonsRight = new JButton[10][10];
    private final boolean[][] currentMovesOnLeftBoard = new boolean[10][10];
    private final boolean[][] currentMovesOnRightBoard = new boolean[10][10];

    /**
     * Constructor for launching new game
     * @param controller an instance of the Controller class
     * @param multiPlayer whether the game will be multiplayer
     */
    public PlayerBoard(Controller controller, boolean multiPlayer) {
        this.controller = controller;
        this.setLayout(null);
        this.setUndecorated(true);
        this.setSize(1040, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("BattleShip");
        this.setResizable(false);
        getContentPane().setBackground(new Color(34, 37, 43));
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents(multiPlayer);
        setupLeftBoard();
        setupRightBoard();
    }

    /**
     * Constructor for starting from a previous game session
     * @param controller an instance of the Controller class
     * @param movesOnLeftPanel a boolean array containing the pressed buttons on the left board
     * @param movesOnRightPanel a boolean array containing the pressed buttons on the right board
     */
    //constructor for loading a saved game
    public PlayerBoard(Controller controller, boolean[][] movesOnLeftPanel, boolean[][] movesOnRightPanel) {
        this.controller = controller;
        //checks if left moves boolean array from file only contains false (0 moves) and sets multiplayer to true/false
        boolean multiPlayer;
        multiPlayer = !Arrays.deepEquals(movesOnRightPanel, new boolean[10][10]);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setSize(1040, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("BattleShip");
        this.setResizable(false);
        getContentPane().setBackground(new Color(34, 37, 43));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents(multiPlayer);
        setupLeftBoard();
        setupRightBoard();
        //this.setVisible(true);
        //WHY IS IT LIKE THIS??? - doesn't show all components until loadMovesOnBoard() completes?
        if (multiPlayer) {
            loadMovesOnBoard(movesOnLeftPanel, movesOnRightPanel);
        }
        else {
            loadMovesOnBoard(movesOnLeftPanel);
        }

    }

    /**
     * Clicks the buttons that maps to a true value in the inputted boolean array
     * @param movesOnLeftPanel a boolean array containing the which buttons should be pressed on the left board
     * @param movesOnRightPanel a boolean array containing the which buttons should be pressed on the right board
     */
    private void loadMovesOnBoard(boolean[][] movesOnLeftPanel, boolean[][] movesOnRightPanel) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (movesOnLeftPanel[row][col]) {
                    leftButtonListener(row,col);
                    System.out.println("CLICK LEFT");
                }
                if (movesOnRightPanel[row][col]) {
                    rightButtonListener(row,col);
                    System.out.println("CLICK RIGHT");
                }
            }
        }
    }

    /**
     * Clicks the buttons that maps to a true value in the inputted boolean array. Only used for single player games
     * @param movesOnLeftPanel a boolean array containing the which buttons should be pressed on the left board
     */
    private void loadMovesOnBoard(boolean[][] movesOnLeftPanel) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (movesOnLeftPanel[row][col]) {
                    leftButtonListener(row,col);
                    System.out.println("CLICK");
                }
            }
        }
    }

    /**
     * Initializes the components of the game board based on whether multiplayer/single-player is selected
     * @param multiPlayer whether to multiplayer
     */
    private void initComponents(boolean multiPlayer) {
        leftPanel = new JPanel();
        leftPanel.setOpaque(true);
        leftPanel.setSize(500,500);
        leftPanel.setLocation(0,30);
        leftPanel.setLayout(new GridLayout(10,10));
        this.add(leftPanel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(10,1));
        centerPanel.setBackground(new Color(34, 37, 43));
        centerPanel.setOpaque(true);
        centerPanel.setSize(40,500);
        centerPanel.setLocation(500+10,30);

        rightPanel = new JPanel();
        rightPanel.setOpaque(true);
        rightPanel.setSize(500,500);
        rightPanel.setLocation(540,30);
        rightPanel.setLayout(new GridLayout(10,10));
        this.add(rightPanel);

        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setOpaque(true);
        topLeftPanel.setSize(510,40);
        topLeftPanel.setLocation(15,-5);
        topLeftPanel.setLayout(new GridLayout(1,10));
        topLeftPanel.setBackground(new Color(34, 37, 43));
        topLeftPanel.add(createLetters("A"));
        topLeftPanel.add(createLetters("B"));
        topLeftPanel.add(createLetters("C"));
        topLeftPanel.add(createLetters("D"));
        topLeftPanel.add(createLetters("E"));
        topLeftPanel.add(createLetters("F"));
        topLeftPanel.add(createLetters("G"));
        topLeftPanel.add(createLetters("H"));
        topLeftPanel.add(createLetters("I"));
        topLeftPanel.add(createLetters("J"));
        this.add(topLeftPanel);
        topLeftPanel.setVisible(true);

        JPanel topRightPanel = new JPanel();
        topRightPanel.setOpaque(true);
        topRightPanel.setSize(510,40);
        topRightPanel.setLocation(555,-5);
        topRightPanel.setLayout(new GridLayout(1,10));
        topRightPanel.setBackground(new Color(34, 37, 43));
        topRightPanel.add(createLetters("A"));
        topRightPanel.add(createLetters("B"));
        topRightPanel.add(createLetters("C"));
        topRightPanel.add(createLetters("D"));
        topRightPanel.add(createLetters("E"));
        topRightPanel.add(createLetters("F"));
        topRightPanel.add(createLetters("G"));
        topRightPanel.add(createLetters("H"));
        topRightPanel.add(createLetters("I"));
        topRightPanel.add(createLetters("J"));
        topRightPanel.setVisible(true);
        this.add(topRightPanel);

        //setup labels on centerPanel
        for (int i = 0; i < 10; i++) {
            JLabel numbers = new JLabel(String.valueOf((i+1)));
            numbers.setForeground(new Color(0, i+15*10, 194));
            //letters.setLocation(515,(i*50)+20);
            numbers.setSize(10,10);
            numbers.setVisible(true);
            centerPanel.add(numbers);
        }
        this.add(centerPanel);

        save = new JButton("SAVE");
        save.setOpaque(true);
        save.setBorderPainted(false);
        save.setBackground(new Color(49, 55, 67));
        save.setForeground(new Color(0, 150, 194));
        save.setSize(150,50);
        save.setLocation(95,540);
        save.setFocusable(false);
        save.addActionListener(this);
        this.add(save);

        stats = new JButton("STATISTICS");
        stats.setOpaque(true);
        stats.setBorderPainted(false);
        stats.setBackground(new Color(49, 55, 67));
        stats.setForeground(new Color(0, 150, 194));
        stats.setSize(150,50);
        stats.setLocation(255,540);
        stats.setFocusable(false);
        stats.addActionListener(this);
        this.add(stats);

        quit = new JButton("QUIT");
        quit.setOpaque(true);
        quit.setBorderPainted(false);
        quit.setBackground(new Color(49, 55, 67));
        quit.setForeground(new Color(0, 150, 194));
        quit.setSize(150,50);
        quit.setLocation(870,540);
        quit.setFocusable(false);
        quit.addActionListener(this);
        this.add(quit);

        //setup for single player
        if (!multiPlayer) {
            this.setSize(530,600);
            this.setLocationRelativeTo(null);
            save.setLocation(20,540);
            stats.setLocation(190,540);
            quit.setLocation(360,540);
        }

    }

    /**
     * Sets up buttons on the left board and adds an actionListener implementation specific to each button
     */
    private void setupLeftBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JButton tmpBtn = new JButton();
                tmpBtn.setSize(10, 10);
                int rowInput = row;
                int colInput = col;
                //custom implementation of actionListener is assigned to button
                tmpBtn.addActionListener((actionEvent) -> leftButtonListener(rowInput, colInput));
                tmpBtn.setOpaque(true);
                tmpBtn.setBackground(new Color(49, 55, 67));
                Border border = new LineBorder(Color.BLACK, 1);
                tmpBtn.setBorder(border);
                buttonsLeft[row][col] = tmpBtn;
                leftPanel.add(tmpBtn);
            }
        }
    }

    /**
     * Sets up buttons on the right board and adds an actionListener implementation specific to each button
     */
    private void setupRightBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JButton tmpBtn = new JButton();
                tmpBtn.setSize(10, 10);
                int rowInput = row;
                int colInput = col;
                //custom implementation of actionListener is assigned to button
                tmpBtn.addActionListener((event) -> rightButtonListener(rowInput, colInput));
                tmpBtn.setOpaque(true);
                tmpBtn.setBackground(new Color(49, 55, 67));
                Border border = new LineBorder(Color.BLACK, 1);
                tmpBtn.setBorder(border);
                buttonsRight[row][col] = tmpBtn;
                rightPanel.add(tmpBtn);
            }
        }
    }

    /**
     * The listener method for the left board's buttons
     * @param row the row of the button on the left board
     * @param col the column of the button on the left board
     */
    private void leftButtonListener(int row, int col) {
        System.out.println("Button pressed on left board at: row:" + row + " col:" + col);
        controller.incrementPlayer2Score();
        buttonsLeft[row][col].setEnabled(false);
        currentMovesOnLeftBoard[row][col] = true;
        if (controller.shipExistsAt(false, row, col)) {
            switch (controller.getShipTypeAt(row, col, false)) {
                case U_bat -> {
                    buttonsLeft[row][col].setText("1");
                    buttonsLeft[row][col].setBackground(new Color(0, 100, 194));
                }
                case Torped -> {
                    buttonsLeft[row][col].setText("2");
                    buttonsLeft[row][col].setBackground(new Color(0, 90, 194));
                }
                case Jagare -> {
                    buttonsLeft[row][col].setText("3");
                    buttonsLeft[row][col].setBackground(new Color(0, 70, 194));
                }
                case Kryssare -> {
                    buttonsLeft[row][col].setText("4");
                    buttonsLeft[row][col].setBackground(new Color(0, 60, 194));
                }
                case Slagskepp -> {
                    buttonsLeft[row][col].setText("5");
                    buttonsLeft[row][col].setBackground(new Color(0, 30, 194));
                }
            }
            if (controller.hitShipSunk(false, row, col)) {
                refreshLeftPanel();
                if (!gameWon) {
                    if (controller.checkIfRightPlayerWon()) {
                        gameWon = true;
                        gameWonAnnouncement(true);
                    }
                }
            }
        }
        else {
            buttonsLeft[row][col].setBackground(Color.DARK_GRAY);
        }
    }

    /**
     * The listener method for the right board's buttons
     * @param row the row of the button on the right board
     * @param col the column of the button on the right board
     */
    private void rightButtonListener(int row, int col) {
        System.out.println("Button pressed on right board at: row:" + row + " col:" + col);
        controller.incrementPlayer1Score();
        buttonsRight[row][col].setEnabled(false);
        currentMovesOnRightBoard[row][col] = true;
        if (controller.shipExistsAt(true, row, col)) {
            switch (controller.getShipTypeAt(row, col,true)) {
                case U_bat -> {
                    buttonsRight[row][col].setText("1");
                    buttonsRight[row][col].setBackground(new Color(200, 0, 0));
                }
                case Torped -> {
                    buttonsRight[row][col].setText("2");
                    buttonsRight[row][col].setBackground(new Color(170, 0, 0));
                }
                case Jagare -> {
                    buttonsRight[row][col].setText("3");
                    buttonsRight[row][col].setBackground(new Color(130, 0, 0));
                }
                case Kryssare -> {
                    buttonsRight[row][col].setText("4");
                    buttonsRight[row][col].setBackground(new Color(100, 0, 0));
                }
                case Slagskepp -> {
                    buttonsRight[row][col].setText("5");
                    buttonsRight[row][col].setBackground(new Color(50, 0, 0));
                }
                default -> buttonsRight[row][col].setBackground(Color.BLACK);
            }
            if (controller.hitShipSunk(true, row, col)) {
                refreshRightPanel();
                if (!gameWon) {
                    if (controller.checkIfLeftPlayerWon()) {
                        gameWon = true;
                        gameWonAnnouncement(false);
                    }
                }
            }
        }
        else {
            buttonsRight[row][col].setBackground(Color.DARK_GRAY);
        }
    }

    /**
     * Shows a message that announces that a specified player has won
     * @param player2 whether player2 has won or not
     */
    public void gameWonAnnouncement(boolean player2) {
        if (player2) {
            if (Arrays.deepEquals(currentMovesOnRightBoard, new boolean[10][10])) {
                JOptionPane.showMessageDialog(this,"YOU WON!");
            }
            else {
                JOptionPane.showMessageDialog(this,"PLAYER 2 WON!");
            }
            controller.letWinnerEnterNameToPotentiallyAddToScoreboard(JOptionPane.showInputDialog("Enter a name for the scoreboard!"),controller.getMovesRequiredToWin(true));
            controller.showStats();
        }
        else {
            JOptionPane.showMessageDialog(this,"PLAYER 1 WON!");
            controller.letWinnerEnterNameToPotentiallyAddToScoreboard(JOptionPane.showInputDialog("Enter a name for the scoreboard!"),controller.getMovesRequiredToWin(false));
            controller.showStats();
        }
    }

    /**
     * Creates a new JLabel with inputted string and returns it
     * @param string the string to be shown in the JLabel
     * @return a JLabel with the specified string
     */
    private JLabel createLetters(String string){
        JLabel letter = new JLabel(string);
        letter.setForeground(new Color(0, 131, 194));
        letter.setSize(10,10);
        letter.setVisible(true);
        centerPanel.add(letter);
        return letter;
    }

    /**
     * Updates the left panel by checking for ships that have been sunk
     */
    private void refreshLeftPanel() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (controller.shipExistsAt(false,row,col)) {
                    if (controller.positionContainSunkenShip(row,col,false)) {
                        buttonsLeft[row][col].setText("X");
                    }
                }
            }
        }
    }

    /**
     * Updates the right panel by checking for ships that have been sunk
     */
    private void refreshRightPanel() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (controller.shipExistsAt(true,row,col)) {
                    if (controller.positionContainSunkenShip(row,col,true)) {
                        buttonsRight[row][col].setText("X");
                    }
                }
            }
        }
    }

    /**
     * The listener of the PlayerBoard class
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == save) {
            if (controller.saveGameToDisk(currentMovesOnLeftBoard, currentMovesOnRightBoard)) {
                JOptionPane.showMessageDialog(this, "Saved game to disk!");
            }
            else {
                JOptionPane.showMessageDialog(this, "Could not save game to disk!");

            }
        }

        else if (e.getSource() == stats) {
            System.out.println("Pressed stats");
            new ShowStats(controller.getScoreObjectsFromDiskAsStrings().toArray(new String[0]));
        }

        else if (e.getSource() == quit) {
            if (JOptionPane.showConfirmDialog(this, "Do you really want to exit to main menu?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        }

    }

}
