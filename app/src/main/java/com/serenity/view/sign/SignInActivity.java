package com.serenity.view.sign;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.serenityapp.R;
import com.example.util.PreferenceUtil;
import com.serenity.dao.UserDao;
import com.serenity.view.guide.GuideActivity;
import com.serenity.view.home.HomeActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isLogin = false;
    public static SignInActivity instance =  null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signin);
        instance = this;
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        TextView needRegister = findViewById(R.id.needregister);
        Button accountButton = (Button)findViewById(R.id.sign_in_button);
        needRegister.setOnClickListener(this);
        accountButton.setOnClickListener(this);
        isLogin = PreferenceUtil.getBooleanValue(this, PreferenceUtil.LOGIN, "login");
        if(!isLogin){

//            欢迎界面
            PreferenceUtil.setBooleanPair(this, PreferenceUtil.LOGIN, true, "login");


        }else {
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.needregister:
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
                break;
            case R.id.sign_in_button:
                UserDao userDao = new UserDao();
                EditText accountEditText = (EditText)findViewById(R.id.accounttextview);
                EditText passwordEditText = (EditText)findViewById(R.id.passwordtextview);
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!((account.equals("") && password.equals(""))
                        || (account == null && password == null))){
                    if (userDao.signIn(account,password))
                    {
                        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                    }else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Wrong account/password or Unregister!");
                        builder.setCancelable(true);
                        builder.show();
                    }
                }
                break;
            default:
                break;
        }
    }
}
