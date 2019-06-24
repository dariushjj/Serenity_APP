package com.serenity.view.sign;

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
import androidx.appcompat.app.AppCompatActivity;

import com.android.serenityapp.R;
import com.serenity.dao.UserDao;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        TextView needRegister = findViewById(R.id.needregister);
        Button accountButton = (Button)findViewById(R.id.sign_in_button);
        needRegister.setOnClickListener(this);
        accountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.needregister:
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
                break;
            case R.id.sign_up_button:
                UserDao userDao = new UserDao();
                EditText accountEditText = (EditText)findViewById(R.id.accounttextview);
                EditText passwordEditText = (EditText)findViewById(R.id.passwordtextview);
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!((account.equals("") && password.equals(""))
                        || (account == null && password == null))){
                    if (userDao.signIn(account,password)) {
//                    登录成功，跳转下个界面

                    }else {
                        Toast.makeText(SignInActivity.this, "Wrong account/password! or " +
                                "Unregister!", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                break;
        }
    }
}
