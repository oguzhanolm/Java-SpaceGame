import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Oyun extends JPanel implements KeyListener,ActionListener {

    
    Timer timer = new Timer(7,this);
    private int gecen_sure=0;
    private int harcanan_ates=0;    
      
    private BufferedImage uzayGemisi;
    private BufferedImage uzayli;
    private BufferedImage arkaPlan;
    private BufferedImage baslangic;
    
    private BufferedImage gemiKalanCan1;
    private BufferedImage gemiKalanCan2;
    private BufferedImage gemiKalanCan3;
    private BufferedImage uzayliKalanCan1;
    private BufferedImage uzayliKalanCan2;
    private BufferedImage uzayliKalanCan3;
    
    private BufferedImage missile1;
    private BufferedImage missile2;
    private BufferedImage missile3;
    private BufferedImage missile4;
    private BufferedImage missile5;

    
    
    // Birden fazla ateş edilebilmesi için bu arrayListi oluşturuyoruz
    private ArrayList<Ates> atesler = new ArrayList<>();
    private ArrayList<AtesUzayli> uzayliAtes = new ArrayList<>();

    
    
    //Buradaki int değerler uzaylı ve uzay gemimizin konumunu belirleyecek olan değişkenler   
    private int atesDirY =5;
    private int uzayliAtesDirY = 5;
    private int uzayliX = 700; 
    private int uzayliDirX = 5;    
    private int uzayGemisiX = 0;
    private int DirUzayX = 20;

    
     
    private int uzayliCan=1;
    private int gemiCan=1;
    
    private int atesReload=1;
   
    
    //Hedefin vurulduğunu kontrol eder
    public boolean kontrolSavas()
    {     
        return  atesler.stream().filter((ates) -> (ates.getY()== 70)).anyMatch((ates) -> (ates.getX()== uzayliX ||ates.getX()== uzayliX+5||ates.getX()== uzayliX+10||ates.getX()== uzayliX+15||ates.getX()== uzayliX+20||ates.getX()== uzayliX+25||ates.getX()== uzayliX+30||ates.getX()== uzayliX+35||ates.getX()== uzayliX+40||ates.getX()== uzayliX+45||ates.getX()== uzayliX+50||ates.getX()== uzayliX+55));
    }
    public boolean kontrolRakip()
    {                
        return  uzayliAtes.stream().filter((ates) -> (ates.getY()==510)).anyMatch((ates) -> (ates.getX()== uzayGemisiX ||ates.getX()== uzayGemisiX+5||ates.getX()== uzayGemisiX+10||ates.getX()== uzayGemisiX+15||ates.getX()== uzayGemisiX+20||ates.getX()== uzayGemisiX+25||ates.getX()== uzayGemisiX+30||ates.getX()== uzayGemisiX+35||ates.getX()== uzayGemisiX+40||ates.getX()== uzayGemisiX+45||ates.getX()== uzayGemisiX+50||ates.getX()== uzayGemisiX+55));
    }
    
    
    
    public Oyun() {  
            
        
        try {
            arkaPlan = ImageIO.read(new FileImageInputStream(new File("spacebg.jpg")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            baslangic = ImageIO.read(new FileImageInputStream(new File("baslangic.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            uzayGemisi = ImageIO.read(new FileImageInputStream(new File("ship3.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }        
        try {
            uzayli = ImageIO.read(new FileImageInputStream(new File("alien.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        //Missile Imajları
        try {
            missile1 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile2 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile3 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile4 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile5 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        
        
        //Can Barı Imajları
        try {
            gemiKalanCan1 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {

        }
                try {
            gemiKalanCan2 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {

        }
        try {
            gemiKalanCan3 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
           
        }
        try {
            uzayliKalanCan1 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
          
        }
        try {
            uzayliKalanCan2 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
            
        }
        try {
            uzayliKalanCan3 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
           
        }
                                     
        }

    

    @Override
    public void paint(Graphics g) {
        super.paint(g);        
        gecen_sure += 5;
        
        g.drawImage(arkaPlan,0 ,0, null);
        g.drawImage(uzayli,uzayliX,0,uzayli.getWidth()/20,uzayli.getHeight()/20,this);                                                          
        g.drawImage(uzayGemisi,uzayGemisiX,500,uzayGemisi.getWidth()/5,uzayGemisi.getHeight()/5,this);     
        
        if(baslangic!=null){g.drawImage(baslangic,40, 150,baslangic.getWidth(),baslangic.getHeight(), this);}

        if(uzayliKalanCan1!=null){g.drawImage(uzayliKalanCan1, 860,30,uzayliKalanCan1.getWidth()/5,uzayliKalanCan1.getHeight()/5, this);}              
        if(uzayliKalanCan2!=null){g.drawImage(uzayliKalanCan2, 890,30,uzayliKalanCan2.getWidth()/5,uzayliKalanCan2.getHeight()/5, this);}         
        if(uzayliKalanCan3!=null){g.drawImage(uzayliKalanCan3, 920,30,uzayliKalanCan3.getWidth()/5,uzayliKalanCan3.getHeight()/5, this);}       
        if(gemiKalanCan1!=null){g.drawImage(gemiKalanCan1, 860,510,gemiKalanCan1.getWidth()/5,gemiKalanCan1.getHeight()/5, this);}              
        if(gemiKalanCan2!=null){g.drawImage(gemiKalanCan2, 890,510,gemiKalanCan2.getWidth()/5,gemiKalanCan2.getHeight()/5, this);}              
        if(gemiKalanCan3!=null){g.drawImage(gemiKalanCan3, 920,510,gemiKalanCan3.getWidth()/5,gemiKalanCan3.getHeight()/5, this);}              

        if(missile1!=null) {g.drawImage(missile1, 860, 460, missile1.getWidth()/3,missile1.getHeight()/3,this);}
        if(missile2!=null) {g.drawImage(missile2, 875, 460, missile2.getWidth()/3,missile2.getHeight()/3,this);}
        if(missile3!=null) {g.drawImage(missile3, 890, 460, missile3.getWidth()/3,missile3.getHeight()/3,this);}
        if(missile4!=null) {g.drawImage(missile4, 905, 460, missile4.getWidth()/3,missile4.getHeight()/3,this);}
        if(missile5!=null) {g.drawImage(missile5, 920, 460, missile5.getWidth()/3,missile5.getHeight()/3,this);}
        
        //Ateş ettiğimizde bunun resimi çiziyor
        g.setColor(Color.blue);                                                                                                                 
        atesler.forEach((ates)      -> {g.fillRect(ates.getX(), ates.getY(),5 , 10);});                                                         
        uzayliAtes.forEach((ates)   -> {g.fillRect(ates.getX(), ates.getY(), 5, 10);});

        g.setColor(Color.ORANGE);
        g.drawLine(825,0, 825, 825);
        g.drawLine(830,0, 830, 830);
        g.drawLine(835,0, 835, 835);
        g.drawLine(840,0, 840, 840);
        
                   
         if (kontrolSavas()){                                                                                                                    
         if(uzayliCan==1){uzayliKalanCan1=null;}                                                                                                
         if(uzayliCan==2){uzayliKalanCan2=null;}                                                                                                
         if(uzayliCan==3)                                                                                                                       
            {                                                                                                                                   
                uzayliKalanCan3=null;            
                timer.stop();
                Font fnt0 = new Font("arial", Font.BOLD,50);                                                                                    
                g.setFont(fnt0);
                g.setColor(Color.lightGray);
                g.drawString("Tebrikler Kazandınız!", 220, 250);
                Font fnt1 = new Font("arial", Font.BOLD,30);
                g.setFont(fnt1);
                g.drawString("Yeniden Başlamak için ENTER tuşuna basınız", 150, 350);
                Font fnt2 = new Font("arial", Font.BOLD,20);                                                                                    
                g.setFont(fnt2);
                g.setColor(Color.ORANGE);
                g.drawString("Harcanan Mermi: "+harcanan_ates, 380, 430);
                g.drawString("Geçen Süre       : "+gecen_sure/1000.0 ,380, 460);
                uzayliCan=1;                                                                                                                   
            }
         else 
         {
           System.out.println("Uzaylı vuruldu");
           uzayliCan++;
         }
           
        }
        if(kontrolRakip()){           
            if(gemiCan==1){gemiKalanCan1=null;}
            if(gemiCan==2){gemiKalanCan2=null;}                                                                                                 
            if(gemiCan==3)
                {
                    gemiKalanCan3=null;
                    timer.stop();
                    Font fnt0 = new Font("arial", Font.BOLD,50);
                    g.setFont(fnt0);
                    g.setColor(Color.lightGray);
                    g.drawString("Maalesef Kaybettiniz!", 220, 250);
                    Font fnt1 = new Font("arial", Font.BOLD,30);
                    g.setFont(fnt1);
                    g.drawString("Yeniden Başlamak için ENTER tuşuna basınız", 150, 350);
                    Font fnt2 = new Font("arial", Font.BOLD,20);                                                                                
                    g.setFont(fnt2);
                    g.setColor(Color.ORANGE);
                    g.drawString("Hayatta Kalma Süreniz: "+gecen_sure/1000.0 ,380, 430);
                    gemiCan=1;
                }
           else
           {
                System.out.println("Gemi vuruldu");
                gemiCan++;
                
           }
        }                                                                                                                                       //__________________________________                                                                            


        
                
    }
   
      
    @Override
    public void keyTyped(KeyEvent e) {        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        switch (c) {
            case KeyEvent.VK_LEFT:
                if (uzayGemisiX <= 0){
                    uzayGemisiX = 0;                   
                }
                else {
                    uzayGemisiX -= DirUzayX;
                }   break;
            case KeyEvent.VK_RIGHT:
                if (uzayGemisiX >= 710){
                    uzayGemisiX = 710;                 
                }
                else {
                    uzayGemisiX += DirUzayX;
                }   break;
            case KeyEvent.VK_SPACE:                       
        switch (atesReload) {
            case 1:
                atesler.add(new Ates(uzayGemisiX+35, 500));
                harcanan_ates++;
                missile1=null;
                atesReload++;
                break;
            case 2:
                atesler.add(new Ates(uzayGemisiX+35, 500));
                harcanan_ates++;
                missile2=null;
                atesReload++;
                break;
            case 3:
                atesler.add(new Ates(uzayGemisiX+35, 500));
                harcanan_ates++;
                missile3=null;
                atesReload++;
                break;
            case 4:
                atesler.add(new Ates(uzayGemisiX+35, 500));
                harcanan_ates++;
                missile4=null;
                atesReload++;
                break;
            case 5:
                atesler.add(new Ates(uzayGemisiX+35, 500));
                harcanan_ates++;
                missile5=null;
                atesReload++;
                break;
            default:
                break;
        }
                    break;
                       
            case KeyEvent.VK_R:
//_____________________________________
              atesReload=1;  
        try {
            missile1 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile2 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile3 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile4 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile5 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {
            
        }        
//_____________________________________   
            break;
                
                
            case KeyEvent.VK_ENTER:
                timer.restart();
                uzayliX = 700; 
                uzayGemisiX = 0;
                atesReload=1;
                uzayliCan=1;
                gemiCan=1;
                baslangic=null;
//_____________________________________                
        try {
            gemiKalanCan1 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {

        }
        try {
            gemiKalanCan2 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {

        }
        try {
            gemiKalanCan3 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
           
        }
        try {
            uzayliKalanCan1 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
          
        }
        try {
            uzayliKalanCan2 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
            
        }
        try {
            uzayliKalanCan3 = ImageIO.read(new FileImageInputStream(new File("heal.png")));
        } catch (IOException ex) {
           
        }
//_____________________________________
                
        try {
            missile1 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile2 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile3 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile4 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {

        }
        try {
            missile5 = ImageIO.read(new FileImageInputStream(new File("missile.png")));
        } catch (IOException ex) {
            
        }        
            break;
                    
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                    break;
            default:
                    break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        uzayliX += uzayliDirX;
        
        
        Random sayac = new Random();
     

        int atesSayac = sayac.nextInt(75);
     
        
        if (atesSayac==0){
        uzayliAtes.add(new AtesUzayli(uzayliX+35,75));
        atesSayac=0;
        }
        else atesSayac--;
        
        atesler.forEach((ates) -> {
            ates.setY(ates.getY()-atesDirY);            
        });
        uzayliAtes.forEach((ates) -> {
            ates.setY(ates.getY()+uzayliAtesDirY);
        });
        
        

                
        
        if (uzayliX >= 750) {
            uzayliDirX = -uzayliDirX;
        }
        if (uzayliX <= 0)   {
            uzayliDirX = -uzayliDirX;
        }

        
        repaint();
    
    }
    
    
}
