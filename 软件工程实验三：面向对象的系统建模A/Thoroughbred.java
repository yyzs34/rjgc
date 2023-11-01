public class Thoroughbred {
    private String mother;
    private String father;
    private int birthyear;

    // 构造函数
    public Thoroughbred(String mother, String father, int birthyear) {
        this.mother = mother;
        this.father = father;
        this.birthyear = birthyear;
    }

    // 获取当前年龄
    public int getCurrentAge() {
        int currentYear = 2023; // 当前年份，你可以根据实际情况进行更新
        return currentYear - birthyear;
    }

    // 获取父亲
    public String getFather() {
        return father;
    }

    // 获取母亲
    public String getMother() {
        return mother;
    }
}