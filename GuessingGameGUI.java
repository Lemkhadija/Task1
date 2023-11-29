import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGameGUI extends JFrame {
    private int randomNumber;
    private JTextField guessField;
    private JTextArea feedbackArea;

    public GuessingGameGUI() {
        // Générer un nombre aléatoire entre 1 et 100
        randomNumber = (int) (Math.random() * 100) + 1;

        // Configuration de la fenêtre
        setTitle("Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des composants
        JLabel guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);

        // Ajout du gestionnaire d'événements au bouton Guess
        guessButton.addActionListener(new GuessButtonListener());

        // Configuration du gestionnaire de disposition
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        // Ajout des composants à la fenêtre
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        // Affichage de la fenêtre
        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Récupérer la supposition de l'utilisateur
                int userGuess = Integer.parseInt(guessField.getText());

                // Comparer avec le nombre généré
                if (userGuess == randomNumber) {
                    feedbackArea.append("Congratulations! You guessed the correct number.\n");
                    guessField.setEnabled(false); // Désactiver le champ de saisie après avoir trouvé la bonne réponse
                } else if (userGuess < randomNumber) {
                    feedbackArea.append("Too low. Try again.\n");
                } else {
                    feedbackArea.append("Too high. Try again.\n");
                }
            } catch (NumberFormatException ex) {
                feedbackArea.append("Invalid input. Please enter a valid number.\n");
            } finally {
                guessField.setText(""); // Effacer le champ de saisie après chaque tentative
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessingGameGUI());
    }
}
