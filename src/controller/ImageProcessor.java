/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ImageFile;
import view.ControlPanel;

/**
 *
 * @author aminda
 */
public class ImageProcessor {
    public static ImageFile refImgFile;
    public static ControlPanel refCntrlPnel;
    public static ImageProcessor imgPrccssor;
    public ImageProcessor() {        
        imgPrccssor=this;
    }
    

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.print(this);
        
        refImgFile = new ImageFile();
        refCntrlPnel = new ControlPanel();
        refCntrlPnel.setVisible(true);
       
    }
    
}
