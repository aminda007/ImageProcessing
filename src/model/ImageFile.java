/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.ImageProcessor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        try {
            File input = new File(imagePath);
            image = ImageIO.read(input);
            File current = new File("current.jpg");
            ImageIO.write(image, "jpg", current);
            showImage();
        } catch (IOException|NullPointerException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saveOriginal(){
        try {
            File input = new File(imagePath);
            image = ImageIO.read(input);
            File current = new File("original.jpg");
            ImageIO.write(image, "jpg", current);
//            showImage();
        } catch (IOException|NullPointerException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void showImage(){
        System.out.println("before");
        System.out.println(System.getProperty("java.class.path"));
        try {
            ImageProcessor.refCntrlPnel.showImage();
        } catch (Exception e) {
            System.out.print(e);
        }       
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
    
    public void brightness(int change){
        int red=255;
         int green=255;
         int blue=255;
        try {
         File input = new File("original.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight(); 
         
         for(int i=0; i<height; i++){        
            for(int j=0; j<width; j++){            
               Color c = new Color(image.getRGB(j, i));
                if (change<0){
                    
                    red = (int)((c.getRed()) * (change/50.0) + c.getRed());
                    green = (int)( (c.getGreen()) * (change/50.0) + c.getGreen());
                    blue = (int)( (c.getBlue()) * (change/50.0) + c.getGreen());
                   
                    if(red<0){
                        red=0;
                    }              
                    if(green<0){
                        green=0;
                    }           
                    if(blue<0){
                        blue=0;
                    }
                    
                }
                else if (change>0){
                    
                    red = (int)((255-c.getRed()) * (change/50.0) + c.getRed());
                    green = (int)( (255-c.getGreen()) * (change/50.0) + c.getGreen());
                    blue = (int)( (255-c.getBlue()) * (change/50.0) + c.getGreen());
                   
                    if(red>255){
                        red=255;
                    }              
                    if(green>255){
                        green=255;
                    }           
                    if(blue>255){
                        blue=255;
                    }
                    
                }else{
                    red = (int)((c.getRed()));
                    green = (int)( (c.getGreen()));
                    blue = (int)( (c.getBlue()));
                }
               
               Color newColor = new Color(red,green,blue);              
               image.setRGB(j,i,newColor.getRGB());
            }
         }  
         if(change<0){
             System.out.println("brgtness decrese");
         }else if(change>0){
             System.out.println("brgtness increse");
         }
         System.out.println("loop is over");
         File ouptut = new File("current.jpg");
         ImageIO.write(image, "jpg", ouptut);    
         showImage();
        } catch (Exception e) {
            System.out.println(e+ "exception ocurred" +" "+red+" "+green+" "+blue);
        }
        
    } 
    
    public void contrast(int change){
        int red=255;
         int green=255;
         int blue=255;
        try {
        File input = new File("original.jpg");
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight(); 
        float factor = (259f * (change + 255)) / (255f * (259 - change));
        for(int i=0; i<height; i++){        
            for(int j=0; j<width; j++){            
               Color c = new Color(image.getRGB(j, i));
                    
                    red = (int)((c.getRed()-128) * factor + 128);
                    green = (int)( (c.getGreen()-128) * factor+ 128);
                    blue = (int)( (c.getBlue()-128) * factor + 128);
                   
                    if(red<0){
                        red=0;
                    }              
                    if(green<0){
                        green=0;
                    }           
                    if(blue<0){
                        blue=0;
                    }
                                      
                    if(red>255){
                        red=255;
                    }              
                    if(green>255){
                        green=255;
                    }           
                    if(blue>255){
                        blue=255;

                }
               
                Color newColor = new Color(red,green,blue);              
                image.setRGB(j,i,newColor.getRGB());
            }
        }  
        if(change<0){
            System.out.println("contrast decrese");
        }else if(change>0){
            System.out.println("contrast increse");
        }
        System.out.println("loop is over");
        File ouptut = new File("current.jpg");
        ImageIO.write(image, "jpg", ouptut);    
        showImage();
        } catch (Exception e) {
            System.out.println(e+ "exception ocurred" +" "+red+" "+green+" "+blue);
        }
        
    }
    public void medianFilter(){
        int i=1;
        int j=1;
        try {
        File input = new File("original.jpg");
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight(); 
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
        
        for(i=2; i<height-3; i++){        
            for(j=2; j<width-3; j++){            
//               Color c = new Color(image.getRGB(j, i));
                System.out.println(" "+i+" "+j);
                System.out.println("up");
                pixel[0]=new Color(image.getRGB(i-1,j-1));
                System.out.println("down1");
                pixel[1]=new Color(image.getRGB(i-1,j));
                System.out.println("down2");
                pixel[2]=new Color(image.getRGB(i-1,j+1));
                System.out.println("down3");
                pixel[3]=new Color(image.getRGB(i,j+1));
                System.out.println("middle");
                pixel[4]=new Color(image.getRGB(i+1,j+1));
                pixel[5]=new Color(image.getRGB(i+1,j));
                pixel[6]=new Color(image.getRGB(i+1,j-1));
                pixel[7]=new Color(image.getRGB(i,j-1));
                pixel[8]=new Color(image.getRGB(i,j));
                for(int k=0;k<9;k++){
//                    System.out.println(k);
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();

                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                Color newColor = new Color(R[4],B[4],G[4]);              
                image.setRGB(j,i,newColor.getRGB());
            }
        }  
        System.out.println("loop is over");
        File ouptut = new File("current.jpg");
        ImageIO.write(image, "jpg", ouptut);    
        showImage();
        } catch (Exception e) {
            System.out.println(e+ "exception ocurred" +" "+i+" "+j+" ");
        }
        
    }
    
}
