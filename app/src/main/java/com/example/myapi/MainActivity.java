package com.example.myapi;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.stream.JsonReader;


public class MainActivity extends AppCompatActivity {

    private TextView Mytxt;
    private Spinner convertFrom;
    private Spinner convertTo;
    private Button convertAmount;
    Double usd =1.0;
    Double egp;
    Double eur;
    Double gbp;
    Double sar;
    Double aed;
    Double kwd;
    Double base;
    Double toCurruncy;
    Double amount;
    EditText finalAmount;
    Float finalAmount2;
    String number="1";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertFrom = (Spinner) findViewById(R.id.spinner);
        convertTo = (Spinner) findViewById(R.id.spinner2);
        Mytxt = findViewById(R.id.MyTxt);
        finalAmount = (EditText) findViewById(R.id.editText);




        String[] dropDownList = {"USD","EGP","EUR","GBP","SAR","AED","KWD"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,dropDownList);
        convertFrom.setAdapter(adapter);
        convertTo.setAdapter(adapter);
        convertAmount = (Button) findViewById(R.id.button);

        convertAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("button","button clicked ");
                Toast.makeText(getApplicationContext(),"converted from " + convertFrom.getSelectedItem().toString()
                        +" to " + convertTo.getSelectedItem()
                        ,Toast.LENGTH_SHORT).show();

                // retro fit buiilder
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://currency-ex-21.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                // instance for interface
                MyApiCall myApiCall = retrofit.create(MyApiCall.class);

                Call<DataModel> call = myApiCall.getData();
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        // on response failed

                        if (!response.isSuccessful()){
                            Mytxt.setText("connection Error ");
                            return;
                        }
                        Double[] rates = {
                                usd =1.0 ,
                                egp = Double.valueOf(response.body().getEGP()),
                                eur = response.body().getEUR(),
                                gbp = response.body().getGBP(),
                                sar = response.body().getSAR(),
                                aed = response.body().getAED(),
                                kwd = response.body().getKWD()
                        };
                        base = Double.valueOf(rates[convertFrom.getSelectedItemPosition()]);
                        toCurruncy = Double.valueOf(rates[convertTo.getSelectedItemPosition()]);
                        String amountTocheck = finalAmount.getText().toString();
                        if(amountTocheck.matches("")){
                            Toast.makeText(getApplicationContext(),"please Enter Amount" ,Toast.LENGTH_SHORT).show();
                        }else {

                            number = finalAmount.getText().toString();
                            finalAmount2 = Float.parseFloat(number);
                            Log.i("number", String.valueOf(number));
                            amount =((1/base)*toCurruncy)*Float.valueOf(number);
                            String amount2 = String.format("%.4f",amount);
                            Mytxt.setText(String.valueOf(amount2)+ " "+ convertTo.getSelectedItem());

                        }

















                        Log.i("info","app created2");
                        Log.i("postion", String.valueOf(response.body()) );
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        Mytxt.setText(t.getMessage());
                        Log.i("error",t.getMessage());

                    }
                });

            }
        });
        Log.i("info","app created");



    }
}
