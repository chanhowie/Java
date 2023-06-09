package ExamSystem.data;

public class TestPaper {
    private Problem [] problem =null;
    int index = -1;
    String problemSource;
    public void setProblem(Problem [] problem){
        this.problem=problem;
    }

    public Problem getProblem(int i) {
        if(problem==null){
            return null;
        }
        if(problem.length==0){
            return null;
        }
        if(i>problem.length||i<0){
            return null;
        }
        return problem[i];
    }
    public Problem nextProblem(){
        index++;
        if(problem==null){
            return null;
        }
        if(problem.length==0){
            return null;
        }
        if(index==problem.length){
            index=problem.length-1;
        }
        return problem[index];
    }
    public Problem previousProblem(){
        index--;
        if(problem==null){
            return null;
        }
        if(problem.length==0){
            return null;
        }
        if(index<0){
            index=0;
        }
        return problem[index];
    }
    public Problem[] getAllProblem(){
        if(problem==null){
            return null;
        }
        if(problem.length==0){
            return null;
        }
        return problem;
    }
    public int getProblemAmount(){
        if(problem==null){
            return 0;
        }
        return problem.length;
    }
    public void setProblemSource(String source){
        problemSource=source;
    }

    public String getProblemSource() {
        return problemSource;
    }
    public void acceptTeacher(Teacher teacher){
        teacher.giveTestPaperScore(this);
    }
}
