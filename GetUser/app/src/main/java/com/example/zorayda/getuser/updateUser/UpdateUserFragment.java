package com.example.zorayda.getuser.updateUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.zorayda.getuser.MainActivity;
import com.example.zorayda.getuser.R;
import com.example.zorayda.getuser.seeUser.model.UserResponse;

import static com.example.zorayda.getuser.MainActivity.ID;
import static com.example.zorayda.getuser.MainActivity.NAME;
import static com.example.zorayda.getuser.MainActivity.NUMBER;
import static com.example.zorayda.getuser.MainActivity.URL_IMG;
import static com.example.zorayda.getuser.MainActivity.VIEW_TYPE;
import static com.example.zorayda.getuser.MainActivity.VIEW_TYPE_SEE;

public class UpdateUserFragment extends Fragment implements UpdateUserContract.View{

    String mId, mNumber, mName, mUrlImg;

    EditText mNameEditText;
    EditText mNumberEditText;
    ImageView mAvatarEditText;
    Button mSendDataButton;

    MaterialDialog mMaterialDialog;
    UpdateUserContract.Presenter mPresenter;

    public UpdateUserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_user, container, false);
        initializeWidget(view);

        return view;
    }

    private void initializeWidget(View view) {
        if (getActivity() == null) {
            return;
        }

        setPresenter(new UpdateUserPresenter(getContext(), this));

        getActivity().setTitle(getString(R.string.actualizar_usuario));

        mMaterialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.cargando)
                .progress(true, 0)
                .build();

        mMaterialDialog.setCancelable(false);

        if (getArguments() != null) {
            mId = getArguments().getString(ID);
            mName = getArguments().getString(NAME);
            mNumber = getArguments().getString(NUMBER);
            mUrlImg = getArguments().getString(URL_IMG);
        }

        mNameEditText = view.findViewById(R.id.editTextName);
        mNumberEditText = view.findViewById(R.id.editTextNumber);
        mAvatarEditText = view.findViewById(R.id.imageViewAvatar);
        mSendDataButton = view.findViewById(R.id.buttonUpdateUser);

        mNameEditText.setText(mName);
        mNumberEditText.setText(mNumber);

        if (mUrlImg != null){
            Glide.with(getActivity())
                    .load(mUrlImg)
                    .into(mAvatarEditText);

        }

        mSendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNameEditText.setError(null);
                mNumberEditText.setError(null);

                if (!validateText(mNameEditText.getText().toString())){
                    mNameEditText.setError(getString(R.string.obligatori));
                } else if (!validateText(mNumberEditText.getText().toString())){
                    mNumberEditText.setError(getString(R.string.obligatori));
                } else if (!validateText(mId)) {
                    Toast.makeText(getActivity(), R.string.intenta_mas_tarde, Toast.LENGTH_SHORT).show();
                } else {
                    UserResponse userResponse = new UserResponse();
                    userResponse.id = mId;
                    userResponse.name = mNameEditText.getText().toString();
                    userResponse.number = mNumberEditText.getText().toString();
                    mPresenter.sendDataUserUpdate(mId, mNameEditText.getText().toString(), mNumberEditText.getText().toString());
                }
            }
        });
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
    public void displayResponse(String message, boolean isSuccess) {
        if (!isAdded() || getActivity() == null){
            return;
        }

        if (isSuccess){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(VIEW_TYPE, VIEW_TYPE_SEE);
            startActivity(intent);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setPresenter(UpdateUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static boolean validateText(String text){
        if (text == null){
            return false;
        } else if (text.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public interface OnFragmentInteractionListener {
    }
}
