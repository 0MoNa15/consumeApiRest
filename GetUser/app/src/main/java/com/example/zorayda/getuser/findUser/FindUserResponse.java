package com.example.zorayda.getuser.findUser;

import com.example.zorayda.getuser.seeUser.model.UserResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FindUserResponse {
    @SerializedName("response")
    @Expose
    public ArrayList<UserResponse> response = null;
}
