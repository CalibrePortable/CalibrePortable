package org.geeklub.smartlib4admin.beans;

/**
 * Created by Vass on 2014/11/10.
 */
public class ServerResponse {

  private int status;

  private String info;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Override public String toString() {
    return "Response{" +
        "status=" + status +
        ", info='" + info + '\'' +
        '}';
  }
}
