package audio;
import java.io.IOException;

public class Audio {
    static Sound starwarsSound = null;
    static Sound ouchSound = null;

    public static void main(String[] args) {
        boolean keepLooping = true;
        while (keepLooping)
        {
            System.out.println("enter s for starwars");
            System.out.println("      o for ouch");
            System.out.println("      b for break out of starwars");
            System.out.println("      e exit program");
            String str = read();
            
            if (str.charAt(0) == 's')
            {
                if (starwarsSound != null)
                    starwarsSound.stopPlaying = true;
                starwarsSound = new Sound("starwars.wav"); 
            }   
            if (str.charAt(0) == 'o')
            {
                if (ouchSound != null)
                    ouchSound.stopPlaying = true;
                ouchSound = new Sound("ouch.wav"); 
            }   
            else if (str.charAt(0) == 'b')
            {
                if (starwarsSound != null)
                    starwarsSound.stopPlaying = true;
            }
            else if (str.charAt(0) == 'e')
            {
                if (starwarsSound != null)
                    starwarsSound.stopPlaying = true;
                if (ouchSound != null)
                    ouchSound.stopPlaying = true;
                keepLooping = false;
            }            
            
        }
    }
///////////////////////////////////////////////////
    public static String read()
    {
        byte [] buffer = new byte[10];
        try
        {
            int numBytes = System.in.read(buffer);
        }
        catch(IOException e)
        {
            System.out.print("Error: " + e);
            System.exit(1);
        }
        String str = new String(buffer);
        int ball = 5;
        return (str);
    }

    public static void write(String str)
    {
        System.out.print(str);
    }    
}
