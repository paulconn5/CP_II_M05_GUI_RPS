import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    //Labels, Icons, TextAreas, Buttons
    ImageIcon rockIcon = new ImageIcon("rock.png");
    ImageIcon paperIcon = new ImageIcon("paper.png");
    ImageIcon scissorsIcon = new ImageIcon("scissors.png");
    ImageIcon quitIcon = new ImageIcon("quit.png");

    //Transform Rock to a set size
    Image newRockImage = rockIcon.getImage().getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    ImageIcon rockImage = new ImageIcon(newRockImage);

    //Transform Paper to a set size
    Image newPaperImage = paperIcon.getImage().getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    ImageIcon paperImage = new ImageIcon(newPaperImage);  // transform it back

    //Transform Scissors to a set size
    Image newScissorsImage = scissorsIcon.getImage().getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    ImageIcon scissorsImage = new ImageIcon(newScissorsImage);  // transform it back

    //Transform quit to a set size
    Image newQuitImage = quitIcon.getImage().getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    ImageIcon quitImage = new ImageIcon(newQuitImage);  // transform it back

    JLabel titleLabel;
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel resultPanel;
    JPanel labelPanel;
    JTextArea resultOutput;

    int humanWins = 0;
    int botWins = 0;
    int ties = 0;

    //The Frame
    public RockPaperScissorsFrame() {
        super("Rock Paper Scissors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        createResultPanel();
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        createLabelPanel();
        mainPanel.add(labelPanel, BorderLayout.SOUTH);


        add(mainPanel);
        //Set Screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width, height);
        setVisible(true);
    }

    // Top panel (4 buttons: Rock, Paper, Scissors, Quit)
    private void createButtonPanel() {
        buttonPanel = new JPanel();
        JButton rockButton = new JButton(rockImage);
        buttonPanel.add(rockButton);
        JButton paperButton = new JButton(paperImage);
        buttonPanel.add(paperButton);
        JButton scissorsButton = new JButton(scissorsImage);
        buttonPanel.add(scissorsButton);
        JButton quitButton = new JButton(quitImage);
        buttonPanel.add(quitButton);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //ACTION LISTENERS

        quitButton.addActionListener(( ActionEvent ae) -> System.exit(0));

        rockButton.addActionListener(( ActionEvent ae) -> RunGame("R"));

        paperButton.addActionListener(( ActionEvent ae) -> RunGame("P"));

        scissorsButton.addActionListener(( ActionEvent ae) -> RunGame("S"));

    }
    // Middle Panel (Text Area That states output of RPS game)
    private void createResultPanel() {
        resultPanel = new JPanel();
        resultOutput = new JTextArea(20, 60);
        resultOutput.setEditable(false);
        resultOutput.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(resultOutput);
        resultPanel.add(scrollPane);
    }

    private void createLabelPanel() {
        labelPanel = new JPanel();
        titleLabel = new JLabel("Rock Paper Scissors", JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        labelPanel.add(titleLabel);
    }
    //Game Logic
    private String botAnswer() {
        Random rand = new Random();
        int botAnswer = rand.nextInt(3);

        return switch (botAnswer) {
            case 0 -> "R";
            case 1 -> "P";
            case 2 -> "S";
            default -> // Should not be possible
                    "Error";
        };
    }

    private void RunGame(String p1) {
        String p2 = botAnswer();
        if (p1.equalsIgnoreCase("R")) {
            if (p2.equalsIgnoreCase("S")) {
                resultOutput.append("You win with rock beating scissors!\n");
                humanWins += 1;
            } else if (p2.equalsIgnoreCase("P")) {
                resultOutput.append("Bot wins with paper beating rock!\n");
                botWins += 1;
            } else {
                resultOutput.append("It's a tie!\n");
                ties += 1;
            }
        } else if (p1.equalsIgnoreCase("P")) {
            if (p2.equalsIgnoreCase("S")) {
                resultOutput.append("Bot wins with scissors beating paper!\n");
                botWins += 1;
            } else if (p2.equalsIgnoreCase("R")) {
                resultOutput.append("You win with paper beating rock!\n");
                humanWins += 1;
            } else {
                resultOutput.append("It's a tie!\n");
                ties += 1;
            }
        } else if (p1.equalsIgnoreCase("S")) {
            if (p2.equalsIgnoreCase("P")) {
                resultOutput.append("You win with scissors beating paper!\n");
                humanWins += 1;
            } else if (p2.equalsIgnoreCase("R")) {
                resultOutput.append("Bot wins with rock beating scissors!\n");
                botWins += 1;
            } else {
                resultOutput.append("It's a tie!\n");
                ties += 1;
            }
        } else {
            resultOutput.append("It's a tie!\n");
            ties += 1;
        }

        titleLabel.setText("Human: " + humanWins + " Bot: " + botWins + " Ties: " + ties);
    }


}
