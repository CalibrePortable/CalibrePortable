package org.geeklub.smartlib.module.settings;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.InjectView;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.NewPasswordInfo;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import org.geeklub.smartlib.utils.ToastUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PasswordModifyActivity extends BaseActivity {

    @InjectView(R.id.et_new_password)
    EditText mNewPassword;
    @InjectView(R.id.et_new_re_password)
    EditText mNewRePassword;
    @InjectView(R.id.btn_commit)
    Button mConfirm;
    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
        initCallBacks();
    }

    private void initToolBar() {
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolBar.setTitle("修改密码");
        setSupportActionBar(mToolBar);
    }

    private void initCallBacks() {
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNewPasswordToServer(mNewPassword.getText().toString(), mNewRePassword.getText().toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_modify_password;
    }

    private void sendNewPasswordToServer(String newPassword, String reNewPassword) {
        if (TextUtils.isEmpty(newPassword)) {
            mNewPassword.setError("empty new password...");
        }

        if (TextUtils.isEmpty(reNewPassword)) {
            mNewPassword.setError("empty new repassword...");
        }

        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();

        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        if (newPassword.equals(reNewPassword)) {
            service.modifyPassword(
                    new NewPasswordInfo(user.getUserId(), user.getPassWord(), newPassword, reNewPassword),
                    new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {

                            ToastUtil.showShort("密码修改成功,请重新登陆...");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            ToastUtil.showShort(error.getMessage());
                        }
                    });
        }
    }


}
