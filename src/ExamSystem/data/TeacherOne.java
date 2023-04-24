package ExamSystem.data;
import javax.swing.*;
public class TeacherOne {
    int correctAmount=0;
    public void giveTestPaperScore(TestPaper testPaper){

        if(testPaper==null){
            JOptionPane.showMessageDialog(null,"没答题","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Problem p[]=testPaper.getAllProblem();
        if(p==null||p.length==0){
            JOptionPane.showMessageDialog(null,"没答题","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
        }
        for(int i=0;i<p.length;i++){
         //原来的   boolean b=compare(p[i].getUserAnswer(),p[i].getCorrectAnswer());

            //新的方式
            boolean b=(p[i].getUserAnswer().equals(p[i].getCorrectAnswer()));
            if(b){
                correctAmount++;
            }
        }
        double result=(double) correctAmount/(double) p.length;
        int r=(int)(result*100);
        String s="共有"+p.length+"道题,"+
                "您作对了"+correctAmount+"道题,"+
                "正确率约为"+r+"%";
        JLabel mess=new JLabel(s);
        JOptionPane.showMessageDialog(null,mess,"成绩",JOptionPane.PLAIN_MESSAGE);
    }
    private boolean compare(String s,String t){
        boolean isTrue=true;
        for (int i=0;i<s.length();i++){
            String temp=""+s.charAt(i);
            if(!(t.contains(temp)))
                isTrue=false;
        }
        for (int i=0;i<t.length();i++){
            String temp=""+t.charAt(i);
            if(!(s.contains(temp)))
                isTrue=false;
        }
        return isTrue;
    }
}
