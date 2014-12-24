package org.geeklub.smartlib.module.register.model;

import android.text.TextUtils;
import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.api.Constant;
import org.geeklub.smartlib.beans.RegisterInfo;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.register.presenter.OnPassWordMatchListener;
import org.geeklub.smartlib.module.register.presenter.OnRegisterFinishedListener;
import org.geeklub.smartlib.module.register.presenter.OnUserInputListener;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterModelImpl implements RegisterModel {

  @Override public void register(final String userId, String userName, final String password,
      String passWordConfirm, OnUserInputListener userInputListener,
      OnPassWordMatchListener matchListener, final OnRegisterFinishedListener finishedListener) {

    if (TextUtils.isEmpty(userId)) {
      userInputListener.userIdError();
      return;
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

    NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

    service.register(new RegisterInfo(userId, userName, password, passWordConfirm),
        new Callback<ServerResponse>() {
          @Override public void success(ServerResponse serverResponse, Response response) {

            if (serverResponse.getStatus() == Constant.RESULT_SUCCESS) {
              SmartLibraryUser.saveUser(userId, password);
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
