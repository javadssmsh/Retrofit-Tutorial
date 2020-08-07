package developer.company.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextView txtLogin;

    ApiInterface request;
    String url = "http://192.168.1.2/retrofit/";
    TextInputEditText edtUsername;
    TextInputEditText edtEmail;
    TextInputEditText edtPhone;
    TextInputEditText edtPassword;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                if (response.body().equals("USER_REGISTER")) {
                    Toast.makeText(RegisterActivity.this, "You are already registered", Toast.LENGTH_LONG).show();
                } else if (response.body().equals("SUCCESS")) {
                    Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_LONG).show();
                } else if (response.body().equals("WRONG")) {
                    Toast.makeText(RegisterActivity.this, "Something is wrong!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}