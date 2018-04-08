package youngjung.test.View.ahoy;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class AhoyOnboarderCard {
    /**
     * 의뢰서 양식
     1.성별 :  sex
     2.한달 급여 : money
     3. 한달 생활비 : monthly_money;
     4. 제목 : title
     5. 가격 : price
     6. 내용 : content
     */
    public String sex;
    public int show_money;
    public int monthly_money;
    public String title;
    public int price;

    public String description;
    public Drawable imageResource;
    public int image_money;

    public int money;

    @StringRes
    public int titleResourceId;
    @StringRes
    public int descriptionResourceId;
    @DrawableRes
    public int imageResourceId;
    @ColorRes
    public int titleColor;
    @ColorRes
    public int descriptionColor;
    @ColorRes
    public int backgroundColor;

    public float titleTextSize;
    public float descriptionTextSize;
    public int iconWidth, iconHeight, marginTop, marginLeft, marginRight, marginBottom;

    public AhoyOnboarderCard(String sex, int show_money, int monthly_money, String title, int price, String description) {
        this.sex = sex;
        this.show_money = show_money;
        this.monthly_money = monthly_money;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public AhoyOnboarderCard(String title, String description, int imageResourceId, int image_money, int money) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.image_money = image_money;
        this.money = money;
    }

    public AhoyOnboarderCard(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public AhoyOnboarderCard(int title, int description) {
        this.titleResourceId = title;
        this.descriptionResourceId = description;
    }

    public AhoyOnboarderCard(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public AhoyOnboarderCard(String title, String description, Drawable imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    public AhoyOnboarderCard(int title, int description, int imageResourceId) {
        this.titleResourceId = title;
        this.descriptionResourceId = description;
        this.imageResourceId = imageResourceId;
    }

    public AhoyOnboarderCard(int title, int description, Drawable imageResource) {
        this.titleResourceId = title;
        this.descriptionResourceId = description;
        this.imageResource = imageResource;
    }

    public int getImage_money() {
        return image_money;
    }

    public void setImage_money(int image_money) {
        this.image_money = image_money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public int getTitleResourceId() {
        return titleResourceId;
    }

    public String getDescription() {
        return description;
    }

    public int getDescriptionResourceId() {
        return descriptionResourceId;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getDescriptionColor() {
        return descriptionColor;
    }

    public void setTitleColor(int color) {
        this.titleColor = color;
    }

    public void setDescriptionColor(int color) {
        this.descriptionColor = color;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public float getDescriptionTextSize() {
        return descriptionTextSize;
    }

    public void setDescriptionTextSize(float descriptionTextSize) {
        this.descriptionTextSize = descriptionTextSize;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconLayoutParams(int iconWidth, int iconHeight, int marginTop, int marginLeft, int marginRight, int marginBottom) {
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMonthly_money() {
        return monthly_money;
    }

    public void setMonthly_money(int monthly_money) {
        this.monthly_money = monthly_money;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShow_money() {
        return show_money;
    }

    public void setShow_money(int show_money) {
        this.show_money = show_money;
    }
}
