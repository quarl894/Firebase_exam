package youngjung.test;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
    public static final int PURPLE = 0;
    public static final int WHITE = 1;
    private static MyDBHelper dbHelper = null;
    private static Context context;
    private static HashMap<String, Integer[]> categoryHashMap;

    private static int[] category_image_purple = {R.drawable.ranking_beauty_purplee, R.drawable.ranking_elec_purple, R.drawable.ranking_hobby_purplee, R.drawable.ranking_food_purplee, R.drawable.ranking_trip_purplee, R.drawable.ranking_else_purplee};
    private static int[] category_image_white = {R.drawable.ranking_beauty_whitee, R.drawable.ranking_elec_whitee, R.drawable.ranking_hobby_whitee, R.drawable.ranking_food_whitee, R.drawable.ranking_trip_whitee, R.drawable.ranking_else_whitee};

    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new MyDBHelper(getApplicationContext());
        context = this;

//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("sum_ctg", null, null);
//        db.delete("tb_date", null, null);
//        db.delete("sum_money", null, null);
//        db.delete("tb_saving_item", null, null);

        String[] category_text = {"패션 및 뷰티", "전자기기", "취미생활", "음식", "여행", "기타"};

        categoryHashMap = new HashMap<>();
        for (int i = 0; i < category_image_purple.length; i++) {
            categoryHashMap.put(category_text[i], new Integer[]{category_image_purple[i], category_image_white[i]});
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

    public static HashMap<String, Integer[]> getCategoryHashMap() {
        return categoryHashMap;
    }

    public static int[] getCategory_image_white() {
        return category_image_white;
    }

    public String Moneyfomat(int amount) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(amount) + "원";
    }

    public String MoneyfomatWithoutWon(int amount) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(amount);
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;

        int px = Math.round((float) dp * density);
        return px;
    }

    public static float getPercentage(int num1, int num2) {
        if (num2 == 0) {
            return 0;
        }

        float result = num1 / (float) num2 * 100;
        return result > 100 ? 100 : result;
    }
}
