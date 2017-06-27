/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.color.ColorSpace;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
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
     UIManager.put("nimbusBase", new Color(Color.gray.getRGB()));
     UIManager.put("nimbusBlueGrey", new Color(Color.lightGray.getRGB()));
     UIManager.put("control", new Color(Color.white.getRGB()));   
    try {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception e) {
        // If Nimbus is not available, you can set the GUI to another look and feel.
    }
        refImgFile = new ImageFile();
        refCntrlPnel = new ControlPanel();
        refCntrlPnel.setVisible(true);
       
    }
    
}
