package com.ayesh.choufasman.UI;

import com.ayesh.choufasman.ProteinSecondaryStructure;

import javax.swing.*;
import java.awt.*;

public class ChouFasmanSwingApp {
    private ProteinSecondaryStructure structurePredictor = new ProteinSecondaryStructure();

    public void createAndShowGUI() {
        try {
            // Set the Windows look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the frame
        JFrame frame = new JFrame("Chou-Fasman Protein Structure Predictor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create a panel to hold components with a vertical BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Create a title label
        JLabel titleLabel = new JLabel("Protein Structure Prediction", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(50, 50, 50)); // Dark text
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the title
        panel.add(titleLabel);

        // Add some spacing between components
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create input field with a placeholder
        JTextArea inputArea = new JTextArea(5, 30);
        inputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputArea.setText("Enter amino acid sequence (e.g., ACDEFGHIKLMNP...)");
        inputArea.setForeground(Color.GRAY);

        // Clear placeholder text when user starts typing
        inputArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (inputArea.getText().equals("Enter amino acid sequence (e.g., ACDEFGHIKLMNP...)")) {
                    inputArea.setText("");
                    inputArea.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (inputArea.getText().isEmpty()) {
                    inputArea.setText("Enter amino acid sequence (e.g., ACDEFGHIKLMNP...)");
                    inputArea.setForeground(Color.GRAY);
                }
            }
        });
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        inputScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(inputScrollPane);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create result area with a placeholder
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(245, 245, 245)); // Slightly darker for result area
        resultArea.setText("Result will be displayed here...");
        resultArea.setForeground(Color.GRAY);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(resultScrollPane);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create a button to process input
        JButton predictButton = new JButton("Predict Structure");
        predictButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        predictButton.setBackground(new Color(0, 120, 215)); // Windows blue button color
        predictButton.setForeground(Color.BLACK);
        predictButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button action listener
        predictButton.addActionListener(e -> {
            String sequence = inputArea.getText().toUpperCase().trim();
            if (isValidSequence(sequence)) {
                String result = structurePredictor.predict(sequence);
                resultArea.setForeground(Color.BLACK); // Reset result color to normal
                resultArea.setText(result);
            } else {
                resultArea.setForeground(Color.RED); // Show error message in red
                resultArea.setText("Invalid amino acid sequence. Please enter valid amino acids.");
            }
        });

        // Add button to the panel
        panel.add(predictButton);

        // Add panel to frame and make it visible
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Check if the input sequence contains valid amino acids
    private boolean isValidSequence(String sequence) {
        return sequence.matches("[ACDEFGHIKLMNPQRSTVWY]+");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChouFasmanSwingApp().createAndShowGUI();
        });
    }
}
