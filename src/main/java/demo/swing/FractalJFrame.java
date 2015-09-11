package demo.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FractalJFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private final static int WIDTH = 600;
    private final static int HEIGHT = 700;
    private final static int MIN_LEVEL = 0;
    private Color color = Color.BLUE;
    
    private JButton increaseLevelJButton;
    private JButton decreaseLevelJButton;
    private JButton setColorJButton;
    private JPanel mainJPanel;
    private JPanel controlJPanel;
    private FractalJPanel drawSpace;
    private JLabel levelJLabel;
    
    public FractalJFrame() {
        super("Koch Snowflake");
        
        controlJPanel = new JPanel();
        setColorJButton = new JButton("Color");
        increaseLevelJButton = new JButton("Increase Level");
        decreaseLevelJButton = new JButton("Decrease Level");
        controlJPanel.add(setColorJButton);
        controlJPanel.add(increaseLevelJButton);
        controlJPanel.add(decreaseLevelJButton);
        
        setColorJButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                color = JColorChooser.showDialog(FractalJFrame.this, "Choose a color", color);
                if (color == null) {
                    color = Color.BLUE;
                }
                
                drawSpace.setColor(color);
            }
        });
        
        increaseLevelJButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                int level = drawSpace.getLevel();
                level++;
                
                if (level >= MIN_LEVEL) {
                    levelJLabel.setText("level: " + level);
                    drawSpace.setLevel(level);
                    repaint();
                }
            }
        });
        
        decreaseLevelJButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent event) {
                int level = drawSpace.getLevel();
                level--;
                
                if (level >= MIN_LEVEL) {
                    levelJLabel.setText("level: " + level);
                    drawSpace.setLevel(level);
                    repaint();
                }
            }
        });
        
        levelJLabel = new JLabel("level: 0");
        controlJPanel.add(levelJLabel);
        
        drawSpace = new FractalJPanel(0);
        
        mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.add(controlJPanel, BorderLayout.NORTH);
        mainJPanel.add(drawSpace);
        
        add(mainJPanel);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    
    public static void main(String args[]) {
        FractalJFrame frame = new FractalJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}