package youngjung.test.Model;

/**
 * Created by HANSUNG on 2018-02-13.
 */

public class Profile {
    String name;
    String email;
    String uuid;
    String goal;
    String goal_money;
    String monthly_money;
    String sex;

    //내부 db에 저장할 추가 데이터
    int acc_money;
    String ctg; //카테고리
    String date;

    public Profile(){}

    //내부 DB에서 사용할 생성자.
    public Profile(String ctg){
        this.ctg = ctg;
    }

    public Profile(String name, String email, String uuid, String goal, String goal_money, String monthly_money, String sex) {
        this.name = name;
        this.email = email;
        this.uuid = uuid;
        this.goal = goal;
        this.goal_money = goal_money;
        this.monthly_money = monthly_money;
        this.sex = sex;
    }

    public Profile(String name, String email, String uuid, String goal, String goal_money, String monthly_money) {
        this.name = name;
        this.email = email;
        this.uuid = uuid;
        this.goal = goal;
        this.goal_money = goal_money;
        this.monthly_money = monthly_money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoal_money() {
        return goal_money;
    }

    public void setGoal_money(String goal_money) {
        this.goal_money = goal_money;
    }

    public String getMonthly_money() {
        return monthly_money;
    }

    public void setMonthly_money(String monthly_money) {
        this.monthly_money = monthly_money;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAcc_money() {
        return acc_money;
    }

    public void setAcc_money(int acc_money) {
        this.acc_money = acc_money;
    }

    public String getCtg() {
        return ctg;
    }

    public void setCtg(String ctg) {
        this.ctg = ctg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
