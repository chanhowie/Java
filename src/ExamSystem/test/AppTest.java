package ExamSystem.test;
import cn.edu.hbue.chenchenhui.gui.AddProblem;
import cn.edu.hbue.chenchenhui.gui.ExamProblem;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class AppTest extends Frame {
    Button buttonAddProblem;
    Button buttonExam;

    public AppTest(){
        this.setTitle("请选择要进入的模式");
        this.setLocation(500,300);
        this.setSize(400,200);
        this.setLayout(null);

        buttonExam=new Button("Exam Module");
        buttonExam.setSize(200,200);
        buttonExam.setLocation(0,0);

        buttonAddProblem=new Button("Add Problem Module");
        buttonAddProblem.setSize(200,200);
        buttonAddProblem.setLocation(200,0);

        this.add(buttonExam);
        this.add(buttonAddProblem);

        this.addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent windowEvent) {}
            public void windowClosing(WindowEvent windowEvent) {System.exit(0);}
            public void windowClosed(WindowEvent windowEvent) {}
            public void windowIconified(WindowEvent windowEvent) {}
            public void windowDeiconified(WindowEvent windowEvent) {}
            public void windowActivated(WindowEvent windowEvent) {}
            public void windowDeactivated(WindowEvent windowEvent) {}
        });
        buttonExam.addActionListener(actionEvent -> {
            this.setVisible(false);
              ExamProblem myFrame=new ExamProblem();
              myFrame.setVisible(true);
        });
        buttonAddProblem.addActionListener(actionEvent -> {
            this.setVisible(false);
            AddProblem addProblem=new AddProblem();
            addProblem.setVisible(true);
        });
    }
        public static void main(String[] args) {
            AppTest appTest=new AppTest();
            appTest.setVisible(true);
    }

}
