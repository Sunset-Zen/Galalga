package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerHandler
{   // Attributes
    Player ptemp;
    public static LinkedList<Player> playerList = new LinkedList<>();

    // Constructor
    public PlayerHandler()
    {   System.out.println("Before I Add player to List: " + playerList.size());
        addPlayer(new Player(300, 550));
        System.out.println("After I Add player to List: " + playerList.size());
    }

    // Methods
    public void addPlayer(Player player)
    {   playerList.add(player);
    }
    public void removePlayer(Player player)
    {   playerList.remove(player);
    }
    public void update()
    {   if(playerList.size() != 0)
        {
            for(int i = 0; i < playerList.size(); i++)
            {   ptemp = playerList.get(i);
                ptemp.update();

                if(ptemp.health < 0) // If the player has no health -> delete
                {   removePlayer(ptemp);
                }
            }
        }
    }
    public void draw(Graphics2D g2d)
    {   ptemp = playerList.getFirst();
        ptemp.draw(g2d);
    }

    public LinkedList<Player> playerLinkedList(){
        return playerList;
    }
    public int getY() {
        return playerList.get(0).y;
    }
    public int getX() {
        return playerList.get(0).x;
    }
    public int getHealth(){return playerList.get(0).health;}
    public int getScore(){return playerList.get(0).score;}
    public int getVelocity(){return playerList.get(0).velX;}
    public Rectangle getPlayerBounds(){
        return playerList.get(0).getBounds();
    }
    public Player getPlayer() throws IndexOutOfBoundsException
    {   if(!playerList.isEmpty()){
            return playerList.getFirst();
        }
        return null;
    }
}
