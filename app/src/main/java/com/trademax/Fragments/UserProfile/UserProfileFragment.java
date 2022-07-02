package com.trademax.Fragments.UserProfile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trademax.Activities.MainActivity;
import com.trademax.Activities.MyOrders.MyOrderActivity;
import com.trademax.Activities.Privacy_Policy.Privacy_PolicyActivity;
import com.trademax.Activities.Settings.SettingActivity;
import com.trademax.Activities.ShippingAddress.ShippingAddressActivity;
import com.trademax.Activities.Support.SupportActivity;
import com.trademax.Activities.Term_Condition.Term_ConditionActivity;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import com.trademax.Utils.WebServices;


public class UserProfileFragment extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Activity activity;
    private View view;

    private CircleImageView profile_image;
    private TextView txt_userName;
    private TextView txt_userEmail;

    private LinearLayout li_myOrder;
    private LinearLayout li_shippingAddress;
    private LinearLayout li_settings;
    private LinearLayout li_term_Condition;
    private LinearLayout li_privacy_policy;
    private LinearLayout li_support;
    private LinearLayout li_signOut;
    private ViewGroup viewGroup;
    private View loginView;

    private GoogleApiClient mGoogleApiClient;
    public static int score;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private String userName;
    private String userEmail;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        activity = getActivity();
        init();
        listiners();

        String loginStatus = CSPreferences.readString(activity, Utils.USERLOGIN);

        if (loginStatus.equalsIgnoreCase("")) {
            li_signOut.setVisibility(View.GONE);
            loginView.setVisibility(View.GONE);
        } else {
            li_signOut.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.VISIBLE);
        }


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        if (CSPreferences.readString(activity, Utils.USERPHOTO).equals("null") ||
                CSPreferences.readString(activity, Utils.USERPHOTO).equals("")) {
            profile_image.setImageResource(R.drawable.logo);
        } else {
            Glide.with(activity).load(WebServices.ImageBaseURL + CSPreferences.readString(activity, Utils.USERPHOTO)).placeholder(R.drawable.logo).into(profile_image);
        }

        txt_userName.setText(CSPreferences.readString(activity, Utils.USERNAME));
        txt_userEmail.setText(CSPreferences.readString(activity, Utils.USEREMAIL));

        return view;
    }

    private void listiners() {
        li_myOrder.setOnClickListener(this);
        li_shippingAddress.setOnClickListener(this);
        li_settings.setOnClickListener(this);
        li_signOut.setOnClickListener(this);
        li_term_Condition.setOnClickListener(this);
        li_privacy_policy.setOnClickListener(this);
        li_support.setOnClickListener(this);
    }

    private void init() {

        viewGroup = view.findViewById(android.R.id.content);
        loginView = view.findViewById(R.id.loginView);

        profile_image = view.findViewById(R.id.profile_image);
        txt_userName = view.findViewById(R.id.txt_userName);
        txt_userEmail = view.findViewById(R.id.txt_userEmail);

        li_myOrder = view.findViewById(R.id.li_myOrder);
        li_shippingAddress = view.findViewById(R.id.li_shippingAddress);
        li_settings = view.findViewById(R.id.li_settings);
        li_term_Condition = view.findViewById(R.id.li_term_Condition);
        li_privacy_policy = view.findViewById(R.id.li_privacy_policy);
        li_support = view.findViewById(R.id.li_support);
        li_signOut = view.findViewById(R.id.li_signOut);

    }

    @Override
    public void onClick(View v) {

        if (v == li_myOrder) {
            Intent intent = new Intent(activity, MyOrderActivity.class);
            startActivity(intent);
        } else if (v == li_shippingAddress) {
            Intent intent = new Intent(activity, ShippingAddressActivity.class);
            startActivity(intent);
        } else if (v == li_settings) {
            Intent intent = new Intent(activity, SettingActivity.class);
            startActivity(intent);
        } else if (v == li_signOut) {
            showCustomDialog();
        } else if (v == li_term_Condition) {
            Intent intent = new Intent(activity, Term_ConditionActivity.class);
            startActivity(intent);
        } else if (v == li_privacy_policy) {
            Intent intent = new Intent(activity, Privacy_PolicyActivity.class);
            startActivity(intent);
        } else if (v == li_support) {
            Intent intent = new Intent(activity, SupportActivity.class);
            startActivity(intent);
        }
    }

    private void showCustomDialog() {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_dialog);

        final Window window = dialog.getWindow();
        window.setLayout(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setDimAmount(0.5f);
        window.setAttributes(wlp);

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btn_signout = (Button) dialog.findViewById(R.id.btn_signout);

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CSPreferences.readString(activity, Utils.LOGINTYPE).equalsIgnoreCase("")) {
                    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {
                                    if (status.isSuccess()) {
                                    CSPreferences.putString(activity, Utils.USERLOGIN, "false");
//
                                        CSPreferences.putString(activity, Utils.USERNAME, "");
                                        CSPreferences.putString(activity, Utils.USERPHOTO, "");
                                        CSPreferences.putString(activity, Utils.USEREMAIL, "");
                                        CSPreferences.putString(activity, Utils.USERID, "");
                                        CSPreferences.putString(activity, Utils.USERLOGIN, "");
                                        CSPreferences.putString(activity, Utils.LOGINTYPE, "");

                                        Intent intent = new Intent(activity, MainActivity.class);
                                        activity.startActivity(intent);
                                        activity.finish();

                                    } else {
                                        Toast.makeText(activity, "Session not close", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }else  if (CSPreferences.readString(activity, Utils.LOGINTYPE).equalsIgnoreCase("")) {

                    CSPreferences.putString(activity, Utils.USERLOGIN, "false");
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();

                }else {

                    CSPreferences.putString(activity,Utils.USERLOGIN,"false");
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            userName = account.getDisplayName();
            userEmail = account.getEmail();
            userId = account.getId();
            try {
                Glide.with(this).load(account.getPhotoUrl()).into(profile_image);
            } catch (NullPointerException e) {
                Toast.makeText(activity, "image not found", Toast.LENGTH_LONG).show();
            }

        } else {

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(requireActivity());
            mGoogleApiClient.disconnect();
        }
    }
}