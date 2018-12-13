package com.example.zorayda.getuser.connect;

import com.example.zorayda.getuser.findUser.FindUserResponse;
import com.example.zorayda.getuser.seeUser.model.GetUsersResponse;

public class ApiCallback {

    public interface ListUsersCallback{
        void onSuccess(GetUsersResponse getUsersResponse);
        void onError(String message);
    }

    public interface UpdateUserCallback{
        void onSuccess(String statusResponse);
        void onError(String message);
    }

    public interface findUserResponse{
        void onSuccess(FindUserResponse findUserResponse);
        void onError(String message);
    }
}
