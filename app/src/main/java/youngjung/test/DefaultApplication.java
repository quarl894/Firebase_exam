package youngjung.test;

import android.app.Application;

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

    private void initFont(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
