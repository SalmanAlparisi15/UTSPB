package com.example.utspb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.utspb.R;
import com.example.utspb.data.response.Responses;
import com.example.utspb.data.retrofit.ApiConfig;
import com.example.utspb.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {
    TextView tvnama = findViewById(R.id.tvnamaDetail);
    TextView tvusername = findViewById(R.id.tvusernamaDetail);
    TextView tvbio = findViewById(R.id.tvbioDetail);
    TextView tvtwitter= findViewById(R.id.tvtwitterDetail);
    ImageView imageView = findViewById(R.id.gambarDetail);
    Button btnShare= findViewById(R.id.btnShare);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String username = bundle.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<Responses> userCall = apiService.getuserService(username);
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(Intent.ACTION_SEND);
                    intent1.setType("text/plain");
                    String shareMessage = tvnama.getText().toString() +"\n"+
                            tvusername.getText().toString() +"\n"+
                            tvbio.getText().toString() +"\n"+
                            tvtwitter.getText().toString();
                    intent1.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(intent1, "Bagikan Pesan Melalui..."));
                }
            });


            userCall.enqueue(new Callback<Responses>() {
                @Override
                public void onResponse(Call<Responses> call, Response<Responses> response) {
                    if (response.isSuccessful()) {

                        Responses user = response.body();
                        if (user != null) {

                            String name = "Name: " + user.getName();
                            String username = "Username: " + user.getLogin();
                            String bio = "Bio: " + user.getBio();
                            String twitter = "Twitter: " + user.getTwitterUsername();
                            String gambar = user.getAvatarUrl();

                            tvnama.setText(name);
                            tvusername.setText(username);
                            tvbio.setText(bio);
                            tvtwitter.setText(twitter);
                            Picasso.get().load(gambar).into(imageView);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Responses> call, Throwable t) {
                    Toast.makeText(Detail.this, "Terdapat Kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}