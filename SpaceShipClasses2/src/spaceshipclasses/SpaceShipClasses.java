/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceshipclasses;

import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

import java.util.ArrayList;

public class SpaceShipClasses extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;

    Image outerSpaceImage;
    Image MissleImage;
    Image MissleImage2;

//variables for rocket.
    Image rocketImage;
    Image rocketAnimImage;
    static int rocketXPos;
    static int rocketYPos;
    static int rocketXSpeed;
    int rocketYSpeed;
    boolean rocketXDirRight;
    int BackgroundXPos1;
    int BackgroundSpeed;

    int numStars = 10;
    ArrayList<Star> stars = new ArrayList<Star>();
    ArrayList<Star> missles = new ArrayList<Star>();
    Image starImage;

    static SpaceShipClasses frame;
    public static void main(String[] args) {
        frame = new SpaceShipClasses();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public SpaceShipClasses() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
//                    missles.add(new Missle(rocketXDirRight));

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();

                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {

      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                    rocketYSpeed++;
                } else if (e.VK_DOWN == e.getKeyCode()) {
                    rocketYSpeed--;
                } else if (e.VK_LEFT == e.getKeyCode()) {
                    if (rocketXSpeed > -20)
                        rocketXSpeed--;
                    if (rocketXSpeed < 0)
                        rocketXDirRight = false;
                     
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                    if (rocketXSpeed < 20)
                        rocketXSpeed++;
                    if (rocketXSpeed > 0)
                        rocketXDirRight = true;
                    
                } else if (e.VK_INSERT == e.getKeyCode()) {                 
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }



////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.black);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
            if (rocketXSpeed > 0)
            {
            BackgroundXPos1-=rocketXSpeed/8;
            }
            if (rocketXSpeed < 0)
            {
            BackgroundXPos1 -=rocketXSpeed/8;
            }
            
            g.drawImage(outerSpaceImage,BackgroundXPos1,Window.getY(0),
                1920,Window.getHeight2(),this);
            
            for (int j = 0; j < 200 ; j++)
            {
                g.drawImage(outerSpaceImage,BackgroundXPos1 + 1920 * j,Window.getY(0),
                1920,Window.getHeight2(),this);
                
                g.drawImage(outerSpaceImage,BackgroundXPos1 - 1920 *j,Window.getY(0),
                1920,Window.getHeight2(),this);
            }
        

        if (rocketXSpeed != 0)
        {
        if (rocketXDirRight)
            drawRocket(rocketAnimImage,Window.getX(rocketXPos),Window.getYNormal(rocketYPos),
            0.0,2.0,2.0 );
        else
            drawRocket(rocketAnimImage,Window.getX(rocketXPos),Window.getYNormal(rocketYPos),
            0.0,-2.0,2.0 );
        }
        else
        {
        if (rocketXDirRight)
            drawRocket(rocketImage,Window.getX(rocketXPos),Window.getYNormal(rocketYPos),
            0.0,2.0,2.0 );
        else
            drawRocket(rocketImage,Window.getX(rocketXPos),Window.getYNormal(rocketYPos),
            0.0,-2.0,2.0 );    
        }
            

        for (int i=0;i<stars.size();i++)
            stars.get(i).draw(this,g,starImage);
        for (int i=0;i<missles.size();i++)
            missles.get(i).draw(this,g,MissleImage2);
        
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawCircle(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.fillOval(-10,-10,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }   
////////////////////////////////////////////////////////////////////////////
    public void drawRocket(Image image,int xpos,int ypos,double rot,double xscale,
            double yscale) {
        int width = image.getWidth(this);
        int height = image.getHeight(this);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.drawImage(image,-width/2,-height/2,
        width,height,this);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.04;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {

        rocketXSpeed = 0;
        rocketYSpeed = 0;
        rocketXDirRight = true;
//init the location of the rocket to the center.
        rocketXPos = Window.getWidth2()/2;
        rocketYPos = Window.getHeight2()/2;

        for (int i=0;i<numStars;i++)
            stars.add(new Star());
        for (int i=0;i<missles.size();i++)
            missles.add(new Star());
        
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            outerSpaceImage = Toolkit.getDefaultToolkit().getImage("./outerSpace4.jpg");
            rocketImage = Toolkit.getDefaultToolkit().getImage("./rocket.GIF");
            rocketAnimImage = Toolkit.getDefaultToolkit().getImage("./animRocket.GIF");
            starImage = Toolkit.getDefaultToolkit().getImage("./starAnim.GIF");
            MissleImage = Toolkit.getDefaultToolkit().getImage("./missle.GIF");
            MissleImage2 = Toolkit.getDefaultToolkit().getImage("./chainsaw.GIF");
            reset();
                             
        }
          
        if (rocketYSpeed > 0)
        {
            if (rocketYPos < Window.getHeight2() - 30)
                rocketYPos += rocketYSpeed;
            else
            {
                rocketYSpeed = -rocketYSpeed/3;
                rocketYPos = Window.getHeight2();
            }
        }    
        else if (rocketYSpeed < 0)
        {
            if (rocketYPos > 30)
                rocketYPos += rocketYSpeed;
            else
            {
                rocketYSpeed = -rocketYSpeed/3;
                rocketYPos = 0;
            }
        } 
        else
        {
            rocketYSpeed = 0;
        }
        
        for (int i=0;i<stars.size();i++)
            stars.get(i).move(-rocketXSpeed);
     
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}


