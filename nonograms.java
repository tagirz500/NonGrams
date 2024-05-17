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
    private JPanel centerGridPanel;
    private JPanel panelAnsr;
    private JButton resetButton = new JButton("Reset");
    BmpConverter c = new BmpConverter();
    private int whiteCellsCount = 0;        
    private int clickCount = 0;
    private int gridSize = width*height;
    private int colouredCorrectly = 0;
    private int colouredCount = 0;
    
    
    


    public  nonograms() {     

        JPanel leftPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JButton answerButton = new JButton("Reveal Answer");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the source of the picture: \n");
        System.out.println("1. Main campaign");
        System.out.println("2. BMP File\n");
        int choice = scanner.nextInt();

        //gives option to choose out of hardcoded arrays 
        if (choice == 1) {
            System.out.println("\nChoose the picture array: \n");
            System.out.println("1 - Sand Clock");
            System.out.println("2 - Cross");
            System.out.println("3 - Umbrella");
            int pictureChoice = scanner.nextInt();

            //choice of arrays
            switch (pictureChoice) {
                case 1:
                    chosenArray = PictureArray.getClockArray();
                    break;
                case 2:
                    chosenArray = PictureArray.getCrossArray();
                    break;
                case 3:
                    chosenArray = PictureArray.getUmbrellaArray();
                    break;
                    
            }
            int[][] pictureGrid = chosenArray;
            width = chosenArray[0].length;
            height = chosenArray.length;


            //cretes button and add them to playbele grid panel 
            centerGridPanel = new JPanel(new GridLayout(width, height));
            panelAnsr = new JPanel(new GridLayout(width, height));
            for (int i = 0; i < pictureGrid.length; i++) {
                for (int j = 0; j < pictureGrid[i].length; j++) {
                    JButton button = new JButton();
                    if (pictureGrid[i][j] == 1) {
                        button.setBackground(Color.BLACK); 
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
            
            //option to use bmpp files for a game
        } else if (choice == 2) {
            c.converter();
            width = BmpConverter.getHeight();
            height = BmpConverter.getWidth();
            centerGridPanel = new JPanel(new GridLayout(width, height));
            panelAnsr = BmpConverter.getPanel();
            
            
        } 

       JButton checkButton = new JButton("Check");
        mainPanel.add(centerGridPanel, BorderLayout.CENTER);
        rightPanel.setLayout(new GridLayout(9,1));
        botomPanel.setLayout(new GridLayout(1,3));
        botomPanel.add(resetButton);
        botomPanel.add(checkButton);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        Border margin = BorderFactory.createEmptyBorder(5, 22, 5, 51);
        topPanel.setBorder(margin);
        topPanel.setLayout(new GridLayout(1, height - 1));
        topPanel.setPreferredSize(new Dimension(0, 20));
        leftPanel.setLayout(new GridLayout(width, 1));
        leftPanel.setPreferredSize(new Dimension(20, 0));

        JFrame endGameFrame = new JFrame();
        JPanel panel = new JPanel();
        endGameFrame.add(panel);
        endGameFrame.setSize(400,100);
        setWidthAndHeight(height,width);
        JLabel labelLoss = new JLabel("You have completed the puzzle incorectly");
        JLabel labelWin = new JLabel("You have completed the puzzle correctly");
        
       //actions when button is pressed
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == checkButton){
                    compareButtonColors(panelAnsr, centerGridPanel);
                    System.out.println(colouredCorrectly + " squares coloured correctly out of: " + (gridSize - whiteCellsCount));
                    

                }
                //creates a new window with a loss text 
                if (e.getSource() == checkButton && (gridSize - whiteCellsCount) <= colouredCount){
                    
                    panel.add(labelLoss);
                    endGameFrame.setVisible(true);
                }
                
                

                //createed a window with a win text
                if (e.getSource() == checkButton && (gridSize - whiteCellsCount) == colouredCorrectly && colouredCorrectly == colouredCount){
                    
                    endGameFrame.dispose();
                    JFrame endGameFrame1 = new JFrame();
                    JPanel panel = new JPanel();
                    endGameFrame1.add(panel);
                    endGameFrame1.setSize(300,80);
                    panel.add(labelWin);
                    endGameFrame1.add(panel);
                    endGameFrame1.setVisible(true);
                    

                }
               
                //replaces gridpanel and shows the ansewr 
                if (e.getSource() == answerButton){
                    mainPanel.remove(centerGridPanel);
                    mainPanel.add(panelAnsr,BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                }
              
            }
        };


        botomPanel.add(answerButton);
        answerButton.addActionListener(action);
         
        //list into an array
        Integer[] rgbArray = rgbValues.toArray(new Integer[rgbValues.size()]);
        int size = rgbArray.length;


        //Cretaes colours choice buttons
        for (int x = 0; x < size; x++){
            JButton button = new JButton("     ");
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

        creatingCenterPanel();
            
        mainPanel.add(botomPanel, BorderLayout.SOUTH);
        frame.setSize(810, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
        printRowColumns(panelAnsr, leftPanel, topPanel);

       
    }

    //compare colours of users grid panel and answer grid panel
    public int compareButtonColors(JPanel panelAsr, JPanel centerGridPanel) {
        Component[] components1 = panelAsr.getComponents(); // array of GUI components - components[]
        Component[] components2 = centerGridPanel.getComponents();
        colouredCorrectly = 0;
        for (int i = 0; i < components1.length; i++) {
            
            JButton button1 = (JButton) components1[i];
            JButton button2 = (JButton) components2[i]; 
            Color button2Background = button2.getBackground();
            if (button1.getBackground().equals(button2.getBackground())) {
                button2.setBorder(new LineBorder(Color.GRAY));
                colouredCorrectly++;
            } else if (!button1.getBackground().equals(button2.getBackground()) && !button2Background.equals(Color.YELLOW)){
                 button2.setBorder(new LineBorder(Color.magenta, 2));
                }
                else if (button2Background.equals(Color.YELLOW)){
                button2.setBorder(new LineBorder(Color.GRAY));
                }
        }
        return colouredCorrectly; 
    }


    //creates a label for row and columns fro left an top panel
    public int printRowColumns(JPanel panelAnsr, JPanel leftPanel, JPanel topPanel) {
        Component[] components = panelAnsr.getComponents(); //components into array
        
        int[] rowCount = new int[width];
        int[] colCount = new int[height];
        
        for (int i = 0; i < components.length; i++) {
            JButton button = (JButton) components[i];
            Color currentColour = button.getBackground();
    
            if (!currentColour.equals(Color.WHITE)) {
                int x = i / height; //only gives a full number to show a row number
                int y = i % height; //gives a reaminder to show a column number
                rowCount[x]++; 
                colCount[y]++;//increase value in the array by one 
            }
            else if (currentColour.equals(Color.WHITE)){
                whiteCellsCount++;
            }
        }
        //creates row labels on top panel
        for (int x = 0; x < width; x++) {
            JLabel rowLabel = new JLabel(String.valueOf(rowCount[x]));
            rowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leftPanel.add(rowLabel);
        }
        //creates column labels on left panel
        for (int y = 0; y < height; y++) {
            JLabel colLabel = new JLabel(String.valueOf(colCount[y]));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            topPanel.add(colLabel);
        }
        return whiteCellsCount;
    }

    //creates a center panel from array or a bmp file
    public int creatingCenterPanel(){
        
        
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
                            Color beforeClickColour = gamebutton.getBackground();
                            clickCount++;
                            
                            if (gamebutton.getBackground().equals(selectedColor) || gamebutton.getBackground().equals(Color.BLACK)) {
                             gamebutton.setBackground(Color.YELLOW);
                            } else {
                            if (selectedColor == null){
                                gamebutton.setBackground(Color.BLACK);
                            }
                            else{
                                gamebutton.setBackground(selectedColor);
                            }
                        }
                        if (!gamebutton.getBackground().equals(Color.YELLOW) && beforeClickColour.equals(Color.YELLOW)){
                            colouredCount++;
                            
                        }
                        if (gamebutton.getBackground().equals(Color.YELLOW)){
                            colouredCount--;
                           
                        }
                        
                        
                        }
                        if (e.getSource() == resetButton){
                            gamebutton.setBackground(Color.YELLOW);
                            gamebutton.setBorder(new LineBorder(Color.GRAY));
                        
                    }
                    }
                    
                };
                gamebutton.addActionListener(action2);
                resetButton.addActionListener(action2);     
               
            }   
        }
        return clickCount;    
        
        }
        //setter for grid size value
        public void setWidthAndHeight(int newWidth, int newHeight) {
            width = newWidth;
            height = newHeight;
            gridSize = width * height; 
        }
}


