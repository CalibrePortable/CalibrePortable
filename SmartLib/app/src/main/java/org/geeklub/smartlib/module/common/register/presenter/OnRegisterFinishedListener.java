package org.geeklub.smartlib.module.common.register.presenter;

/**
 * Created by Vass on 2014/10/27.
 */
public interface OnRegisterFinishedListener {

  /**
   * 注册失败
   */
  public void onFail(String msg);
  /**
   * 注册成功
   */
  public void onSuccess(String msg);
}
