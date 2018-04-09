package youngjung.test.Model;

import android.provider.ContactsContract;

/**
 * 의뢰서 양식
 1.성별 :  sex
 2.한달 급여 : money
 3. 한달 생활비 : monthly_money;
 4. 제목 : title
 5. 가격 : price
 6. 내용 : content
 7. 영수증 주인 uid : uuid
 8. 영수증 보낸 날짜 : data
 9. 카테고리 : category
 */

public class RequestForm{
    String sex;
    int money;
    int monthly_money;
    String title;
    int price;
    String content;
    String uuid;
    String date;
    int category;

    public RequestForm() {
    }

    public RequestForm(String sex, int money, int monthly_money, String title, int price, String content, String uuid, String date, int category) {
        this.sex = sex;
        this.money = money;
        this.monthly_money = monthly_money;
        this.title = title;
        this.price = price;
        this.content = content;
        this.uuid = uuid;
        this.date = date;
        this.category = category;
    }

    public RequestForm(String sex, int money, int monthly_money, String title, int price, String content, String uuid, String date) {
        this.sex = sex;
        this.money = money;
        this.monthly_money = monthly_money;
        this.title = title;
        this.price = price;
        this.content = content;
        this.uuid = uuid;
        this.date = date;
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
}
