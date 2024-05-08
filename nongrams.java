import java.awt.GridLayout;

import javax.swing.*;


public class nongrams {

public static void main (String[] args){
  JFrame grid = new JFrame("Nongrams");
  JPanel panel = new JPanel();
    
    grid.setContentPane(panel);
    grid.setVisible(true);
    grid.setSize(1200,1600);
    grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    grid.setLayout(new GridLayout(3,3));

    JButton btn1 = new JButton("1");
        panel.add(btn1);
    JButton btn2 = new JButton("2");
        panel.add(btn2);
    JButton btn3 = new JButton("3");
        panel.add(btn3);
    JButton btn4 = new JButton("4");
        panel.add(btn4);
    JButton btn5 = new JButton("5");
        panel.add(btn5);
    JButton btn6 = new JButton("6");
        panel.add(btn6);
    JButton btn7 = new JButton("7");
        panel.add(btn7);
    JButton btn8 = new JButton("8");
        panel.add(btn8);
    JButton btn9 = new JButton("9");
        panel.add(btn9);
    
}


  

}

