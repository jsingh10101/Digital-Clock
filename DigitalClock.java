import java.applet.Applet;
import java.awt.*;
//<Applet code="DigitalClock.java" width=400 height=300></Applet>
public class DigitalClock extends Applet {
    @Override
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void paint(Graphics g){
        long l=System.currentTimeMillis();
        l/=1000;
        l%=(24*3600);
        int min=((int)l/60)%60+30;
        int hr=(int)l/3600+5;
        if(min>=60){
            hr++;
            min-=60;
        }
        if(hr>=24)
            hr-=24;
        int sec=(int)l%60;
        int j=33;
        if(hr>12) {
            hr -= 12;
            j=99;
        }
        else if(hr==0)
            hr=12;
        drawer(br(hr/10),g,1);
        drawer(br(hr%10),g,2);
        drawer(null,g,3);
        drawer(br(min/10),g,4);
        drawer(br(min%10),g,5);
        drawer(null,g,6);
        drawer(br(sec/10),g,7);
        drawer(br(sec%10),g,8);
        drawer(br(j),g,10);
        drawer(br(65),g,11);
        drawer(br(66),g,12);
    }
    private boolean[] br(int n){
        boolean[] b={true,true,true,true,true,true,true};
        if(n==0)
            b[6]=false;
        else if(n==1)
            b[0]=b[5]=b[4]=b[6]=b[3]=false;
        else if (n==2)
            b[5]=b[2]=false;
        else if(n==3)
            b[5]=b[4]=false;
        else if(n==4)
            b[0]=b[4]=b[3]=false;
        else if (n==5)
            b[1]=b[4]=false;
        else if (n==6)
            b[1]=false;
        else if (n==7)
            b[6]=b[5]=b[4]=b[3]=false;
        else if (n==8)
            b[0]=true;
        else if (n==9)
            b[4]=false;
        else if (n==33)
            b[3]=false;
        else if(n==65)
            b[2]=b[3]=b[6]=false;
        else if(n==66)
            b[4]=b[3]=b[6]=false;
        else if(n==99)
            b[2]=b[3]=false;
        return b;
    }

    private void drawer(boolean[] b,Graphics g,int gap){
        int y1=140,y2=150,y3=160,x1=120+15*--gap,x2=130+15*--gap;
        if(b==null){
            g.fillRect(x1-2,y1+3,3,3);
            g.fillRect(x1-2,y1+13,3,3);
        }
        else {
            if (!(b[4] || b[3] || b[6])&&b[5]) {
                x1 -= 10;
                x2 -= 10;
            }
            if (b[0])
                g.drawLine(x1, y1, x2, y1);
            if (b[5])
                g.drawLine(x2, y1, x2, y2);
            if (b[4])
                g.drawLine(x2, y2, x2, y3);
            if (b[3])
                g.drawLine(x1, y3, x2, y3);
            if (b[2])
                g.drawLine(x1, y2, x1, y3);
            if (b[1])
                g.drawLine(x1, y1, x1, y2);
            if (b[6])
                g.drawLine(x1, y2, x2, y2);
        }
    }
}
