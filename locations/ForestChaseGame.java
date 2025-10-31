package locations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* README
 * Use WASD or arrow keys to control your character through this text-based endless runner
 */

public class ForestChaseGame extends JFrame {
    private int playerLine = 1; //player starts at line 1
    private int playerPosition = 5; //player starts slightly right to the left side of the screen
    private int ghostPosition = 0;
    private List<Obstacle> obstacles = new ArrayList<>();
    private int forestWidth = 30; //wof the forest
    private int forestHeight = 3; //h of the forest (number of lines)
    private int score = 1;
    private int obstacleSpeed = 1; //SET OBSTACLE SPEED HERE
    private int obstacleSpawnRate = 5; //SET OBSTACLE SPAWN RATE HERE
    private int obstacleSpawnCounter = 0; //counts obstacle spawn
    private Timer timer;
    private String escaped;

    public ForestChaseGame() {
        escaped = "WIP";
        setTitle("RUN.RUN.RUN.RUN.RUN.RUN.RUN."); //sets JFrame title
        setSize(1000, 300); //sets JFrame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //if closed, will close
        
        JTextArea textArea = new JTextArea(); //creates a sort of text box in the frame
        textArea.setEditable(false); //this text box cannot be edited
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 40));
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //controls player movements
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && playerPosition > 0) { //player moves left if space allows
                    playerPosition--;
                } else if (key == KeyEvent.VK_RIGHT && playerPosition < forestWidth - 1) { //player moves right if space allows
                    playerPosition++;
                } else if (key == KeyEvent.VK_UP && playerLine > 0) { //player moves up if space allows
                    playerLine--;
                } else if (key == KeyEvent.VK_DOWN && playerLine < forestHeight - 1) { //player moves down if space allows
                    playerLine++;
                }
            }
        });

        textArea.setFocusable(true); //allows text area to receive key events
        JOptionPane.showMessageDialog(this, "The aura that has manifested is demonic on a level you've never seen. Run. Use your arrow keys.");
        runGameLoop(textArea);
        setVisible(true); //shows window
    }

    private void runGameLoop(JTextArea textArea) {
        timer = new Timer((1/score)*70, new ActionListener() { //SET GAME SPEED HERE
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateGame()) {
                    timer.stop();
                    escaped = "true";
                    JOptionPane.showMessageDialog(ForestChaseGame.this, "You escaped its grasp. For now. Close this tab.");
                    
                } else if (checkCollisions()) {
                    escaped = "false";
                    timer.stop();
                    JOptionPane.showMessageDialog(ForestChaseGame.this, "It caught up to you. You weren't prepared.");
                } else {
                    textArea.setText(renderGame());
                }
            }
        });
        timer.start();
    }

    public String isEscaped() {
        return escaped;
    }

    private boolean updateGame() {
        //player movement controlled by key events (up/down/right/left)
        moveGhost();
        moveObstacles();
        generateObstacles();
        score++;
        return gotAway();
    }

    private void moveGhost() {
        ghostPosition = (int)(score/10 * .5); // Ghost always stays on the far left side of the screen, moving up closer tho
    }

    private void moveObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.setPosition(obstacle.getPosition() - obstacleSpeed);
        }
        obstacles.removeIf(obstacle -> obstacle.getPosition() < 0); //remove obstacles that go off-screen
    }

    private void generateObstacles() {
        obstacleSpawnCounter++;
        if (obstacleSpawnCounter >= obstacleSpawnRate) {
            Random rand = new Random();
            int line = rand.nextInt(forestHeight); // random line
            obstacles.add(new Obstacle(line, forestWidth - 1)); // add obstacle at the end of a random line
            obstacleSpawnCounter = 0;
        }
    }

    private boolean checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getLine() == playerLine && obstacle.getPosition() == playerPosition) {
                return true;
            }
            if (playerPosition == ghostPosition) {
                return true;
            }
        }
        return false;
    }

    private String renderGame() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < forestHeight; i++) {
            for (int j = 0; j < forestWidth; j++) {
                if (i == playerLine && j == playerPosition) {
                    sb.append("♰");
                } else if (i == playerLine && j == ghostPosition) {
                    sb.append("🕱"); //SET GHOST SPRITE HERE (compliant with UTF-8)
                } else {
                    boolean isObstacle = false;
                    for (Obstacle obstacle : obstacles) {
                        if (obstacle.getLine() == i && obstacle.getPosition() == j) {
                            if (obstacle.getIsTree())
                                sb.append("🌲"); //SET TREE SPRITE HERE (compliant with UTF-8)
                            else
                                sb.append("🌳"); //SET SHRUB SPRITE HERE (compliant with UTF-8)
                            isObstacle = true;
                            break;
                        }
                    }
                    if (!isObstacle) {
                        sb.append("-");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean gotAway() {
        return score >= 300;
    }
}

//a class for an obstacle
class Obstacle {
    private int line;
    private int position;
    private boolean isTree;

    public Obstacle(int line, int position) {
        this.line = line;
        this.position = position;
        this.isTree = Math.random() > 0.5;
    }

    //getters and setters
    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsTree() {
        return isTree;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
