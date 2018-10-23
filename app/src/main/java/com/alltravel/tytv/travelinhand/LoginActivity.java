package com.alltravel.tytv.travelinhand;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alltravel.tytv.travelinhand.model.base.User;
import com.alltravel.tytv.travelinhand.services.UserServices;
import com.alltravel.tytv.travelinhand.singleton.RetrofitInstance;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        user = fetchLogin(user);

    }
    private User fetchLogin(User user){
        UserServices userServices = RetrofitInstance.getRetrofitInstance().create(UserServices.class);
        final User u = new User();
        Call<Object> call = userServices.login(user);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                JsonParser parser = new JsonParser();

                JsonObject resJson = parser.parse(new Gson().toJson(response.body())).getAsJsonObject();
                loginErrorTxt.setText(resJson.get("message").getAsString());
                if(resJson.get("isError").getAsBoolean()){
                    return;
                }else{
                    JsonObject userJson = resJson.getAsJsonObject("user");
                    u.setFullName(userJson.get("fullName").getAsString());
                    u.setUsername(userJson.get("username").getAsString());
                    u.setPhone(userJson.get("phone").getAsString());
                    u.setEmail(userJson.get("email").getAsString());

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loginErrorTxt.setText(t.getMessage());
                Log.wtf("sss", t.getMessage());
            }
        });
        return  u;
    }
    public void dismissKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(passwordTxt.getWindowToken(), 0);
    }
}
