package com.siamsoft.retro_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siamsoft.retro_crud.api.ApiRequestBiodata;
import com.siamsoft.retro_crud.api.Retroserver;
import com.siamsoft.retro_crud.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText nama_v, salary_v, desig_v, address;
    Button btnsave;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nama_v = findViewById(R.id.name);
        salary_v =findViewById(R.id.salary);
        desig_v = findViewById(R.id.designation);
        btnsave = findViewById(R.id.btn);
        address = findViewById(R.id.address);
        pd = new ProgressDialog(this);

        //-----------------insert

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show();

                String snama = nama_v.getText().toString();
                String salary = salary_v.getText().toString();
                String sdesig = desig_v.getText().toString();
                String ad = address.getText().toString();
                ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);

                Call<ResponseModel> sendbio = api.sendBiodata(snama,salary,sdesig,ad);
                sendbio.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String code = response.body().getCode();

                        if(code.equals("1"))
                        {
                            Toast.makeText(MainActivity.this, "Data save successfully", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(MainActivity.this, "Data Error pls entry correctly", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });





    }
}