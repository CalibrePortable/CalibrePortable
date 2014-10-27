package org.geeklub.smartlib.login.model;

import android.text.TextUtils;

import java.util.List;
import org.geeklub.smartlib.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.login.presenter.OnUserInputListener;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Vass on 2014/10/26.
 */
public class LoginModelImpl implements LoginModel {

  @Override
  public void login(String username, String password, OnUserInputListener userInputListener,
      OnLoginFinishedListener loginFinishedListener) {

    if (TextUtils.isEmpty(username)) {
      userInputListener.onAccountError();
      return;
    }

    if (TextUtils.isEmpty(password)) {
      userInputListener.onPasswordError();
      return;
    }
  }
}
