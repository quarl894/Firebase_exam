package youngjung.test.ui.dialog;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

import youngjung.test.R;


public class LodingDialog extends Dialog {

    public LodingDialog(@NonNull Context context) {
        super(context);

        init();
    }

    public LodingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        init();
    }

    private void init(){
        setContentView(R.layout.dialog_loading);

        initDialog();
        initLoadingView();
    }

    private void initDialog(){
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void initLoadingView(){
        LottieAnimationView lottieView = findViewById(R.id.lottie);

        lottieView.setAnimation("loading_animation.json");
        lottieView.loop(true);
        lottieView.setRepeatCount(10);
        lottieView.playAnimation();
    }
}
