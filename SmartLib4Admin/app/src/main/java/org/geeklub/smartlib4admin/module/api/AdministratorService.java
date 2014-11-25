package org.geeklub.smartlib4admin.module.api;

import java.util.List;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.LoginUser;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/15.
 */
public interface AdministratorService {

  @POST("/administrator/login") void login(@Body LoginUser user, Callback<ServerResponse> callback);

  @GET("/searchA/{userId}/{type}/page={page}/{keyword}") void search(@Path("userId") String usrId,
      @Path("type") String type, @Path("page") int page, @Path("keyword") String keyword,
      Callback<List<Book>> callback);

  @POST("/booklist/book/update/{userId}/{bookId}/{password}") void edit(@Body Book book,
      Callback<ServerResponse> callback);

  @GET("/administrator/returnConfirm/{bookId}/{userId}/{password}") void returnBook(
      @Path("bookId") String bookId, @Path("userId") String userId,
      @Path("password") String password);

  @POST("/administrator/addBook/:userId/:password") void addBook();

  @GET("/administrator/deleteBook/{bookId}/{userId}/{password}") void deleteBook(
      @Path("bookId") String bookId, @Path("userId") String userId,
      @Path("password") String password);

  @GET("/administrator/return/{userId}/{password}/page={page}") void haveLended(
      @Path("userId") String userId, @Path("password") String password, @Path("page") int page,
      Callback<List<Book>> callback);
}
