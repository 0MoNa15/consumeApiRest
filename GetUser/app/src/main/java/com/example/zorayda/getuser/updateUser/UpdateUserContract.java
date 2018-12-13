package com.example.zorayda.getuser.updateUser;

import com.example.zorayda.getuser.mvpBase.BasePresenter;
import com.example.zorayda.getuser.mvpBase.BaseView;

public interface UpdateUserContract {
    interface View extends BaseView<UpdateUserContract.Presenter> {
        void isLoading(boolean isLoading);
        void displayResponse(String statusResponse, boolean isSuccess);
    }

    interface Presenter extends BasePresenter {
        void sendDataUserUpdate(String id, String name, String number);
    }
}
