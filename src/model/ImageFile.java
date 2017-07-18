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
    
    private static String imagePath="len_top.jpg";
    private BufferedImage  image;
    private BufferedImage newImage;
    private int width;
    private int height;

    public static String getImagePath() {
        return imagePath;
    }

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
    public void scaleUpImage(){
        try {
         File input = new File("current.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         newImage = new BufferedImage((int)(width*2), (int)(height*2), BufferedImage.TYPE_INT_RGB);
         for(int i=0; i<height; i++){        
            for(int j=0; j<width; j++){            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               Color newColor = new Color(red,green,blue);              
               newImage.setRGB(2*j,2*i,newColor.getRGB());
               newImage.setRGB(2*j+1,2*i+1,newColor.getRGB());
            }
         }        
         File ouptut = new File("current.jpg");
         ImageIO.write(newImage, "jpg", ouptut);        
        } catch (Exception e) {}
        showImage();
    }
    
     public void scaleDownImage(){
                  int i=0;
         int j=0;
        try {
         File input = new File("current.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();

         newImage = new BufferedImage((int)(width/2), (int)(height/2), BufferedImage.TYPE_INT_RGB);
         for(i=0; i<height/2; i++){        
            for(j=0; j<width/2; j++){            
               Color c = new Color(image.getRGB(j*2, i*2));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               Color newColor = new Color(red,green,blue);
               newImage.setRGB(j,i,newColor.getRGB());
               
            }
         }        
         File ouptut = new File("current.jpg");
         ImageIO.write(newImage, "jpg", ouptut);        
        } catch (Exception e) {
            System.out.println(e +" when "+i+" "+j);
        }
        showImage();
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
        int i=0;
        int j=0;
        try {
        File input = new File("current.jpg");
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight(); 
        newImage = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_RGB);
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
        for(j=0; j<height; j++){
            for(i=0; i<width; i++){
                Color c = new Color(image.getRGB(i, j));
                if((i-1<0) && (j-1<0)){
                    newImage.setRGB(i, j, c.getRGB());                   
                }else if((i+1>=width) && (j-1<0)){
                    newImage.setRGB(i, j, c.getRGB());
                }else if((i-1<0) && (j+1>=height)){
                    newImage.setRGB(i, j, c.getRGB());
                }else if((i+1>width) && (j+1>=height)){
                    newImage.setRGB(i, j, c.getRGB());
                }else if(j-1<0){
                    newImage.setRGB(i, j, c.getRGB());
                }else if(i-1<0){
                    newImage.setRGB(i, j, c.getRGB());
                }else if(i+1>=width){
                    newImage.setRGB(i, j, c.getRGB());
                }else if(j+1>=height){
                    newImage.setRGB(i, j, c.getRGB());
                }else{
                    pixel[0]=new Color(image.getRGB(i-1,j-1));
                    pixel[1]=new Color(image.getRGB(i-1,j));
                    pixel[2]=new Color(image.getRGB(i-1,j+1));
                    pixel[3]=new Color(image.getRGB(i,j+1));
                    pixel[4]=new Color(image.getRGB(i+1,j+1));
                    pixel[5]=new Color(image.getRGB(i+1,j));
                    pixel[6]=new Color(image.getRGB(i+1,j-1));
                    pixel[7]=new Color(image.getRGB(i,j-1));
                    pixel[8]=new Color(image.getRGB(i,j));
                    for(int k=0;k<9;k++){
                        R[k]=pixel[k].getRed();
                        B[k]=pixel[k].getBlue();
                        G[k]=pixel[k].getGreen();
                    }
                    Arrays.sort(R);
                    Arrays.sort(G);
                    Arrays.sort(B);
                    Color newColor = new Color(R[4],B[4],G[4]);              
                    newImage.setRGB(i, j, newColor.getRGB());
                }
            }
        }  
        File ouptut = new File("current.jpg");
        ImageIO.write(newImage, "jpg", ouptut);    
        showImage();
        } catch (Exception e) {
            System.out.println(e+ "exception ocurred" +" "+i+" "+j+" ");
        }    
    }
    
    public void meanFilter(int type){
        try {
            File input = new File("current.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            newImage = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_RGB);
            int [][] mask;
            int divisor;
            if(type==0){
                mask = new int[] [] {new int[]{1,1,1}, new int[]{1,1,1}, new int[]{1,1,1}};
                divisor = 9;
            }else if(type==1){
                mask = new int[] [] {new int[]{1,1,1}, new int[]{1,2,1}, new int[]{1,1,1}};
                divisor = 10;
            }else{
                mask = new int[] [] {new int[]{1,2,1}, new int[]{2,4,2}, new int[]{1,2,1}};
                divisor = 16;
            }
            for(int j=0; j<height; j++){
                for(int i=0; i<width; i++){
                    Color c = new Color(image.getRGB(i, j));
                    if((i-1<0) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>=width) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i-1<0) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>width) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i+1>=width){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j+1>=height){
                        newImage.setRGB(i, j, c.getRGB());
                    }else{
                        double colorRed=0;
                        double colorGreen=0;
                        double colorBlue=0;
                        for(int row=0; row<3; row++){
                            for(int clmn=0; clmn<3; clmn++){
                                colorRed = colorRed + ((double)( new Color(image.getRGB(i-1+clmn, j-1+row)).getRed()*mask[row][clmn]))/divisor;
                                colorGreen = colorGreen + ((double)(new Color(image.getRGB(i-1+clmn, j-1+row)).getGreen()*mask[row][clmn]))/divisor;
                                colorBlue = colorBlue + ((double)(new Color(image.getRGB(i-1+clmn, j-1+row)).getBlue()*mask[row][clmn]))/divisor;
                            }
                        }
                        Color newColor = new Color((int)(colorRed), (int)(colorGreen), (int)(colorBlue));
                        newImage.setRGB(i, j, newColor.getRGB());
                    }
                }
            }
            File ouptut = new File("current.jpg");
            ImageIO.write(newImage, "jpg", ouptut);    
            showImage();
        
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void midPointFilter(){
        try {
            File input = new File("current.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            newImage = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_RGB);
            for(int j=0; j<height; j++){
                for(int i=0; i<width; i++){
                    Color c = new Color(image.getRGB(i, j));
                    if((i-1<0) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>=width) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i-1<0) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>width) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i+1>=width){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j+1>=height){
                        newImage.setRGB(i, j, c.getRGB());
                    }else{
                        double colorRed=0;
                        double colorGreen=0;
                        double colorBlue=0;
                        double[] R=new double[9];
                        double[] B=new double[9];
                        double[] G=new double[9];
                        Color[] pixel=new Color[9];  
                        pixel[0]=new Color(image.getRGB(i-1,j-1));
                        pixel[1]=new Color(image.getRGB(i-1,j));
                        pixel[2]=new Color(image.getRGB(i-1,j+1));
                        pixel[3]=new Color(image.getRGB(i,j+1));
                        pixel[4]=new Color(image.getRGB(i+1,j+1));
                        pixel[5]=new Color(image.getRGB(i+1,j));
                        pixel[6]=new Color(image.getRGB(i+1,j-1));
                        pixel[7]=new Color(image.getRGB(i,j-1));
                        pixel[8]=new Color(image.getRGB(i,j));
                        for(int k=0;k<9;k++){
                            R[k]=pixel[k].getRed();
                            B[k]=pixel[k].getBlue();
                            G[k]=pixel[k].getGreen();
                        }
                        Arrays.sort(R);
                        Arrays.sort(G);
                        Arrays.sort(B);
                        Color newColor = new Color((int)(R[0]+R[8])/2,(int)(G[0]+G[8])/2,(int)(B[0]+B[8])/2);              
                        newImage.setRGB(i, j, newColor.getRGB());
                    }
                }
            }
            File ouptut = new File("current.jpg");
            ImageIO.write(newImage, "jpg", ouptut);    
            showImage();       
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void minMaxFilter(String type){
        try {
            File input = new File("current.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            newImage = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_RGB);
            for(int j=0; j<height; j++){
                for(int i=0; i<width; i++){
                    Color c = new Color(image.getRGB(i, j));
                    if((i-1<0) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>=width) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i-1<0) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>width) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i+1>=width){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j+1>=height){
                        newImage.setRGB(i, j, c.getRGB());
                    }else{
                        double colorRed=0;
                        double colorGreen=0;
                        double colorBlue=0;
                        double[] R=new double[9];
                        double[] B=new double[9];
                        double[] G=new double[9];
                        Color[] pixel=new Color[9];  
                        pixel[0]=new Color(image.getRGB(i-1,j-1));
                        pixel[1]=new Color(image.getRGB(i-1,j));
                        pixel[2]=new Color(image.getRGB(i-1,j+1));
                        pixel[3]=new Color(image.getRGB(i,j+1));
                        pixel[4]=new Color(image.getRGB(i+1,j+1));
                        pixel[5]=new Color(image.getRGB(i+1,j));
                        pixel[6]=new Color(image.getRGB(i+1,j-1));
                        pixel[7]=new Color(image.getRGB(i,j-1));
                        pixel[8]=new Color(image.getRGB(i,j));
                        for(int k=0;k<9;k++){
                            R[k]=pixel[k].getRed();
                            B[k]=pixel[k].getBlue();
                            G[k]=pixel[k].getGreen();
                        }
                        Arrays.sort(R);
                        Arrays.sort(G);
                        Arrays.sort(B);
                        if(type=="min"){
                            Color newColor = new Color((int)(R[0]),(int)(G[0]),(int)(B[0]));              
                            newImage.setRGB(i, j, newColor.getRGB());
                        }else{
                            Color newColor = new Color((int)(R[8]),(int)(G[8]),(int)(B[8]));              
                            newImage.setRGB(i, j, newColor.getRGB());
                        }    
                    }
                }
            }
            File ouptut = new File("current.jpg");
            ImageIO.write(newImage, "jpg", ouptut);    
            showImage();   
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void edgeDetection(int type){
        try {
            File input = new File("current.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            newImage = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_RGB);
            int [][] edgeMask;
            if(type==0){
                edgeMask = new int[] [] {new int[]{-1,0,1}, new int[]{-1,0,1}, new int[]{-1,0,1}};
            }else if(type==1){
                edgeMask = new int[] [] {new int[]{-1,-1,-1}, new int[]{0,0,0}, new int[]{1,1,1}};
            }
            else if(type==2){
                edgeMask = new int[] [] {new int[]{-1, 0, 1}, new int[]{-2, 0, 2}, new int[]{-1, 0, 1}};
            }
            else if(type==3){
                edgeMask = new int[] [] {new int[]{0, 1, 0}, new int[]{1, -4, 1}, new int[]{0, 1, 0}};
            }
            else{
                edgeMask = new int[] [] {new int[]{0, -1, 0}, new int[]{-1, 4, -1}, new int[]{0, -1, 0}};
            }
            for(int j=0; j<height; j++){
                for(int i=0; i<width; i++){
                    Color c = new Color(image.getRGB(i, j));
                    if((i-1<0) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>=width) && (j-1<0)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i-1<0) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if((i+1>width) && (j+1>=height)){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i-1<0){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(i+1>=width){
                        newImage.setRGB(i, j, c.getRGB());
                    }else if(j+1>=height){
                        newImage.setRGB(i, j, c.getRGB());
                    }else{
                        double red=0;
                        double green=0;
                        double blue=0;
                        for(int row=0; row<3; row++){
                            for(int clmn=0; clmn<3; clmn++){
                                red = red + ((int)( new Color(image.getRGB(i-1+clmn, j-1+row)).getRed()*edgeMask[row][clmn]));
                                green = green + ((int)(new Color(image.getRGB(i-1+clmn, j-1+row)).getGreen()*edgeMask[row][clmn]));
                                blue = blue + ((int)(new Color(image.getRGB(i-1+clmn, j-1+row)).getBlue()*edgeMask[row][clmn]));
                                
                                red = red + ((int)( new Color(image.getRGB(i-1+clmn, j-1+row)).getRed()*edgeMask[2-clmn][row]));
                                green = green + ((int)(new Color(image.getRGB(i-1+clmn, j-1+row)).getGreen()*edgeMask[2-clmn][row]));
                                blue = blue + ((int)(new Color(image.getRGB(i-1+clmn, j-1+row)).getBlue()*edgeMask[2-clmn][row]));
                                
                            }
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
                        if(red<0){
                            red=0;
                        }              
                        if(green<0){
                            green=0;
                        }           
                        if(blue<0){
                            blue=0;
                        }
                            Color newColor = new Color((int)(red), (int)(green), (int)(blue));
                            newImage.setRGB(i, j, newColor.getRGB());
                    }
                }
            }
            File ouptut = new File("current.jpg");
            ImageIO.write(newImage, "jpg", ouptut);    
            showImage();       
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
