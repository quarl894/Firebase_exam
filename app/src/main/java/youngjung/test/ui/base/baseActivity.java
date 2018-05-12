package youngjung.test.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import youngjung.test.ui.dialog.LodingDialog;

/**
 * Created by HANSUNG on 2018-02-10.
 */

public class baseActivity extends AppCompatActivity {
    private Handler uiHandler;
    private LodingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHandler = new Handler();
    }

    protected void post(Runnable runnable) {
        uiHandler.post(runnable);
    }

    protected void postDelayed(Runnable runnable, long delayMillis) {
        uiHandler.postDelayed(runnable, delayMillis);
    }

    protected void showToast(String text) {
        Toast.makeText(
                getBaseContext(),
                text,
                Toast.LENGTH_LONG
        ).show();
    }

    protected void showLoading(Context context) {
        loadingDialog = new LodingDialog(context);
        loadingDialog.show();
    }

    protected void hideLoadingDialog() {
        this.loadingDialog.hide();
    }

    protected void deleteDialog(){
        this.loadingDialog.dismiss();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
