package developer.company.retrofittutorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getData.php")
    Call<List<Books>> getData();

    @POST("register.php")
    Call<Users> registerAccount(@Query("username") String username,
                                @Query("email") String email,
                                @Query("phone") String phone,
                                @Query("password") String password);


    @GET("login.php")
    Call<Users> loginAccount(@Query("email") String email,
                             @Query("password") String password);

}
