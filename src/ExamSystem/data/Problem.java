package ExamSystem.data;

public class Problem {
    boolean isChoice;
    boolean isJudge;
    String content;
    String giveChoiceA,giveChoiceB,giveChoiceC,giveChoiceD;
    String imageName;
    String correctAnswer="";
    String userAnswer="";

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public boolean isJudge() {
        return isJudge;
    }

    public void setJudge(boolean judge) {
        isJudge = judge;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public String getGiveChoiceA() {
        return giveChoiceA;
    }

    public void setGiveChoiceA(String giveChoiceA) {
        this.giveChoiceA = giveChoiceA;
    }

    public String getGiveChoiceB() {
        return giveChoiceB;
    }

    public void setGiveChoiceB(String giveChoiceB) {
        this.giveChoiceB = giveChoiceB;
    }

    public String getGiveChoiceC() {
        return giveChoiceC;
    }

    public void setGiveChoiceC(String giveChoiceC) {
        this.giveChoiceC = giveChoiceC;
    }

    public String getGiveChoiceD() {
        return giveChoiceD;
    }

    public void setGiveChoiceD(String giveChoiceD) {
        this.giveChoiceD = giveChoiceD;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
