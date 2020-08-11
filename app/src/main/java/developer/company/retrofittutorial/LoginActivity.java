package developer.company.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {


    TextView txtRegister;

    ApiInterface request;
    String url = "http://192.168.1.3/retrofit/";
    TextInputEditText edtEmail;
    TextInputEditText edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (restoreStateUSer()){

            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();

        }

        request = ApiClient.getApiClient(url).create(ApiInterface.class);

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        txtRegister = findViewById(R.id.txt_register);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();
                saveStateUser();

            }
        });
    }

    private void getUser() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        Call<Users> call = request.loginAccount(email, password);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.body().getResponse().equals("USER_LOGIN")) {

                    Toast.makeText(LoginActivity.this, "User Logged in ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                } else if (response.body().getResponse().equals("NO_ACCOUNT")) {

                    Toast.makeText(LoginActivity.this, "There is no account! ", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveStateUser(){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PrefUser",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("user_state",true);
        editor.commit();

    }

    private boolean restoreStateUSer(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PrefUser",MODE_PRIVATE);
        boolean stateUser;
        stateUser = sharedPreferences.getBoolean("user_state",false);
        return stateUser;
    }
}