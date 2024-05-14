import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.util.Scanner;

public class nonograms extends BmpConverter {
    private int width;
    private int height;
    private Color selectedColor;
    private int[][] chosenArray;
    private JFrame frame = new JFrame("nonograms(Hanjies)");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel botomPanel = new JPanel(new BorderLayout());
    private JPanel rightPanel = new JPanel();
    BmpConverter c = new BmpConverter();
    
    public nonograms() {



        
        
        JPanel centerGridPanel;
        JPanel panelAnsr;
        JPanel leftPanel = new JPanel();
        JPanel topPanel = new JPanel();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the source of the picture: ");
        System.out.println("1. Array");
        System.out.println("2. BMP File");
        int sourceChoice = scanner.nextInt();

        if (sourceChoice == 1) {
            System.out.println("Choose the picture array: ");
            System.out.println("1. Umbrella");
            System.out.println("2. Smile Face");
            System.out.println("3. Cross");
            int pictureChoice = scanner.nextInt();

            switch (pictureChoice) {
                case 1:
                    chosenArray = PictureArray.getUmbrellaArray();
                    break;
                case 2:
                    chosenArray = PictureArray.getSmileArray();
                    break;
                case 3:
                    chosenArray = PictureArray.getCrossArray();
                    break;
                default:
                    System.out.println("Invalid choice");
                    return;
            }
            int[][] pictureGrid = chosenArray;
             width = chosenArray[0].length;
            height = chosenArray.length;
            centerGridPanel = new JPanel(new GridLayout(width, height));
            panelAnsr = new JPanel(new GridLayout(width, height));
            for (int i = 0; i < pictureGrid.length; i++) {
                for (int j = 0; j < pictureGrid[i].length; j++) {
                    JButton button = new JButton();
                    if (pictureGrid[i][j] == 1) {
                        button.setBackground(Color.BLACK); // Change color for cells with picture
                    } else {
                        button.setBackground(Color.WHITE);
                    }
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            button.setBackground(Color.BLACK);
                        }
                    });
                    panelAnsr.add(button);
                }
            }

           

        } else if (sourceChoice == 2) {
            c.converter();
            // Use BMP class to get width and height
            width = BmpConverter.getHeight();
            height = BmpConverter.getWidth();
            centerGridPanel = new JPanel(new GridLayout(width, height));
            panelAnsr = BmpConverter.getPanel();
        } else {
            return;
        }

       ;// Change to umbrella, sun, or elephant

        // Add buttons for each cell in the picture grid
      
        mainPanel.add(centerGridPanel, BorderLayout.CENTER);

        rightPanel.setLayout(new GridLayout(9,1));
        botomPanel.setLayout(new GridLayout(1,3));
        JButton resetButton = new JButton("Reset");
        botomPanel.add(resetButton);
       

        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        JButton checkButton = new JButton("Check");
        botomPanel.add(checkButton);
        
        JButton answerButton = new JButton("Reveal Answer");
        JButton importButton = new JButton("Import a Bmp File");
        
        Border margin = BorderFactory.createEmptyBorder(5, 22, 5, 51);
        topPanel.setBorder(margin);
        topPanel.setLayout(new GridLayout(1, height - 1));
        topPanel.setPreferredSize(new Dimension(0, 20));
        
        // Create labels for column headers

        leftPanel.setLayout(new GridLayout(width, 1));
        leftPanel.setPreferredSize(new Dimension(20, 0));
        
        // Create labels for row headers

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == checkButton){
                    System.out.println(compareButtonColors(panelAnsr, centerGridPanel) + " squares coloured correctly");

                }
                if(e.getSource() == importButton){
                    c.converter();
                }
            }
        };

        botomPanel.add(answerButton);
        botomPanel.add(importButton);
        importButton.addActionListener(action);
        answerButton.addActionListener(new ActionListener() {
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
        rightPanel.setLayout(new GridLayout(6,1));
        checkButton.addActionListener(action);

        

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                JButton gamebutton = new JButton();
                centerGridPanel.add(gamebutton);
                gamebutton.setBackground(Color.YELLOW);
                gamebutton.setBorder(new LineBorder(Color.GRAY));           
                ActionListener action2 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource() == gamebutton){
                            gamebutton.setBackground(selectedColor);
                        }
                        if (e.getSource() == resetButton){
                            gamebutton.setBackground(Color.YELLOW);
                        }
                    }
                };
                gamebutton.addActionListener(action2);
                resetButton.addActionListener(action2);     
            }   
        }

        mainPanel.add(botomPanel, BorderLayout.SOUTH);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
        printRowColumns(panelAnsr, leftPanel, topPanel);
    }

    int compareButtonColors(JPanel panelAsr, JPanel centerGridPanel) {
        Component[] components1 = panelAsr.getComponents(); // array of GUI components - components[]
        Component[] components2 = centerGridPanel.getComponents();
        int Colored = 0;
        for (int i = 0; i < components1.length; i++) {
            
            JButton button1 = (JButton) components1[i];
            JButton button2 = (JButton) components2[i];
            Color button2Background = button2.getBackground();
            if (button1.getBackground().equals(button2.getBackground())) {
                button2.setBorder(new LineBorder(Color.GRAY));
                Colored++;
            } else if (!button1.getBackground().equals(button2.getBackground()) && !button2Background.equals(Color.YELLOW)){
                 button2.setBorder(new LineBorder(Color.magenta, 2));
            }
        }
        return Colored; 
    }

    public void printRowColumns(JPanel panelAnsr, JPanel leftPanel, JPanel topPanel) {
        Component[] components = panelAnsr.getComponents();
        
        int[] rowCount = new int[width];
        int[] colCount = new int[height];
        
        for (int i = 0; i < components.length; i++) {
            JButton button = (JButton) components[i];
            Color currentColor = button.getBackground();
    
            if (!currentColor.equals(Color.WHITE)) {
                int row = i / height; //only gives a full number to show row, row 0 is 1
                int col = i % height; //gives a reaminder to show column
                rowCount[row]++; 
                colCount[col]++;
            }
        }
    
        for (int row = 0; row < width; row++) {
            JLabel rowLabel = new JLabel(String.valueOf(rowCount[row]));
            rowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leftPanel.add(rowLabel);
        }
    
        for (int col = 0; col < height; col++) {
            JLabel colLabel = new JLabel(String.valueOf(colCount[col]));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            topPanel.add(colLabel);
        }
    }
   
}


