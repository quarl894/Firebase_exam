package youngjung.test.Model;

/**
 * Created by HANSUNG on 2018-02-13.
 */

public class Profile {
    String name;
    String email;
    String fcmToken;

    public Profile(){}

    public Profile(String name, String email, String fcmToken) {
        this.name = name;
        this.email = email;
        this.fcmToken = fcmToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
