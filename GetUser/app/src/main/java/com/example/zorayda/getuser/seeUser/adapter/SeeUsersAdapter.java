package com.example.zorayda.getuser.seeUser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zorayda.getuser.R;
import com.example.zorayda.getuser.seeUser.model.ListUser;
import com.example.zorayda.getuser.seeUser.model.UserResponse;

import java.util.ArrayList;

import butterknife.BindView;

public class SeeUsersAdapter extends RecyclerView.Adapter<SeeUsersAdapter.ViewHolderSeeUsersAdapter> implements View.OnClickListener {

    private ArrayList<ListUser> mData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View.OnClickListener mListener;

    public SeeUsersAdapter(Context context, ArrayList<ListUser> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolderSeeUsersAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_list_users, viewGroup, false);
        view.setOnClickListener(this);
        return new ViewHolderSeeUsersAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSeeUsersAdapter viewHolderSeeUsersAdapter, int i) {
        viewHolderSeeUsersAdapter.addUser(mData.get(i).usuario);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }

    class ViewHolderSeeUsersAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewName)
        TextView mNameTextView;

        @BindView(R.id.textViewNumber)
        TextView mNumberTextView;

        @BindView(R.id.textViewId)
        TextView mIdTextView;

        @BindView(R.id.imageViewAvatar)
        ImageView mAvatarImageView;

        ViewHolderSeeUsersAdapter(@NonNull View itemView) {
            super(itemView);
            mNumberTextView = itemView.findViewById(R.id.textViewNumber);
            mIdTextView = itemView.findViewById(R.id.textViewId);
            mNameTextView = itemView.findViewById(R.id.textViewName);
            mAvatarImageView = itemView.findViewById(R.id.imageViewAvatar);
        }

        void addUser(UserResponse userResponse) {
            mNameTextView.setText(userResponse.name);
            mNumberTextView.setText(userResponse.number);
            mIdTextView.setText(userResponse.id);

            if (userResponse.avatar != null){
                Glide.with(mContext)
                        .load(userResponse.avatar)
                        .into(mAvatarImageView);
            }
        }
    }
}
