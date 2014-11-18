package org.geeklub.smartlib.module.login.model;

import android.text.TextUtils;

import org.geeklub.smartlib.api.Constant;
import org.geeklub.smartlib.beans.LoginUser;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.module.login.presenter.OnUserInputListener;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/10/26.
 */
public class LoginModelImpl implements LoginModel {

  @Override
  public void login(final String userId, final String password,
      OnUserInputListener userInputListener, final OnLoginFinishedListener loginFinishedListener) {

    if (TextUtils.isEmpty(userId)) {
      userInputListener.onAccountError();
      return;
    }

    if (TextUtils.isEmpty(password)) {
      userInputListener.onPasswordError();
      return;
    }

    RestAdapter restAdapter =
        new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();

    NormalUserService service = restAdapter.create(NormalUserService.class);

    service.login(new LoginUser(userId, password), new Callback<ServerResponse>() {
      @Override public void success(ServerResponse serverResponse, Response response) {
        LogUtil.i(serverResponse.getInfo());

        if (serverResponse.getStatus() == Constant.RESULT_SUCCESS) {

          SmartLibraryUser.saveUser(userId, password);

          loginFinishedListener.onSuccess(serverResponse.getInfo());
        } else {
          loginFinishedListener.onFail(serverResponse.getInfo());
        }
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i(error.getMessage());

        loginFinishedListener.onFail(error.getMessage());
      }
    });
  }

  @Override public boolean ifLoginBefore() {
    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
    if (user == null) {
      return false;
    }
    return true;
  }
}