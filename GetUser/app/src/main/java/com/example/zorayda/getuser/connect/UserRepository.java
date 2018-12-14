package com.example.zorayda.getuser.connect;

import android.content.Context;
import android.util.Log;

import com.example.zorayda.getuser.BuildConfig;
import com.example.zorayda.getuser.R;
import com.example.zorayda.getuser.findUser.FindUserResponse;
import com.example.zorayda.getuser.seeUser.model.GetUsersResponse;
import com.example.zorayda.getuser.seeUser.model.UserResponse;
import com.example.zorayda.getuser.updateUser.model.GeneralResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private Webservice mWebservice;
    private Context mContext;
    private Gson gson;
    private OkHttpClient mOkHttpClient;

    public UserRepository(Context context) {
        mContext = context;

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit mRetrofit;
        Retrofit.Builder build;

        build = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create(gson));

        mRetrofit = build.build();
        mWebservice = mRetrofit.create(Webservice.class);

    }

    public void getUserApi(final ApiCallback.ListUsersCallback listUsersCallback) {
        mWebservice.getUsers().enqueue(new CallbackCustom<GetUsersResponse>(mContext) {
            @Override
            public void onResponse(Call<GetUsersResponse> call, Response<GetUsersResponse> response) {
                super.onResponse(call, response);

                if (response.isSuccessful()) {

                    switch (response.code()) {
                        case 200:
                            listUsersCallback.onSuccess(response.body());
                            break;
                    }
                } else {
                    switch (response.code()) {
                        case 404:
                            listUsersCallback.onError(mContext.getString(R.string.error));
                            break;

                        case 500:
                            listUsersCallback.onError(mContext.getString(R.string.error));
                            break;

                        default:
                            listUsersCallback.onError(mContext.getString(R.string.error));
                            break;

                    }
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                listUsersCallback.onError(mContext.getString(R.string.error));
            }

            @Override
            public void onFailure(Call<GetUsersResponse> call, Throwable t) {
                super.onFailure(call, t);

                if (t instanceof IOException) {
                    listUsersCallback.onError(mContext.getString(R.string.error_internet));

                } else {
                    listUsersCallback.onError(mContext.getString(R.string.error_servidor));
                }
            }
        });
    }

    public void updateDataUserApi(String id, String name, String number, final ApiCallback.UpdateUserCallback updateUserCallback) {

        mWebservice.updateDataUser(id, name, number).enqueue(new CallbackCustom<GeneralResponse>(mContext) {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {

                    switch (response.code()) {
                        case 200:
                            String messageResponse = "";

                            if (response.body().response) {
                                messageResponse = mContext.getString(R.string.actualizado_correctamente);
                            } else {
                                messageResponse = mContext.getString(R.string.actualizaci_n_incorrecta);
                            }
                            updateUserCallback.onSuccess(messageResponse);
                            break;
                    }
                } else {
                    switch (response.code()) {
                        case 404:
                            updateUserCallback.onError(mContext.getString(R.string.error_404));
                            break;

                        case 500:
                            updateUserCallback.onError(mContext.getString(R.string.error_500));
                            break;

                        default:
                            updateUserCallback.onError(mContext.getString(R.string.error));
                            break;

                    }
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                updateUserCallback.onError(message + " onError");
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                super.onFailure(call, t);

                if (t instanceof IOException) {
                    updateUserCallback.onError(mContext.getString(R.string.error_internet));

                } else {
                    updateUserCallback.onError(mContext.getString(R.string.error_servidor));
                }
            }
        });
    }


    public void findUser(String username, final ApiCallback.findUserResponse findUserResponse) {
        mWebservice.searchUserByName(username).enqueue(new CallbackCustom<FindUserResponse>(mContext) {
            @Override
            public void onResponse(Call<FindUserResponse> call, Response<FindUserResponse> response) {
                super.onResponse(call, response);
                if (response.isSuccessful()) {

                    switch (response.code()) {
                        case 200:
                            Log.e("Debug", "onResponse: " + response.body());
                            findUserResponse.onSuccess(response.body());
                            break;
                    }
                } else {
                    switch (response.code()) {
                        case 404:
                            findUserResponse.onError(mContext.getString(R.string.error_404));
                            break;

                        case 500:
                            findUserResponse.onError(mContext.getString(R.string.error_500));
                            break;

                        default:
                            findUserResponse.onError(mContext.getString(R.string.error));
                            break;

                    }
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                findUserResponse.onError(message);

            }

            @Override
            public void onFailure(Call<FindUserResponse> call, Throwable t) {
                super.onFailure(call, t);

                if (t instanceof IOException) {
                    findUserResponse.onError(mContext.getString(R.string.error_internet));

                } else {
                    findUserResponse.onError(mContext.getString(R.string.error_servidor));
                }
            }
        });
    }
}
