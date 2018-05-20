package youngjung.test.View;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.MainActivity;
import youngjung.test.Model.RequestForm;
import youngjung.test.View.ahoy.AhoyOnboarderAdapter;
import youngjung.test.View.ahoy.AhoyOnboarderCard;
import com.codemybrainsout.onboarder.utils.ShadowTransformer;
import com.codemybrainsout.onboarder.views.CircleIndicatorView;
import com.codemybrainsout.onboarder.views.FlowingGradientClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import youngjung.test.R;
import youngjung.test.View.ahoy.AhoyOnboarderFragment;

import static youngjung.test.MainActivity.receipt;

/**
 * Created by HANSUNG on 2018-03-11.
 */
public class Eval_Activity extends youngjung.test.View.ahoy.AhoyOnboarderActivity implements AhoyOnboarderFragment.CheckListener{
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
    private TextView tv_text;
    private TextView eval_title;
    private Button btn_req_ok;
    int ck_stamp = -1;

    HashMap<Integer, Integer> ck_hash = new HashMap<>();
    private DatabaseReference databaseReference;
    RequestForm a1;
    RequestForm a2;
    RequestForm a3;
    int num1 =0 , num2 = 1, num3 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval);
        setStatusBackgroundColor();
        hideActionBar();
        parentLayout = findViewById(com.codemybrainsout.onboarder.R.id.parent_layout);
        circleIndicatorView = findViewById(R.id.circle_indicator_view);
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

        tv_text = findViewById(R.id.tv_text);
        eval_title = findViewById(R.id.eval_title);
        btn_req_ok = findViewById(R.id.btn_req_ok);

        if(receipt.size() >3){
            //랜덤 3개 뽑기
            num1 = (int)(Math.random()*receipt.size()-1);
            num2 = (int)(Math.random()*receipt.size()-1);
            num3 = (int)(Math.random()*receipt.size()-1);

            while(num1==num2 || num1==num3 || num2==num3){
                num2 = (int)(Math.random()*receipt.size()-1);
                num3 = (int)(Math.random()*receipt.size()-1);
             //   Log.e("while what's num: ", Integer.toString(num1) + ", " + num2 + ", "+ num3);
            }
        }
        Log.e("what's num: ", Integer.toString(num1) + ", " + num2 + ", "+ num3);

        a1 = receipt.get(num1);
        a2 = receipt.get(num2);
        a3 = receipt.get(num3);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        /**
         * 의뢰서 양식
         1.성별 :  sex
         2.한달 급여 : money
         3. 한달 생활비 : monthly_money;
         4. 제목 : title
         5. 가격 : price
         6. 내용 : content
         7. 영수증 주인 uid : uuid
         8. 영수증 보낸 날짜 : data
         9. 카테고리 : category
         10. 허불허 : check
         */
        btn_req_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ck_hash.size()!=3) Toast.makeText(getApplicationContext(), "평가를 모두 완료해주세요.",Toast.LENGTH_SHORT).show();
                else{
                    databaseReference.child("finished receipt").child(a1.getUuid()).push().setValue(new RequestForm(a1.getSex(),a1.getMoney(),a1.getMonthly_money(),a1.getTitle(),a1.getPrice(),a1.getContent(),a1.getUuid(),a1.getDate(),a1.getCategory(),ck_hash.get(0),a1.getToken()));
                    databaseReference.child("finished receipt").child(a2.getUuid()).push().setValue(new RequestForm(a2.getSex(),a2.getMoney(),a2.getMonthly_money(),a2.getTitle(),a2.getPrice(),a2.getContent(),a2.getUuid(),a2.getDate(),a2.getCategory(),ck_hash.get(1),a2.getToken()));
                    databaseReference.child("finished receipt").child(a3.getUuid()).push().setValue(new RequestForm(a3.getSex(),a3.getMoney(),a3.getMonthly_money(),a3.getTitle(),a3.getPrice(),a3.getContent(),a3.getUuid(),a3.getDate(),a3.getCategory(),ck_hash.get(2),a3.getToken()));

                    //개발 기간 동안은 주석처리.
                    databaseReference.child("Request receipt").child(a1.getUuid()).child(a1.getValue()).removeValue();
                    databaseReference.child("Request receipt").child(a2.getUuid()).child(a2.getValue()).removeValue();
                    databaseReference.child("Request receipt").child(a3.getUuid()).child(a3.getValue()).removeValue();

                    Toast.makeText(getApplicationContext(), "영수증이 전달되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Eval_Activity.this, MainActivity.class);
                    startActivity(i);
                    receipt.clear();
                    finish();
                }
            }
        });
        /**
         * 의뢰서 양식
         1.성별 :  sex
         2.한달 급여 : money
         3. 한달 생활비 : monthly_money;
         4. 제목 : title
         5. 가격 : price
         6. 내용 : content
         */
        Log.e("receipt size: ", " " +receipt.size());

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard(a1.getSex(), a1.getMoney(), a1.getMonthly_money(), a1.getTitle(), a1.getPrice(), a1.getContent(),0);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard(a2.getSex(), a2.getMoney(), a2.getMonthly_money(), a2.getTitle(), a2.getPrice(), a2.getContent(),0);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard(a3.getSex(), a3.getMoney(), a3.getMonthly_money(), a3.getTitle(), a3.getPrice(), a3.getContent(),0);

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
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/NotoSansCJKkr-Regular.otf");
        Typeface face3 = Typeface.createFromAsset(getAssets(), "fonts/NotoSansCJKkr-Medium.otf");
        tv_text.setTypeface(face2);
        eval_title.setTypeface(face3);
        btn_req_ok.setTypeface(face2);

    }

    @Override
    public void Btn_Check(int stamp) {
        ck_stamp = stamp;
        ck_hash.put(vpOnboarderPager.getCurrentItem(),ck_stamp);
        if(ck_hash.containsKey(vpOnboarderPager.getCurrentItem())){
            ck_hash.remove(vpOnboarderPager.getCurrentItem());
            ck_hash.put(vpOnboarderPager.getCurrentItem(),ck_stamp);
        }
       // Log.e("확인",Integer.toString(ck_stamp) + ", " +vpOnboarderPager.getCurrentItem() + ", " +ck_hash.size());
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
//            showFinish();
            fadeOut(ivNext);
            fadeIn(ivPrev);
            btn_req_ok.setVisibility(View.VISIBLE);
        } else if (position == firstPagePosition) {
            fadeOut(ivPrev);
            fadeIn(ivNext);
            hideFinish();
            fadeIn(circleIndicatorView);
            btn_req_ok.setVisibility(View.INVISIBLE);
        } else {
            fadeIn(circleIndicatorView);
            hideFinish();
            fadeIn(ivPrev);
            fadeIn(ivNext);
            btn_req_ok.setVisibility(View.INVISIBLE);
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
        Toast.makeText(this, "영수증이 전달되었습니다.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Eval_Activity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
