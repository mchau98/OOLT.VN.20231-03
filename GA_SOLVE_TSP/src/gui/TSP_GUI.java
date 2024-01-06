package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import tsp.City;
import tsp.GA;
import tsp.Population;
import tsp.TSP_GA;
import tsp.TourManager;

public class TSP_GUI {

    private JFrame frame;
    private JTextField textFieldCities;
    private JTextField textFieldPopSize;
    private JTextField textFieldGenerations;

    private Population pop;
    private int numberOfCities;
    private int populationSize;
    private int generations;

    public TSP_GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 700);
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

        // Add labels for initial and final distances
        JLabel lblInitialDistance = new JLabel("Initial Distance:");
        lblInitialDistance.setBounds(10, 600, 150, 25);
        frame.getContentPane().add(lblInitialDistance);

        JLabel lblFinalDistance = new JLabel("Final Distance:");
        lblFinalDistance.setBounds(10, 630, 150, 25);
        frame.getContentPane().add(lblFinalDistance);

        // Add the custom JPanel for cities
        CityPanel cityPanel = new CityPanel();
        cityPanel.setBackground(Color.LIGHT_GRAY);
        cityPanel.setBounds(10, 160, 400, 400); // Adjust the bounds as needed
        frame.getContentPane().add(cityPanel);

        JButton btnRun = new JButton("Run GA");
        btnRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfCities = Integer.parseInt(textFieldCities.getText());
                populationSize = Integer.parseInt(textFieldPopSize.getText());
                generations = Integer.parseInt(textFieldGenerations.getText());
                lblInitialDistance.setText("Initial Distance:" + 0);
                lblFinalDistance.setText("Final Distance:" + 0);

                // Tạo ngẫu nhiên các thành phố
                TourManager.clearCities(); // Xóa các thành phố cũ trước khi thêm mới
                for (int i = 0; i < numberOfCities; i++) {
                    City city = new City();
                    TourManager.addCity(city);
                }
                // Khởi tạo quần thể
                pop = new Population(populationSize, true);
                cityPanel.repaint();
                // System.out.println("Initial distance: " + pop.getFittest().getDistance());
                lblInitialDistance.setText("Initial Distance:" + pop.getFittest().getDistance());
                // Refresh the CityPanel to trigger the paintComponent method

                Timer timer = new Timer(10, new ActionListener() {
                    private int currentGeneration = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (currentGeneration < generations) {
                            // Tiến hóa quần thể for one generation
                            pop = GA.evolvePopulation(pop);
                            // Refresh the CityPanel to trigger the paintComponent method
                            cityPanel.repaint();
                            currentGeneration++;
                        } else {
                            // Stop the timer after reaching the specified number of generations
                            ((Timer) e.getSource()).stop();
                            lblFinalDistance.setText("Final Distance:" + pop.getFittest().getDistance());
                        }
                    }
                });

                // Start the timer
                timer.start();

            }
        });

        btnRun.setBounds(50, 120, 80, 25);
        frame.getContentPane().add(btnRun);

        // Add Quit button
        JButton btnQuit = new JButton("Quit");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Terminate the application
            }
        });
        btnQuit.setBounds(280, 120, 100, 25);
        frame.getContentPane().add(btnQuit);

        // Add Help button and JDialog for Help
        JButton btnHelp = new JButton("Help");
        JDialog helpDialog = new JDialog(frame, "Help", Dialog.ModalityType.APPLICATION_MODAL);
        JTextPane helpTextPane = new JTextPane();

        // Set the help information in the JTextPane
        helpTextPane.setText(
                "Traveling Salesman Problem Genetic Algorithm (TSP-GA) Program\n\n"
                        + "1. Number of Cities:"
                        + "\n   This represents the total cities the traveling salesman will visit.\n\n"
                        + "2. Population Size:"
                        + "\n   The population consists of possible tours (solutions to the TSP).\n\n"
                        + "3. Generations:"
                        + "\n   Each generation involves the evolution of the population.\n\n"
                        + "4. Run GA:\n   Click the \"Run GA\" button to start the genetic algorithm."
                        + "\n   The program will generate a random set of cities, create an initial population, and evolve the population over the specified number of generations.\n\n"
                        + "5. Visualization:\n   The program visualizes the cities as red circles on the panel."
                        + "\n   The initial distance of the best tour in the initial population is displayed."
                        + "\n 6. Final Results:\n   Once the specified number of generations is reached, the final distance of the best tour is displayed."
                        + "\n   The program continues to display the final state until you run the algorithm again.\n"
                        + "7. Quit:\n   Click the \"Quit\" button to exit the program.\n\n");

        helpTextPane.setEditable(false);
        helpDialog.add(new JScrollPane(helpTextPane));

        btnHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the help dialog when the Help button is clicked
                helpDialog.setSize(550, 500);
                helpDialog.setLocationRelativeTo(frame);
                helpDialog.setVisible(true);
            }
        });

        btnHelp.setBounds(155, 120, 100, 25);
        frame.getContentPane().add(btnHelp);
    }

    // Add a custom JPanel for drawing cities
    class CityPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw circles for each city
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            if (pop != null) {
                for (int i = 0; i < numberOfCities; i++) {
                    City city = pop.getFittest().getCity(i);
                    // Scale the city coordinates based on the panel dimensions
                    int x = (int) (city.getX() * panelWidth / 500.0);
                    int y = (int) (city.getY() * panelHeight / 500.0);
                    // System.out.println(x);
                    // System.out.println(y);
                    // // System.out.println(panelHeight);
                    // // System.out.println(panelWidth);
                    // // System.out.println("----");
                    g.setColor(Color.RED); // Set the color for the city circles
                    g.fillOval(x, y, 6, 6); // Adjust the size of the circles as needed
                }

                for (int i = 0; i < numberOfCities - 1; i++) {
                    City firstCity = pop.getFittest().getCity(i);
                    City lastCity = pop.getFittest().getCity(i + 1);
                    // Scale the city coordinates based on the panel dimensions
                    int firstX = (int) (firstCity.getX() * panelWidth / 500.0);
                    int firstY = (int) (firstCity.getY() * panelHeight / 500.0);
                    int lastX = (int) (lastCity.getX() * panelWidth / 500.0);
                    int lastY = (int) (lastCity.getY() * panelHeight / 500.0);

                    // Draw the line connecting the last city to the first city
                    g.setColor(Color.BLUE); 
                    g.drawLine(firstX + 3, firstY + 3, lastX + 3, lastY + 3); 
                }
            }

        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TSP_GUI window = new TSP_GUI();
                    window.frame.setTitle("Genertic-algorithm TSP");
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}