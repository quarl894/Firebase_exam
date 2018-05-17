package youngjung.test.View.ahoy;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.DefaultApplication;
import youngjung.test.Model.test;
import youngjung.test.R;
import youngjung.test.View.Eval_Activity;
import youngjung.test.View.ahoy.AhoyOnboarderCard;

public class AhoyOnboarderFragment extends Fragment {

    private static final String AHOY_PAGE_TITLE = "ahoy_page_title";
    private static final String AHOY_PAGE_TITLE_RES_ID = "ahoy_page_title_res_id";
    private static final String AHOY_PAGE_TITLE_COLOR = "ahoy_page_title_color";
    private static final String AHOY_PAGE_TITLE_TEXT_SIZE = "ahoy_page_title_text_size";
    private static final String AHOY_PAGE_DESCRIPTION = "ahoy_page_description";
    private static final String AHOY_PAGE_DESCRIPTION_RES_ID = "ahoy_page_description_res_id";
    private static final String AHOY_PAGE_DESCRIPTION_COLOR = "ahoy_page_description_color";
    private static final String AHOY_PAGE_DESCRIPTION_TEXT_SIZE = "ahoy_page_description_text_size";
    private static final String AHOY_PAGE_IMAGE_RES_ID = "ahoy_page_image_res_id";
    private static final String AHOY_PAGE_BACKGROUND_COLOR = "ahoy_page_background_color";
    private static final String AHOY_PAGE_ICON_WIDTH = "ahoy_page_icon_width";
    private static final String AHOY_PAGE_ICON_HEIGHT = "ahoy_page_icon_height";
    private static final String AHOY_PAGE_MARGIN_LEFT = "ahoy_page_margin_left";
    private static final String AHOY_PAGE_MARGIN_RIGHT = "ahoy_page_margin_right";
    private static final String AHOY_PAGE_MARGIN_TOP = "ahoy_page_margin_top";
    private static final String AHOY_PAGE_MARGIN_BOTTOM = "ahoy_page_margin_bottom";
    private static final String AHOY_PAGE_IMAGE_MONEY = "ahoy_page_image_money";
    private static final String AHOY_PAGE_MONEY = "ahoy_page_money";

    private static final String AHOY_PAGE_SEX = "ahoy_page_sex";
    private static final String AHOY_PAGE_SHOW_MONEY = "ahoy_page_show_money";
    private static final String AHOY_PAGE_MONTH_MONEY = "ahoy_page_month_money";
    private static final String AHOY_PAGE_BTN_CHECK = "ahoy_page_btn_yes";

    private String title;
    private String description;
    DefaultApplication app;

    @StringRes
    private int titleResId;
    private int imageMoneyKey;
    @ColorRes
    private int titleColor;
    @StringRes
    private int descriptionResId;
    @ColorRes
    private int backgroundColor;
    @ColorRes
    private int descriptionColor;
    @DrawableRes
    private int imageResId;
    private float titleTextSize;
    private float descriptionTextSize;

    private View view;
    private ImageView ivOnboarderImage;
    private ImageView image_money;
    private TextView tvOnboarderTitle;
    private TextView tvOnboarderDescription;

    private Button btn_yes;
    private Button btn_no;
    private ImageView stamp;

    private CardView cardView;
    private int iconHeight, iconWidth;
    private int marginTop, marginBottom, marginLeft, marginRight;

    //추가
    private TextView tv_sex;
    private TextView tv_show_money;
    private TextView tv_month_money;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    //추가
    private String sex;
    private int show_money;
    private int month_money;
    private TextView price;
    private int moneyKey;
    private int check;

    private CheckListener checkListener;
    public AhoyOnboarderFragment() {
    }

    public static AhoyOnboarderFragment newInstance(youngjung.test.View.ahoy.AhoyOnboarderCard card) {
        Bundle args = new Bundle();
        args.putString(AHOY_PAGE_TITLE, card.getTitle());
        args.putString(AHOY_PAGE_DESCRIPTION, card.getDescription());
        args.putInt(AHOY_PAGE_TITLE_RES_ID, card.getTitleResourceId());
        args.putInt(AHOY_PAGE_DESCRIPTION_RES_ID, card.getDescriptionResourceId());
        args.putInt(AHOY_PAGE_TITLE_COLOR, card.getTitleColor());
        args.putInt(AHOY_PAGE_DESCRIPTION_COLOR, card.getDescriptionColor());
        args.putInt(AHOY_PAGE_IMAGE_RES_ID, card.getImageResourceId());
        args.putFloat(AHOY_PAGE_TITLE_TEXT_SIZE, card.getTitleTextSize());
        args.putFloat(AHOY_PAGE_DESCRIPTION_TEXT_SIZE, card.getDescriptionTextSize());
        args.putInt(AHOY_PAGE_BACKGROUND_COLOR, card.getBackgroundColor());
        args.putInt(AHOY_PAGE_ICON_HEIGHT, card.getIconHeight());
        args.putInt(AHOY_PAGE_ICON_WIDTH, card.getIconWidth());
        args.putInt(AHOY_PAGE_MARGIN_LEFT, card.getMarginLeft());
        args.putInt(AHOY_PAGE_MARGIN_RIGHT, card.getMarginRight());
        args.putInt(AHOY_PAGE_MARGIN_TOP, card.getMarginTop());
        args.putInt(AHOY_PAGE_MARGIN_BOTTOM, card.getMarginBottom());
        args.putInt(AHOY_PAGE_IMAGE_MONEY,card.getImage_money());
        args.putInt(AHOY_PAGE_MONEY,card.getPrice());

        //추가
        args.putString(AHOY_PAGE_SEX, card.getSex());
        args.putInt(AHOY_PAGE_SHOW_MONEY, card.getShow_money());
        args.putInt(AHOY_PAGE_MONTH_MONEY, card.getMonthly_money());
        args.putInt(AHOY_PAGE_BTN_CHECK, card.getCheck());

        AhoyOnboarderFragment fragment = new AhoyOnboarderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CheckListener){
            checkListener = (CheckListener) context;
        }else{
            throw new RuntimeException(context.toString() + "must implement CheckListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = new DefaultApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        title = bundle.getString(AHOY_PAGE_TITLE, null);
        titleResId = bundle.getInt(AHOY_PAGE_TITLE_RES_ID, 0);
        titleColor = bundle.getInt(AHOY_PAGE_TITLE_COLOR, 0);
        titleTextSize = bundle.getFloat(AHOY_PAGE_TITLE_TEXT_SIZE, 0f);
        description = bundle.getString(AHOY_PAGE_DESCRIPTION, null);
        descriptionResId = bundle.getInt(AHOY_PAGE_DESCRIPTION_RES_ID, 0);
        descriptionColor = bundle.getInt(AHOY_PAGE_DESCRIPTION_COLOR, 0);
        descriptionTextSize = bundle.getFloat(AHOY_PAGE_DESCRIPTION_TEXT_SIZE, 0f);
        imageResId = bundle.getInt(AHOY_PAGE_IMAGE_RES_ID, 0);
        backgroundColor = bundle.getInt(AHOY_PAGE_BACKGROUND_COLOR, 0);
        iconWidth = bundle.getInt(AHOY_PAGE_ICON_WIDTH, (int) dpToPixels(128, getActivity()));
        iconHeight = bundle.getInt(AHOY_PAGE_ICON_HEIGHT, (int) dpToPixels(128, getActivity()));
        marginTop = bundle.getInt(AHOY_PAGE_MARGIN_TOP, (int) dpToPixels(80, getActivity()));
        marginBottom = bundle.getInt(AHOY_PAGE_MARGIN_BOTTOM, (int) dpToPixels(0, getActivity()));
        marginLeft = bundle.getInt(AHOY_PAGE_MARGIN_LEFT, (int) dpToPixels(0, getActivity()));
        marginRight = bundle.getInt(AHOY_PAGE_MARGIN_RIGHT, (int) dpToPixels(0, getActivity()));
        imageMoneyKey = bundle.getInt(AHOY_PAGE_IMAGE_MONEY,0);

        //추가
        sex = bundle.getString(AHOY_PAGE_SEX, null);
        show_money = bundle.getInt(AHOY_PAGE_SHOW_MONEY, 0);
        month_money = bundle.getInt(AHOY_PAGE_MONTH_MONEY, 0);
        moneyKey = bundle.getInt(AHOY_PAGE_MONEY,0);
        check = bundle.getInt(AHOY_PAGE_BTN_CHECK, -1);

        view = inflater.inflate(R.layout.fragment_ahoy, container, false);
        ivOnboarderImage = view.findViewById(R.id.iv_image);
        image_money = view.findViewById(R.id.image_money);
        tvOnboarderTitle = view.findViewById(com.codemybrainsout.onboarder.R.id.tv_title);
        tvOnboarderDescription = view.findViewById(com.codemybrainsout.onboarder.R.id.tv_description);
        cardView = view.findViewById(com.codemybrainsout.onboarder.R.id.cv_cardview);

        //추가
        tv_sex = view.findViewById(R.id.tv_sex);
        tv_show_money = view.findViewById(R.id.tv_money);
        tv_month_money = view.findViewById(R.id.tv_money_month);

        price = view.findViewById(R.id.money);
        btn_yes = view.findViewById(R.id.btn_yes);
        btn_no = view.findViewById(R.id.btn_no);
        stamp = view.findViewById(R.id.stamp);

        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_yes.setBackground(getResources().getDrawable(R.drawable.aboy_btn2));
                btn_no.setBackground(getResources().getDrawable(R.drawable.aboy_btn));
                stamp.setImageResource(R.drawable.ok_stamp);
                check = 1;
                checkListener.Btn_Check(check);
                Log.e("Check: ",Integer.toString(check));
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_no.setBackground(getResources().getDrawable(R.drawable.aboy_btn2));
                btn_yes.setBackground(getResources().getDrawable(R.drawable.aboy_btn));
                stamp.setImageResource(R.drawable.no_stamp);
                check = 0;
                checkListener.Btn_Check(check);

                Log.e("Check: ",Integer.toString(check));
            }
        });

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NotoSansCJKkr-Regular.otf");
        Typeface face3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NotoSansCJKkr-Bold.otf");

        price.setTypeface(face);
        tv_show_money.setTypeface(face3);
        btn_no.setTypeface(face2);
        btn_yes.setTypeface(face2);
        btn_yes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btn_no.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv_sex.setTypeface(face3);
        tv_month_money.setTypeface(face3);
        tv1.setTypeface(face2);
        tv2.setTypeface(face2);
        tv3.setTypeface(face2);
        tvOnboarderDescription.setTypeface(face2);


        //영수증 배경화면 수정
        cardView.setBackgroundResource(R.drawable.receipt_back);

        if(sex !=null){
            tv_sex.setText(sex);
        }

        if(show_money !=0){
            tv_show_money.setText(app.Moneyfomat(show_money)+"");
        }

        if(month_money !=0){
            tv_month_money.setText(app.Moneyfomat(month_money)+"");
        }

        if(moneyKey !=0){
            price.setText(app.Moneyfomat(moneyKey)+"");
        }

        if(imageMoneyKey !=0){
            image_money.setImageDrawable(ContextCompat.getDrawable(getActivity(), imageMoneyKey));
        }

        if (title != null) {
            tvOnboarderTitle.setText(title);
        }

        if (titleResId != 0) {
            tvOnboarderTitle.setText(getResources().getString(titleResId));
        }

        if (description != null) {
            tvOnboarderDescription.setText(description);
        }

        if (descriptionResId != 0) {
            tvOnboarderDescription.setText(getResources().getString(descriptionResId));
        }

        if (titleColor != 0) {
            tvOnboarderTitle.setTextColor(ContextCompat.getColor(getActivity(), titleColor));
        }

        if (descriptionColor != 0) {
            tvOnboarderDescription.setTextColor(ContextCompat.getColor(getActivity(), descriptionColor));
        }

        if (imageResId != 0) {
            ivOnboarderImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), imageResId));
        }

        if (titleTextSize != 0f) {
            tvOnboarderTitle.setTextSize(titleTextSize);
        }

        if (descriptionTextSize != 0f) {
            tvOnboarderDescription.setTextSize(descriptionTextSize);
        }

        if (backgroundColor != 0) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), backgroundColor));
        }

        if (iconWidth != 0 && iconHeight != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iconWidth, iconHeight);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
            ivOnboarderImage.setLayoutParams(layoutParams);
        }

        return view;
    }

    public float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        checkListener = null;
    }

    public CardView getCardView() {
        return cardView;
    }

    public TextView getTitleView() {
        return tvOnboarderTitle;
    }

    public TextView getDescriptionView() {
        return tvOnboarderDescription;
    }

    public interface CheckListener{
        void  Btn_Check(int check);
    }

}
