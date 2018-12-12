package com.example.zorayda.getuser.connect;

import com.example.zorayda.getuser.seeUser.model.GetUsersResponse;

public class ApiCallback {

    public interface ListUsersCallback{
        void onSuccess(GetUsersResponse getUsersResponse);
        void onError(String message);
    }

    public interface UpdateUserCallback{
        void onSuccess(String message);
        void onError(String message);
    }
}
