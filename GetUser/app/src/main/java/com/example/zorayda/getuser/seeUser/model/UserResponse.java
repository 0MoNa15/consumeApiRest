package com.example.zorayda.getuser.seeUser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("nombre")
    @Expose
    public String name;
    @SerializedName("numero")
    @Expose
    public String number;
    @SerializedName("avatar")
    @Expose
    public String avatar;

    @Override
    public String toString() {
        return "UserResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
