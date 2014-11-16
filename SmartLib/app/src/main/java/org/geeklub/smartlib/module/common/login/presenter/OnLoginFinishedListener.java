package org.geeklub.smartlib.module.common.login.presenter;

/**
 * Created by Vass on 2014/10/26.
 */
public interface OnLoginFinishedListener {

  void onSuccess(String msg);

  void onFail(String msg);
}
