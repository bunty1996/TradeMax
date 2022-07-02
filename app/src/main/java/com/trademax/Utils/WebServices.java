package com.trademax.Utils;

import com.trademax.Handler.AddAddressHandler;
import com.trademax.Handler.AddToCartHandler;
import com.trademax.Handler.BannerHandler;
import com.trademax.Handler.CartListHandler;
import com.trademax.Handler.ChangePassHandler;
import com.trademax.Handler.CheckoutHandler;
import com.trademax.Handler.CurrentUserHandler;
import com.trademax.Handler.DeleteListHandler;
import com.trademax.Handler.FavouriteHandler;
import com.trademax.Handler.FilterHandler;
import com.trademax.Handler.ForgotPassHandler;
import com.trademax.Handler.GetAddressHandler;
import com.trademax.Handler.GetCategoriesDetailsHandler;
import com.trademax.Handler.GetOrdersHandler;
import com.trademax.Handler.GetProductBySubCategoriesHandler;
import com.trademax.Handler.HomeGetAllHandler;
import com.trademax.Handler.LoginHandler;
import com.trademax.Handler.MyOrderHandler;
import com.trademax.Handler.PainterPlasterLikeProductHandler;
import com.trademax.Handler.RegisterHandler;
import com.trademax.Handler.ResetPassHandler;
import com.trademax.Handler.SearchCategoriesDetailsHandler;
import com.trademax.Handler.SearchHandler;
import com.trademax.Handler.SimilarproductHandler;
import com.trademax.Handler.SocialLoginHandler;
import com.trademax.Handler.TransectionHandler;
import com.trademax.Handler.UpdateProfileHandler;
import com.trademax.Handler.UploadUserImageHnadler;
import com.trademax.Handler.VerifyOTPHandler;
import com.trademax.Models.AddAddress.AddAddressExample;
import com.trademax.Models.AddToCart.AddToCartExample;
import com.trademax.Models.CartListModel.CartlistExample;
import com.trademax.Models.ChangePassword.ChangePassExample;
import com.trademax.Models.CheckoutModel.CheckoutExample;
import com.trademax.Models.CurrentUserData.CurrentUserExample;
import com.trademax.Models.DeleteList.DeleteExample;
import com.trademax.Models.Favourite.FavouriteExample;
import com.trademax.Models.Filter.FilterExample;
import com.trademax.Models.ForgotPass.ForgotPassExample;
import com.trademax.Models.GetAddress.GetAddressExample;
import com.trademax.Models.GetBanner.BannerExample;
import com.trademax.Models.GetCategoriesDetails.GetCategoriesDetailsExample;
import com.trademax.Models.GetOrders.GetOrderExample;
import com.trademax.Models.HomeGetAll.GetAllProductsExample;
import com.trademax.Models.Login.LoginExample;
import com.trademax.Models.MyOrder.MyOrderExample;
import com.trademax.Models.PainterPlasterLikeProduct.PainterPlasterLikeProductExample;
import com.trademax.Models.ProductBySubCategories.ProductBySubCatExample;
import com.trademax.Models.ProfileUpdate.ProfileupdateExample;
import com.trademax.Models.Register.SignUpExample;
import com.trademax.Models.ResetPass.ResetPaswordExample;
import com.trademax.Models.SearchCategoriesDetailsExample.SearchCategoriesDetailsExample;
import com.trademax.Models.SearchModel.SearchExample;
import com.trademax.Models.SimilarProduct.SimilarProductExample;
import com.trademax.Models.SocialLogin.SocialLoginExample;
import com.trademax.Models.Transections.TransectionsExample;
import com.trademax.Models.UploadUserImage.UploadUserImageExample;
import com.trademax.Models.VerifyOTP.VerifyOTPExample;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {
    private static final String TAG = "WebServices";
    //    public static final String BASE_URL = "http://93.188.167.68:4604/api/";
    public static final String BASE_URL = "http://13.54.226.124:4604/api/";
    public static final String ImageBaseURL = "http://13.54.226.124/";
    private static Retrofit retrofit = null;
    private static WebServices mInstance;
    private API api;

    public WebServices() {
        mInstance = this;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(API.class);

        }
    }

    public static WebServices getmInstance() {
        return mInstance;
    }

    public void loginMethod(String email, String password, final LoginHandler loginHandler) {
        api.loginAPI(email, password).enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {

                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        loginHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                loginHandler.onError(t.getMessage());
            }
        });
    }

    public void registerMethod(String name, String email, String password, final RegisterHandler registerHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fullname", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        api.registerAPI(jsonObject).enqueue(new Callback<SignUpExample>() {
            @Override
            public void onResponse(Call<SignUpExample> call, Response<SignUpExample> response) {
                if (response.code() == 200) {
                    registerHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        registerHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SignUpExample> call, Throwable t) {
                registerHandler.onError(t.getMessage());
            }
        });
    }

    public void forgotPassMethod(String email, final ForgotPassHandler forgotPassHandler) {
        api.forgotPass(email).enqueue(new Callback<ForgotPassExample>() {
            @Override
            public void onResponse(Call<ForgotPassExample> call, Response<ForgotPassExample> response) {
                if (response.code() == 200) {
                    forgotPassHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        forgotPassHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ForgotPassExample> call, Throwable t) {
                forgotPassHandler.onError(t.getMessage());
            }
        });
    }

    public void verifyOTPMethod(String token, String otp, final VerifyOTPHandler verifyOTPHandler) {
        api.verifyOTPApi(token, otp).enqueue(new Callback<VerifyOTPExample>() {
            @Override
            public void onResponse(Call<VerifyOTPExample> call, Response<VerifyOTPExample> response) {
                if (response.code() == 200) {
                    verifyOTPHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        verifyOTPHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<VerifyOTPExample> call, Throwable t) {
                verifyOTPHandler.onError(t.getMessage());
            }
        });
    }

    public void resetPasswordMethod(String token, String confirmPass, final ResetPassHandler resetPassHandler) {
        api.resetPasswordAPI(token, confirmPass).enqueue(new Callback<ResetPaswordExample>() {
            @Override
            public void onResponse(Call<ResetPaswordExample> call, Response<ResetPaswordExample> response) {
                if (response.code() == 200) {
                    resetPassHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        resetPassHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResetPaswordExample> call, Throwable t) {
                resetPassHandler.onError(t.getMessage());
            }
        });
    }

    public void updateProfileMethod(String id, String fullname, String address, String phoneNumber,
                                    String sex, String state, String city, String country,
                                    String zipCode, String deviceToken, final UpdateProfileHandler updateProfileHandler) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fullname", fullname.toString());
        jsonObject.addProperty("address", address);
        jsonObject.addProperty("phoneNumber", phoneNumber);
        jsonObject.addProperty("sex", sex);
        jsonObject.addProperty("state", state);
        jsonObject.addProperty("city", city);
        jsonObject.addProperty("country", country);
        jsonObject.addProperty("zipCode", zipCode);
        jsonObject.addProperty("deviceToken", deviceToken);
        api.updateProfileAPI(id, jsonObject).enqueue(new Callback<ProfileupdateExample>() {
            @Override
            public void onResponse(Call<ProfileupdateExample> call, Response<ProfileupdateExample> response) {
                if (response.code() == 200) {
                    updateProfileHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        updateProfileHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileupdateExample> call, Throwable t) {
                updateProfileHandler.onError(t.getMessage());
            }
        });
    }

    public void changePasswordMethod(String token, String content_type, String id, String oldPass, String newPass, final ChangePassHandler changePassHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("oldPassword", oldPass.toString());
        jsonObject.addProperty("newPassword", newPass);
        api.changePasswordAPI(token, content_type, id, jsonObject).enqueue(new Callback<ChangePassExample>() {
            @Override
            public void onResponse(Call<ChangePassExample> call, Response<ChangePassExample> response) {
                if (response.code() == 200) {
                    changePassHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        changePassHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePassExample> call, Throwable t) {
                changePassHandler.onError(t.getMessage());
            }
        });
    }

    public void showCurrentUserDataMethod(String token, String id, final CurrentUserHandler currentUserHandler) {
        api.showCurrentDataApi(token, id).enqueue(new Callback<CurrentUserExample>() {
            @Override
            public void onResponse(Call<CurrentUserExample> call, Response<CurrentUserExample> response) {
                if (response.code() == 200) {
                    currentUserHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        currentUserHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentUserExample> call, Throwable t) {
                currentUserHandler.onError(t.getMessage());
            }
        });
    }

    public void getOrdersMethod(String user_id, final GetOrdersHandler getOrdersHandler) {
        api.getOrderApi(user_id).enqueue(new Callback<GetOrderExample>() {
            @Override
            public void onResponse(Call<GetOrderExample> call, Response<GetOrderExample> response) {
                if (response.code() == 200) {
                    getOrdersHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getOrdersHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOrderExample> call, Throwable t) {
                getOrdersHandler.onError(t.getMessage());
            }
        });
    }

    public void getHomeDataMethod(String userid, int pageNo, int pageSize, final HomeGetAllHandler homeGetAllHandler) {
        api.getHomeDataApi(userid, pageNo, pageSize).enqueue(new Callback<GetAllProductsExample>() {
            @Override
            public void onResponse(Call<GetAllProductsExample> call, Response<GetAllProductsExample> response) {
                if (response.code() == 200) {
                    homeGetAllHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        homeGetAllHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllProductsExample> call, Throwable t) {
                homeGetAllHandler.onError(t.getMessage());
            }
        });
    }

    public void getproductBySubCategoriesMethod(String userId, int pageNo, int pageSize, String subCategoryId, final GetProductBySubCategoriesHandler getProductBySubCategoriesHandler) {
        api.getproductBySubCategoriesApi(userId, pageNo, pageSize, subCategoryId).enqueue(new Callback<ProductBySubCatExample>() {
            @Override
            public void onResponse(Call<ProductBySubCatExample> call, Response<ProductBySubCatExample> response) {
                if (response.code() == 200) {
                    getProductBySubCategoriesHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getProductBySubCategoriesHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductBySubCatExample> call, Throwable t) {
                getProductBySubCategoriesHandler.onError(t.getMessage());
            }
        });
    }

    public void getsortproductBySubCategoriesMethod(String userId, int pageNo, int pageSize, String subCategoryId, String pricesort, final GetProductBySubCategoriesHandler getProductBySubCategoriesHandler) {
        api.getsortproductBySubCategoriesApi(userId, pageNo, pageSize, subCategoryId, pricesort).enqueue(new Callback<ProductBySubCatExample>() {
            @Override
            public void onResponse(Call<ProductBySubCatExample> call, Response<ProductBySubCatExample> response) {
                if (response.code() == 200) {
                    getProductBySubCategoriesHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getProductBySubCategoriesHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductBySubCatExample> call, Throwable t) {
                getProductBySubCategoriesHandler.onError(t.getMessage());
            }
        });
    }

    public void getFavouriteDataMethod(String userId, final FavouriteHandler favouriteHandler) {
        api.getFavouriteApi(userId).enqueue(new Callback<FavouriteExample>() {
            @Override
            public void onResponse(Call<FavouriteExample> call, Response<FavouriteExample> response) {
                if (response.code() == 200) {
                    favouriteHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        favouriteHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<FavouriteExample> call, Throwable t) {
                favouriteHandler.onError(t.getMessage());
            }
        });
    }
    //upload image

    //upload image
    public void uploadimageMethod(RequestBody id, MultipartBody.Part file, final UploadUserImageHnadler uploadUserImageHnadler) {

        api.UploadFile(id, file).enqueue(new Callback<UploadUserImageExample>() {
            @Override
            public void onResponse(Call<UploadUserImageExample> call, Response<UploadUserImageExample> response) {
                if (response.code() == 200) {
                    uploadUserImageHnadler.onSuccess(response.body());
                } else {

                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        uploadUserImageHnadler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadUserImageExample> call, Throwable t) {
                uploadUserImageHnadler.onError(t.getMessage());
            }
        });
    }


    public void getCategoriesDetailsMethod(String category_id, final GetCategoriesDetailsHandler getCategoriesDetailsHandler) {
        api.getCategoriesDetailsApi(category_id).enqueue(new Callback<GetCategoriesDetailsExample>() {
            @Override
            public void onResponse(Call<GetCategoriesDetailsExample> call, Response<GetCategoriesDetailsExample> response) {
                if (response.code() == 200) {
                    getCategoriesDetailsHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getCategoriesDetailsHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCategoriesDetailsExample> call, Throwable t) {
                getCategoriesDetailsHandler.onError(t.getMessage());
            }
        });
    }


    public void addAddressDetailsMethod(String addressId, String userId, String fullName, String address, String city, String state,
                                        String zipCode, String country, final AddAddressHandler addAddressHandler) {
        api.addAddressApi(addressId, userId, fullName, address, city, state, zipCode, country).enqueue(new Callback<AddAddressExample>() {
            @Override
            public void onResponse(Call<AddAddressExample> call, Response<AddAddressExample> response) {
                if (response.code() == 200) {
                    addAddressHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        addAddressHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddAddressExample> call, Throwable t) {
                addAddressHandler.onError(t.getMessage());
            }
        });
    }

    public void getAddressDetailsMethod(String userId, String addressId, final GetAddressHandler getAddressHandler) {
        api.getAddressApi(userId, addressId).enqueue(new Callback<GetAddressExample>() {
            @Override
            public void onResponse(Call<GetAddressExample> call, Response<GetAddressExample> response) {
                if (response.code() == 200) {
                    getAddressHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getAddressHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAddressExample> call, Throwable t) {
                getAddressHandler.onError(t.getMessage());
            }
        });
    }

    public void getSearchMethod(String category_id, final SearchCategoriesDetailsHandler searchHandler) {
        api.getallsearchApi(category_id).enqueue(new Callback<SearchCategoriesDetailsExample>() {
            @Override
            public void onResponse(Call<SearchCategoriesDetailsExample> call, Response<SearchCategoriesDetailsExample> response) {
                if (response.code() == 200) {
                    searchHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        searchHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchCategoriesDetailsExample> call, Throwable t) {
                searchHandler.onError(t.getMessage());
            }
        });
    }

    //like
    public void getlikeProductMethod(String userId, String productId, final PainterPlasterLikeProductHandler painterPlasterLikeProductHandler) {
        api.getLikeProductApi(userId, productId).enqueue(new Callback<PainterPlasterLikeProductExample>() {
            @Override
            public void onResponse(Call<PainterPlasterLikeProductExample> call, Response<PainterPlasterLikeProductExample> response) {
                if (response.code() == 200) {
                    painterPlasterLikeProductHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        painterPlasterLikeProductHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PainterPlasterLikeProductExample> call, Throwable t) {
                painterPlasterLikeProductHandler.onError(t.getMessage());
            }
        });
    }

    public void getCartList(String userId, final CartListHandler cartListHandler) {
        api.getcartlist(userId).enqueue(new Callback<CartlistExample>() {
            @Override
            public void onResponse(Call<CartlistExample> call, Response<CartlistExample> response) {
                if (response.code() == 200) {
                    cartListHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        cartListHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CartlistExample> call, Throwable t) {
                cartListHandler.onError(t.getMessage());
            }
        });
    }

    public void getdelete(String userId, String productId, final DeleteListHandler deleteListHandler) {
        api.getdeletelist(userId, productId).enqueue(new Callback<DeleteExample>() {
            @Override
            public void onResponse(Call<DeleteExample> call, Response<DeleteExample> response) {
                if (response.code() == 200) {
                    deleteListHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        deleteListHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DeleteExample> call, Throwable t) {
                deleteListHandler.onError(t.getMessage());
            }
        });
    }

    public void getaddtocart(String userId, String productId, String quantity, String total,
                             String variation, String status, final AddToCartHandler addToCartHandler) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("productId", productId);
        jsonObject.addProperty("quantity", quantity);
        jsonObject.addProperty("total", total);
        jsonObject.addProperty("variation", variation);
        jsonObject.addProperty("status", status);
        api.getaddtocart(jsonObject).enqueue(new Callback<AddToCartExample>() {
            @Override
            public void onResponse(Call<AddToCartExample> call, Response<AddToCartExample> response) {
                if (response.code() == 200) {
                    addToCartHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        addToCartHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddToCartExample> call, Throwable t) {
                addToCartHandler.onError(t.getMessage());
            }
        });
    }

    //similar product//
    public void getsimilarproduct(String categoriid, String productId, final SimilarproductHandler similarproductHandler) {
        api.getsimilarproduct(categoriid, productId).enqueue(new Callback<SimilarProductExample>() {
            @Override
            public void onResponse(Call<SimilarProductExample> call, Response<SimilarProductExample> response) {
                if (response.code() == 200) {
                    similarproductHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        similarproductHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SimilarProductExample> call, Throwable t) {
                similarproductHandler.onError(t.getMessage());
            }
        });
    }
//filter//

    public void getfilterproduct(String size, String minPrice, String maxPrice, String color, final FilterHandler filterHandler) {
        api.getfilterproducts(size, minPrice, maxPrice, color).enqueue(new Callback<FilterExample>() {
            @Override
            public void onResponse(Call<FilterExample> call, Response<FilterExample> response) {
                if (response.code() == 200) {
                    filterHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        filterHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterExample> call, Throwable t) {
                filterHandler.onError(t.getMessage());
            }
        });
    }


    public void getcheckout(String userid, String total, String tax, String address, JsonArray jsonArray, final CheckoutHandler handler) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userid);
        jsonObject.addProperty("totalAmount", total);
        jsonObject.addProperty("tax", tax);
        jsonObject.addProperty("addressId", address);
//        jsonObject.addProperty("cart", );
        jsonObject.add("cart", jsonArray);
        api.getcheckout(jsonObject).enqueue(new Callback<CheckoutExample>() {
            @Override
            public void onResponse(Call<CheckoutExample> call, Response<CheckoutExample> response) {
                if (response.code() == 200) {
                    handler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        handler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckoutExample> call, Throwable t) {
                handler.onError(t.getMessage());
            }
        });
    }

    public void getmyorders(String userId, MyOrderHandler myOrderHandler) {
        api.getmyorders(userId).enqueue(new Callback<MyOrderExample>() {
            @Override
            public void onResponse(Call<MyOrderExample> call, Response<MyOrderExample> response) {
                if (response.code() == 200) {
                    myOrderHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        myOrderHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyOrderExample> call, Throwable t) {
                myOrderHandler.onError(t.getMessage());
            }
        });
    }

    //////SocialLogin///

//    public void getsociallogin(String socialLinkId, String platform, String email,
//                               String fullname, String avatar, String phoneNumber, String deviceToken, SocialLoginHandler socialLoginHandler) {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("socialLinkId", socialLinkId);
//        jsonObject.addProperty("platform", platform);
//        jsonObject.addProperty("email", email);
//        jsonObject.addProperty("fullname", fullname);
//        jsonObject.addProperty("avatar", avatar);
//        jsonObject.addProperty("phoneNumber", phoneNumber);
//        jsonObject.addProperty("deviceToken", deviceToken);
//
//        api.getsociallink(socialLinkId,platform,email,fullname,avatar,phoneNumber,deviceToken).enqueue(new Callback<SocialLoginExample>() {
//            @Override
//            public void onResponse(Call<SocialLoginExample> call, Response<SocialLoginExample> response) {
//                if (response.code() == 200) {
//                    socialLoginHandler.onSuccess(response.body());
//                } else {
//                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
//                    try {
//                        JSONObject jsonObject = new JSONObject(responceData);
//                        String message = jsonObject.optString("message");
//                        socialLoginHandler.onError(message);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SocialLoginExample> call, Throwable t) {
//                socialLoginHandler.onError(t.getMessage());
//            }
//        });
//    }

    public void getfbsociallink(String socialLinkId, String platform, String email,
                                String fullname, String avatar, String phoneNumber, String deviceToken, SocialLoginHandler socialLoginHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("socialLinkId", socialLinkId);
        jsonObject.addProperty("platform", platform);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("fullname", fullname);
        jsonObject.addProperty("avatar", avatar);
        jsonObject.addProperty("phoneNumber", phoneNumber);
        jsonObject.addProperty("deviceToken", deviceToken);

        api.getfbsociallink(jsonObject).enqueue(new Callback<SocialLoginExample>() {
            @Override
            public void onResponse(Call<SocialLoginExample> call, Response<SocialLoginExample> response) {
                if (response.code() == 200) {
                    socialLoginHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        socialLoginHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialLoginExample> call, Throwable t) {
                socialLoginHandler.onError(t.getMessage());
            }
        });
    }

    public void getsearchproducts(String search, String searchtype, String token, SearchHandler searchHandler) {
        api.getsearch(search, searchtype, token).enqueue(new Callback<SearchExample>() {
            @Override
            public void onResponse(Call<SearchExample> call, Response<SearchExample> response) {
                if (response.code() == 200) {
                    searchHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        searchHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchExample> call, Throwable t) {
                searchHandler.onError(t.getMessage());
            }
        });
    }

    public void getBannermethod(String token, final BannerHandler bannerHandler) {
        api.getBannerApi(token).enqueue(new Callback<BannerExample>() {
            @Override
            public void onResponse(Call<BannerExample> call, Response<BannerExample> response) {
                if (response.code() == 200) {
                    bannerHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        bannerHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BannerExample> call, Throwable t) {
                bannerHandler.onError(t.getMessage());
            }
        });
    }

    ///////transections//

    public void getTransectionMethod(String token, String productId, String amount, String currency, String source, final TransectionHandler transectionHandler) {
        api.getTransections(token, productId, amount, currency, source).enqueue(new Callback<TransectionsExample>() {
            @Override
            public void onResponse(Call<TransectionsExample> call, Response<TransectionsExample> response) {
                if (response.code() == 200) {
                    transectionHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        transectionHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<TransectionsExample> call, Throwable t) {
                transectionHandler.onError(t.getMessage());
            }
        });
    }


}
