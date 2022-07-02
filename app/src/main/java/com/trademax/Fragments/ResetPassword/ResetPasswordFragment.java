package com.trademax.Fragments.ResetPassword;

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
import com.trademax.Fragments.Login_Register.LoginSignUpFragment;
import com.trademax.Fragments.ResetPassword.presenter.ResetPasswordPresenter;
import com.trademax.Fragments.ResetPassword.view.ResetPasswordView;
import com.trademax.Fragments.ForgotPass.ForgotPasswordFragment;
import com.trademax.R;
import com.trademax.Utils.Utils;


public class ResetPasswordFragment extends Fragment implements View.OnClickListener, ResetPasswordView {

    private View view;
    private Activity activity;
    private EditText et_newPassword;
    private EditText et_confirmPassword;
    private Button btn_login;
    private ResetPasswordPresenter confirmPasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_confirm_password, container, false);
        activity = getActivity();

        MainActivity.linear_toolbar.setVisibility(View.VISIBLE);

        init();
        listeners();

        confirmPasswordPresenter = new ResetPasswordPresenter(activity,this);
        return view;
    }

    private void init() {

        et_newPassword = view.findViewById(R.id.et_newPassword);
        et_confirmPassword = view.findViewById(R.id.et_confirmPassword);
        btn_login = view.findViewById(R.id.btn_login);

    }

    private void listeners() {

        MainActivity.linear_toolbar.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == MainActivity.linear_toolbar) {
            ForgotPasswordFragment fragment = new ForgotPasswordFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();
//            activity.finish();

        }
        if (v == btn_login) {

            confirmPasswordPresenter.hitConfirmPass(et_newPassword,et_confirmPassword);

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

        LoginSignUpFragment fragment = new LoginSignUpFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
        transaction.commit();
    }
}