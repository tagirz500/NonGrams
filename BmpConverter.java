import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class BmpConverter{
private static JPanel panelAnsr;
private static int width; 
private static int height;
protected static Set<Integer> rgbValues = new HashSet<>();
public void converter() {

    
    //importing bmp files via folder
    String folderPath = "C:/Users/tagir/Desktop/New folder/NonGrams/BMP Images";
    File BmpFiles = new File(folderPath);
   // using listFiles method as a part of the File
    File[] files = BmpFiles.listFiles();
    System.out.println("Following files have been found:\n");
    for (int i = 0; i < files.length; i++){
        System.out.println((i+1) + " - " + files[i].getName());
    }
    System.out.println("\nChoose a file to for a game\n");

    //reads images width and height, taker user input and selects a file
    try
    {Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    File chosenFile = files[choice - 1];
    BufferedImage bmpImage = ImageIO.read(chosenFile);
    BmpConverter.width = bmpImage.getWidth();
    BmpConverter.height = bmpImage.getHeight();

    panelAnsr = new JPanel(new GridLayout(height, width));
   
   
  
    PixelArt(bmpImage);

    //fixes IOEXCEPTION erros
    } catch (IOException error) {
        System.err.println("Error: " + error.getMessage());
    }


    
}   
 //goes throught every pixel and record is coordinates and rgb values
public void PixelArt(BufferedImage bmpImage){

  
   for (int y = 0; y < height; y++){
        for (int x = 0; x < width; x++){
        int rgb = bmpImage.getRGB (x, y);
        Color colour = new Color(rgb);
        JButton pixel = new JButton();
        pixel.setBorder(new LineBorder(Color.GRAY));           
        pixel.setBackground(colour);    
        panelAnsr.add(pixel);
        rgbValues.add(rgb);
        
       
    
}
}


}



//getters



public static  JPanel getPanel(){
    return panelAnsr;
}

public static int getWidth(){
    return width;

}
public static int getHeight(){
    return height;

}
public static Set<Integer> getRGB(){
    return rgbValues;
}

}


class PictureArray {
    private static final int[][] umbrellaArray = {
    {0,0,0,1,0,0,0},
    {0,0,1,1,1,0,0},
    {0,1,1,1,1,1,0},
    {1,1,1,1,1,1,1},
    {0,0,0,1,0,0,0},
    {0,1,0,1,0,0,0},
    {0,0,1,1,0,0,0},

    };

    private static final int[][] sandClockArray = {
      {0,1,1,1,0},
      {0,1,0,1,0},
      {0,0,1,0,0},
      {0,1,0,1,0},
      {0,1,1,1,0}
    };

    private static final int[][] crossArray = {
     {1,0,0,0,1},
     {0,1,0,1,0},
     {0,0,1,0,0},
     {0,1,0,1,0},
     {1,0,0,0,1},
     
    };

    //getters

    public static int[][] getUmbrellaArray() {
        return umbrellaArray;
    }

    public static int[][] getClockArray() {
        return sandClockArray;
    }

    public static int[][] getCrossArray() {
        return crossArray;
    }
}