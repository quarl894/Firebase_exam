package youngjung.test;

import android.app.Application;
import android.support.v7.app.ActionBar;

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

    private void initFont(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSansCJKkr-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static MyDBHelper getDbHelper() {
        return dbHelper;
    }
}
