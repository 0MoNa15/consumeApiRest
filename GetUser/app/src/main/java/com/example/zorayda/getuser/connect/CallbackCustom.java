package com.example.zorayda.getuser.connect;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.widget.Toast;

import com.example.zorayda.getuser.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackCustom<T> implements Callback<T> {
    private Context mContext;

    public CallbackCustom(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        /*
            Siempre que en otro lugar del proyecto se sobreesbriba este método
            y se llame al super, entonces entrará a validarse esta condicional
            en la cual se certifica que la respuesta está incorrecta, de ser así,
            solo se ejecutará esta condicional y lo que halla en el resto del
            método sobreescrito no se ejecutará
         */
        if (!response.isSuccessful()) {
            try {
                JSONObject errorBody = new JSONObject(response.errorBody().string());
                String message = errorBody.has("mensaje") ? errorBody.getString("mensaje") : mContext.getString(R.string.error);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                onError(message);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.error), Toast.LENGTH_SHORT).show();
                onError(mContext.getString(R.string.error));
            } catch (IOException e) {
                e.printStackTrace();
                onError(mContext.getString(R.string.error));
            }
        }
    }

    @CallSuper
    public void onError(String message){

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(mContext, R.string.error, Toast.LENGTH_SHORT).show();
    }
}
