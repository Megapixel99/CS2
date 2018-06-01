/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceshipclasses;

import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;


class Star {
    Image Star;
    private int starXPos;
    private int starYPos;
    
    Star()
    {
        Star = Toolkit.getDefaultToolkit().getImage("./starAnim.GIF");
        starXPos = (int)(Math.random() * Window.getWidth2());
        starYPos = (int)(Math.random() * Window.getHeight2());
    }
    public void draw(Graphics2D g)
    {
//        drawStar(obj,g,image, Window.getX(starXPos), Window.getYNormal(starYPos), 0.0, 1.0, 1.0);
        drawStar(g, Window.getX(starXPos),Window.getYNormal(starYPos),0.0,1.0,1.0 );
    }
    public void move(int moveVal)
    {
        starXPos+= SpaceShipClasses.rocketXSpeed;
        if (starXPos <= 30)
        {
            starXPos = Window.getWidth2();
            starYPos = (int)(Math.random() * Window.getHeight2());
        }
        else if (starXPos >= Window.getWidth2() + 30)
        {
            starXPos = 0;
            starYPos = (int)(Math.random() * Window.getHeight2());
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void drawStar(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale)
    {
//        int width = image.getWidth(obj);
//        int height = image.getHeight(obj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
//        g.drawImage(Star,-width/2,-height/2,
//        width,height,this);
        g.setColor(Color.yellow);
        g.fillOval(-10,-10,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }    

    }
