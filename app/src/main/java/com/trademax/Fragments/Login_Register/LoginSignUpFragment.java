package com.trademax.Fragments.Login_Register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.PersistableBundle;
import android.se.omapi.Session;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.trademax.Fragments.ForgotPass.ForgotPasswordFragment;
import com.trademax.Fragments.Login_Register.presenter.Login_SignUpPresenter;
import com.trademax.Fragments.Login_Register.view.Login_RegisterView;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import okhttp3.Request;

public class LoginSignUpFragment extends Fragment implements Login_RegisterView, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LoginSignUpFragment";
    private static final int SIGN_IN = 1;
    private EditText et_loginEmail;
    private EditText et_loginPassword;
    private TextView txt_Login;

    private EditText et_SignUpName;
    private EditText et_SignUpEmail;
    private EditText et_SignUpPassword;
    private TextView txt_SignUp;
    private TextView txt_resetPass;
    private TextView txt_LOGIN;
    private ImageView login_google;
    private ImageView login_facebook;

    private Button btn_login;
    private Button btn_signUp;

    private LinearLayout loginView;
    private LinearLayout registerView;
    private View view;

    private Activity activity;
    private Login_SignUpPresenter login_signUpPresenter_Login;
    private Login_SignUpPresenter login_signUpPresenter_SignUp;
    private SharedPreferences pref;

    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private AccessToken accessToken;

    private GoogleSignInOptions gso;
    private int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FacebookSdk.sdkInitialize(getActivity());
        view = inflater.inflate(R.layout.fragment_login_sign_up, container, false);
        activity = getActivity();

        accessToken = AccessToken.getCurrentAccessToken();

        init();
        listeners();
        keyhash();

     /*   gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        loginView.setVisibility(View.VISIBLE);
        registerView.setVisibility(View.GONE);
        txt_Login.setTextColor(getResources().getColor(R.color.white));
        txt_SignUp.setTextColor(getResources().getColor(R.color.white));
        txt_SignUp.setAlpha((float) 0.3);


        return view;
    }

    private void init() {

        callbackManager = CallbackManager.Factory.create();

        ///////////Login/////////
        et_loginEmail = (EditText) view.findViewById(R.id.et_loginEmail);
        et_loginPassword = (EditText) view.findViewById(R.id.et_loginPassword);
        txt_Login = (TextView) view.findViewById(R.id.txt_login);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        login_google = (ImageView) view.findViewById(R.id.login_google);
        login_facebook = (ImageView) view.findViewById(R.id.login_facebook);

        //////Register////////
        et_SignUpName = (EditText) view.findViewById(R.id.et_SignUpName);
        et_SignUpEmail = (EditText) view.findViewById(R.id.et_SignUpEmail);
        et_SignUpPassword = (EditText) view.findViewById(R.id.et_SignUpPassword);

        btn_signUp = (Button) view.findViewById(R.id.btn_signUp);
        txt_SignUp = (TextView) view.findViewById(R.id.txt_signUp);
        txt_resetPass = (TextView) view.findViewById(R.id.txt_resetPass);
        txt_LOGIN = (TextView) view.findViewById(R.id.txt_LOGIN);

        loginView = (LinearLayout) view.findViewById(R.id.loginView);
        registerView = (LinearLayout) view.findViewById(R.id.registerView);

        login_signUpPresenter_Login = new Login_SignUpPresenter(activity, this);
        login_signUpPresenter_SignUp = new Login_SignUpPresenter(activity, this);
    }

    private void listeners() {

        txt_Login.setOnClickListener(this);
        txt_SignUp.setOnClickListener(this);
        txt_resetPass.setOnClickListener(this);
        txt_LOGIN.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
        login_facebook.setOnClickListener(this);
        login_google.setOnClickListener(this);

    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {

        if (v == txt_Login) {
            loginView.setVisibility(View.VISIBLE);
            registerView.setVisibility(View.GONE);
            txt_Login.setTextColor(getResources().getColor(R.color.white));
            txt_SignUp.setTextColor(getResources().getColor(R.color.white));
            txt_SignUp.setAlpha(0.3F);
            txt_Login.setAlpha(10);

        } else if (v == txt_SignUp) {
            loginView.setVisibility(View.GONE);
            registerView.setVisibility(View.VISIBLE);
            txt_Login.setTextColor(getResources().getColor(R.color.white));
            txt_Login.setAlpha(0.3F);
            txt_SignUp.setTextColor(getResources().getColor(R.color.white));
            txt_SignUp.setAlpha(10);

        } else if (v == txt_resetPass) {

            ForgotPasswordFragment fragment = new ForgotPasswordFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();

        } else if (v == txt_LOGIN) {

            loginView.setVisibility(View.VISIBLE);
            registerView.setVisibility(View.GONE);
            txt_Login.setTextColor(getResources().getColor(R.color.white));
            txt_SignUp.setTextColor(getResources().getColor(R.color.white));
            txt_SignUp.setAlpha(0.3F);
            txt_Login.setAlpha(10);

        } else if (v == btn_login) {

            login_signUpPresenter_Login.hitLogin(et_loginEmail, et_loginPassword);

        } else if (v == btn_signUp) {

            login_signUpPresenter_SignUp.hitRegister(et_SignUpName, et_SignUpEmail, et_SignUpPassword);

        } else if (v == login_facebook) {
            facebookLogin();
            // LoginManager.getInstance().logOut();
        } else if (v == login_google) {

            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
            /*Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {

//                                Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();
//                                gotoMainActivity();
                            } else {
                                Toast.makeText(activity, "Session not close", Toast.LENGTH_LONG).show();
                            }
                        }
                    });*/
        }


    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void showMessage(Activity activity, String message) {
        Utils.showMessage(activity, message);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void facebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {

                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String id = object.getString("id");
//                                        String token = object.getString("token");
//                                        String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
//
                                    login_signUpPresenter_Login.fbsocialLogin(name, email, id, "", "F", "");
                                    //  Toast.makeText(activity,object.toString() , Toast.LENGTH_LONG).show();
                                    Log.e("facebook_data", object.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,birthday,gender,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e("onCancel", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("error", error.toString());

            }
        });
    }

    public void keyhash() {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo("com.trademax", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    public void hashFromSHA1(String sha1) {
        String[] arr = sha1.split(":");
        byte[] byteArr = new byte[arr.length];

        for (int i = 0; i < arr.length; i++) {
            byteArr[i] = Integer.decode("0x" + arr[i]).byteValue();
        }

        Log.e("hashasasaassa== : ", Base64.encodeToString(byteArr, Base64.NO_WRAP));
    }

//    private void handleSignInResult(GoogleSignInResult result) {
//        if (result.isSuccess()) {
//            GoogleSignInAccount account = result.getSignInAccount();
//            userName.setText(account.getDisplayName());
////            userEmail.setText(account.getEmail());
////            userId.setText(account.getId());
//            try {
////                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
//            } catch (NullPointerException e) {
//                Toast.makeText(activity, "image not found", Toast.LENGTH_LONG).show();
//            }
//
//        } else {
//            Toast.makeText(activity, "Successfull", Toast.LENGTH_SHORT).show();
//        }
////            gotoMainActivity();
//    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){

            GoogleSignInAccount account = result.getSignInAccount();

            String name = account.getDisplayName();
            String id = account.getId();
            String email = account.getEmail();
            String idtoken = account.getIdToken();
            String photourl = String.valueOf(account.getPhotoUrl());

//            CSPreferences.putString(activity, Utils.USERNAME, name);
//            CSPreferences.putString(activity, Utils.USEREMAIL, email);
            Log.e("googleId",id);
            login_signUpPresenter_Login.fbsocialLogin(name, email, id,idtoken, "G", photourl);

        }else{
            Toast.makeText(activity,"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
//        if (opr.isDone()) {
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
//    }

}