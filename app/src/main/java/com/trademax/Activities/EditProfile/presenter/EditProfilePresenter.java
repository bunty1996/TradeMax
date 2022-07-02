package com.trademax.Activities.EditProfile.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.EditText;

import com.trademax.Activities.EditProfile.view.EditProfileView;
import com.trademax.Handler.UpdateProfileHandler;
import com.trademax.Handler.UploadUserImageHnadler;
import com.trademax.Models.ProfileUpdate.ProfileupdateExample;
import com.trademax.Models.UploadUserImage.UploadUserImageExample;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfilePresenter {

    private Activity activity;
    private EditProfileView editProfileView;
    private PorterShapeImageView profile_image;
    private EditText et_fullName;
    private EditText et_contact;
    private EditText et_address;
    private EditText et_city;
    private EditText et_state;
    private EditText et_country;
    private RequestBody id;
    private MultipartBody.Part imagePart;


    public EditProfilePresenter(Activity activity, EditProfileView editProfileView) {
        this.activity = activity;
        this.editProfileView = editProfileView;

    }

    public void hitUpdateProfileMethod(EditText et_fullName, EditText et_contact, EditText et_address, EditText et_city, EditText et_state, EditText et_country) {
        this.et_fullName = et_fullName;
        this.et_contact = et_contact;
        this.et_address = et_address;
        this.et_city = et_city;
        this.et_state = et_state;
        this.et_country = et_country;
        if (checkValidation()) {
            String userId = CSPreferences.readString(activity, Utils.USERID);
            if (userId == "") {
                userId = null;
                editProfileView.showMessage(activity,"Please login first");
            }else {
                editProfileView.showDialog(activity);
                WebServices.getmInstance().updateProfileMethod(userId, et_fullName.getText().toString().trim(), et_address.getText().toString().trim(), et_contact.getText().toString().trim(), ""
                        , et_state.getText().toString().trim(), et_city.getText().toString().trim(), et_country.getText().toString().trim(), "", "", new UpdateProfileHandler() {
                            @Override
                            public void onSuccess(ProfileupdateExample profileupdateExample) {
                                editProfileView.hideDialog();
                                if (profileupdateExample != null) {
                                    if (profileupdateExample.getIsSuccess() == true) {
                                        editProfileView.showMessage(activity, profileupdateExample.getMessage());

                                        CSPreferences.putString(activity, Utils.USERNAME, profileupdateExample.getData().getFullname());
                                        CSPreferences.putString(activity, Utils.CONTACTNUMBER, profileupdateExample.getData().getPhoneNumber());
                                        CSPreferences.putString(activity, Utils.ADDRESS, profileupdateExample.getData().getAddress());
                                        CSPreferences.putString(activity, Utils.CITY, profileupdateExample.getData().getCity());
                                        CSPreferences.putString(activity, Utils.STATE, profileupdateExample.getData().getState());
                                        CSPreferences.putString(activity, Utils.COUNTRY, profileupdateExample.getData().getCountry());

                                        activity.finish();

                                    } else {
                                        editProfileView.showMessage(activity, profileupdateExample.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onError(String message) {
                                editProfileView.hideDialog();
                                editProfileView.showMessage(activity, message);
                            }
                        });

            }
        }
    }

    public void hitUpdateImageMethod(Bitmap photo) {
//        if (checkValidation()) {
//

        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream);
            byte[] photoByteArray = stream.toByteArray();
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray);
            id = RequestBody.create(MediaType.parse("multipart/form-data"), CSPreferences.readString(activity, Utils.USERID));
            Random random = new Random();
            imagePart =
                    MultipartBody.Part.createFormData("file", "abc" + random.nextInt(1000000) + ".jpg", requestFile);
        }
//            }
        editProfileView.showDialog(activity);
        WebServices.getmInstance().uploadimageMethod(id, imagePart, new UploadUserImageHnadler() {
            @Override
            public void onSuccess(UploadUserImageExample uploadUserImageExample) {
                editProfileView.hideDialog();
                if (uploadUserImageExample != null) {
                    if (uploadUserImageExample.getIsSuccess() == true) {
//                        editProfileView.showMessage(activity, uploadUserImageExample.getMessage());
                    } else {
//                        editProfileView.showMessage(activity, uploadUserImageExample.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                editProfileView.hideDialog();
                editProfileView.showMessage(activity, message);
            }
        });
    }


    public boolean checkValidation() {
        if (et_fullName.getText().toString().trim().length() == 0) {
            editProfileView.showMessage(activity, "Please enter name");
            return false;
        } else if (et_contact.getText().toString().trim().length() == 0) {
            editProfileView.showMessage(activity, "Please enter contact number");
            return false;
        } else if (et_address.getText().toString().trim().length() == 0) {
            editProfileView.showMessage(activity, "Please enter address");
            return false;
        } else if (et_city.getText().toString().trim().length() == 0) {
            editProfileView.showMessage(activity, "Please enter city");
            return false;
        } else if (et_state.getText().toString().trim().length() == 0) {
            editProfileView.showMessage(activity, "Please enter state");
            return false;
        } else if (et_country.getText().toString().trim().length() == 0) {
            editProfileView.showMessage(activity, "Please enter country");
            return false;
        }
        return true;
    }
}