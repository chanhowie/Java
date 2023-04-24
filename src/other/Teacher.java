package other;

public class Teacher extends SpeakForeignLanguageAdapter{
    public Teacher() {
    }

    public Teacher(String name, int age) {
        super(name, age);
    }

    @Override
    public void speakChinese() {
        System.out.println("会说中文");
    }

    @Override
    public void speakHenan() {
        super.speakHenan();
        System.out.println("会说河南话");
    }
}
