package demo.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FractalJPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private int level;
    private Color color;
    private final static int WIDTH = 600;
    private final static int HEIGHT = 700;
    private final static int length = 450;
    
    public FractalJPanel(int level) {
        setColor(Color.BLUE);
        setLevel(level);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    private double getAngle(int x1, int y1, int x2, int y2) {
        return Math.atan2((y1 - y2), (x2 - x1)) + Math.PI / 3;
    }
    
    public void drawFractal(int level, int x1, int y1, int x2, int y2, Graphics g) {
        if (level == 0) {
            g.drawLine(x1, y1, x2, y2);
        } else {
            int xa = (int) Math.round(x1 + (x2 - x1) / 3.0);
            int ya = (int) Math.round(y1 + (y2 - y1) / 3.0);
            int xb = (int) Math.round((xa + x2) / 2.0);
            int yb = (int) Math.round((ya + y2) / 2.0);
            
            int l = (int) (length / Math.pow(3, (this.level - level + 1)));
            double angle = getAngle(x1, y1, x2, y2);
            int xc = (int) Math.round(xa + l * Math.cos(angle));
            int yc = (int) Math.round(ya - l * Math.sin(angle));
            drawFractal(level - 1, x1, y1, xa, ya, g);
            drawFractal(level - 1, xa, ya, xc, yc, g);
            drawFractal(level - 1, xc, yc, xb, yb, g);
            drawFractal(level - 1, xb, yb, x2, y2, g);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(color);
        drawFractal(level, 50, 450, 275, 60, g);
        drawFractal(level, 275, 60, 500, 450, g);
        drawFractal(level, 500, 450, 50, 450, g);
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
}