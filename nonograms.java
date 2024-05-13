import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class nonograms extends BmpConverter {
    private int width;
    private int height;
    private Color selectedColor;
    private JFrame frame = new JFrame("nonograms(Hanjies)");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel botomPanel = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel();
    BmpConverter c = new BmpConverter();
    
    public nonograms() {
        c.converter();
        width = BmpConverter.getHeight();
        height = BmpConverter.getWidth();
        rgbValues = BmpConverter.getRGB();
        JPanel centerGridPanel = new JPanel(new GridLayout(width, height));
        JPanel panelAnsr = BmpConverter.getPanel();
        mainPanel.add(centerGridPanel, BorderLayout.CENTER);
        frame.add(new JButton("North"), BorderLayout.NORTH);
        rightPanel.setLayout(new GridLayout(9,1));
        botomPanel.setLayout(new GridLayout(1,3));
        
        
        JButton resetButton = new JButton("Reset");
        botomPanel.add(resetButton);
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
                if (e.getSource() == resetButton){

                }
            }
        });
         
        Integer[] rgbArray = rgbValues.toArray(new Integer[rgbValues.size()]);
        int size = rgbArray.length;
         
        for (int x = 0; x < size; x++){
            JButton button = new JButton("    ");
            Color colour = new Color(rgbArray[x]);
            button.setBackground(colour);
            rightPanel.add(button); 
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if (e.getSource() == button){
                        selectedColor = colour;
                    }
                }
            });
        }

        mainPanel.add(rightPanel, BorderLayout.EAST);
        JButton checkButton = new JButton("Check");
        botomPanel.add(checkButton);
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == checkButton){
                    System.out.println( compareButtonColors(panelAnsr, centerGridPanel) + " squares coloured correctly");

                }
            }
        };
        checkButton.addActionListener(action);
    
        mainPanel.add(botomPanel, BorderLayout.SOUTH);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        frame.setSize(height*40, width*35);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                JButton gamebutton = new JButton();
                centerGridPanel.add(gamebutton);
                gamebutton.setBackground(Color.YELLOW);
                gamebutton.setBorder(new LineBorder(Color.GRAY));
                gamebutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == gamebutton){
                            gamebutton.setBackground(selectedColor);
                        }
                    }    
                });
            }
        }
    }

        int compareButtonColors(JPanel panelAsr, JPanel centerGridPanel) {
        Component[] components1 = panelAsr.getComponents(); //aray of GUI components - components[]
        Component[] components2 = centerGridPanel.getComponents();
        int Colored = 0;
        for (int i = 0; i < components1.length; i++) {
            
            JButton button1 = (JButton) components1[i];
            JButton button2 = (JButton) components2[i];
            Color button2Background = button2.getBackground();
            if (button1.getBackground().equals(button2.getBackground()) && !button2Background.equals(Color.YELLOW)) {
                button2.setBorder(new LineBorder(Color.GRAY));
                 Colored++;
                
            }
            else if (!button1.getBackground().equals(button2.getBackground()) && !button2Background.equals(Color.YELLOW)){
                 button2.setBorder(new LineBorder(Color.magenta, 2));
            }
        }
        return Colored; 
    }
}
