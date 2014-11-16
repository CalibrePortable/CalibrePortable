package org.geeklub.smartlib.api;

import java.util.List;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.beans.LoginUser;
import org.geeklub.smartlib.beans.NewUser;
import org.geeklub.smartlib.beans.ServerResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/3.
 */
public interface NormalUserService {

  @GET("/passChange/{userId}/{oldPass}/{newPass}/{renewPass}") void modifyPassword(
      @Path("userId") String userId, @Path("oldPass") String oldPassword,
      @Path("newPass") String newPassword, @Path("renewPass") String reNewPassword,
      Callback<ServerResponse> callback);

  @GET("/like/{bookId}/{userId}/{password}") void likePlusOne(@Path("bookId") String bookId,
      @Path("userId") String userId, @Path("password") String password,
      Callback<ServerResponse> callback);

  @POST("/normalUser/login") void login(@Body LoginUser user, Callback<ServerResponse> callback);

  @POST("/normalUser/register") void register(@Body NewUser user,
      Callback<ServerResponse> callback);

  @GET("/normalUser/borrow/{bookId}/{userId}/{password}") void borrow(@Path("bookId") String bookId,
      @Path("userId") String userId, @Path("password") String password,
      Callback<ServerResponse> callback);

  @GET("/search/{userId}/{type}/page={page}/{keyword}") void search(@Path("userId") String userId,
      @Path("type") int type, @Path("page") int page, @Path("keyword") String keyword,
      Callback<List<Book>> callback);

  @GET("/normalUser/return/{userId}/{password}") void haveBorrowed(@Path("userId") String userId,
      @Path("password") String password, Callback<List<Book>> callback);
}
