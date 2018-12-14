package com.example.zorayda.getuser.updateUser;

import com.example.zorayda.getuser.mvpBase.BasePresenter;
import com.example.zorayda.getuser.mvpBase.BaseView;
import com.example.zorayda.getuser.seeUser.model.UserResponse;

public interface UpdateUserContract {
    interface View extends BaseView<UpdateUserContract.Presenter> {
        void isLoading(boolean isLoading);
        void displayResponse(String statusResponse, boolean isSuccess);
    }

    interface Presenter extends BasePresenter {
        void sendDataUserUpdate(String id, String name, String number);
    }
}
