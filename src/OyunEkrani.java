import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame  {

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    
    
    public static void main(String[] args){
        
    OyunEkrani ekran = new OyunEkrani("Uzay Savasi");
    
    ekran.setResizable(false);
    ekran.setFocusable(false);
        
    ekran.setSize(1000,600); 
    ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
    Oyun oyun = new Oyun();        
    oyun.requestFocus();
    
    oyun.addKeyListener(oyun);                              //Klavyeden input alma
    oyun.setFocusable(true);                                //  
    oyun.setFocusTraversalKeysEnabled(false);               //
    ekran.add(oyun);                                        //Oyun ekranını oluşturma
    ekran.setVisible(true);
 
    }
 }
    

