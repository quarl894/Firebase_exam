package youngjung.test.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import youngjung.test.MainActivity;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;

/**
 * Created by HANSUNG on 2018-02-10.
 */

public class LoginActivity extends baseActivity {
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public SharedPreferences prefs;
    private TextView tv1, tv4;

    String TAG = getClass().getName().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        tv1 = findViewById(R.id.login_text1);
        SpannableString str = new SpannableString(Html.fromHtml("<strong> 똑똑한 소비</strong>"));
        str.setSpan(new HightlighterSpan(ContextCompat.getColor(this, R.color.aquamarine), ContextCompat.getColor(this, R.color.dark_two), 0), 0, str.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(str);

        tv4 = findViewById(R.id.login_text4);
        SpannableString str4 = new SpannableString(Html.fromHtml("<strong> 허불허</strong>"));
        str4.setSpan(new HightlighterSpan(ContextCompat.getColor(this, R.color.aquamarine), ContextCompat.getColor(this, R.color.dark_two), 0), 0, str4.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv4.setText(str4);
//        tv1.setText(Html.fromHtml("<strong>똑똑한 소비</strong>인지 의심된다면<br> 가볍게 <strong>허불허</strong>하세요!"));
//        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            }
                        } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleBtnUi();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();

                //회원 DB 저장
//                Profile profile = new Profile(account.getDisplayName(), account.getEmail(), FirebaseInstanceId.getInstance().getToken());
//
//                databaseReference.child("Member Information").child(account.getId()).setValue(profile);
                prefs.edit().putBoolean("isFirstRun", false).apply();
                firebaseAuthWithGoogle(account);

//                boolean isFirstRun = prefs.getBoolean("isFirstRun",true);

//                if(isFirstRun)
//                {
//                    Log.e("login test: ", "처음");
//                    //처음만 true 그다음부터는 false 바꾸는 동작
//                }else{
//                    Log.e("login test: ", "처음아님");
//                    firebaseAuthWithGoogle(account);
//                }
//                Log.d(TAG, "이름 =" + account.getDisplayName());
//                Log.d(TAG, "이메일=" + account.getEmail());
//                Log.d(TAG, "getId()=" + account.getId());
//                Log.d(TAG, "getAccount()=" + account.getAccount());
//                Log.d(TAG, "getIdToken()=" + account.getIdToken());

            } else {
                showToast("로그인에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            Query query = databaseReference.child("Member Information").orderByChild("uuid").equalTo(uid);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        // 데이터를 넣어준 후(?) 메인으로 인텐트
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), LoginEditActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            showToast("Authentication failed.");
                        }
                    }
                });
    }

    private void loginGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN);
    }

    //최초인지 확인
    public void checkFirstRun() {
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            prefs.edit().putBoolean("isFirstRun", false).apply();
            //처음만 true 그다음부터는 false 바꾸는 동작
        }
    }

    private void googleBtnUi() {
        // TODO Auto-generated method stub

        SignInButton googleButton = findViewById(R.id.sign_in_button);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
            }
        });

        for (int i = 0; i < googleButton.getChildCount(); i++) {
            View v = googleButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(14);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setText("");
                tv.setTextColor(getResources().getColor(R.color.dark));
                tv.setSingleLine(true);
                tv.setBackground(getResources().getDrawable(R.drawable.icon_g));
                tv.setPadding(15, 15, 15, 15);

                ViewGroup.LayoutParams params = tv.getLayoutParams();
                tv.setLayoutParams(params);

                return;
            }
        }
    }
}
