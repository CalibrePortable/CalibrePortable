package org.geeklub.smartlib4admin.module.api;

import java.util.HashMap;
import java.util.Map;
import retrofit.RestAdapter;

/**
 * Created by Vass on 2014/11/29.
 */
public class RestApiDispencer {
  private Map<String, Object> restApiInstances = new HashMap<String, Object>();
  private RestAdapter restAdapter;

  public RestApiDispencer(){

  }

  public RestApiDispencer(RestAdapter restAdapter) {
    this.restAdapter = restAdapter;
  }

  public void setRestAdapter(RestAdapter restAdapter) {
    this.restAdapter = restAdapter;
  }

  public <T> T getRestApi(Class<T> clazz) {
    T client = null;
    if ((client = (T) restApiInstances.get(clazz.getCanonicalName())) != null) {
      return client;
    }
    client = restAdapter.create(clazz);
    restApiInstances.put(clazz.getCanonicalName(), client);
    return client;
  }
}
