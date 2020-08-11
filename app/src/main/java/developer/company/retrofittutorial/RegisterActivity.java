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

public class RegisterActivity extends AppCompatActivity {

    TextView txtLogin;

    ApiInterface request;
    String url = "http://192.168.1.3/retrofit/";
    TextInputEditText edtUsername;
    TextInputEditText edtEmail;
    TextInputEditText edtPhone;
    TextInputEditText edtPassword;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restoreStateUSer()){

            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            finish();

        }

        setContentView(R.layout.activity_register);

        request = ApiClient.getApiClient(url).create(ApiInterface.class);

        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtPhone = findViewById(R.id.edt_phone);
        btnRegister = findViewById(R.id.btn_register);

        txtLogin = findViewById(R.id.txt_login);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUser();
                saveStateUser();

            }
        });
    }

    private void getUser() {

        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();

        Call<Users> call = request.registerAccount(username, email, phone, password);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if (response.body().getResponse().equals("USER_REGISTER")) {

                    Toast.makeText(RegisterActivity.this, "You are already registered", Toast.LENGTH_LONG).show();

                } else if (response.body().getResponse().equals("SUCCESS")) {

                    Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();

                } else if (response.body().getResponse().equals("WRONG")) {

                    Toast.makeText(RegisterActivity.this, "Something is wrong!", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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