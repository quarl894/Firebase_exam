package youngjung.test.View;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.View.ahoy.AhoyOnboarderAdapter;
import youngjung.test.View.ahoy.AhoyOnboarderCard;
import com.codemybrainsout.onboarder.utils.ShadowTransformer;
import com.codemybrainsout.onboarder.views.CircleIndicatorView;
import com.codemybrainsout.onboarder.views.FlowingGradientClass;

import java.util.ArrayList;
import java.util.List;

import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-03-11.
 */
public class Eval_Activity extends youngjung.test.View.ahoy.AhoyOnboarderActivity {
    private CircleIndicatorView circleIndicatorView;
    private ViewPager vpOnboarderPager;
    private AhoyOnboarderAdapter ahoyOnboarderAdapter;
    private TextView btnSkip;
    private ImageView ivNext, ivPrev;
    private FrameLayout navigationControls;
    private FrameLayout buttonsLayout;
    private RelativeLayout parentLayout;
    private ImageView backgroundImage;
    private View backgroundImageOverlay;

    private ShadowTransformer mCardShadowTransformer;
    private Typeface typeface;
    private List<Integer> colorList;
    private boolean solidBackground = false;
    private List<AhoyOnboarderCard> pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval);
        setStatusBackgroundColor();
        hideActionBar();

        parentLayout = findViewById(com.codemybrainsout.onboarder.R.id.parent_layout);
        circleIndicatorView = findViewById(com.codemybrainsout.onboarder.R.id.circle_indicator_view);
        btnSkip = findViewById(com.codemybrainsout.onboarder.R.id.btn_skip);
        buttonsLayout = findViewById(com.codemybrainsout.onboarder.R.id.buttons_layout);
        navigationControls = findViewById(com.codemybrainsout.onboarder.R.id.navigation_layout);
        ivNext =  findViewById(com.codemybrainsout.onboarder.R.id.ivNext);
        ivPrev = findViewById(com.codemybrainsout.onboarder.R.id.ivPrev);
        backgroundImage =  findViewById(com.codemybrainsout.onboarder.R.id.background_image);
        backgroundImageOverlay = findViewById(com.codemybrainsout.onboarder.R.id.background_image_overlay);
        vpOnboarderPager = findViewById(com.codemybrainsout.onboarder.R.id.vp_pager);
        vpOnboarderPager.addOnPageChangeListener(this);
        btnSkip.setOnClickListener(this);
        ivPrev.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        setActiveIndicatorColor(R.color.golden_yellow);

        hideFinish(false);
        fadeOut(ivPrev, false);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("클리오 팬슬 아이라이너", "Label your packages with a barcode before we collect it from you.",R.drawable.barcode,R.drawable.barcode,"1");
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("클리오 팬슬 아이라이너", "Label your packages with a barcode before we collect it from you.",R.drawable.barcode,R.drawable.barcode,"1");
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("클리오 팬슬 아이라이너", "Label your packages with a barcode before we collect it from you.",R.drawable.barcode,R.drawable.barcode,"1");

        ahoyOnboarderCard1.setBackgroundColor(R.color.white);
        ahoyOnboarderCard2.setBackgroundColor(R.color.white);
        ahoyOnboarderCard3.setBackgroundColor(R.color.white);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.grey_600);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(false);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.cornflower);
        colorList.add(R.color.cornflower);
        colorList.add(R.color.cornflower);

        setColorBackground(colorList);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        setFont(face);

        setOnboardPages(pages);

    }

    public void setOnboardPages(List<youngjung.test.View.ahoy.AhoyOnboarderCard> pages) {

        this.pages = pages;
        ahoyOnboarderAdapter = new AhoyOnboarderAdapter(pages, getSupportFragmentManager(), dpToPixels(0, this), typeface);
        mCardShadowTransformer = new ShadowTransformer(vpOnboarderPager, ahoyOnboarderAdapter);
        mCardShadowTransformer.enableScaling(true);
        vpOnboarderPager.setAdapter(ahoyOnboarderAdapter);
        vpOnboarderPager.setPageTransformer(false, mCardShadowTransformer);
        circleIndicatorView.setPageIndicators(pages.size());

    }

    public float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    private void setStatusBackgroundColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, com.codemybrainsout.onboarder.R.color.black_transparent));
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        boolean isInFirstPage = vpOnboarderPager.getCurrentItem() == 0;
        boolean isInLastPage = vpOnboarderPager.getCurrentItem() == ahoyOnboarderAdapter.getCount() - 1;

        if (i == com.codemybrainsout.onboarder.R.id.btn_skip && isInLastPage) {
            onFinishButtonPressed();
        } else if (i == com.codemybrainsout.onboarder.R.id.ivPrev && !isInFirstPage) {
            vpOnboarderPager.setCurrentItem(vpOnboarderPager.getCurrentItem() - 1);
        } else if (i == com.codemybrainsout.onboarder.R.id.ivNext && !isInLastPage) {
            vpOnboarderPager.setCurrentItem(vpOnboarderPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        int firstPagePosition = 0;
        int lastPagePosition = ahoyOnboarderAdapter.getCount() - 1;
        circleIndicatorView.setCurrentPage(position);
        circleIndicatorView.setCurrentPage(position);

        if (position == lastPagePosition) {
            fadeOut(circleIndicatorView);
            showFinish();
            fadeOut(ivNext);
            fadeIn(ivPrev);
        } else if (position == firstPagePosition) {
            fadeOut(ivPrev);
            fadeIn(ivNext);
            hideFinish();
            fadeIn(circleIndicatorView);
        } else {
            fadeIn(circleIndicatorView);
            hideFinish();
            fadeIn(ivPrev);
            fadeIn(ivNext);
        }

        if (solidBackground && (pages.size() == colorList.size())) {
            backgroundImage.setBackgroundColor(ContextCompat.getColor(this, colorList.get(position)));
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void fadeOut(View v) {
        fadeOut(v, true);
    }

    private void fadeOut(final View v, boolean delay) {

        long duration = 0;
        if (delay) {
            duration = 300;
        }

        if (v.getVisibility() != View.GONE) {
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
            fadeOut.setDuration(duration);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(fadeOut);
        }
    }

    private void fadeIn(final View v) {

        if (v.getVisibility() != View.VISIBLE) {
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
            fadeIn.setDuration(300);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    v.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(fadeIn);
        }
    }

    private void showFinish() {
        btnSkip.setVisibility(View.VISIBLE);
        this.btnSkip.animate().translationY(0 - dpToPixels(5, this)).setInterpolator(new DecelerateInterpolator()).setDuration(500).start();
    }

    private void hideFinish(boolean delay) {

        long duration = 0;
        if (delay) {
            duration = 250;
        }

        this.btnSkip.animate().translationY(this.btnSkip.getBottom() + dpToPixels(100, this)).setInterpolator(new AccelerateInterpolator()).setDuration(duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                btnSkip.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
    }

    private void hideFinish() {
        hideFinish(true);
    }

    private void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void showNavigationControls(boolean navigation) {
        if (navigation) {
            navigationControls.setVisibility(View.VISIBLE);
        } else {
            navigationControls.setVisibility(View.GONE);
        }
    }

    public void setImageBackground(int resId) {
        backgroundImageOverlay.setVisibility(View.VISIBLE);
        backgroundImage.setImageResource(resId);
    }

    public void setColorBackground(@ColorRes int color) {
        backgroundImage.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    public void setColorBackground(List<Integer> color) {
        this.colorList = color;
        solidBackground = true;
        backgroundImage.setBackgroundColor(ContextCompat.getColor(this, color.get(0)));
    }

    public void setGradientBackground() {

        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(com.codemybrainsout.onboarder.R.drawable.translate)
                .onRelativeLayout(parentLayout)
                .setTransitionDuration(4000)
                .start();
    }

    public void setGradientBackground(int drawable) {
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(drawable)
                .onRelativeLayout(parentLayout)
                .setTransitionDuration(4000)
                .start();
    }

    public void setInactiveIndicatorColor(int color) {
        this.circleIndicatorView.setInactiveIndicatorColor(color);
    }

    public void setActiveIndicatorColor(int color) {
        this.circleIndicatorView.setActiveIndicatorColor(color);
    }

    /**
     * <br/><br/>
     * <b>N.B. Builds before JELLY_BEAN will use the default style</b>
     * <br/><br/>
     * Set the xml drawable style of the skip/done button, <br/>
     * using for example: ContextCompat.getDrawable(this, R.drawable.rectangle_button);
     *
     * @param res A drawable xml file representing your desired button style
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setFinishButtonDrawableStyle(Drawable res) {
        btnSkip.setBackground(res);
    }

    public void setFinishButtonTitle(CharSequence title) {
        btnSkip.setText(title);
    }

    public void setFinishButtonTitle(@StringRes int titleResId) {
        btnSkip.setText(titleResId);
    }

    public void setFont(Typeface typeface) {
        this.btnSkip.setTypeface(typeface);
        this.typeface = typeface;
    }

    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    }

}
