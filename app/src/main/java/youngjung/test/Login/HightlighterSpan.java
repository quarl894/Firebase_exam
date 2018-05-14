package youngjung.test.Login;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.BackgroundColorSpan;
import android.text.style.LineBackgroundSpan;
import android.text.style.ReplacementSpan;
import android.util.DisplayMetrics;

import youngjung.test.DefaultApplication;

public class HightlighterSpan extends ReplacementSpan {
    int backgroundColor;
    int foregroundColor;
    int mWidth;

    public HightlighterSpan(int backgroundColor, int foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        //return text with relative to the Paint
        mWidth = Math.round(paint.measureText(text, start, end));
        return mWidth;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float left, int top, int right, int bottom, @NonNull Paint paint) {
        int highlighterMargin = DefaultApplication.dpToPx(8);
        if (backgroundColor != 0) {
            paint.setColor(backgroundColor);
            canvas.drawRect(left, top + highlighterMargin, right + mWidth, bottom - highlighterMargin, paint);
        }

        paint.setColor(foregroundColor);
        canvas.drawText(text, start, end, left, right, paint);
    }
}
