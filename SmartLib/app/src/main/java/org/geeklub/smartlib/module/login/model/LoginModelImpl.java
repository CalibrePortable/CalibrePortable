package org.geeklub.smartlib.module.login.model;

import android.text.TextUtils;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.api.Constant;
import org.geeklub.smartlib.beans.LoginInfo;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.module.login.presenter.OnUserInputListener;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.SmartLibraryUser;

import retrofit.Callback;
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

        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        service.login(new LoginInfo(userId, password), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {

                if (serverResponse.getStatus() == Constant.RESULT_SUCCESS) {

                    SmartLibraryUser.saveUser(userId, password);

                    loginFinishedListener.onSuccess(serverResponse.getInfo());
                } else {
                    loginFinishedListener.onFail(serverResponse.getInfo());
                }
            }

            @Override
            public void failure(RetrofitError error) {

                loginFinishedListener.onFail(error.getMessage());
            }
        });
    }

    @Override
    public boolean ifLoginBefore() {
        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        if (user == null) {
            return false;
        }
        return true;
    }
}
