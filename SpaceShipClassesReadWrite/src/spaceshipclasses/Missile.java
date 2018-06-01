
package spaceshipclasses;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;


public class Missile {
    private int xPos;
    private int yPos;
    private boolean moveRight;
    Missile(boolean _moveRight,int startXPos,int startYPos)
    {
        xPos = startXPos;
        yPos = startYPos;
        moveRight = _moveRight;
    }
    public int getXPos()
    {
        return(xPos);
    }
    public int getYPos()
    {
        return(yPos);
    }    
    public boolean move()
    {
        if (moveRight)
            xPos+=5;
        else
            xPos-=5;
        
        if (xPos > Window.getWidth2())
            return (true);
        else if (xPos < 0)
            return (true);
        
        return (false);
    }    
    public void draw(SpaceShipClasses obj,Graphics2D g,Image image)
    {
        drawMissle(obj, g, image,Window.getX(xPos),Window.getYNormal(yPos),0,.25,.25); 
    }   
////////////////////////////////////////////////////////////////////////////
    public void drawMissle(SpaceShipClasses obj,Graphics2D g,Image image,int xpos,int ypos,double rot,double xscale,double yscale)
    {
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
