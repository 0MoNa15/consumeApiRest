package com.example.zorayda.getuser.seeUser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetUsersResponse {
    @SerializedName("response")
    @Expose
    public ArrayList<ListUser> response = null;

}
