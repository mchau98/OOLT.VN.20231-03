package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tsp.TSP_GA;

public class TSP_GUI {

    private JFrame frame;
    private JTextField textFieldCities;
    private JTextField textFieldPopSize;
    private JTextField textFieldGenerations;

    public TSP_GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCities = new JLabel("Number of Cities:");
        lblCities.setBounds(10, 10, 150, 25);
        frame.getContentPane().add(lblCities);

        textFieldCities = new JTextField();
        textFieldCities.setBounds(160, 10, 120, 25);
        frame.getContentPane().add(textFieldCities);
        textFieldCities.setColumns(10);

        JLabel lblPopSize = new JLabel("Population Size:");
        lblPopSize.setBounds(10, 45, 150, 25);
        frame.getContentPane().add(lblPopSize);

        textFieldPopSize = new JTextField();
        textFieldPopSize.setBounds(160, 45, 120, 25);
        frame.getContentPane().add(textFieldPopSize);
        textFieldPopSize.setColumns(10);

        JLabel lblGenerations = new JLabel("Generations:");
        lblGenerations.setBounds(10, 80, 150, 25);
        frame.getContentPane().add(lblGenerations);

        textFieldGenerations = new JTextField();
        textFieldGenerations.setBounds(160, 80, 120, 25);
        frame.getContentPane().add(textFieldGenerations);
        textFieldGenerations.setColumns(10);

        JButton btnRun = new JButton("Run GA");
        btnRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int numberOfCities = Integer.parseInt(textFieldCities.getText());
                int populationSize = Integer.parseInt(textFieldPopSize.getText());
                int generations = Integer.parseInt(textFieldGenerations.getText());

                TSP_GA.runGA(numberOfCities, populationSize, generations);
            }
        });
        btnRun.setBounds(160, 120, 120, 25);
        frame.getContentPane().add(btnRun);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TSP_GUI window = new TSP_GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
