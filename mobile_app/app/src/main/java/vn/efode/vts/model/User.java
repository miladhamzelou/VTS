package vn.efode.vts.model;

import java.sql.Timestamp;

/**
 * Created by Nam on 04/04/2017.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String birthday;
    private String sex;
    private String address;
    private String userStatusTypeId;
    private String image;
    private Timestamp lastLoginTime;

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserStatusTypeId() {
        return userStatusTypeId;
    }

    public void setUserStatusTypeId(String userStatusTypeId) {
        this.userStatusTypeId = userStatusTypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
