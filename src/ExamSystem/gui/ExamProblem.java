package ExamSystem.gui;

import cn.edu.hbue.chenchenhui.data.*;

import java.awt.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class ExamProblem extends Frame{

    public final Button button;
    private final Button buttonA;
    private final Button buttonB;
    private final Button buttonC;
    private final Button buttonD;
    public TextArea textArea;

    int answerSizeW=100;
    int answerSizeH=100;

    int answerLocationX=100;
    float answerLocationXDistance= 1.5f;
    int answerLocationY=600;

    TeacherOne teacherOne=new TeacherOne();


    int nowProblem=0;

    public ExamProblem(){

        GiveTestPaper initTestPaper=new RandomInitTestPaper();
        TestPaper testPaper=initTestPaper.getTestPaper("D://交通理论.xls",5);
        Problem [] problem=testPaper.getAllProblem();

        this.setTitle("考试系统");
        this.setLocation(300,50);
        this.setSize(900,768);
        this.setLayout(null);


        this.addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent windowEvent) {}
            public void windowClosing(WindowEvent windowEvent) {System.exit(0);}
            public void windowClosed(WindowEvent windowEvent) {}
            public void windowIconified(WindowEvent windowEvent) {}
            public void windowDeiconified(WindowEvent windowEvent) {}
            public void windowActivated(WindowEvent windowEvent) {}
            public void windowDeactivated(WindowEvent windowEvent) {}
        });
        button=new Button("next");
        buttonA=new Button("A");
        buttonB=new Button("B");
        buttonC=new Button("C");
        buttonD=new Button("D");

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);



        button.setLocation((int) ((int)answerLocationX*answerLocationXDistance*5),answerLocationY);
        initButton(button,5);
        initButton(buttonA,1);
        initButton(buttonB,2);
        initButton(buttonC,3);
        initButton(buttonD,4);

        textArea=new TextArea();
        textArea.setSize(800,500);
        textArea.setLocation(50,50);


        //一开始判断如果题库中还存在题的话    就在判断框中，填入第一题的内容
       textArea.append("请开始答题");
        button.setLabel("Begin");

        buttonA.addActionListener(actionEvent -> {
            buttonColorInit( buttonA, buttonB, buttonC, buttonD);
            buttonA.setBackground(Color.blue);
            problem[nowProblem].setUserAnswer("A");
        });
        buttonB.addActionListener(actionEvent -> {
            problem[nowProblem].setUserAnswer("B");
            buttonColorInit( buttonA, buttonB, buttonC, buttonD);
            buttonB.setBackground(Color.blue);
        });
        buttonC.addActionListener(actionEvent -> {
            problem[nowProblem].setUserAnswer("C");
            buttonColorInit( buttonA, buttonB, buttonC, buttonD);
            buttonC.setBackground(Color.blue);
        });
        buttonD.addActionListener(actionEvent -> {
            problem[nowProblem].setUserAnswer("D");
            buttonColorInit( buttonA, buttonB, buttonC, buttonD);
            buttonD.setBackground(Color.blue);
        });


        button.addActionListener(actionEvent -> {
            if(button.getLabel()!="Submit"){
                button.setLabel("NEXT");
            }
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            //清空颜色
            buttonColorInit( buttonA, buttonB, buttonC, buttonD);

            if(problem[nowProblem].getUserAnswer()!=""){
                nowProblem++;
            }
            if(nowProblem==problem.length-1){
                button.setLabel("Submit");
            }

            if (nowProblem<problem.length){
                if(problem[nowProblem].isJudge()){

                    buttonC.setVisible(false);
                    buttonD.setVisible(false);
                    inputOne(problem[nowProblem]);
                }
                else if(problem[nowProblem].isChoice()){
                    buttonC.setVisible(true);
                    buttonD.setVisible(true);
                    inputTwo(problem[nowProblem]);
                }
            }
            else {
                //交卷
                teacherOne.giveTestPaperScore(testPaper);
                buttonColorInit( buttonA, buttonB, buttonC, buttonD);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
                buttonC.setEnabled(false);
                buttonD.setEnabled(false);
                button.setEnabled(false);
            }
        });

        this.add(button);
        this.add(buttonA);
        this.add(buttonB);
        this.add(buttonC);
        this.add(buttonD);
        this.add(textArea);
    }
    private void buttonColorInit(Button buttonA,Button buttonB,Button buttonC,Button buttonD){
        buttonA.setBackground(null);
        buttonB.setBackground(null);
        buttonC.setBackground(null);
        buttonD.setBackground(null);
    }
    private void initButton(Button button,int k){
        button.setSize(answerSizeW,answerSizeH);
        button.setLocation((int) ((int)answerLocationX*answerLocationXDistance*k),answerLocationY);
    }
    public void inputOne(Problem problem){
        textArea.append(problem.getContent()+"\n");
        textArea.append(problem.getGiveChoiceA());
        textArea.append(problem.getGiveChoiceB()+"\n");
    //    textArea.append("\n图像的名字"+problem.getImageName());
    //    textArea.append("\n正确答案"+problem.getCorrectAnswer()+"\n");
    }
    public void inputTwo(Problem problem){
        textArea.append(problem.getContent()+"\n");
        textArea.append(problem.getGiveChoiceA());
        textArea.append(problem.getGiveChoiceB());
        textArea.append(problem.getGiveChoiceC());
        textArea.append(problem.getGiveChoiceD()+"\n");
    //    textArea.append("\n图像的名字"+problem.getImageName());
    //    textArea.append("\n正确答案"+problem.getCorrectAnswer()+"\n");
    }
}
