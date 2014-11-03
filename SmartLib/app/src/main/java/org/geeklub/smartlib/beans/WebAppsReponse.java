package org.geeklub.smartlib.beans;

import java.util.List;

/**
 * Created by Vass on 2014/11/3.
 */
public class WebAppsReponse {

  private int status;

  private List<WebApp> apps;

  public List<WebApp> getApps() {
    return apps;
  }

  public void setApps(List<WebApp> apps) {
    this.apps = apps;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override public String toString() {
    return "Reponse{" +
        "status=" + status +
        ", apps=" + apps +
        '}';
  }

  public static class WebApp {
    private String author;
    private String category;
    private String description;
    private String icon_src;
    private String manifest;
    private String name;
    private int rating;
    private String url;
    private boolean isAdded;

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getIcon_src() {
      return icon_src;
    }

    public void setIcon_src(String icon_src) {
      this.icon_src = icon_src;
    }

    public String getManifest() {
      return manifest;
    }

    public void setManifest(String manifest) {
      this.manifest = manifest;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getRating() {
      return rating;
    }

    public void setRating(int rating) {
      this.rating = rating;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public boolean isAdded() {
      return isAdded;
    }

    public void setAdded(boolean isAdded) {
      this.isAdded = isAdded;
    }

    @Override public String toString() {
      return "WebApp{" +
          "author='" + author + '\'' +
          ", description='" + description + '\'' +
          ", name='" + name + '\'' +
          '}';
    }
  }
}
