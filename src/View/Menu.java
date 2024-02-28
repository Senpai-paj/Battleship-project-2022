package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Johannes Rosengren Systemutvecklare HT22 (datorID: an6380)
 * @author Victor Pirojoc Systemutvecklare HT22 (datorID: an4721)
 *
 * The main menu of the GUI part of the program
 */
public class Menu extends JFrame implements ActionListener {
    private final JPanel mainPanel;
    private JButton Pvp;
    private JButton singlePlayer;
    private JButton AI;
    private JButton Stats;
    private JButton Load_Game;
    private JButton Quit;
    private final Controller controller;

    /**
     * Constructor for initializing the main menu
     * @param controller an instance of a Controller class object
     */
    public Menu(Controller controller){
        this.controller = controller;
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        setSize(480, 570);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);
        this.setTitle("Menu");
        this.setUndecorated(true);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        mainPanel.setBackground(Color.BLACK);
        buttonsLayout();
        this.setVisible(true);
    }

    /**
     * Initialization of the button objects and subsequently population of the mainPanel
     */
    private void buttonsLayout() {
        singlePlayer = new JButton("Single Player");
        singlePlayer.setFocusable(false);
        singlePlayer.setFont(new Font("Copperplate", Font.BOLD,45));
        singlePlayer.setSize(270*2, 45*2);
        singlePlayer.setLocation(-30, 90);
        singlePlayer.setBorderPainted(false);
        singlePlayer.setBackground(Color.BLACK);
        singlePlayer.setForeground(new Color(0, 180, 255));
        singlePlayer.addActionListener(this);
        mainPanel.add(singlePlayer);

        Pvp = new JButton("Player vs Player");
        Pvp.setFocusable(false);
        Pvp.setFont(new Font("Copperplate", Font.BOLD,48));
        Pvp.setSize(270*2, 45*2);
        Pvp.setLocation(-30, 0);
        Pvp.setBorderPainted(false);
        Pvp.setBackground(Color.BLACK);
        Pvp.setForeground(new Color(0, 155, 255));
        Pvp.addActionListener(this);
        mainPanel.add(Pvp);

        AI = new JButton("Player vs AI");
        AI.setFocusable(false);
        AI.setFont(new Font("Copperplate", Font.BOLD,45));
        AI.setSize(270*2, 45*2);
        AI.setLocation(-30, 190);
        AI.setBackground(Color.BLACK);
        AI.setBorderPainted(false);
        AI.setForeground(new Color(0, 137, 194));
        AI.addActionListener(this);
        mainPanel.add(AI);

        Load_Game = new JButton("Load Game");
        Load_Game.setFocusable(false);
        Load_Game.setFont(new Font("Copperplate", Font.BOLD,45));
        Load_Game.setSize(270*2, 45*2);
        Load_Game.setLocation(-30, 290);
        Load_Game.setBorderPainted(false);
        Load_Game.setBackground(Color.BLACK);
        Load_Game.setForeground(new Color(0, 100, 194));
        Load_Game.addActionListener(this);
        mainPanel.add(Load_Game);

        Stats = new JButton("Stats");
        Stats.setFocusable(false);
        Stats.setFont(new Font("Copperplate", Font.BOLD,45));
        Stats.setSize(270*2, 55*2);
        Stats.setLocation(-30, 380);
        Stats.setBorderPainted(false);
        Stats.setBackground(Color.BLACK);
        Stats.setForeground(new Color(0, 80, 194));
        Stats.addActionListener(this);
        mainPanel.add(Stats);

        Quit = new JButton("Quit");
        Quit.setFocusable(false);
        Quit.setFont(new Font("Copperplate", Font.BOLD,45));
        Quit.setSize(270*2, 45*2);
        Quit.setLocation(-30, 480);
        Quit.setBorderPainted(false);
        Quit.setBackground(Color.BLACK);
        Quit.setForeground(new Color(0, 40, 194));
        Quit.addActionListener(this);
        mainPanel.add(Quit);

    }

    /**
     * Listener for the main menu
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Pvp) {
            controller.initNewGameBoards();
            new PlayerBoard(controller,true);
        }
        else if(e.getSource() == singlePlayer){
            controller.initNewGameBoards();
            new PlayerBoard(controller,false);
        }
        else if (e.getSource() == AI) {
            JOptionPane.showMessageDialog(this,"Not implemented!");
        }
        else if (e.getSource() == Stats) {
            new ShowStats(controller.getScoreObjectsFromDiskAsStrings().toArray(new String[0]));
        }
        else if (e.getSource() == Load_Game) {
            controller.loadGameFromDisk();
        }
        else if (e.getSource() == Quit) {
            System.exit(0);
        }

    }

    /**
     * Shows a message using a JOptionPane
     * @param output the message to be shown
     */
    public void showMessage(String output) {
        JOptionPane.showMessageDialog(null,output);
    }
}

