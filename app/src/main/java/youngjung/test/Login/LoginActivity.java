package youngjung.test.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;

/**
 * Created by HANSUNG on 2018-02-10.
 */

public class LoginActivity extends baseActivity {
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public SharedPreferences prefs;
  
    String TAG = getClass().getName().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();

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

        signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginGoogle();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
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
//                boolean isFirstRun = prefs.getBoolean("isFirstRun",true);
                boolean isFirstRun = true;

                if(isFirstRun)
                {
                    //회원 DB 저장
//                    Profile profile = new Profile(account.getDisplayName(), account.getEmail(), FirebaseInstanceId.getInstance().getToken());
//
//                    databaseReference.child("Member Information").child(account.getId()).setValue(profile);
                    prefs.edit().putBoolean("isFirstRun",false).apply();
                    firebaseAuthWithGoogle(account, 0);
                    Log.e("login test: ", "처음");
                    //처음만 true 그다음부터는 false 바꾸는 동작
                }else{
                    Log.e("login test: ", "처음아님");
                    firebaseAuthWithGoogle(account, 1);
                }
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

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct, int num) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        final int check = num;
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            switch(check){
                                case 0 :
                                    Intent intent = new Intent(getApplicationContext(), LoginEditActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                case 1 :
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent2);
                                    finish();
                                    Log.d(TAG, "signInWithCredential:success");
                                    break;
                            }

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
    public void checkFirstRun(){
        boolean isFirstRun = prefs.getBoolean("isFirstRun",true);
        if(isFirstRun)
        {
            prefs.edit().putBoolean("isFirstRun",false).apply();
            //처음만 true 그다음부터는 false 바꾸는 동작
        }
    }
}
