import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    JPanel panel;
    JMenu menu;
    JMenuBar menuBar;
    JMenuItem item1, item2, item3, item4;
    JTextArea textArea;
    JOptionPane askForNameJOptionPane;
    Player player;
    Menu oldMenu;
    Gui gui;

    public Gui() {
        gui = this;
        oldMenu = new Menu();
        menu = new JMenu("Menu");
        menuBar = new JMenuBar();
        item1 = new JMenuItem("SinglePlayer");
        item2 = new JMenuItem("Multiplayer");
        item3 = new JMenuItem("Loadgame");
        item4 = new JMenuItem("Quit");
        askForNameJOptionPane = new JOptionPane();
        textArea = new JTextArea();
        textArea.setBounds(30, 30, 300, 300);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menuBar.add(menu);
        add(askForNameJOptionPane);
        add(textArea);
        add(menuBar);
        setJMenuBar(menuBar);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = JOptionPane.showInputDialog("Please give your player a name: ");
                player = new Player(string);
                new Game(player, oldMenu);
            }
        });

        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
