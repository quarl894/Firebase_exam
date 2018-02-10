package youngjung.test.Model;

/**
 * Created by HANSUNG on 2018-02-10.
 */

public class ChatData {
    private String userName;
    private String message;

    //빈 생성자 반드시 만들어줘야함. 아니면 에러남.
    public ChatData(){}

    public ChatData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
