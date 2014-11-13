package org.geeklub.smartlib.services;

import java.util.List;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.beans.ServerResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/3.
 */
public interface NormalUserService {

  @GET("/passChange/{userId}/{oldPass}/{newPass}/{renewPass}") void modifyPassword(
      @Path("userId") String userId, @Path("oldPass") String oldPassword,
      @Path("newPass") String newPassword, @Path("renewPass") String reNewPassword,Callback<ServerResponse> callback);

  @FormUrlEncoded @POST("/normalUser/login") void login(@Field("userId") String userId,
      @Field("password") String password, Callback<ServerResponse> callback);

  @FormUrlEncoded @POST("/normalUser/register") void register(@Field("userId") String userId,
      @Field("userName") String userName, @Field("password") String password,
      @Field("rePassword") String rePassword, Callback<ServerResponse> callback);

  @GET("/normalUser/borrow/{bookId}/{userId}/{password}") void borrow(@Path("bookId") String bookId,
      @Path("userId") String userId, @Path("password") String password,
      Callback<ServerResponse> callback);

  @GET("/search/{type}/page={page}/{keyword}") void search(@Path("type") int type,
      @Path("page") int page, @Path("keyword") String keyword, Callback<List<Book>> callback);

  @GET("/normalUser/return/{userId}/{password}") void haveBorrowed(@Path("userId") String userId,
      @Path("password") String password, Callback<List<Book>> callback);
}
