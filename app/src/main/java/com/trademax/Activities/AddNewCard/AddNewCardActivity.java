package com.trademax.Activities.AddNewCard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.view.CardMultilineWidget;
import com.trademax.Activities.AddNewCard.presenter.TransectionPresenter;
import com.trademax.Activities.AddNewCard.view.TransectionView;
import com.trademax.Models.CartListModel.CartlistData;
import com.trademax.R;
import com.trademax.Utils.Utils;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.util.ArrayList;

public class AddNewCardActivity extends AppCompatActivity implements View.OnClickListener, TransectionView {

    //    private CreditCardView creditCardView;
    CardMultilineWidget cardMultilineWidget;
    private ImageView tool_back;
    private Button card_pay_button;
    private Activity activity;

    private TransectionPresenter transectionPresenter;
    private String amount;
    private String addressId;
    private ArrayList<CartlistData> cartlistData;
    private String pricestring;
    private String tv_totalprice;
    private int valueprice;
    private TextView txt_totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        activity = this;
        init();
        liteners();

        cardMultilineWidget = findViewById(R.id.cardMultiLineWidget);

        String dataString = getIntent().getStringExtra("cartlistData");
        cartlistData = new Gson().fromJson(dataString, new TypeToken<ArrayList<CartlistData>>() {
        }.getType());

        pricestring = getIntent().getStringExtra("price");
//        pricestring = String.valueOf(cartlistData.get(0).getTotal());

//        int tax = 15;

//        int finalPrice = (Integer.valueOf(pricestring) * tax) / 100;
//        Log.e("finalPrice", String.valueOf(finalPrice));

   /*     String mynum2 = pricestring;
        final int backTotalPrice = Integer.parseInt(mynum2);
//        valueprice = delivery + backTotalPrice + finalPrice;
        valueprice = backTotalPrice + finalPrice;*/

//        double num1 = Double.parseDouble(pricestring);
//        double num2 = Double.parseDouble(String.valueOf(tax));
//        double sum = num1 + num2;

        tv_totalprice = String.valueOf(pricestring);

        txt_totalPrice.setText("Pay $" + pricestring + " using");


//        Intent intent = getIntent();
//        ArrayList<CartlistData> myList = (ArrayList<CartlistData>) intent.getSerializableExtra("cartlistData");
//
//        addressId = intent.getStringExtra("addressId");
//        amount = intent.getStringExtra("amount");

//        ArrayList<CartlistData> list = (ArrayList<CartlistData>) intent.getSerializableExtra("cartlistData");

//        Intent intent = getIntent();
//        cartArrayList = intent.getParcelableArrayListExtra("cartlistData");
////
////        addressId = args.getString("addressId");
////        amount = args.getString("amount");
//        Log.e("mlksdlf",addressId);

        transectionPresenter = new TransectionPresenter(activity, this);

    }


    private void saveCard() {

        Card card = cardMultilineWidget.getCard();
        if (card == null) {
            Toast.makeText(getApplicationContext(), "Invalid card", Toast.LENGTH_SHORT).show();
        } else {
            if (!card.validateCard()) {
                // Do not continue token creation.
                Toast.makeText(getApplicationContext(), "Invalid card", Toast.LENGTH_SHORT).show();
            } else {
                CreateToken(card);
            }
        }
    }


    private void CreateToken(Card card) {
        Stripe stripe = new Stripe(getApplicationContext(), getString(R.string.publishablekey));
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {

                        Log.e("Stripe Token", token.getId());
                        Intent intent = new Intent();
                        intent.putExtra("card", token.getCard().getLast4());
                        intent.putExtra("stripe_token", token.getId());
                        intent.putExtra("cardtype", token.getCard().getBrand());

                        transectionPresenter.getTransectionToken(token.getId(), tv_totalprice);
                        setResult(0077, intent);
                        finish();
                    }

                    public void onError(Exception error) {

                        // Show localized error message
                        Toast.makeText(getApplicationContext(),
                                error.getLocalizedMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

   /* private void CreateToken(Card card) {
//        Stripe stripe = new Stripe(this, getString(R.string.publishablekey));

        Stripe stripe = new Stripe(this, getString(R.string.publishablekey));
        stripe.createCardToken(card, new ApiResultCallback<Token>() {
            @Override
            public void onSuccess(Token token) {
                //getTokenList().addToList(token);

                // Send token to your server
                Log.e("Stripe Token", token.getId());
                Intent intent = new Intent();
                intent.putExtra("card", token.getCard().getLast4());
                intent.putExtra("stripe_token", token.getId());
                intent.putExtra("cardtype", token.getCard().getBrand());

                transectionPresenter.getTransectionToken(token.getId(), tv_totalprice);
                setResult(0077, intent);
                finish();
            }

            @Override
            public void onError(@NotNull Exception error) {
                // Show localized error message
                Toast.makeText(getApplicationContext(),
                        error.getLocalizedMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });

    }*/


    private void init() {

        tool_back = findViewById(R.id.tool_back);
        txt_totalPrice = findViewById(R.id.txt_totalPrice);
        card_pay_button = findViewById(R.id.card_pay_button);

    }

    private void liteners() {
        tool_back.setOnClickListener(this);
        card_pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCard();
            }
        });
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }

    @Override
    public void onClick(View v) {

        if (v == tool_back) {
            finish();
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
}