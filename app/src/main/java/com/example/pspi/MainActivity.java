package com.example.pspi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    EditText ett1,ett2,ett3;
    TextView tvv;
    Button btnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ett1=(EditText)findViewById(R.id.et1);
        ett2=(EditText)findViewById(R.id.et2);
        ett3=(EditText)findViewById(R.id.et3);
        btnn=(Button)findViewById(R.id.btn);
        tvv=(TextView)findViewById(R.id.tv5);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("cal");

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PyObject obj = pyobj.callAttr("calc", ett1.getText().toString(),ett2.getText().toString(),ett3.getText().toString());

                tvv.setText("Answer: "+obj.toString());
            }
        });

    }
}