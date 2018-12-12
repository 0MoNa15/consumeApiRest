package com.example.zorayda.getuser.updateUser;

import android.content.Context;

import com.example.zorayda.getuser.connect.ApiCallback;
import com.example.zorayda.getuser.connect.UserRepository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class UpdateUserPresenter implements UpdateUserContract.Presenter{

    private UpdateUserContract.View mView;
    private UserRepository mUserRepository;

    UpdateUserPresenter(Context context, UpdateUserContract.View view){
        this.mView = checkNotNull(view);
        mUserRepository = new UserRepository(context);
    }

    @Override
    public void sendDataUserUpdate(String id, String name, String number) {
        mView.isLoading(true);
        mUserRepository.updateDataUserApi(id, name, number, new ApiCallback.UpdateUserCallback() {
            @Override
            public void onSuccess(String message) {
                mView.displayResponse(message, true);
                mView.isLoading(false);
            }

            @Override
            public void onError(String message) {
                mView.displayResponse(message, false);
                mView.isLoading(false);
            }
        });
    }

    @Override
    public void start() {

    }
}
