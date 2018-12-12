package com.example.zorayda.getuser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.zorayda.getuser.seeUser.UsersFragment;
import com.example.zorayda.getuser.updateUser.UpdateUserFragment;

public class MainActivity extends AppCompatActivity implements UsersFragment.OnFragmentInteractionListener{

    Fragment mFragment;

    public static String VIEW_TYPE = "view_type";
    public static String VIEW_TYPE_UPDATE = "view_type_update";
    public static String VIEW_TYPE_SEE = "view_type_see";
    public static String ID = "id";
    public static String NAME = "name";
    public static String NUMBER = "number";
    public static String URL_IMG = "url_img";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWdget();
    }

    private void initializeWdget() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (getIntent().getExtras() != null){
            if (getIntent().getExtras().getString(VIEW_TYPE).equalsIgnoreCase(VIEW_TYPE_UPDATE)) {
                mFragment = new UpdateUserFragment();

                Bundle args = new Bundle();
                args.putString(ID, getIntent().getExtras().getString(ID));
                args.putString(NAME, getIntent().getExtras().getString(NAME));
                args.putString(NUMBER, getIntent().getExtras().getString(NUMBER));
                args.putString(URL_IMG, getIntent().getExtras().getString(URL_IMG));
                mFragment.setArguments(args);

            } else if (getIntent().getExtras().getString(VIEW_TYPE).equalsIgnoreCase(VIEW_TYPE_SEE)){
                mFragment = new UsersFragment();
            }
        }

        if (mFragment == null){
            fragmentManager.beginTransaction().add(R.id.content, new UsersFragment()).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.content, mFragment).commit();
        }
    }
}
