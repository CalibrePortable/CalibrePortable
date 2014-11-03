package org.geeklub.smartlib.services;

import org.geeklub.smartlib.beans.WebAppsReponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/11/3.
 */
public interface NormalUserService {
  @GET("/rank/0/{page}") void getResult(@Path("page") String page,Callback<WebAppsReponse> reponseCallback);


}
