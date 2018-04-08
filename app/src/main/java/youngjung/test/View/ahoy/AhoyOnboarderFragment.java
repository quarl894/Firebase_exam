package youngjung.test.View.ahoy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.R;
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


    private String title;
    private String description;
    private String moneyKey;

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
    private TextView money;
    private Button btn_yes;
    private Button btn_no;

    private CardView cardView;
    private int iconHeight, iconWidth;
    private int marginTop, marginBottom, marginLeft, marginRight;

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
        args.putString(AHOY_PAGE_MONEY,card.getMoney());


        AhoyOnboarderFragment fragment = new AhoyOnboarderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        moneyKey = bundle.getString(AHOY_PAGE_MONEY,null);
        imageMoneyKey = bundle.getInt(AHOY_PAGE_IMAGE_MONEY,0);

        view = inflater.inflate(R.layout.fragment_ahoy, container, false);
        ivOnboarderImage = view.findViewById(R.id.iv_image);
        image_money = view.findViewById(R.id.image_money);
        tvOnboarderTitle = view.findViewById(com.codemybrainsout.onboarder.R.id.tv_title);
        tvOnboarderDescription = view.findViewById(com.codemybrainsout.onboarder.R.id.tv_description);
        cardView = view.findViewById(com.codemybrainsout.onboarder.R.id.cv_cardview);

        money = view.findViewById(R.id.money);
        btn_yes = view.findViewById(R.id.btn_yes);
        btn_no = view.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_yes.setBackgroundColor(getResources().getColor(R.color.golden_yellow));
                btn_no.setBackgroundColor(getResources().getColor(R.color.pale_grey));
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_yes.setBackgroundColor(getResources().getColor(R.color.pale_grey));
                btn_no.setBackgroundColor(getResources().getColor(R.color.golden_yellow));
            }
        });
        if(moneyKey !=null){
            money.setText(moneyKey);
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
}
