/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.ImageProcessor;
import static controller.ImageProcessor.refImgFile;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author aminda
 */
public class ImageFile {
    
    private static String imagePath;
    BufferedImage  image;
    int width;
    int height;

    /**
     * @return the imagePath
     */
    public static String getImagePath() {
        return imagePath;
    }

    /**
     * @param aImagePath the imagePath to set
     */
    public static void setImagePath(String aImagePath) {
        imagePath = aImagePath;
    }
    public void originalImage(){
        File input = new File(imagePath);
        try {
            image = ImageIO.read(input);
//            File ouptut = new File("original.jpg");
//            ImageIO.write(image, "jpg", ouptut);
            File current = new File("current.jpg");
            ImageIO.write(image, "jpg", current);
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        showImage();

    }
    public void showImage(){
        System.out.println("before");
        System.out.println(System.getProperty("java.class.path"));
//        System.out.println("");
//        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("aaa.jpg"));
//        Image img =icon.getImage().getScaledInstance(100, 200, Image.SCALE_SMOOTH);
//        ImageProcessor.refCntrlPnel.getImage().setIcon(icon);
//        ImageProcessor.refCntrlPnel.getImage().setIcon(new ImageIcon("aaa.jpg"));
        ImageProcessor.refCntrlPnel.showImage();
        System.out.println("after");
    }
    
    public void greyScaleImage(){
        try {
         File input = new File("current.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
         File ouptut = new File("current.jpg");
         ImageIO.write(image, "jpg", ouptut);
         
        } catch (Exception e) {}
        showImage();
    }
    
}
