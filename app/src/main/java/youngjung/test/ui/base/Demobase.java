package youngjung.test.ui.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-02-13.
 */

public abstract class Demobase extends baseActivity {

    protected String[] mParties = new String[] {
            "신상이라서", "1+1이라서", "트랜디해서", "세일중이라서","가성비가 좋아서"
    };

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTfRegular = Typeface.createFromAsset(getAssets(), "fonts/NanumSquareB.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "fonts/NanumSquareR.ttf");
    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
