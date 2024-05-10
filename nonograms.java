import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class nonograms extends BmpConverter {
    private int width;
    private int height;

    private JFrame frame = new JFrame("Nonograms(Hanjies)");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel botomPanel = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel();
    BmpConverter c = new BmpConverter();
    public nonograms() {
        
       
        c.converter();
        width = BmpConverter.getWidth();
        height = BmpConverter.getHeight();
        rgbValues = BmpConverter.getRGB();
        JPanel centerGridPanel = new JPanel(new GridLayout(width, height));
        JPanel panelAnsr = BmpConverter.getPanel();
        mainPanel.add(centerGridPanel, BorderLayout.CENTER);
        frame.add(new JButton("North"), BorderLayout.NORTH);
        rightPanel.setLayout(new GridLayout(9,1));
        botomPanel.setLayout(new GridLayout(1,3));
        
        
        botomPanel.add(new JButton("Colour - 1"));
        JButton answerButton = new JButton("Reveal Answer");
        botomPanel.add(answerButton);
        answerButton.addActionListener (new ActionListener() {
        
            @Override
          public void actionPerformed(ActionEvent e) {
              if (e.getSource() == answerButton){
                  mainPanel.remove(centerGridPanel);
                  mainPanel.add(panelAnsr,BorderLayout.CENTER);
                  frame.revalidate();
                  frame.repaint();
              }
          }
          
         });
         
         Integer[] rgbArray = rgbValues.toArray(new Integer[rgbValues.size()]);
         int size = rgbArray.length;
         
         for (int x = 0; x < size; x++){
             JButton button = new JButton("        ");
             Color colour = new Color(rgbArray[x]);
             button.setBackground(colour);
             rightPanel.add(button); 
             
          
         }
  

        mainPanel.add(rightPanel, BorderLayout.EAST);
        botomPanel.add(new JButton("Colour - 3"));
        mainPanel.add(botomPanel, BorderLayout.SOUTH);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        frame.setSize(height*30, width*30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);

        
           for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                JButton gamebutton = new JButton();
                centerGridPanel.add(gamebutton);
                gamebutton.setBackground(Color.YELLOW);
                gamebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                if (e.getSource() == gamebutton){
                gamebutton.setBackground(Color.BLACK);
                
    }
}    
                });
            }
            }
            }
  
         

}


        
    








    





