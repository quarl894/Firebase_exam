package youngjung.test.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HANSUNG on 2018-02-16.
 */

public class test implements Parcelable {
    String id;
    String name;
    String tel;

    public test(String id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.tel);
    }

    public test() {
    }

    protected test(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.tel = in.readString();
    }

    public static final Parcelable.Creator<test> CREATOR = new Parcelable.Creator<test>() {
        @Override
        public test createFromParcel(Parcel source) {
            return new test(source);
        }

        @Override
        public test[] newArray(int size) {
            return new test[size];
        }
    };
}
