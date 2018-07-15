/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drm_test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kianm_000
 */
public class Window1 {
    
    private JFrame f;
    private JPanel p;
    private JLabel l;    
    private JLabel l2;
    private JTextField tf;
    private JButton b;
    private static ArrayList<String> ids = new ArrayList<>();
    private static Scanner listReader;
    private static String ID;
    private static ArrayList<String> verified;

    public Window1() {
        gui();
    }
    
    public static boolean check() throws FileNotFoundException {
        String userL = ID.toLowerCase();        
        verified = new ArrayList<>();        
        listReader = new Scanner(new File("c://Users/Kian/Documents/NetBeansProjects/DRMTest/src/drm_test/list.txt")); //File hosted by provider, updated w/ IDs of game owners       
        
        while (listReader.hasNextLine()) {
            verified.add(listReader.nextLine());
        }         
        return verified.contains(userL);
    }
    
    
    public void gui() {
        f = new JFrame("Identifier");
        f.setVisible(true);
        f.setSize(300, 30);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);
        
        l = new JLabel("User ID: ");
        l2 = new JLabel("         ");
        tf = new JTextField(10);
        
        b = new JButton("Submit");
        b.setPreferredSize(new Dimension(100, 15));
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    ID = tf.getText();
                    if (check()) {
                        l2.setText("");                      
                        
                        Runtime runtime = Runtime.getRuntime();
                        runtime.exec("c://Users/Kian/Documents/NetBeansProjects/DRMTest/src/drm_test/BROFORCE_Brototype.exe",null,new File("c://Users/Kian/Documents/NetBeansProjects/DRMTest/src/drm_test/"));
                    }
                    else if (!check()) {
                        l2.setText("This user does not have access.");
                    }       } catch (FileNotFoundException ex) {
                    Logger.getLogger(Window1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Window1.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
        });    
        p.add(l, BorderLayout.WEST);
        p.add(tf, BorderLayout.CENTER);
        p.add(b, BorderLayout.EAST);
        p.add(l2, BorderLayout.SOUTH);
        f.add(p);
        f.pack();
        f.repaint();
        
        ID = tf.getText();
    }
    
    
}
