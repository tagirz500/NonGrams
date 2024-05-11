import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BmpConverter{
private static JPanel panelAnsr;
private static int width; 
private static int height;
protected static Set<Integer> rgbValues = new HashSet<>();
public void converter() {

    
    //importing bmp files via folder
    String folderPath = "C:/Users/tagir/Desktop/New folder/NonGrams/BMP Images";
    File BmpFiles = new File(folderPath);
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
    BufferedImage image = ImageIO.read(chosenFile);
    BmpConverter.width = image.getWidth();
    BmpConverter.height = image.getHeight();

    panelAnsr = new JPanel(new GridLayout(height, width));
   
   
  
    PixelArt(image);

    //fixes IOEXCEPTION erros
    } catch (IOException error) {
        System.err.println("Error: " + error.getMessage());
    }


    
}   
 //goes throught every pixel and record is coordinates and rgb values
public void PixelArt(BufferedImage image){

  
   for (int y = 0; y < height; y++){
        for (int x = 0; x < width; x++){
        int rgb = image.getRGB (x, y);
        Color colour = new Color(rgb);
        JButton pixel = new JButton();
        pixel.setBackground(colour);    
        panelAnsr.add(pixel);
        rgbValues.add(rgb);
        Color colorPixel = pixel.getBackground();
       
    
}
}
System.out.println(rgbValues);

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

