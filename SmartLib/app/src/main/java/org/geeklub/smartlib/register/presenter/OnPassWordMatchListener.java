package org.geeklub.smartlib.register.presenter;

/**
 * Created by Vass on 2014/10/8.
 */
public interface OnPassWordMatchListener {
  /**
   * 两次输入密码不一致的回调函数
   */
  public void onPassWordNotMatchError();
}
