package org.geeklub.smartlib.register.model;

import android.text.TextUtils;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.register.presenter.OnPassWordMatchListener;
import org.geeklub.smartlib.register.presenter.OnRegisterFinishedListener;
import org.geeklub.smartlib.register.presenter.OnUserInputListener;
import org.geeklub.smartlib.services.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterModelImpl implements RegisterModel {

  @Override public void register(String userId, String userName, String password,
      String passWordConfirm, OnUserInputListener userInputListener,
      OnPassWordMatchListener matchListener, final OnRegisterFinishedListener finishedListener) {

    if (TextUtils.isEmpty(userId)) {
      userInputListener.userIdError();
    }
    if (TextUtils.isEmpty(userName)) {
      userInputListener.userNameError();
      return;
    }

    if (TextUtils.isEmpty(password)) {
      userInputListener.passwordError();
      return;
    }

    if (!isPasswordSame(password, passWordConfirm)) {
      matchListener.onPassWordNotMatchError();
      return;
    }

    RestAdapter restAdapter =
        new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();

    NormalUserService service = restAdapter.create(NormalUserService.class);

    service.register(userId, userName, password, passWordConfirm, new Callback<ServerResponse>() {
      @Override public void success(ServerResponse serverResponse, Response response) {

        if (serverResponse.getStatus() == 0) {
          LogUtil.i("注册成功");
          finishedListener.onSuccess(serverResponse.getInfo());
        } else {
          LogUtil.i("注册失败");
          finishedListener.onFail(serverResponse.getInfo());
        }
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i("注册失败");
        finishedListener.onFail(error.getMessage());
      }
    });
  }

  @Override public void verifyPassWord(String firstPassWord, String secondPassWord,
      OnPassWordMatchListener listener) {

    if (!isPasswordSame(firstPassWord, secondPassWord)) {
      listener.onPassWordNotMatchError();
      return;
    }
  }

  private boolean isPasswordSame(String firstPassWord, String secondPassWord) {
    if (firstPassWord.equals(secondPassWord)) {
      return true;
    }
    return false;
  }
}
