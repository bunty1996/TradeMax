package com.trademax.Activities.EditProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trademax.Activities.EditProfile.presenter.EditProfilePresenter;
import com.trademax.Activities.EditProfile.view.EditProfileView;
import com.trademax.Handler.CurrentUserHandler;
import com.trademax.Models.CurrentUserData.CurrentUserExample;
import com.trademax.R;
import com.trademax.Utils.CSPreferences;
import com.trademax.Utils.Utils;
import com.trademax.Utils.WebServices;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.io.File;
import java.io.IOException;
import java.util.Date;


import static com.facebook.internal.Validate.hasPermission;
import static com.trademax.Utils.Utils.hasFeatureCamera;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, EditProfileView {
    private static final String TAG = "EditProfileActivity";
    private Activity activity;
    private ImageView img_back;
    private EditProfilePresenter editProfilePresenter;
    private LinearLayout linear_done;
    private Button btn_done;
    private PorterShapeImageView profile_image;
    private TextView txt_name;
    private TextView txt_email;

    private EditText et_fullName;
    private EditText et_contact;
    private EditText et_address;
    private EditText et_city;
    private EditText et_state;
    private EditText et_country;
    private ImageView img_camera;

    final private int PICK_IMAGE = 1;
    final private int CAPTURE_IMAGE = 2;
    private String imgPath;
    private Bitmap bitmap;
    private File mCurrentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        activity = this;
        init();
        listiners();

        if (CSPreferences.readString(activity, Utils.USERPHOTO).equals("null") ||
                CSPreferences.readString(activity, Utils.USERPHOTO).equals("")) {
            profile_image.setImageResource(R.drawable.logo);
        } else {
            Glide.with(activity).load(WebServices.ImageBaseURL + CSPreferences.readString(activity, Utils.USERPHOTO)).placeholder(R.drawable.logo).into(profile_image);
        }

        showCurrentUserDataMethod();
        editProfilePresenter = new EditProfilePresenter(activity, this);

    }

    private void init() {

        img_back = findViewById(R.id.img_back);
        linear_done = findViewById(R.id.linear_done);
        btn_done = findViewById(R.id.btn_done);
        img_camera = findViewById(R.id.img_camera);
        profile_image = findViewById(R.id.profile_image);
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);

        et_fullName = findViewById(R.id.et_fullName);
        et_contact = findViewById(R.id.et_contact);
        et_address = findViewById(R.id.et_address);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        et_country = findViewById(R.id.et_country);

    }

    private void listiners() {
        img_back.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        img_camera.setOnClickListener(this);

    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
//            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            finish();
        } else if (v == btn_done) {
            editProfilePresenter.hitUpdateProfileMethod(et_fullName, et_contact, et_address, et_city, et_state, et_country);
            editProfilePresenter.hitUpdateImageMethod(bitmap);
        } else if (v == img_camera) {
            selectImage();
        }
    }

    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }


    public String getImagePath() {
        return imgPath;
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    chooseFromCamera();

                } else if (options[item].equals("Choose from Gallery")) {
                    chooseFromGallery();

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void chooseFromCamera() {

        if (!hasFeatureCamera(activity)) {
            Toast.makeText(this, "Unable to detect camera", Toast.LENGTH_LONG).show();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !hasPermission(this, Manifest.permission.CAMERA)
        ) {
            requestPermissions(new String[]{(Manifest.permission.CAMERA)}, CAPTURE_IMAGE);
        } else {
            openCamera();
        }

    }

    private void chooseFromGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ) {
            requestPermissions(new String[]{(Manifest.permission.WRITE_EXTERNAL_STORAGE)},
                    PICK_IMAGE
            );
        } else {
            openGallery();
        }
    }

    //camra and galary
    private void openCamera() {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAPTURE_IMAGE);
    }


    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_IMAGE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                profile_image.setImageBitmap(photo);
                editProfilePresenter.hitUpdateImageMethod(photo);

                Log.d(TAG, "onAc" + photo);
            } else if (requestCode == PICK_IMAGE) {

                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    String path = getPathFromURI(selectedImageUri);
                    Log.d(TAG, "onActivityimg" + path);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        editProfilePresenter.hitUpdateImageMethod(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    profile_image.setImageURI(Uri.parse(path));
                    Log.d(TAG, "onActivityu" + selectedImageUri);
                }
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
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

    public void showCurrentUserDataMethod() {

        String userId = CSPreferences.readString(activity, Utils.USERID);
        String token = CSPreferences.readString(activity, Utils.TOKEN);

        if (userId == "") {
            userId = null;
        } else {
//            editProfileView.showDialog(activity);
            WebServices.getmInstance().showCurrentUserDataMethod(token, userId, new CurrentUserHandler() {
                @Override
                public void onSuccess(CurrentUserExample currentUserExample) {
//                            editProfileView.hideDialog();
                    if (currentUserExample != null) {
                        if (currentUserExample.getIsSuccess() == true) {
//                                    editProfileView.showMessage(activity, currentUserExample.getMessage());

                            txt_name.setText(currentUserExample.getData().getFullname());
                            txt_email.setText(currentUserExample.getData().getEmail());
                            et_fullName.setText(currentUserExample.getData().getFullname());
                            et_contact.setText(currentUserExample.getData().getPhoneNumber());
                            et_address.setText(currentUserExample.getData().getAddress());
                            et_city.setText(currentUserExample.getData().getCity());
                            et_state.setText(currentUserExample.getData().getState());
                            et_country.setText(currentUserExample.getData().getCountry());
//                                    activity.finish();

                        } else {
                            Toast.makeText(activity, currentUserExample.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onError(String message) {
//                            editProfileView.hideDialog();
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}