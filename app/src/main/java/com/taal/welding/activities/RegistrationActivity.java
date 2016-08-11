package com.taal.welding.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.taal.welding.R;
import com.taal.welding.database.DatabaseHelper;
import com.taal.welding.model.RegistrationClass;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {
    @Bind(R.id.regBut)
    Button mReg;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.username)
    EditText mUsername;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.confirmPassword)
    EditText mConfirmPassword;
    private DatabaseHelper db;
    private Boolean flag = false;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.BLACK);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {
            //statusbar.setVisibility(View.VISIBLE);
        }
        else
        {
            window.setStatusBarColor(Color.BLACK);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try{
                getSupportActionBar().setTitle("BLDC PoC");

            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return true;
    }

    @OnClick(R.id.regBut)
    protected void registration() {
        System.out.println("password " + mPassword.getText().toString());
        List<RegistrationClass> allTags = db.getAllToDos();
        for (RegistrationClass tag : allTags) {
            if (mName.getText().toString().trim().equals(tag.get_name()) || mUsername.getText().toString().trim().equals(tag.get_userName())) {
                Toast.makeText(getApplicationContext(), "Name or Username already exist", Toast.LENGTH_LONG).show();
                flag = false;
                break;
            }
            else {
                flag = true;
            }
        }
        if((flag || allTags.isEmpty()) && (!mName.getText().toString().trim().isEmpty() && !mUsername.getText().toString().trim().isEmpty() && !mPassword.getText().toString().trim().isEmpty())) {
            if(mPassword.getText().toString().trim().equals(mConfirmPassword.getText().toString().trim())){
                db.createReg(new RegistrationClass(mName.getText().toString().trim(), mUsername.getText().toString().trim(), mPassword.getText().toString().trim(), mConfirmPassword.getText().toString().trim()));
                mIntent = new Intent(this, LoginActivity.class);
                startActivity(mIntent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Does not match password and confirm password", Toast.LENGTH_LONG).show();
            }
        }
    }
}
