package developer.company.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BooksAdapter adapter;
    List<Books> booksList = new ArrayList<>();

    ApiInterface request;
    String url = "http://192.168.1.2/retrofit/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = ApiClient.getApiClient(url).create(ApiInterface.class);

        recyclerView = findViewById(R.id.rv_books);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        request.getData().enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {

                booksList = response.body();
                adapter = new BooksAdapter(getApplicationContext(), booksList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("RETROFIT",t.getMessage());
            }
        });

    }
}