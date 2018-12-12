package com.example.zorayda.getuser.seeUser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUser {
    @SerializedName("usuario")
    @Expose
    public UserResponse usuario;
}
