package youngjung.test;

import android.app.Application;

import java.text.NumberFormat;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Font 적용
 */

public class DefaultApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

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
    public String Moneyfomat(int amount){
        return NumberFormat.getNumberInstance(Locale.KOREA).format(amount) +"원";
    }
}
