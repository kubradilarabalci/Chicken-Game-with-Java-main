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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import javax.swing.JLabel;





public class GamePlay
  extends JPanel
  implements KeyListener, ActionListener
{
  private static final Graphics Graphics = null;
  private boolean play = false;
  private int totalBricks = 21;
  private Timer time;
  private int delay = 4;
  private int backgroundY = 0;
  private int wheat_score = 10;
  private int score = 0; 
  private String score_str = String.valueOf(score);
  private int bigwheatposX = (int)(Math.random() * 870.0D) + 10;
  private int bigwheatposY = 1000;
  private int bigwheat_score = 20;
  private int playerposX = 10;
  private int playerposY = 50; 
  private int eggposX = 10000;
  private int eggposY = playerposY;
  private int basketX = 155;
  private int basketY = 1000;
  private int wheatposX = (int)(Math.random() * 870.0D) + 10;
  private int wheatposY = 1000;
  private int catposX = 155;
  private int catposY = -600;
  private int egg_c = 3;
  private String egg_count = String.valueOf(egg_c);
  private int mainSpeed = -1;
  private boolean egg_bottom = false;
  private boolean wheat_top = false;
  private boolean bigwheat_top = false;
  private boolean cat_top = false;
  private boolean basket_top = false;
  private boolean is_intersect = false;
  private boolean bigis_intersect = false;
  private boolean is_dead = false;
  private int level_number = 1;
  private String level = String.valueOf(level_number);
  private boolean egg_fired = false;
  private boolean fire_button = false;
  private boolean is_catched = false;
  private boolean jump_pressed = false;
  private boolean is_started = false;
  private BufferedImage wheat;
  private BufferedImage arrow_keys;
  private BufferedImage bigwheat;
  private BufferedImage chicken;
  private BufferedImage cat;
  private BufferedImage background;
  private BufferedImage egg;
  private BufferedImage how_to_play;
  private BufferedImage basket_icon;
  private BufferedImage basket;
  JLabel label = new JLabel("the label");
  Timer timer;
  int x = 50;

  
  public GamePlay()
  {
    try {
      how_to_play = ImageIO.read(new File("pictures\\how_to_play.png"));
      background = ImageIO.read(new File("pictures\\background.jpg"));
      chicken = ImageIO.read(new File("pictures\\chicken.png"));
      wheat = ImageIO.read(new File("pictures\\wheat.png"));
      bigwheat = ImageIO.read(new File("pictures\\wheat.png"));
      egg = ImageIO.read(new File("pictures\\egg.png"));
      cat = ImageIO.read(new File("pictures\\cat.png"));
      basket_icon = ImageIO.read(new File("pictures\\egg_basket.png"));
      basket = ImageIO.read(new File("pictures\\egg_basket.png"));

    }
    catch (IOException localIOException) {}
    
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    timer.start();
    
  }
  

  @Override
  public void paint(Graphics g)
  {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, 1024, 1024);
    g.drawImage(background, 0, backgroundY,1000,1000, null);
    g.drawImage(background, 0, backgroundY+1000,1000,1000, null);
    if(is_started == false) {
        g.setColor(Color.YELLOW);
    	g.setFont(new Font("Monospaced", 2, 50));
    	//g.drawString("Press Enter To Start", 200, 100);
              g.setFont(new Font("Monospaced", 2, 30));
             // g.drawString("   Then Press -> To Play", 250, 150);
              g.setColor(Color.RED);
              g.setFont(new Font("Monospaced", 2, 45));
              g.drawImage(arrow_keys, 300, 300, 330, 270, null);
              g.drawImage(how_to_play, 150, 1, 661, 600, null);
    }
    
    else if(is_started == true) {
    g.setColor(Color.RED);
    g.setFont(new Font("default", 2, 28));
    g.drawString("Level: ", 10, 30);
    g.drawString(level, 92, 30);
    
    g.drawImage(chicken, playerposX, playerposY, 80, 100, null);
    
    g.drawImage(egg, eggposX, eggposY, 40, 50, null);
    

    g.drawImage(wheat, wheatposX, wheatposY, 9,45, null);
    g.drawImage(bigwheat, bigwheatposX, bigwheatposY, 15, 75, null);
    

    g.drawImage(cat, catposX, catposY, 80, 120, null);
    

        boolean drawImage = g.drawImage(basket_icon, 550, 7, 25, 25, null);
    g.drawString("Total Eggs: ", 350, 30);
    g.drawString(egg_count, 500, 30);
    

    g.drawString("Score: ", 825, 30);
    g.drawString(score_str, 910, 30);
    

    g.drawImage(basket_icon, basketX, basketY, 40, 40, null);
    
    if (is_dead)
    {
      g.setFont(new Font("default", 2, 50));
      g.drawString("GAME OVER", 330 , 325);
      g.setFont(new Font("default", 1, 15));
      g.drawString("Score:", 450, 350);
      g.drawString(score_str, 500, 350);
      g.drawString("Press Enter to Play Again.", 393, 375);
    }
    }



    g.dispose();
  }
  


  @Override
  public void actionPerformed(ActionEvent arg0)
  {
    timer.start();
    if (play)
    {


     
          
 
      
      if (((new Rectangle(catposX, catposY, 80, 120).intersects(new Rectangle(playerposX, playerposY, 80, 100))) && (!is_catched)) || (is_dead))
      {
        is_dead = true;
        is_catched = true;
        
        playerposY += 5;
      }
      


      //cat idi
      if (!is_dead) {
        if (new Rectangle(wheatposX, wheatposY, 9, 45).intersects(new Rectangle(eggposX, eggposY, 40, 50)))
        {
          score += 10;
          wheatposY = 1000;
          wheatposX = ((int)(Math.random() * 870.0D) + 10);
          eggposX = 1000;
          egg_fired = false;
          fire_button = false;
          egg_bottom = false;
                 

        score_str = String.valueOf(score);

         if (score == x || score == x+10)
          {
            level_number += 1;
            level = String.valueOf(level_number);
            mainSpeed += -1;
            x += 50;

          }
          

        
        }
        
        
        
        if (new Rectangle(catposX, catposY, 80, 120).intersects(new Rectangle(eggposX, eggposY, 40, 50)))
        {
          eggposX = 1000;
          egg_fired = false;
          fire_button = false;
          egg_bottom = false;
        
        }
        
        
        if (new Rectangle(bigwheatposX, bigwheatposY, 15, 75).intersects(new Rectangle(eggposX, eggposY, 40, 50)))
        {
          score += 20;
          bigwheatposY = 1000;
          bigwheatposX = ((int)(Math.random() * 870.0D) + 10);
          eggposX = 1000;
          egg_fired = false;
          fire_button = false;
          egg_bottom = false;
                 

        score_str = String.valueOf(score);
        
          if (score == x || score == x+10)
          {
            level_number += 1;
            level = String.valueOf(level_number);
            mainSpeed += -1;
            x += 50;
            
          }
          
        
        
        }
        
        
        
        
        
        
        
        

        if (new Rectangle(basketX, basketY, 40, 40).intersects(new Rectangle(playerposX, playerposY,80, 100)))
        {
          basketX = 1000;
          if (egg_c < 10) {
            egg_c += 3;
            basketX = 1000;
            if (egg_c > 10)
              egg_c = 10;
            egg_count = String.valueOf(egg_c);
          }
        }
        if (backgroundY > -1000) {
          backgroundY += -1;
        } else {
          System.out.println(backgroundY);
          backgroundY = 0;
        }
        if (egg_fired)
        {
          eggposY += 4;
          if (eggposY > 1000) {
            egg_bottom = true;
          }
          if (egg_bottom)
          {
            eggposY = 999;
            egg_fired = false;
            fire_button = false;
            egg_bottom = false;
          }
        }
        
        if (!basket_top)
        {
          basketY += mainSpeed;
          if (basketY < -25)
            basket_top = true;
          if (basket_top)
          {
            basket_top = false;
            basketX = ((int)(Math.random() * 900.0D) + 10);
            basketY = 1000;
          }
        }
        


        if (!cat_top)
        {
          catposY += mainSpeed;
          if (catposY < -150)
            cat_top = true;
          if (cat_top)
          {
            is_catched = false;
            cat_top = false;
            catposX = ((int)(Math.random() * 870.0D) + 5);
            catposY = 1000;
          }
        }
        


        if (!wheat_top) {
          wheatposY += mainSpeed;
          if (wheatposY < -60) {
            wheat_top = true;
          }
          
          

          if (wheat_top)
          {
            is_intersect = false;
            wheat_top = false;
            wheatposX = ((int)(Math.random() * 870.0D) + 10);
            wheatposY = 1000;
          }
        }
        
        
        
          if (!bigwheat_top) {
          bigwheatposY += mainSpeed;
          if (bigwheatposY < -150) {
            bigwheat_top = true;
          }
          
          

          if (bigwheat_top)
          {
            bigis_intersect = false;
            bigwheat_top = false;
            bigwheatposX = ((int)(Math.random() * 870.0D) + 10);
            bigwheatposY = 1000;
          }
        }
        
        
        
      }
    }
    
    repaint();
  }
  


  @Override
  public void keyPressed(KeyEvent arg0)
  {
    
    if ((arg0.getKeyCode() == 39) && (!is_dead) && is_started)
    {
        if (playerposX <= 880) {
       
        moveRight();
        }
      
    }
    if ((arg0.getKeyCode() == 37) && (!is_dead) && is_started)
    {
      if (playerposX <= 10) {
        playerposX = 10;
      } else {
        moveLeft();
      }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    if ((arg0.getKeyCode() == 40) && (!is_dead) && is_started)
    {
        mainSpeed -= 1;
    }
    
    if ((arg0.getKeyCode() == 38) && (!is_dead) && is_started)
    {
      if(mainSpeed < -1)
      mainSpeed += 1;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    if ((arg0.getKeyCode() == 32) && (!is_dead) && is_started)
    {

      if ((!fire_button) && (egg_c > 0)) {
        eggposX = playerposX + 15 ;
        eggposY = playerposY + 20 ;
        egg_c -= 1;
        egg_count = String.valueOf(egg_c);
        egg_fired = true;
        fire_button = true;
      }
      
    }
    else if ((arg0.getKeyCode() == 10) && (is_dead))
    {
      is_dead = false;
      x = 50;
      score = 0;
      egg_c = 3;
      level_number = 1;
      playerposX = 10;
      playerposY = 50;
      backgroundY = 0;
      wheatposX = ((int)(Math.random() * 870.0D) + 10);
      wheatposY = 1000;
      wheat_score = 10;
      bigwheatposX = ((int)(Math.random() * 870.0D) + 10);
      bigwheatposY = 1000;
      bigwheat_score = 20;
      eggposX = 1000;
      eggposY = playerposY;
      basketX = 155;
      basketY = -60;
      catposX = 155;
      catposY = -600;
      mainSpeed = -1;
      score_str = String.valueOf(score);
      level = String.valueOf(level_number);
      egg_count = String.valueOf(egg_c);
    }
    else if ((arg0.getKeyCode() == 10) && (!is_dead) && !is_started)
    {

      is_started = true;    }
    
  }
  
  


  public void moveRight()
  {
    play = true;
    playerposX += 20;
  }
  
  public void moveLeft()
  {
    play = true;
    playerposX -= 20;
  }
  
  @Override
  public void keyReleased(KeyEvent arg0) {}
  
  public void keyTyped(KeyEvent arg0) {}
}
