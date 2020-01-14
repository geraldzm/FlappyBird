package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utility {
    public static int clamp(int var, int min, int max){
        if(var >= max)return max;
        else if(var <= min)return min;
        return var;
    }
    public static float clamp(float var, float min, float max){
        if(var >= max)return max;
        else if(var <= min)return min;
        return var;
    }
    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
