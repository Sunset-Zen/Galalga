package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable, KeyListener
{   // Attributes
    public static final int WIDTH = 860;
    public static final int HEIGHT = 670;

    public boolean running = true;
    private Thread gameThread;

    private PlayerLaser plaser;
    private MovingB mb;
    private EnemyHandler eh;
    private PlayerHandler ph1;
    private Helathbar bar;


    // Constructor
    public Game()
    {   canvasSetUp();
        init();
        System.out.print(ph1.playerLinkedList().size()); // Debug Tracker
    }

    // Methods
    public void draw() // Draws Game
    {   // Initialize Drawing Tools
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null)
        {   this.createBufferStrategy(5);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        Graphics2D g3d = (Graphics2D) buffer.getDrawGraphics();

        // Draw Background
        drawBackground(g);

        // Draw Moving Background
        mb.draw(g3d);

        // Draw Enemy Handler
        eh.drawEnemy(g3d);
        eh.drawBoss(g3d);

        // Draw Player Laser
        plaser.draw(g3d);

        // Draw Player && Score
        //ph.draw(g3d);
        ph1.draw(g3d);

        // Draw Health Bar

        // Dispose -> Actually Draws (Puts Things On Screen)
        g.dispose();
        g3d.dispose();
        buffer.show();

    }
    public void update() // Updates Game
    {   // Update Player
        // ph.update();
        ph1.update();

        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null)
        {   this.createBufferStrategy(5);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        Graphics2D g3d = (Graphics2D) buffer.getDrawGraphics();

        bar.update(ph1.getPlayer(), g3d);

        // Update Player Laser
        plaser.update();

        // Update Background
        mb.update();

        // Update Enemies && Boss
        eh.updateEnemy();
        eh.updateBoss();
    }

    @Override
    public void run() // Game Loop -> (Runs Game)
    {   // Game Loop
        final int MAX_fps = 60;
        final int MAX_ups = 60;

        final double F_Optimal = 1000000000/ MAX_fps;
        final double U_Optimal = 1000000000/ MAX_ups;

        double fDeltaTime = 0;
        double uDeltaTime = 0;

        long startTime = System.nanoTime();


        while(running)
        {   // Calaculate difference in time
            long currentTime = System.nanoTime();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            if (uDeltaTime >= U_Optimal)
            {   update();
                uDeltaTime -= U_Optimal;
            }
            if (fDeltaTime >= F_Optimal)
            {   draw();
                fDeltaTime -= F_Optimal;
            }
        }
    }
    public void start() // Start Game Thread
    {   gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    //    public void stop() // Stops Game Thread
//    {    try {
//            gameThread.join();
//            running = false;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void init() // Instantiates Game Objects
    {   // Initialize Player
        ph1 = new PlayerHandler();

        // Initialize Laser
        plaser = new PlayerLaser(ph1.getPlayer().x, ph1.getPlayer().y+9);

        // Initialize Enemy Handler
        eh = new EnemyHandler();

        // Initialize MovingB
        mb = new MovingB(0, 0);

        // Initialize Health Bar
        bar = new Helathbar(660, 117);
    }
    public void canvasSetUp() // Sets Up Base Canvas
    {   this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    public void drawBackground(Graphics g) // Draws Canvas Background
    {   // space space
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        Image bimg = new ImageIcon("src/com/company/images/space_edited (1).png").getImage();
        g.drawImage(bimg,0, 0, this);
    }
    //    public Player getPlayer1()
//    {   return player1;
//    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) // Player Input
    {   switch (e.getKeyCode())
        {   case 37: // Left
                //ph.player.velX = -9;
                //ph.getPlayer().velX = -9;
                ph1.getPlayer().velX = -9;
                plaser.laserMovement(ph1.getPlayer());
                break;
            case 39: // Right
                //ph.player.velX = 9;
                //ph.getPlayer().velX = 9;
                ph1.getPlayer().velX = 9;
                plaser.laserMovement(ph1.getPlayer());
                break;
            case 38: // Up
                //ph.player.velX = 0;
                //ph.getPlayer().velX = 0;
                ph1.getPlayer().velX = 0;
                plaser.laserMovement(ph1.getPlayer());
                break;
            case 32: // Space -> (Shoot)
                plaser.velY = -70;
                plaser.velX = 0;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
    }
}
