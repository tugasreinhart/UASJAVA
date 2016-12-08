package Snake;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {
	
	public Snake() {

        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Snake();
                ex.setVisible(true);                
            }
        });
    }
}
