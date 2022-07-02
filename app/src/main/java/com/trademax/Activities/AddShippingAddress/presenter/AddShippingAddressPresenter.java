package com.trademax.Activities.AddShippingAddress.presenter;

import android.app.Activity;
import android.widget.EditText;

import com.trademax.Activities.AddShippingAddress.view.AddShippingAddressView;
import com.trademax.Handler.AddAddressHandler;
import com.trademax.Models.AddAddress.AddAddressExample;
import com.trademax.Models.GetAddress.GetAddressDatum;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;

public class AddShippingAddressPresenter {

    private final String dataString;
    private Activity activity;
    private AddShippingAddressView addShippingAddressView;
    private String et_name;
    private String et_address;
    private String et_city;
    private String et_state;
    private String et_zipCode;
    private String et_country;
    private String addresId;
    private GetAddressDatum getAddressDatum;
    private String addressId;

    public AddShippingAddressPresenter(Activity activity, AddShippingAddressView addShippingAddressView, String dataString) {
        this.activity = activity;
        this.addShippingAddressView = addShippingAddressView;
        this.dataString = dataString;

    }

    public void addAddressDetails(EditText etName, EditText etAddress, EditText etcity, EditText etstate, EditText etzipCode, EditText etcountry, GetAddressDatum getAddressDatum) {

        this.et_name = etName.toString();
        this.et_address = etAddress.toString();
        this.et_city = etcity.toString();
        this.et_state = etstate.toString();
        this.et_zipCode = etzipCode.toString();
        this.et_country = etcountry.toString();
        this.getAddressDatum = getAddressDatum;

        String userId = CSPreferences.readString(activity, Utils.USERID);

        if (userId == "") {
            userId = null;

            addShippingAddressView.showMessage(activity,"Please login first");
        } else {

            if (checkValidation()) {
                addShippingAddressView.showDialog(activity);

                if (dataString.equalsIgnoreCase("123")) {
                    addressId = "";
                } else {
                    addressId = getAddressDatum.getId();
                }

                WebServices.getmInstance().addAddressDetailsMethod(addressId, userId, etName.getText().toString().trim(), etAddress.getText().toString().trim(),
                        etcity.getText().toString().trim(), etstate.getText().toString().trim(), etzipCode.getText().toString().trim(),
                        etcountry.getText().toString().trim(), new AddAddressHandler() {
                            @Override
                            public void onSuccess(AddAddressExample addAddressExample) {
                                addShippingAddressView.hideDialog();
                                if (addAddressExample != null) {
                                    if (addAddressExample.getIsSuccess() == true) {
                                        addShippingAddressView.showMessage(activity, addAddressExample.getMessage());
                                        addAddressExample.getData().getAddress();
                                        activity.finish();

                                    } else {
                                        addShippingAddressView.showMessage(activity, addAddressExample.getMessage());
                                    }
                                } else {
                                    addShippingAddressView.showMessage(activity, addAddressExample.getMessage());
                                }
                            }

                            @Override
                            public void onError(String message) {
                                addShippingAddressView.hideDialog();
                                addShippingAddressView.showMessage(activity, message);
                            }
                        });
            }
        }

    }

    public boolean checkValidation() {
        if (et_name.toString().trim().length() == 0) {
            addShippingAddressView.showMessage(activity, "Please enter name");
            return false;
        } else if (et_address.toString().trim().length() == 0) {
            addShippingAddressView.showMessage(activity, "Please enter address");
            return false;
        } else if (et_city.toString().trim().length() == 0) {
            addShippingAddressView.showMessage(activity, "Please enter city");
            return false;
        } else if (et_state.toString().trim().length() == 0) {
            addShippingAddressView.showMessage(activity, "Please enter state");
            return false;
        } else if (et_zipCode.toString().trim().length() == 0) {
            addShippingAddressView.showMessage(activity, "Please enter zipCode");
            return false;
        } else if (et_country.toString().trim().length() == 0) {
            addShippingAddressView.showMessage(activity, "Please enter country");
            return false;
        }
        return true;
    }

}
