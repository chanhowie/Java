package pintu.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class Game extends JFrame implements KeyListener {
    JFrame jFrame=new JFrame("Game");
    int [][] arr=new int [4][4];
    final int blankPicture=15;
    boolean isPress=false;
    long begin=System.currentTimeMillis();
    long end=System.currentTimeMillis();
    long biginsubend=begin-end;

    int x=0;
    int y=0;
    public Game(){
        initJframe();
        makeRandom();
        displaypicture();
        jFrame.addKeyListener(this);
    }

    private void makeRandom() {
        Random random=new Random();
        int [] temparr=new int [16];
        int index=0;
        //初始赋值
        for (int i = 0; i < 16; i++) {
            temparr[i]=i;
        }

        for (int i = 0; i < 16; i++) {
            int tmpnumber=random.nextInt(16);
            int tmp=temparr[i];
            temparr[i]=temparr[tmpnumber];
            temparr[tmpnumber]=tmp;
        }

        for (int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                arr[index/4][index%4]=temparr[index];
                //设置空白图片的位置
                if(temparr[index]==blankPicture){
                    y=index/4;
                    x=index%4;
                }
                index++;
            }
        }
    }

    private void initJframe() {

        jFrame.setSize(684,820);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private void displaypicture() {
        this.jFrame.getContentPane().removeAll();
        JLabel jLabel1=new JLabel(String.valueOf(biginsubend));
        jLabel1.setBounds(0,0,684,100);
        jLabel1.setFont(new Font("微软雅黑",1,40));
        jFrame.getContentPane().add(jLabel1);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon icon=new ImageIcon("src/pintu/picture/zjm1/"+arr[i][j]+".jpg");
                JLabel jLabel=new JLabel(icon);
                jLabel.setBounds(j*150,100+i*150,150,150);
                jLabel.setBorder(new BevelBorder(1));
                this.jFrame.getContentPane().add(jLabel);
            }
        }
        this.jFrame.getContentPane().repaint();
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode()==65){
            if(!isPress){
                isPress=true;
                this.jFrame.getContentPane().removeAll();
                int arrtemp[][]={{0,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        ImageIcon icon=new ImageIcon("src/pintu/picture/zjm1/"+arrtemp[i][j]+".jpg");
                        JLabel jLabel=new JLabel(icon);
                        jLabel.setBounds(j*150,i*150,150,150);
                        jLabel.setBorder(new BevelBorder(1));
                        this.jFrame.getContentPane().add(jLabel);
                    }
                }
                System.out.println("ok");
                this.jFrame.getContentPane().repaint();
                //显示完整的

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
         end=System.currentTimeMillis();
         biginsubend=(end-begin)/1000;

        if(keyEvent.getKeyCode()==65){
            if(isPress){
                isPress=false;
                System.out.println("huifu");
                //显示原来的
                displaypicture();
            }
        }

        if(keyEvent.getKeyCode()==66){
            int index=0;
            x=3;
            y=3;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    arr[i][j]=index;
                    index++;
                }
            }
            displaypicture();
        }



        //左37
        if(keyEvent.getKeyCode()==37)
        {

            if(x==3){
                return;
            }
            arr[y][x]=arr[y][x+1];
            arr[y][x+1]=blankPicture;
            x++;
            displaypicture();
        }

        else if(keyEvent.getKeyCode()==38)
        {
            if(y==3){
                return;
            }
            arr[y][x]=arr[y+1][x];
            arr[y+1][x]=blankPicture;
            y++;
            displaypicture();
        }
        else if(keyEvent.getKeyCode()==39)
        {
            if(x==0){
                return;
            }
            arr[y][x]=arr[y][x-1];
            arr[y][x-1]=blankPicture;
            x--;
            displaypicture();

        }else if(keyEvent.getKeyCode()==40)
        {
            if(y==0){
                return;
            }
            arr[y][x]=arr[y-1][x];
            arr[y-1][x]=blankPicture;
            y--;
            displaypicture();

        }


    }
}
