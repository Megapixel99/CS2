
package spaceshipclasses;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

class Star {
    private int starXPos;
    private int starYPos;
    
    Star()
    {
        starXPos = (int)(Math.random() * Window.getWidth2());
        starYPos = (int)(Math.random() * Window.getHeight2());                
    }

    public void move(int moveVal)
    {
        starXPos += moveVal;
        
        if (starXPos < -30)
        {
            starXPos = Window.getWidth2()+30;
            starYPos = (int)(Math.random() * Window.getHeight2());        
        }
        else if (starXPos > Window.getWidth2() + 30)
        {
            starXPos = -30;
            starYPos = (int)(Math.random() * Window.getHeight2());        
        }        
    }
    public void draw(SpaceShipClasses obj,Graphics2D g,Image image)
    {
        drawStar(obj, g, image,Window.getX(starXPos),Window.getYNormal(starYPos),0,1,1); 
    }    
////////////////////////////////////////////////////////////////////////////
    public void drawStar(SpaceShipClasses obj,Graphics2D g,Image image,int xpos,int ypos,double rot,double xscale,
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
