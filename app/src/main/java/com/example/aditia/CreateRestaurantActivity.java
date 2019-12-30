package com.example.aditia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.aditia.data.Constans;
import com.example.aditia.data.Session;
import com.example.aditia.model.ResturantResponse;
import com.example.aditia.utils.DialogUtils;

public class CreateRestaurantActivity extends AppCompatActivity {

    EditText namarm, kategori, link_foto, alamat;
    Button create_restaurant;
    ProgressDialog progressDialog;
    Session session;
    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_restaurant);
        session = new Session(this);
        progressDialog = new ProgressDialog(this);
        userId = getIntent().getStringExtra("userId");
        initBinding();
        initClick();
    }

    private void initBinding() {
        namarm = findViewById(R.id.et_namarm);
        kategori = findViewById(R.id.et_kategori);
        link_foto = findViewById(R.id.et_link_foto);
        alamat = findViewById(R.id.et_alamat);
        create_restaurant = findViewById(R.id.btn_create_restaurant);
    }

    private void initClick() {
        create_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namarm.getText().toString().isEmpty()){
                    Toast.makeText(CreateRestaurantActivity.this, "Nama Resto ga boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(kategori.getText().toString().isEmpty()){
                    Toast.makeText(CreateRestaurantActivity.this, "Kategori ga boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(link_foto.getText().toString().isEmpty()){
                    Toast.makeText(CreateRestaurantActivity.this, "Link Foto ga boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(alamat.getText().toString().isEmpty()){
                    Toast.makeText(CreateRestaurantActivity.this, "Alamat ga boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    createRestaurant();
                }
            }
        });
    }
    public void createRestaurant() {
        DialogUtils.openDialog(this);
        AndroidNetworking.post(Constans.CREATE_RESTAURANT)
                .addBodyParameter("userid", userId)
                .addBodyParameter("namarm", namarm.getText().toString())
                .addBodyParameter("kategori", kategori.getText().toString())
                .addBodyParameter("link_foto", link_foto.getText().toString())
                .addBodyParameter("alamat", alamat.getText().toString())
                .build()
                .getAsObject(ResturantResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof ResturantResponse) {
                            ResturantResponse res = (ResturantResponse) response;
                            if (res.getStatus().equals("success")) {
                                Toast.makeText(CreateRestaurantActivity.this,"Yeaay berhasil buar restaurant", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(CreateRestaurantActivity.this,"Gagal buat Resto", Toast.LENGTH_SHORT).show();
                            }
                        }
                        DialogUtils.closeDialog();
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(CreateRestaurantActivity.this, "Terjadi kesalahan API", Toast.LENGTH_SHORT).show();
                        Toast.makeText(CreateRestaurantActivity.this, "Terjadi kesalahan API : "+anError.getCause().toString(), Toast.LENGTH_SHORT).show();
                        DialogUtils.closeDialog();
                    }
                });
    }
}
