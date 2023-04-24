package ExamSystem.data;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import jxl.*;

public class RandomInitTestPaper implements GiveTestPaper {
    TestPaper testPaper;
    File fileExcel;
    Problem [] problem;
    InputStream in = null;
    Workbook wb=null;
    Sheet sheet = null;
    LinkedList<Integer> list;
    public  RandomInitTestPaper(){
        testPaper = new TestPaper();
        list = new LinkedList<Integer>();
    }
    @Override
    public TestPaper getTestPaper(String excelFileName,int amount){
        boolean b=setExcel(excelFileName);
        if(b){
            try {
                randomGiveProblem(amount);
            }
            catch (ArrayIndexOutOfBoundsException exp){
                System.out.println("试题必须有类型");
                System.exit(0);
            }
            testPaper.setProblem(problem);
            return testPaper;
        }
        else {
            JOptionPane.showMessageDialog(null,"没有预备题库","消息对话框",JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    private  boolean setExcel(String excelFileName){
        boolean b =true;
        try {
            fileExcel=new File(excelFileName);
            in=new FileInputStream(fileExcel);
            testPaper.setProblemSource(fileExcel.getAbsolutePath());

        } catch (IOException exp) {
            JOptionPane.showMessageDialog(null,"没有预备题库Excel","消息对话框",JOptionPane.WARNING_MESSAGE);
            b=false;
        }
        try {
            wb=Workbook.getWorkbook(in);
            in.close();
        } catch (Exception exp) {
            b=false;
        }
        return b;
    }
    private void randomGiveProblem(int amount){
        list.clear();
        if(wb==null){
            JOptionPane.showMessageDialog(null,"没有预备题库Excel","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
        }
        sheet =wb.getSheet(0);
        int rowsAmount=sheet.getRows();
        int realAmount = Math.min(amount,rowsAmount-1);
        problem = new Problem[realAmount];
        for (int i=0;i<rowsAmount-1;i++){
            list.add(i+1);
        }
        Random random = new Random();
        for (int i=0;i<problem.length;i++){
            int m=random.nextInt(list.size());
            int index=list.remove(m);
            Cell [] cell=sheet.getRow(index);
            problem[i]=new Problem();
            int number=i+1;
            problem[i].setContent("第"+number+"题\n"+cell[0].getContents());
            problem[i].setCorrectAnswer(cell[1].getContents().trim());
            problem[i].setGiveChoiceA(cell[2].getContents().trim());
            problem[i].setGiveChoiceB(cell[3].getContents().trim());
            problem[i].setGiveChoiceC(cell[4].getContents().trim());
            problem[i].setGiveChoiceD(cell[5].getContents().trim());
            String typeStr=cell[6].getContents().trim();
            if (typeStr.equalsIgnoreCase("p")){
                problem[i].setJudge(true);
                problem[i].setChoice(false);
                problem[i].setImageName("havenot.jpg");
            }
            if (typeStr.equalsIgnoreCase("x")){
                problem[i].setJudge(false);
                problem[i].setChoice(true);
                problem[i].setImageName("havenot.jpg");
            }
            if (typeStr.startsWith("p#")||typeStr.startsWith("P#")){
                problem[i].setJudge(true);
                problem[i].setChoice(false);
                String imageName = typeStr.substring(typeStr.indexOf("#")+1);
                problem[i].setImageName(imageName);
            }
            if (typeStr.startsWith("x#")||typeStr.startsWith("X#")){
                problem[i].setJudge(false);
                problem[i].setChoice(true);
                String imageName = typeStr.substring(typeStr.indexOf("#")+1);
                problem[i].setImageName(imageName);
            }
        }
    }



}
