package youngjung.test.Model;

/**
 * 의뢰서 양식
 */

public class RequestForm{
    String id;
    String title;
    int price;
    String content;
    int mymoney;

    public RequestForm() {
    }

    public RequestForm(String id, String title, int price, String content, int mymoney) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.content = content;
        this.mymoney = mymoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getMymoney() {
        return mymoney;
    }

    public void setMymoney(int mymoney) {
        this.mymoney = mymoney;
    }
}
