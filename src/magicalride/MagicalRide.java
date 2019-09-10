
package magicalride;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MagicalRide implements KeyListener{

    JFrame frame = new JFrame();
    Game g = new Game();
    
    MagicalRide()
    {
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(g);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
       new MagicalRide();
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        if( e.getKeyCode() == e.VK_SPACE )
        {
            g.isfly = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if( e.getKeyCode() == e.VK_SPACE )
        {
            g.isfly = false;
        }
    }
}
