package com.example.zorayda.getuser.seeUser;

import android.content.Context;

import com.example.zorayda.getuser.findUser.FindUserResponse;
import com.example.zorayda.getuser.seeUser.model.GetUsersResponse;
import com.example.zorayda.getuser.connect.ApiCallback;
import com.example.zorayda.getuser.connect.UserRepository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


public class SeeUserPresenter implements SeeUserContract.Presenter {

    private SeeUserContract.View mView;
    private UserRepository mUserRepository;

    SeeUserPresenter(Context context, SeeUserContract.View view){
        this.mView = checkNotNull(view);
        mUserRepository = new UserRepository(context);
    }

    @Override
    public void getUsers() {
        mView.isLoading(true);
        mUserRepository.getUserApi(new ApiCallback.ListUsersCallback() {
            @Override
            public void onSuccess(GetUsersResponse getUsersResponse) {
                mView.displayUsers(getUsersResponse.response);
                mView.isLoading(false);
            }

            @Override
            public void onError(String message) {
                mView.displayError(message);
                mView.isLoading(false);
            }
        });
    }

    @Override
    public void searchUserFindUser(String username) {
        mView.isLoading(true);
        mUserRepository.findUser(username, new ApiCallback.findUserResponse() {
            @Override
            public void onSuccess(FindUserResponse findUserResponse) {
                mView.displayResponseFindUser(findUserResponse);
                mView.isLoading(false);
            }

            @Override
            public void onError(String message) {
                mView.displayError(message);
                mView.isLoading(false);
            }
        });
    }

    @Override
    public void start() {

    }
}
