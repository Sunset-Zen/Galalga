package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerLaser
{   // Attributes
    protected PlayerHandler ph = new PlayerHandler();
    private LinkedList<Enemy> el = EnemyHandler.getEnemyBounds();
    private ArrayList<Boss> bl = EnemyHandler.getBossBounds();
    protected int x;
    protected int y;
    protected int speed = 10;
    protected int velX = 0;
    protected int velY = 0;

    int playerHP = ph.getHealth();
    int playerSCORE = ph.getScore();

    Player ptemp = ph.getPlayer();

    // Constructor
    public PlayerLaser(int x, int y)
    {   this.x = x;
        this.y = y;
    }

    // Methods
    public int getX() // Gets X
    {   return x;
    }
    public int getY() // Gets Y
    {   return y;
    }
    public int getSpeed() // Gets Speed
    {   return speed;
    }
    public int getVelX() // Gets XVelocity
    {   return velX;
    }
    public int getVelY() // Gets YVelocity
    {   return velY;
    }
    public void update() // Updates Laser Movement
    {   y += velY;
        x += velX;

        ptemp = ph.getPlayer();

        if (y < 0) {
            y = ptemp.getY();
            x = ptemp.getX();
            velY = 0;
            laserMovement(ptemp);
        }
        if (x < -30) {   velX = 9;}
        if (x > 550) {   velX = -9;}

        Collision(ptemp);

    }

    public void laserMovement(Player player1) // Matches Velocity With Player Velocity
    {   velX = ph.getPlayer().getVelX();
    }
    public void draw(Graphics2D g2d) // Draws Laser
    {   g2d.drawImage(getImage(), x, y, null);
        g2d.draw(getBounds());
    }
    public Image getImage() // Gets Laser Image
    {   Image pimg = new ImageIcon("src/com/company/images/G5S (128x128).png").getImage();
        return pimg;
    }
    public Rectangle getBounds() // Gets Laser Hit Box
    {   return new Rectangle(x+54, y+55, 20, 20);
    }

    public void Collision(Player player1) throws IndexOutOfBoundsException
    {   for (int i = 0; i < el.size(); i++)
        {   if (getBounds().intersects(el.get(i).getBounds()))
            {   player1.score += 2;
                el.get(i).tookDamage(); /////
                //System.out.println("Collision (Left Laser)");

                this.y = player1.y;
                this.x = player1.x-10;
                velY = 0;
                laserMovement(player1);
            }
            if (el.get(i).health < 0)
            {    player1.score += 505;
            }
            if (el.get(i).getELaserBounds().intersects(player1.getBounds()))
            {   player1.health -= el.get(i).damage;

                el.get(i).eLaserX = el.get(i).x;
                el.get(i).eLaserY = el.get(i).y;
            }
        }
        for (int i = 0; i < bl.size(); i++)
        {  if (getBounds().intersects(bl.get(i).getBounds()))
            {   player1.score += 5;
                bl.get(i).tookDamage();/// player1
                //System.out.println("Boss Hit");

                y = ph.getY();
                x = ph.getX()-10;
                velY = 0;
                laserMovement(player1);

            }
            if (bl.get(i).health < 0)
            {   player1.score += 1010;
            }
        }
    }

}
