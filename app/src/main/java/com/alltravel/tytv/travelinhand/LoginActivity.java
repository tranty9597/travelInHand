package com.alltravel.tytv.travelinhand;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.UserServices;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private EditText usernameTxt;
    private EditText passwordTxt;
    private TextView loginErrorTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = findViewById(R.id.usernameTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginErrorTxt = findViewById(R.id.loginErrorTxt);
    }

    public void doLogin(View view){

        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
    User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserServices userServices = RetrofitInstance.getRetrofitInstance().create(UserServices.class);

        Call<User> call = userServices.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                loginErrorTxt.setText( response.body().getFullName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginErrorTxt.setText(t.getMessage());
                Log.wtf("sss", t.getMessage());
            }
        });
    }
}
