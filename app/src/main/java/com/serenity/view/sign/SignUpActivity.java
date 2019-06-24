package com.serenity.view.sign;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.serenityapp.R;
import com.serenity.dao.UserDao;
import com.serenity.model.User;

import org.litepal.LitePal;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Button signUp = (Button)findViewById(R.id.sign_up_button);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up_button:
                UserDao userDao = new UserDao();
                EditText editTextAccount = (EditText)findViewById(R.id.accounttextview);
                EditText editTextPassword = (EditText)findViewById(R.id.passwordtextview);
                String account = editTextAccount.getText().toString();
                String password = editTextPassword.getText().toString();
                if (!((account.equals("") && password.equals(""))
                        || (account == null && password == null))){
                    if (userDao.isRegister(account)){
//                    或者可以选择其他的提示界面
                        Toast.makeText(SignUpActivity.this, "This account has been registered! ",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        userDao.register(account, password);
//                    注册成功后直接登录
                    }
                }

                break;
            default:
                break;

        }
    }
}
