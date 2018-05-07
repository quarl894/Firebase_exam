package youngjung.test;

import android.app.Application;

import java.text.NumberFormat;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import youngjung.test.DB.MyDBHelper;

/**
 * Font 적용
 */

public class DefaultApplication extends Application {
    private static MyDBHelper dbHelper = null;
    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new MyDBHelper(getApplicationContext());

        init();
    }

    private void init(){
        initFont();
    }

    public void initFont(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSansCJKkr-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static MyDBHelper getDbHelper() {
        return dbHelper;
    }
    public String Moneyfomat(int amount){
        return NumberFormat.getNumberInstance(Locale.KOREA).format(amount) +"원";
    }
}
