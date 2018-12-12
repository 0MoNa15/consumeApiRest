package com.example.zorayda.getuser.seeUser;

import com.example.zorayda.getuser.seeUser.model.ListUser;
import com.example.zorayda.getuser.mvpBase.BasePresenter;
import com.example.zorayda.getuser.mvpBase.BaseView;

import java.util.ArrayList;

public interface SeeUserContract {

    interface View extends BaseView<Presenter>{
        void isLoading(boolean isLoading);
        void displayError(String message);
        void displayUsers(ArrayList<ListUser> response);
    }

    interface Presenter extends BasePresenter{
        void getUsers();
    }
}
