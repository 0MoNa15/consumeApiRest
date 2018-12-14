package com.example.zorayda.getuser.findUser;

import com.example.zorayda.getuser.seeUser.model.UserResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("usuario")
    @Expose
    public UserResponse userResponse;
}
