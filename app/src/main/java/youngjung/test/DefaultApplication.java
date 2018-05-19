package youngjung.test;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import youngjung.test.DB.MyDBHelper;

/**
 * Font 적용
 */

public class DefaultApplication extends Application {
    private static MyDBHelper dbHelper = null;
    private static Context context;
    private static HashMap<String, Integer> categoryHashMap;

    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new MyDBHelper(getApplicationContext());
        context = this;

        int[] category_image = {R.drawable.icon_fashion, R.drawable.icon_electronic, R.drawable.icon_hobby, R.drawable.icon_food, R.drawable.icon_travel, R.drawable.ic_launcher_foreground};
        String[] category_text = {"패션 및 뷰티", "전자기기", "취미생활", "음식", "여행", "기타"};

        categoryHashMap = new HashMap<>();
        for (int i = 0; i < category_image.length; i++) {
            categoryHashMap.put(category_text[i], category_image[i]);
        }

        init();
    }

    private void init() {
        initFont();
    }

    public void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSansCJKkr-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static MyDBHelper getDbHelper() {
        return dbHelper;
    }

    public static HashMap<String, Integer> getCategoryHashMap() {
        return categoryHashMap;
    }

    public String Moneyfomat(int amount) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(amount) + "원";
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;

        int px = Math.round((float) dp * density);
        return px;
    }
}
