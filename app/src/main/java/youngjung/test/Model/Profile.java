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

    public Profile(){}

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
}
