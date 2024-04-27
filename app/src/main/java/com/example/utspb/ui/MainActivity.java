package com.example.utspb.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.utspb.R;
import com.example.utspb.data.response.Search;
import com.example.utspb.data.response.Responses;
import com.example.utspb.data.retrofit.ApiConfig;
import com.example.utspb.data.retrofit.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewUi reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = ApiConfig.getApiService();
        Call <Search> call = apiService.searchUser("a");

        recyclerView = findViewById(R.id.Recyclerview);

        call.enqueue(new Callback<Search>() {

            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    List<Responses> users = response.body().getUsers();
                    reviewAdapter = new ReviewUi(users);
                    recyclerView.setAdapter(reviewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });

    }
}