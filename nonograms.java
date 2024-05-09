import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class nonograms extends BmpConverter {
    private int width;
    private int height;

    private JFrame frame = new JFrame("Nonograms(Hanjies)");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel();
    BmpConverter c = new BmpConverter();
    public nonograms() {
        
       
        c.converter();
        width = BmpConverter.getWidth();
        height = BmpConverter.getHeight();
        JPanel centerGridPanel = new JPanel(new GridLayout(width, height));
        JPanel panelAnsr = BmpConverter.getPanel();
        mainPanel.add(panelAnsr, BorderLayout.WEST);
        mainPanel.add(centerGridPanel, BorderLayout.CENTER);
        frame.add(new JButton("South"), BorderLayout.SOUTH);
        frame.add(new JButton("North"), BorderLayout.NORTH);
        rightPanel.add(new JButton("Colour - 1"));
        rightPanel.add(new JButton("Colour - 2"));
        rightPanel.add(new JButton("Colour - 3"));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        mainPanel.add(rightPanel, BorderLayout.EAST);
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


        
    








    





