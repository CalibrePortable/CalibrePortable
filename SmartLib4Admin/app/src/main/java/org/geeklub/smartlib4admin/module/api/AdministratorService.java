package org.geeklub.smartlib4admin.module.api;

import java.util.List;

import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.BookDetailInfo;
import org.geeklub.smartlib4admin.beans.LoginInfo;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import org.geeklub.smartlib4admin.beans.SummaryBook;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/15.
 */
public interface AdministratorService {
    //登陆
    @POST("/admin/login")
    void login(@Body LoginInfo user, Callback<ServerResponse> callback);

    //图书搜索
    @GET("/admin/search/{userId}/{type}/page={page}/{keyword}")
    void search(
            @Path("userId") String usrId, @Path("type") String type, @Path("page") int page,
            @Path("keyword") String keyword, Callback<List<SummaryBook>> callback);

    //归还图书
    @GET("/admin/confirm/{bookId}/{userId}/{password}")
    void returnBook(@Path("bookId") String bookId,
                    @Path("userId") String userId, @Path("password") String password,
                    Callback<ServerResponse> callback);

    //修改图书为已超期
    @GET("/admin/alter/{bookId}/{userId}/{password}")
    void edit(@Path("bookId") String bookId,
              @Path("userId") String userId, @Path("password") String password,
              Callback<ServerResponse> callback);

    //更新图书资料
    @POST("/admin/renew/{bookId}/{bookIsbn}/{userId}/{password}")
    void updateBookInfo(
            @Path("bookId") String bookId, @Path("userId") String userId,
            @Path("password") String password);

    //增加图书
    @GET("/admin/add/{bookIsbn}/{bookType}/{userId}/{password}")
    void addBook(
            @Path("bookIsbn") String bookIsbn, @Path("bookType") String bookType,
            @Path("userId") String userId, @Path("password") String password,
            Callback<ServerResponse> callback);

    //删除图书
    @GET("/admin/delete/{bookId}/{userId}/{password}")
    void deleteBook(@Path("bookId") String bookId,
                    @Path("userId") String userId, @Path("password") String password, Callback<ServerResponse> callback);

    //查看已经借出的图书(无法detail)(无法点赞)(有翻页)
    @GET("/admin/showRe/{userId}/{password}/page={page}")
    void haveLended(
            @Path("userId") String userId, @Path("password") String password, @Path("page") int page,
            Callback<List<Book>> callback);

    //  图书详细内容
    @GET("/public/detail/{bookKind}")
    void bookDetail(@Path("bookKind") String bookKind,
                    Callback<BookDetailInfo> callback);
}
