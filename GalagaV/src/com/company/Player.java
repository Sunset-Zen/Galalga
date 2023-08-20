package com.company;
import javax.swing.*;
import java.awt.*;


public class Player
{   // Attributes
    protected int x;
    protected int y;
    public int velX = 0;
    public int speed = 9;
    protected int score;
    protected int health;
    protected int damage;
    protected Helathbar bar;

    // Constructor
    public Player(int x, int y)
    {   this.x = x;
        this.y = y;
        this.health = 100;
        this.damage = 25;
        this.score = 0;
    }

    public Player() {

    }

    // Methods
    public int getX() // Gets X Position
    {   return x;
    }
    public int getY() // Gets Y Position
    {   return y;
    }
    public int getVelX() // Gets Velocity
    {   return velX;
    }
    public int getPlayerHealth() // Gets Player Health
    {   return health;
    }
    public Image getPlayerImage() // Gets Player Image
    {   Image pimg = new ImageIcon("src/com/company/images/GalagaD.png").getImage();
        return pimg;
    }
    public Rectangle getBounds() // Get Hit Box
    {   return new Rectangle(x + 40, y + 40, 50, 50); // creates rectangle around player
    }
    public void draw(Graphics2D g2d) // Draws Player and Score
    {   // Draw Player
        g2d.drawImage(getPlayerImage(), x, y, null);
        // g2d.draw(getBounds());

        // Draw Score Title
        String scoreTitle = "Score";
        Font stfont = new Font("Roman", Font.ITALIC, 20);
        g2d.setColor(Color.orange);
        g2d.setFont(stfont);
        g2d.drawString(scoreTitle, Game.WIDTH - 200, 30);

        // Draw Score
        int stringx;
        String scoreText = Integer.toString(score);
        Font font = new Font("MV Boli", Font.BOLD, 25);

        int stringWidth = g2d.getFontMetrics(font).stringWidth(scoreText)+1;
        int padding = 15; // distance between center line and each score

        stringx = (Game.WIDTH * 3/4) + padding;

        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString(scoreText, stringx, 58);

        // Draw Health Title
        String healthTitle = "Health";
        Font hfont = new Font("Roman", Font.ITALIC, 20);
        g2d.setColor(Color.orange);
        g2d.setFont(hfont);
        g2d.drawString(healthTitle, Game.WIDTH - 200, 110);


        // Draw Health Bar
        g2d.setColor(Color.white);
        g2d.drawRect(660, 117, 180, 30);
        g2d.setColor(new Color(95, 30, 0));
        g2d.fillRect(660, 117, 180, 30);
        bar = new Helathbar(660, 117);

        bar.draw(g2d);

        bar.update(this, g2d);

        // Draw Health
        int stringh;
        String healthText = Integer.toString(health);
        Font nfont = new Font("MV Boli", Font.BOLD, 25);

        int padding2 = 15;

        stringh = (Game.WIDTH * 3/4) + padding2;

        g2d.setFont(nfont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(healthText, stringh, 140);

    }
    public void update() throws NullPointerException // Updates Player
    {   x += velX;

        // Collision Mechanics
        if (x < -30) {   velX = 9;}
        if (x > 550) {  velX = -9;}

    }
    public void switchDirection(int direction)
    {   velX = speed * direction;
    }
    public void stop()
    {   velX = 0;
    }


}
