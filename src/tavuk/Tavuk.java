/* Mehmet AFACAN 17290073
   Kübra Dilara BALCI 16290079
*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavuk;

/**
 *
 * @author halke
 */
import javax.swing.JFrame;

public class Tavuk {

    public Tavuk() {}

    public static void main(String[] args) {
        JFrame obj = new JFrame("CHICKEN IN THE SPACE");
        GamePlay gamePlay = new GamePlay();
        obj.setBounds(10, 10, 1000, 650);
        obj.setName("Game Frame");
        obj.setVisible(true);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(3);
        obj.add(gamePlay);
    }

}