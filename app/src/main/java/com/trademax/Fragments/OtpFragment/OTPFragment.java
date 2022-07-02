package com.trademax.Fragments.OtpFragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.trademax.Activities.MainActivity;
import com.trademax.Fragments.ResetPassword.ResetPasswordFragment;
import com.trademax.Fragments.ForgotPass.ForgotPasswordFragment;
import com.trademax.Fragments.OtpFragment.presenter.VerifyOTPPresenter;
import com.trademax.Fragments.OtpFragment.view.VerifyOTPView;
import com.trademax.R;
import com.trademax.Utils.GenericTextWatcher;
import com.trademax.Utils.Utils;


public class OTPFragment extends Fragment implements View.OnClickListener, VerifyOTPView {

    private View view;
    private Activity activity;
    private ImageView img_next;
    private VerifyOTPPresenter verifyOTPPresenter;
    private EditText otpET1, otpET2, otpET3, otpET4;

    private String code;
    private TextView txt_reSendOtp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_o_t_p, container, false);
        activity = getActivity();

        MainActivity.linear_toolbar.setVisibility(View.VISIBLE);

        init();
        listeners();

        verifyOTPPresenter = new VerifyOTPPresenter(activity,  this);
        return view;
    }

    private void init() {

        img_next = view.findViewById(R.id.img_next);
        otpET1 = view.findViewById(R.id.otpET1);
        otpET2 = view.findViewById(R.id.otpET2);
        otpET3 = view.findViewById(R.id.otpET3);
        otpET4 = view.findViewById(R.id.otpET4);
        txt_reSendOtp = view.findViewById(R.id.txt_reSendOtp);

        EditText[] edit = {otpET1, otpET2, otpET3, otpET4};

        otpET1.addTextChangedListener(new GenericTextWatcher(otpET1, edit));
        otpET2.addTextChangedListener(new GenericTextWatcher(otpET2, edit));
        otpET3.addTextChangedListener(new GenericTextWatcher(otpET3, edit));
        otpET4.addTextChangedListener(new GenericTextWatcher(otpET4, edit));

    }

    private void listeners() {

        MainActivity.linear_toolbar.setOnClickListener(this);
        img_next.setOnClickListener(this);
        txt_reSendOtp.setOnClickListener(this);
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

        } else if (v == img_next) {
            code = otpET1.getText().toString().trim() + "" + otpET2.getText().toString().trim() + "" + otpET3.getText().toString().trim() + "" +
                    otpET4.getText().toString().trim();
            if (code.length() != 0) {
                verifyOTPPresenter.hitVerifyOtp(code);
            }

//            ConfirmPasswordFragment fragment = new ConfirmPasswordFragment();
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = fm.beginTransaction();
//            transaction.replace(R.id.mainContainer, fragment);
//            transaction.commit();
        }
        else if (v == txt_reSendOtp){
            verifyOTPPresenter.hitForgotPass();
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

            ResetPasswordFragment fragment = new ResetPasswordFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();
    }
}