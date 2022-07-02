package com.trademax.Utils;

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
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //////users////////

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginExample> loginAPI(@Field("email") String email, @Field("password") String password);

    @POST("users/register")
    Call<SignUpExample> registerAPI(@Body JsonObject jsonObject);

////    @FormUrlEncoded
//    @POST("users/register")
//    Call<SignUpExample> registerAPI(@Field("fullname") String fullname, @Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("users/forgotPassword")
    Call<ForgotPassExample> forgotPass(@Field("email") String email);

    @FormUrlEncoded
    @POST("users/otpVerifyAndChangePassword")
    Call<VerifyOTPExample> verifyOTPApi(@Header("x-access-token") String token, @Field("otp") String otp);

    @FormUrlEncoded
    @PUT("users/newPassword")
    Call<ResetPaswordExample> resetPasswordAPI(@Header("x-access-token") String token, @Field("newPassword") String newPassword);

    @PUT("users/update/{id}")
    Call<ProfileupdateExample> updateProfileAPI(@Path("id") String userId, @Body JsonObject jsonObject);

    @PUT("users/changePassword/{id}")
    Call<ChangePassExample> changePasswordAPI(@Header("x-access-token") String token, @Header("Content-Type") String content_type, @Path("id") String userId, @Body JsonObject jsonObject);

    @Multipart
    @POST("users/uploadImage")
    Call<UploadUserImageExample> UploadFile(@Part("id") RequestBody id, @Part MultipartBody.Part file);

    @GET("users/currentUser/{id}")
    Call<CurrentUserExample> showCurrentDataApi(@Header("x-access-token") String token, @Path("id") String user_id);

    ///////orders/////////

    //    @FormUrlEncoded
    @GET("orders/getOrder{userId}")
    Call<GetOrderExample> getOrderApi(@Query("userId") String user_id);

    //////////Products///////

    @GET("products/getAllProducts")
    Call<GetAllProductsExample> getHomeDataApi(@Query("userId") String user_id, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("products/productsBySubCategories")
    Call<ProductBySubCatExample> getproductBySubCategoriesApi(@Query("userId") String userId, @Query("pageNo") int pageNo,
                                                              @Query("pageSize") int pageSize, @Query("subCategoryId") String subCategoryId);

    @GET("products/productsBySubCategories")
    Call<ProductBySubCatExample> getsortproductBySubCategoriesApi(@Query("userId") String userId, @Query("pageNo") int pageNo,
                                                                  @Query("pageSize") int pageSize, @Query("subCategoryId") String subCategoryId
            , @Query("priceSort") String priceSort);


    @GET("products/similarProducts")
    Call<SimilarProductExample> getsimilarproduct(@Query("subCategoryId") String subCategoryId, @Query("productId") String productId);

    @FormUrlEncoded
    @POST("products/filterProducts")
    Call<FilterExample> getfilterproducts(@Field("size") String name,
                                          @Field("minPrice") String minPrice,
                                          @Field("maxPrice") String maxPrice,
                                          @Field("color") String color);

    //////categories////////

    @FormUrlEncoded
    @POST("categories/getCategories")
    Call<GetCategoriesDetailsExample> getCategoriesDetailsApi(@Field("category_id") String category_id);

    @FormUrlEncoded
    @POST("categories/getCategories")
    Call<SearchCategoriesDetailsExample> getallsearchApi(@Field("category_id") String category_id);


    /////////Cart////////

    @FormUrlEncoded
    @POST("carts/addAddress")
    Call<AddAddressExample> addAddressApi(@Field("addressId") String addressId, @Field("userId") String userId, @Field("fullName") String fullName,
                                          @Field("address") String address, @Field("city") String city, @Field("state") String state,
                                          @Field("zipCode") String zipCode, @Field("country") String country);

    @FormUrlEncoded
    @POST("carts/getAddress")
    Call<GetAddressExample> getAddressApi(@Field("userId") String userId, @Field("addressId") String addressId);

//    @FormUrlEncoded
//    @POST("orders/placeOrder")
//    Call<CheckoutExample> getcheckout(@Field("userId") String userId, @Field("totalAmount") String totalAmount,
//                                      @Field("tax") String tax,
//                                      @Field("addressId") String addressId,
//                                      @Field("cart") JSONArray jsonArray);

    //        @FormUrlEncoded
    @POST("orders/placeOrder")
    Call<CheckoutExample> getcheckout(@Body JsonObject jsonObject);

    @FormUrlEncoded
    @POST("carts/addToFav")
    Call<PainterPlasterLikeProductExample> getLikeProductApi(@Field("userId") String userId, @Field("productId") String productId);

    @GET("carts/getFav")
    Call<FavouriteExample> getFavouriteApi(@Query("userId") String userId);

    //    @FormUrlEncoded
    @POST("carts/addToCart")
    Call<AddToCartExample> getaddtocart(@Body JsonObject jsonObject);

//    @FormUrlEncoded
//    @POST("carts/addToCart")
//    Call<Addtocartexample> getaddtocart(@Field("userId") String userId, @Field("productId") String productId,
//                                        @Field("quantity") String quantity,
//                                        @Field("total") String total,
//                                        @Field("variation") String variation,
//                                        @Field("status") String status);


    @GET("carts/getCarts")
    Call<CartlistExample> getcartlist(@Query("userId") String userId);

    @DELETE("carts/deleteItem/{id}")
    Call<DeleteExample> getdeletelist(@Path("id") String id, @Header("x-access-token") String token);

    //orders
    @GET("orders/getOrder")
    Call<MyOrderExample> getmyorders(@Query("userId") String userId);


//    @FormUrlEncoded
//    @POST("users/socialLink")
//    Call<SocialLoginExample> getsociallink(@Field("socialLinkId") String socialLinkId, @Field("platform") String platform,
//                                           @Field("email") String email,
//                                           @Field("fullname") String fullname,
//                                           @Field("avatar") String avatar,
//                                           @Field("phoneNumber") String phoneNumber,
//                                           @Field("deviceToken") String deviceToken);


    @POST("users/socialLink")
    Call<SocialLoginExample> getfbsociallink(@Body JsonObject jsonObject);


    @FormUrlEncoded
    @POST("products/search")
    Call<SearchExample> getsearch(@Field("search") String search,
                                  @Field("searchType") String searchType,
                                  @Header("x-access-token") String token);

    ///////banners
    @GET("banners/getBanners")
    Call<BannerExample> getBannerApi(@Query("x-access-token") String token);

////////Transections////////

    @FormUrlEncoded
    @POST("transactions/create")
    Call<TransectionsExample> getTransections(@Header("x-access-token") String token, @Field("productId") String productId,
                                              @Field("amount") String amount, @Field("currency") String currency,
                                              @Field("source") String source);

}