package com.taal.welding.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.assistent.MainActivity;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.RegistrationClass;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.account)
    protected Button mReg;
    @Bind(R.id.loginBut)
    protected Button mLogin;
    @Bind(R.id.username)
    protected EditText mUsername;
    @Bind(R.id.password)
    protected EditText mPasswprd;
    @Bind(R.id.textViewForgot)
    protected TextView mForgotPassword;
    @Bind(R.id.saveLoginCheckBox)
    protected CheckBox mCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private DatabaseHelper db;
    private Intent mIntent;
    private Boolean flag;
    private String KEY = "Key";
    private String stringData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (getSupportActionBar() != null) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try{
                getSupportActionBar().setTitle("BLDC PoC");


            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        //window.setStatusBarColor(Color.BLACK);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {
            //statusbar.setVisibility(View.VISIBLE);
        }
        else
        {
            window.setStatusBarColor(Color.BLACK);
        }
        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            mUsername.setText(loginPreferences.getString("username", ""));
            mPasswprd.setText(loginPreferences.getString("password", ""));
            mCheckBox.setChecked(true);
        }
    }
    @OnClick(R.id.account)
    protected void registration() {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
    @OnClick(R.id.loginBut)
    protected void login() {
        flag = false;
        System.out.println("Inside login  button ");
        List<RegistrationClass> allTags = db.getAllToDos();
        for (RegistrationClass tag : allTags) {
            System.out.println(tag.get_name() + ".." + tag.get_userName() +".." + tag.get_password());
            if(mUsername.getText().toString().trim().equals(tag.get_userName()) && mPasswprd.getText().toString().trim().equals(tag.get_password())) {
                if (mCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", mUsername.getText().toString().trim());
                    loginPrefsEditor.putString("password", mPasswprd.getText().toString().trim());
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
//                mIntent = new Intent(this, MainMenuActivity.class);
//                mIntent.putExtra(KEY, tag.get_name().toString().trim());
//                startActivity(mIntent);
                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                flag = true;
                //finish();
                System.out.println("Hello in first page ");
                break;
            }
            Log.d("Tag Name", tag.get_name() + tag.get_id() + tag.get_password() + tag.get_userName());
        }
        if(!flag) {
//            mUsername.setText("");
//            mPasswprd.setText("");
            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R.id.textViewForgot)
    protected void forgotPassword() {
        if(!(mUsername.getText().toString().trim().isEmpty())) {
            Intent intent = new Intent(getApplicationContext(), ForgotActivity.class);
            intent.putExtra("message", mUsername.getText().toString().trim());
            startActivity(intent);
        }
        else if(mUsername.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter Username", Toast.LENGTH_LONG).show();
        }
    }
}
