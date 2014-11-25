package org.geeklub.smartlib4admin.module.api;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/15.
 */
public interface AdministratorService {

  @POST("/administrator/login") void login();

  @GET("/searchA/{userId}/{type}/page={page}/{keyword}") void search(@Path("userId") String usrId,
      @Path("type") String type, @Path("page") int page, @Path("keyword") String keyword);

  @POST("/booklist/book/update/{userId}/{bookId}/{password}") void edit();

  @GET("/administrator/returnConfirm/{bookId}/{userId}/{password}") void returnBook(
      @Path("bookId") String bookId, @Path("userId") String userId,
      @Path("password") String password);

  @POST("/administrator/addBook/:userId/:password") void addBook();

  @GET("/administrator/deleteBook/{bookId}/{userId}/{password}") void deleteBook(
      @Path("bookId") String bookId, @Path("userId") String userId,
      @Path("password") String password);

  @GET("/administrator/return/{userId}/{password}/page={page}") void haveLended(
      @Path("userId") String userId, @Path("password") String password, @Path("page") int page);
}
