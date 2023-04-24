package ExamSystem.gui;

import cn.edu.hbue.chenchenhui.data.Problem;
import jxl.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.PublicKey;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Label;

import javax.swing.*;
import java.io.*;

public class AddProblem extends Frame {
    public TextField problemTitle;
    public Button addProblemButton;
    public TextField answerA;
    public TextField answerB;
    public TextField answerC;
    public TextField answerD;

    public TextField currentAnswer;

    static int i=0;//i是行
    static int j = 0; //j是列

    public AddProblem() {
        problemTitle = new TextField("请在此处添加题目");
        problemTitle.setSize(400, 50);
        problemTitle.setLocation(20, 50);

        answerA = new TextField("A:");
        answerA.setSize(100, 50);
        answerA.setLocation(20, 140);

        answerB = new TextField("B:");
        answerB.setSize(100, 50);
        answerB.setLocation(120, 140);

        answerC = new TextField("C:");
        answerC.setSize(100, 50);
        answerC.setLocation(220, 140);

        answerD = new TextField("D:");
        answerD.setSize(100, 50);
        answerD.setLocation(320, 140);

        currentAnswer=new TextField("请输入正确答案");
        currentAnswer.setSize(100,50);
        currentAnswer.setLocation(70,200);



        this.addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent windowEvent) {}
            public void windowClosing(WindowEvent windowEvent) {System.exit(0);}
            public void windowClosed(WindowEvent windowEvent) {}
            public void windowIconified(WindowEvent windowEvent) {}
            public void windowDeiconified(WindowEvent windowEvent) {}
            public void windowActivated(WindowEvent windowEvent) {}
            public void windowDeactivated(WindowEvent windowEvent) {}
        });


        this.setTitle("添加考试题目");
        this.setLocation(500, 250);
        this.setSize(500, 300);
        this.setLayout(null);

        Button addProblemButton = new Button("ADD PROBLEM");
        addProblemButton.setSize(100, 50);
        addProblemButton.setLocation(250, 200);

        this.add(problemTitle);
        this.add(answerA);
        this.add(answerB);
        this.add(answerC);
        this.add(answerD);
        this.add(currentAnswer);
        this.add(addProblemButton);

        addProblemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                userAddProblem(problemTitle.getText(),answerA.getText(),answerB.getText(),answerC.getText(),answerD.getText());
            }
        });
    }
    public  void userAddProblem(String problemTitle,String answerA,String answerB,String answerC,String answerD){
     /*   System.out.println(problemTitle);
        System.out.println(answerA);
        System.out.println(answerB);
        System.out.println(answerC);
        System.out.println(answerD);*/

        File file = new File("D:\\交通理论.xls");//文件流
        //读Excel
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            WritableWorkbook writeWorkbook = Workbook.createWorkbook(new File("D:\\交通理论.xls"));
            WritableSheet writeSheet = writeWorkbook.createSheet("sheet1", 0);

            Label label;
            for (i = 0; i < sheet.getRows(); i++) {//行
                for (j = 0; j < sheet.getColumns(); j++) {//列
                    Cell cell = sheet.getCell(j, i);//这里注意，是(j,i)
                    label = new Label(j, i,cell.getContents());
                    writeSheet.addCell(label);
                    System.out.println(label.getContents());
                }
            }

            //添加新的数据
            //如果题目为空，则添加失败

            for (j = 0; j < sheet.getColumns(); j++) {//列
                if(problemTitle.equals("")){
                    JOptionPane.showMessageDialog(null,"题目为空,添加失败！","消息对话框",JOptionPane.WARNING_MESSAGE);
                    return;
                }


                if(j==0){
                    label = new Label(j, i,problemTitle);
                    writeSheet.addCell(label);
                }
                if(j==1){
                    label = new Label(j, i,currentAnswer.getText());
                    writeSheet.addCell(label);
                }
                if(j==2){
                    label = new Label(j, i,answerA);
                    writeSheet.addCell(label);
                }
                if(j==3){
                    label = new Label(j, i,answerB);
                    writeSheet.addCell(label);
                    j=6;
                }
                if(j==6){
                    if(answerC.equals("C:")&&answerD.equals("D:")){
                        //是判断题
                        label = new Label(j, i,"p");
                        writeSheet.addCell(label);
                        break;
                    }
                    else {
                        label = new Label(j, i,"x");
                        writeSheet.addCell(label);
                        j=4;
                    }

                }
                if(j==4){
                    label = new Label(j, i,answerC);
                    writeSheet.addCell(label);
                }
                if(j==5){
                    label = new Label(j, i,answerD);
                    writeSheet.addCell(label);
                    break;
                }

            }



            workbook.close();    //将工作簿的资源关闭
            writeWorkbook.write();
            //一定要关闭，不然不会写入文件内。
            writeWorkbook.close();
            JOptionPane.showMessageDialog(null,"添加试题成功！","消息对话框",JOptionPane.PLAIN_MESSAGE);


        } catch (IOException | BiffException | WriteException e) {
            JOptionPane.showMessageDialog(null,"添加失败！","消息对话框",JOptionPane.WARNING_MESSAGE);

            e.printStackTrace();
        }
    }
}

