package com.trademax.Fragments.ForgotPass;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.trademax.Activities.MainActivity;
import com.trademax.Fragments.ForgotPass.presenter.ForgotpasswordPresenter;
import com.trademax.Fragments.ForgotPass.view.ForgotPasswordView;
import com.trademax.Fragments.Login_Register.LoginSignUpFragment;
import com.trademax.Fragments.OtpFragment.OTPFragment;
import com.trademax.R;
import com.trademax.Utils.Utils;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener, ForgotPasswordView {

    private View view;
    private Activity activity;
    private Button btn_forgotPass;
    private EditText et_email;

    private ForgotpasswordPresenter forgotpasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        activity = getActivity();

        MainActivity.linear_toolbar.setVisibility(View.VISIBLE);
        init();
        listeners();

        return view;
    }

    private void init() {

        btn_forgotPass = view.findViewById(R.id.btn_forgotPass);
        et_email = view.findViewById(R.id.et_email);

        forgotpasswordPresenter = new ForgotpasswordPresenter(activity, this);
    }

    private void listeners() {
        MainActivity.linear_toolbar.setOnClickListener(this);
        btn_forgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == MainActivity.linear_toolbar) {
            LoginSignUpFragment fragment = new LoginSignUpFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();
//            activity.finish();

        } else if (v == btn_forgotPass) {
            forgotpasswordPresenter.hitForgotPass(et_email);
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
    public void changeFragment() {

        OTPFragment fragment = new OTPFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
        transaction.commit();
    }

//    public static addFragment(){
//
//    }
}