
package magicalride;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Anim
{
    int x1,x2,y1,y2;
}

public class Game extends JPanel
{
    // DEKLARASI DAN INITIALISASI == VARIABLE UTAMA
    
    // frame size
    int FrameWidth = 800, FrameHeight = 500;
    
    // Speed untuk atur background, monster, sama coin
    int speed = 3;
    
    //background
    Point bg1 = new Point(0, 0);
    Point bg2 = new Point(0 , FrameHeight - 130 ); // 130 height dari image7
    
    //character animation
    Point character = new Point( 50 , 0);
    int CharAnimIndex = 40;
    int CharDividenIndex = 20;
    Vector<Anim> CharAnimation = new Vector<Anim>();
    
    // monster animation
    Point monster = new Point( FrameWidth, 300); // harus dibuat vector biar bisa banyak
    int MonsAnimIndex = 40;
    int MonsDividenIndex = 20;
    Vector<Anim> MonsAnimation = new Vector<Anim>();
    
    //Coin Animation
    Point coin = new Point( FrameWidth, 100); // harus dibuat vector biar bisa banyak
    
    int CoinAnimIndex = 40;
    int CoinDividenIndex = 20;
    Vector<Anim> CoinAnimation = new Vector<Anim>();
    
    // control
    // character control
    boolean isfly = false;
    
    
    // Collision
    Rectangle PlayerRect;
    
    // harus dibuat menjadi vector biar bisa banyak
    Rectangle MonsterRect, CoinRect; 
    // bollean intersect buat check collision
    boolean hitMonster = true;
    boolean hitCoin = true;
    
    // Timer Interval Summon Coin dan Monster
    long lastTime, currTime;
    
    // Thread untuk menjalankan process
    Thread thread = new Thread()
    {

        @Override
        public void run() 
        {
            while(true)
            {
                repaint();
                try
                {
                    Thread.sleep(10);
                }
                catch(Exception e)
                {
                    
                }
                
            }
            
        }
        
    };
    
    // contructor
    Game()
    {
        // char animasi
        CharAnimation.add(new Anim());
        CharAnimation.add(new Anim());
        
        // initialisasi vector animasi player
        // index 0
        CharAnimation.get(0).x1 = 1408;
        CharAnimation.get(0).y1 = 966;
        
        CharAnimation.get(0).x2 = 1518;
        CharAnimation.get(0).y2 = 1039;
        
        //index 1
        CharAnimation.get(1).x1 = 1083;
        CharAnimation.get(1).y1 = 965;
        
        CharAnimation.get(1).x2 = 1188;
        CharAnimation.get(1).y2 = 1040;
        
        // Monster
        // initialisasi vector animasi monster
        //index 0
        MonsAnimation.add(new Anim());
        MonsAnimation.get(0).x1 = 6;
        MonsAnimation.get(0).y1 = 268;
        MonsAnimation.get(0).x2 = 172;
        MonsAnimation.get(0).y2 = 418;
        // index 1
        MonsAnimation.add(new Anim());
        MonsAnimation.get(1).x1 = 175;
        MonsAnimation.get(1).y1 = 265;
        MonsAnimation.get(1).x2 = 352;
        MonsAnimation.get(1).y2 = 435;
        
        //coin
        // index 0
        CoinAnimation.add(new Anim());
        CoinAnimation.get(0).x1 = 0;
        CoinAnimation.get(0).y1 = 0;
        CoinAnimation.get(0).x2 = 68;
        CoinAnimation.get(0).y2 = 70;
        //index1
        CoinAnimation.add(new Anim());
        CoinAnimation.get(1).x1 = 0;
        CoinAnimation.get(1).y1 = 70;
        CoinAnimation.get(1).x2 = 68;
        CoinAnimation.get(1).y2 = 140;
        
        
        lastTime = (System.currentTimeMillis())/1000;
        
        // mulai animasi
        thread.start();
    }
    
    // FUNGSI BUAT COIN
    // fungsi buat reset posisi coin
    public void initCoin()
    {
        coin.x = FrameWidth;
        // posisi y harus dirandom
        Random rand = new Random();
        coin.y = rand.nextInt((FrameHeight-50)); // 50 karena ukuran coin
    }
    // fungsi gambar coin
    public void drawCoinImage(Graphics2D g2d)
    {
        if(CoinAnimIndex > 0)
        {
            CoinAnimIndex --;
        }
        else
        {
            CoinAnimIndex = 39;
        }
        g2d.drawImage( new ImageIcon("assets/play/image 16.png").getImage(),
                coin.x, coin.y, coin.x+50, coin.y+50, 
                CoinAnimation.get((CoinAnimIndex/CoinDividenIndex)).x1, CoinAnimation.get((CoinAnimIndex/CoinDividenIndex)).y1, 
                CoinAnimation.get((CoinAnimIndex/CoinDividenIndex)).x2, CoinAnimation.get((CoinAnimIndex/CoinDividenIndex)).y2, this);
        
        // coin ikut bergerak dengan background
        if( coin.x > (0-50) ) // 50 karena ukuran coin adalah 50
        {
            coin.x -=speed;
        }
        else
        {
            initCoin();
        }
    }
    
    // FUNGSI BUAT MONSTER
    // fungsi buat reset posisi monster
    public void initMonster()
    {
         monster.x = FrameWidth;
         // posisi y monster harus dirandom
         Random rand = new Random();
         monster.y = rand.nextInt((FrameHeight-100)); // 100 karena ukuran monster adalah 100
    }
    // fungsi gambar monster
    public void drawImageMonster(Graphics2D g2d)
    {
        if(MonsAnimIndex > 0)
        {
            MonsAnimIndex --;
        }
        else
        {
            MonsAnimIndex = 39;
        }
        
        g2d.drawImage( new ImageIcon("assets/play/image 13.png").getImage(),
                monster.x, monster.y, monster.x+100, monster.y+100, 
                MonsAnimation.get((MonsAnimIndex/MonsDividenIndex)).x1, MonsAnimation.get((MonsAnimIndex/MonsDividenIndex)).y1, 
                MonsAnimation.get((MonsAnimIndex/MonsDividenIndex)).x2, MonsAnimation.get((MonsAnimIndex/MonsDividenIndex)).y2, this);
        
        // monster ikut bergerak dengan background
        if( monster.x > (0-100) ) // 100 karena ukuran x dari monster
        {
            monster.x -=speed;
        }
        else
        {
           initMonster();
        }
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        
        // BACKGROUND
        g2d.drawImage( new ImageIcon("assets/bg/image3.jpg").getImage(), bg1.x, bg1.y, this);
        g2d.drawImage( new ImageIcon("assets/bg/image3.jpg").getImage(), bg1.x + FrameWidth, bg1.y, this);
        
        g2d.drawImage( new ImageIcon("assets/bg/image7.png").getImage(), bg2.x, bg2.y, this);
        g2d.drawImage( new ImageIcon("assets/bg/image7.png").getImage(), bg2.x + FrameWidth, bg2.y, this);
       
        // 3 speed bg belakang, 10 speed bg depan
        bg1.x-=speed;
        if(bg1.x<=-FrameWidth)
        {
            bg1.x=0;
        }
        
        bg2.x-=speed+7;
        if(bg2.x<=-FrameWidth)
        {
            bg2.x=0;
        }
        
        
        
        // PLAYER
        // 75 = index besar ukuran character + 25 belum tau dari mana
        if(character.y < FrameHeight - 100  && !isfly)
        {
            character.y += 4;
        }
        if(isfly)
        {
            if(character.y > 0)
            {
                character.y-=4;
            }
        }
        
        // animasi player
        if(CharAnimIndex > 0 )
        {
            CharAnimIndex --;
        }
        else
        {
            CharAnimIndex = 39;
        }
        
        g2d.drawImage( new ImageIcon("assets/play/image10.png").getImage(),
                character.x, character.y, character.x+75, character.y+75, 
                CharAnimation.get((CharAnimIndex/CharDividenIndex)).x1, CharAnimation.get((CharAnimIndex/CharDividenIndex)).y1, 
                CharAnimation.get((CharAnimIndex/CharDividenIndex)).x2, CharAnimation.get((CharAnimIndex/CharDividenIndex)).y2, this);
        
        // animasi monster
        // dibuat menjadi fungsi
        // drawImageMonster(g2d);
        if( !hitMonster )
        {
            drawImageMonster(g2d);
        }
        
        if( !hitCoin )
        {
            drawCoinImage(g2d);
        }
        
        // Animasi Coin
        // dibuat menjadi fungsi
        // drawCoinImage(g2d);
        
        //check collision
        // buat kotak mengelilingi character
       PlayerRect = new Rectangle(character.x, character.y, 75, 75); // 75 adalah ukuran Player
       MonsterRect = new Rectangle(monster.x, monster.y, 100, 100); // 100 adalah ukuran monster
       CoinRect = new Rectangle(coin.x, coin.y, 50, 50); // 50 adalah ukuran dari coin
       
       // collision player dengan monster
       if ( PlayerRect.intersects(MonsterRect) )
       {
           hitMonster = true;
           initMonster();
       }
       
       // collision player dengan coin
       if( PlayerRect.intersects(CoinRect) )
       {
           hitCoin = true;
           initCoin();
       }
       
       // interval time buat summon monster dan coin lagi :
       currTime = (System.currentTimeMillis())/1000;
       
       if( (currTime - lastTime) >= 1 )
       {
           hitMonster = false;
           hitCoin = false;
           lastTime = currTime;
       }
    }
}
