package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText dep_sum,edit_rate,edit_period;
    Button resultbtn, goback;
    TextView show_sep_result;

    RadioGroup group1;
    RadioButton radio_years, radio_months;

    float result_num, amount, rate, time;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        //creating a timer
        final TextView textView = (TextView) findViewById(R.id.timer);
        Thread t = new Thread(){
            @Override
            public void run(){
                while (!isInterrupted()){
                    try {
                        Thread.sleep(1000); //1000millis = 1sec
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count++;
                                textView.setText(String.valueOf(count));
                            }
                        });
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();


        //declare variables
        show_sep_result = (TextView) findViewById(R.id.show_dep_result);
        resultbtn = (Button) findViewById(R.id.resultbtn);
        dep_sum = (EditText) findViewById(R.id.dep_sum);
        edit_rate = (EditText) findViewById(R.id.edit_rate);
        edit_period = (EditText) findViewById(R.id.edit_period);
        group1 = (RadioGroup) findViewById(R.id.group1);
        radio_years = (RadioButton) findViewById(R.id.radio_years);
        radio_months = (RadioButton) findViewById(R.id.radio_months);
        goback = (Button) findViewById(R.id.goback);

        dep_sum.addTextChangedListener(calculate);
        edit_period.addTextChangedListener(calculate);
        edit_rate.addTextChangedListener(calculate);


        //button opens previous activity
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous_activity();
            }
        });




        //when button is clicked
        resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Float.parseFloat(dep_sum.getText().toString());
                rate = Float.parseFloat(edit_rate.getText().toString());
                time = Float.parseFloat(edit_period.getText().toString());

                show_sep_result.setText("Total saving is  " + String.valueOf(result_num) + " tenge");

            }
        });
    }
    public void previous_activity(){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void radio_clicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_years:
                if (checked)
                    result_num = amount * (rate/100) * time + amount;
                break;
            case R.id.radio_months:
                if (checked)
                    result_num = amount * (rate/100) * (time/12) + amount;
        }
    }

    //like-check if edittexts are empty
    TextWatcher calculate = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String amount = dep_sum.getText().toString();
            String rate = edit_rate.getText().toString();
            String time = edit_period.getText().toString();

            resultbtn.setEnabled(!amount.isEmpty()&&!rate.isEmpty()&&!time.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "You selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}