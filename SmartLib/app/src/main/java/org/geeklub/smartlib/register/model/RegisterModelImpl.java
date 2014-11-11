package org.geeklub.smartlib.register.model;

import android.text.TextUtils;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.register.presenter.OnPassWordMatchListener;
import org.geeklub.smartlib.register.presenter.OnRegisterFinishedListener;
import org.geeklub.smartlib.register.presenter.OnUserInputListener;
import org.geeklub.smartlib.services.NormalUserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterModelImpl implements RegisterModel {

  @Override public void register(String userName, String passWord, String passWordConfirm,
      OnUserInputListener userInputListener, OnPassWordMatchListener matchListener,
      final OnRegisterFinishedListener finishedListener) {

    if (TextUtils.isEmpty(userName)) {
      userInputListener.accountError();
      return;
    }

    if (TextUtils.isEmpty(passWord)) {
      userInputListener.passwordError();
      return;
    }

    if (!isPasswordSame(passWord, passWordConfirm)) {
      matchListener.onPassWordNotMatchError();
      return;
    }

    RestAdapter restAdapter =
        new RestAdapter.Builder().setEndpoint("http://book.duanpengfei.com/API.php").build();

    NormalUserService service = restAdapter.create(NormalUserService.class);

    service.register(userName, passWord, passWordConfirm, new Callback<ServerResponse>() {
          @Override public void success(ServerResponse serverResponse, Response response) {

            if (serverResponse.getStatus() == 0) {
              finishedListener.onSuccess(serverResponse.getInfo());
            } else {
              finishedListener.onFail(serverResponse.getInfo());
            }
          }

          @Override public void failure(RetrofitError error) {
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
