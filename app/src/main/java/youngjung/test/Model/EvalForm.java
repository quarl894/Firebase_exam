package youngjung.test.Model;

/**
 * 평가하기 보내는 양식
 1.성별 :  sex
 2.한달 급여 : money
 3. 한달 생활비 : monthly_money;
 4. 제목 : title
 5. 가격 : price
 6. 내용 : content
 7. 영수증 주인 uid : uuid
 8. 영수증 도착한 날짜 : data
 9. 카테고리
 10. 허불허
 11. 이유
 */

public class EvalForm {
    String sex;
    int money;
    int monthly_money;
    String title;
    int price;
    String content;
    String uuid;
    String date;
    int category;
    boolean check;
    String why;

    // 이유 포함할때 생성자
    public EvalForm(String sex, int money, int monthly_money, String title, int price, String content, String uuid, String date, int category, boolean check) {
        this.sex = sex;
        this.money = money;
        this.monthly_money = monthly_money;
        this.title = title;
        this.price = price;
        this.content = content;
        this.uuid = uuid;
        this.date = date;
        this.category = category;
        this.check = check;
    }
    //이유 없을 때 생성자
    public EvalForm(String sex, int money, int monthly_money, String title, int price, String content, String uuid, String date, int category, boolean check, String why) {
        this.sex = sex;
        this.money = money;
        this.monthly_money = monthly_money;
        this.title = title;
        this.price = price;
        this.content = content;
        this.uuid = uuid;
        this.date = date;
        this.category = category;
        this.check = check;
        this.why = why;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMonthly_money() {
        return monthly_money;
    }

    public void setMonthly_money(int monthly_money) {
        this.monthly_money = monthly_money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
