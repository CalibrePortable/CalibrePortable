package org.geeklub.smartlib.module.common.login.model;

import android.text.TextUtils;

import org.geeklub.smartlib.api.Constant;
import org.geeklub.smartlib.beans.LoginUser;
import org.geeklub.smartlib.beans.SLUser;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.common.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.module.common.login.presenter.OnUserInputListener;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SharedPreferencesUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/10/26.
 */
public class LoginModelImpl implements LoginModel {

  @Override
  public void login(final String username, final String password,
      OnUserInputListener userInputListener, final OnLoginFinishedListener loginFinishedListener) {

    if (TextUtils.isEmpty(username)) {
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

    service.login(new LoginUser(username, password), new Callback<ServerResponse>() {
      @Override public void success(ServerResponse serverResponse, Response response) {
        LogUtil.i(serverResponse.getInfo());

        if (serverResponse.getStatus() == Constant.RESULT_SUCCESS) {

          SharedPreferencesUtil preferencesUtil =
              new SharedPreferencesUtil(GlobalContext.getInstance());
          preferencesUtil.saveUser(new SLUser(username, password));

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
}
