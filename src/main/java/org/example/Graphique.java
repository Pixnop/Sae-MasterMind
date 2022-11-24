package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Graphique  extends JFrame {


    public JComboBox Couleur1;
    public JComboBox Couleur2;
    public JComboBox Couleur3;
    public JComboBox Couleur4;
    public JTable table;
    public JButton envoyerButton;
    public JButton menuButton;

    public JPanel panelLeft;
    public JPanel panelRight;

    public Graphique() {
        super("MasterMind");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);

        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();

        JComboBox Couleur1 = new JComboBox();
        JComboBox Couleur2 = new JComboBox();
        JComboBox Couleur3 = new JComboBox();
        JComboBox Couleur4 = new JComboBox();

        String header[] = {"n°1","n°2","n°3","n°4"};
        Object data[] []= {
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""}};

        JTable table = new JTable(data, header);

        JButton envoyerButton= new JButton();
        JButton menuButton= new JButton();

        envoyerButton.setText("Envoyer");
        menuButton.setText("Menu");


        table.setGridColor(Color.black);

        panelLeft.add(table);
        panelLeft.add(envoyerButton);
        panelLeft.add(menuButton);

        panelRight.add(Couleur1);
        panelRight.add(Couleur2);
        panelRight.add(Couleur3);
        panelRight.add(Couleur4);

        JPanel panneau = new JPanel();
        panneau.add(panelLeft);
        panneau.add(panelRight);
        setContentPane(panneau);
        setSize(500,500);
        setVisible(true);
    }
    public static void main(String [] args){
        JFrame frame = new Graphique();
    }
}
