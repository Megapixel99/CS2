/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceshipclasses;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author 384000346
 */
public class Missle {
    private int missleXPos;
    private int missleYPos;
    
    Missle(boolean rocketXDirRight)
    {
        boolean rocketXDirRight_ = rocketXDirRight;
        missleXPos = SpaceShipClasses.rocketXPos;
        missleYPos = SpaceShipClasses.rocketYPos;                
    }

    public void move(int moveVal)
    {
        missleXPos += SpaceShipClasses.rocketXSpeed;   
    }
    public void draw(SpaceShipClasses obj,Graphics2D g,Image image)
    {
        drawMissles(obj, g, image,Window.getX(missleXPos),Window.getYNormal(missleYPos),0,1,1); 
    }    
////////////////////////////////////////////////////////////////////////////
    public void drawMissles(SpaceShipClasses obj,Graphics2D g,Image image,int xpos,int ypos,double rot,double xscale,
            double yscale) {
        int width = image.getWidth(obj);
        int height = image.getHeight(obj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.drawImage(image,-width/2,-height/2,
        width,height,obj);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    } 
}
