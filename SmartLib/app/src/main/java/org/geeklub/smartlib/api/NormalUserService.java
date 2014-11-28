package org.geeklub.smartlib.api;

import java.util.List;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.beans.LoginInfo;
import org.geeklub.smartlib.beans.NewPasswordInfo;
import org.geeklub.smartlib.beans.RegisterInfo;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.beans.SummaryBook;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/3.
 */
public interface NormalUserService {

  //修改密码
  @POST("/public/passChange") void modifyPassword(@Body NewPasswordInfo newPasswordInfo,
      Callback<ServerResponse> callback);

  //登陆
  @POST("/normal/login") void login(@Body LoginInfo user, Callback<ServerResponse> callback);

  //注册
  @POST("/normal/register") void register(@Body RegisterInfo user,
      Callback<ServerResponse> callback);

  //点赞
  @GET("/public/like/{bookKind}/{userId}/{password}") void likePlusOne(
      @Path("bookKind") String bookKind, @Path("userId") String userId,
      @Path("password") String password, Callback<ServerResponse> callback);

  //扫一扫借书
  @GET("/normal/borrow/{bookId}/{userId}/{password}") void borrow(@Path("bookId") String bookId,
      @Path("userId") String userId, @Path("password") String password,
      Callback<ServerResponse> callback);

  //搜索
  @GET("/normal/search/{userId}/{type}/page={page}/{keyword}") void search(
      @Path("userId") String userId, @Path("type") int type, @Path("page") int page,
      @Path("keyword") String keyword, Callback<List<SummaryBook>> callback);

  //已借阅
  @GET("/normal/showRe/{userId}/{password}/page={page}") void haveBorrowed(
      @Path("userId") String userId, @Path("password") String password,
      Callback<List<SummaryBook>> callback);

  //  图书详细内容
  @GET("/public/detail/{bookKind}") void bookDetail(@Path("bookKind") String bookKind);
}
