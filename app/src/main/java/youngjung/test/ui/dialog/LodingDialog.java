package youngjung.test.ui.dialog;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
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
        final LottieAnimationView lottieView = findViewById(R.id.lottie);

        lottieView.setAnimation("cash.json");
        lottieView.loop(true);
        lottieView.setRepeatCount(5);
        lottieView.playAnimation();

        lottieView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieView.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
