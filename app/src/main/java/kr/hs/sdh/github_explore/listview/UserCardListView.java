package kr.hs.sdh.github_explore.listview;

import android.graphics.drawable.Drawable;

public class UserCardListView {

    private String fullName = "";
    private String userName = "";
    private String organization = "";
    private String location = "";
    private String link = "";
    private Drawable userPhoto;

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrganization() {
        return organization;
    }

    public String getLocation() {
        return location;
    }

    public String getLink() {
        return link;
    }

    public Drawable getUserPhoto() { return userPhoto; }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setUserPhoto(Drawable userPhoto) { this.userPhoto = userPhoto; }

}
