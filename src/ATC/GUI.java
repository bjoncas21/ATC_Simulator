package ATC;

import javax.swing.*;
import java.awt.*;

/**
 * an attempt at representing the simulation, unfinished
 */
public class GUI {
    private JFrame frame;
    private JPanel panel, runwayPanel, planePanel;
    private JButton startButton, stopButton;
    private JTextArea logTextArea;
    private JScrollPane scrollPane;

    public GUI() {
        // Initialize the frame
        frame = new JFrame("Air Traffic Control Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600); // Adjusted size to accommodate new components

        // Panel to hold buttons
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));

        // Start and Stop buttons
        startButton = new JButton("Start Simulation");
        stopButton = new JButton("Stop Simulation");

        // Adding buttons to the panel
        panel.add(startButton);
        panel.add(stopButton);

        // Runway panel
        runwayPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        runwayPanel.setBorder(BorderFactory.createTitledBorder("Runways"));
        for (int i = 1; i <= 3; i++) {
            JPanel runway = new JPanel();
            runway.setBackground(Color.gray);
            runway.setBorder(BorderFactory.createLineBorder(Color.black));
            runway.setToolTipText("Runway " + i);
            runwayPanel.add(runway);
        }

        // Plane representation panel
        planePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        planePanel.setBorder(BorderFactory.createTitledBorder("Planes"));
        for (int i = 1; i <= 5; i++) {
            JLabel planeLabel = new JLabel(new ImageIcon("path_to_plane_icon")); // Use an icon or draw a shape
            planePanel.add(planeLabel);
        }

        // Text area for logs with a scroll pane
        logTextArea = new JTextArea(10, 30);
        logTextArea.setEditable(false);
        scrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add components to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(runwayPanel, BorderLayout.SOUTH);
        frame.add(planePanel, BorderLayout.EAST);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listeners for buttons
        startButton.addActionListener(e -> startSimulation());
        stopButton.addActionListener(e -> stopSimulation());

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startSimulation() {
        logTextArea.append("Simulation started.\n");
        // Start simulation logic
    }

    private void stopSimulation() {
        logTextArea.append("Simulation stopped.\n");
        // Stop simulation logic
    }
}
