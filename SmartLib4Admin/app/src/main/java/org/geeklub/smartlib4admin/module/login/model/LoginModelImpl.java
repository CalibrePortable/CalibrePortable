package org.geeklub.smartlib4admin.module.login.model;

import android.text.TextUtils;

import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.beans.LoginInfo;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.api.Constant;
import org.geeklub.smartlib4admin.module.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib4admin.module.login.presenter.OnUserInputListener;
import org.geeklub.smartlib4admin.utils.SmartLibraryUser;

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
        AdministratorService service = GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);

        service.login(new LoginInfo(userId, password), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {

                if (serverResponse.getStatus() == Constant.RESULT_SUCCESS) {
                    loginFinishedListener.onSuccess(serverResponse.getInfo());
                    SmartLibraryUser.saveUser(userId, password);
                } else {
                    SmartLibraryUser.clear();
                    loginFinishedListener.onFail(serverResponse.getInfo());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                loginFinishedListener.onFail(error.getMessage());

            }
        });

    }


}
