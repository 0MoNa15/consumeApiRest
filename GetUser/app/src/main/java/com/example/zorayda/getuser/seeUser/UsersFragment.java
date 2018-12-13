package com.example.zorayda.getuser.seeUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zorayda.getuser.findUser.FindUserResponse;
import com.example.zorayda.getuser.seeUser.adapter.SeeUsersAdapter;
import com.example.zorayda.getuser.seeUser.model.ListUser;
import com.example.zorayda.getuser.MainActivity;
import com.example.zorayda.getuser.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.example.zorayda.getuser.MainActivity.ID;
import static com.example.zorayda.getuser.MainActivity.NAME;
import static com.example.zorayda.getuser.MainActivity.NUMBER;
import static com.example.zorayda.getuser.MainActivity.URL_IMG;
import static com.example.zorayda.getuser.MainActivity.VIEW_TYPE;
import static com.example.zorayda.getuser.MainActivity.VIEW_TYPE_UPDATE;

public class UsersFragment extends Fragment implements SeeUserContract.View{

    MaterialDialog mMaterialDialog;
    SeeUserContract.Presenter mPresenter;
    SeeUsersAdapter mAdapter;

    ArrayList<ListUser> mUsers = new ArrayList<>();

    //@BindView(R.id.recyclerViewLisUsers)
    RecyclerView mListUsersRecyclerView;

    public UsersFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);
        mListUsersRecyclerView = view.findViewById(R.id.recyclerViewLisUsers);
        initializeWidget();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    @Override
    public void isLoading(boolean isLoading) {
        if (isLoading) {
            mMaterialDialog.show();
        } else {
            mMaterialDialog.dismiss();
        }
    }

    @Override
    public void displayError(String message) {
        if (!isAdded() || getActivity() == null){
            return;
        }

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayUsers(ArrayList<ListUser> listUsers) {
        if (!isAdded()) {
            return;
        }

        if (listUsers != null){
            mUsers.clear();
            mUsers.addAll(listUsers);
            mAdapter.notifyDataSetChanged();
        } else {
            mUsers.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void displayResponseFindUser(FindUserResponse findUserResponse) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(VIEW_TYPE, VIEW_TYPE_UPDATE);
        intent.putExtra(NAME, findUserResponse.response.get(0).name);
        intent.putExtra(NUMBER, findUserResponse.response.get(0).number);
        intent.putExtra(ID, findUserResponse.response.get(0).id);
        intent.putExtra(URL_IMG, findUserResponse.response.get(0).avatar);
        startActivity(intent);
    }

    @Override
    public void setPresenter(SeeUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public interface OnFragmentInteractionListener {
    }

    private void initializeWidget() {
        if (getActivity() == null){
            return;
        }
        getActivity().setTitle(getString(R.string.lista_usuarios_titulo));
        setPresenter(new SeeUserPresenter(getContext(), this));

        mAdapter = new SeeUsersAdapter(getContext(), mUsers);
        mListUsersRecyclerView.setAdapter(mAdapter);


        mMaterialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.cargando)
                .progress(true, 0)
                .build();

        mMaterialDialog.setCancelable(false);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mListUsersRecyclerView.getChildAdapterPosition(v);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(VIEW_TYPE, VIEW_TYPE_UPDATE);
                intent.putExtra(NAME, mUsers.get(position).usuario.name);
                intent.putExtra(NUMBER, mUsers.get(position).usuario.number);
                intent.putExtra(ID, mUsers.get(position).usuario.id);
                intent.putExtra(URL_IMG, mUsers.get(position).usuario.avatar);
                startActivity(intent);
            }
        });
    }

    public void refreshList(){
        mPresenter.getUsers();
    }
}
