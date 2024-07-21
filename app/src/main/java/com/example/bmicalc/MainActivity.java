package com.example.bmicalc;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bmicalc.R;

public class MainActivity extends AppCompatActivity {

    Button but;
    EditText input1, input2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        but = findViewById(R.id.sub);
        input1 = findViewById(R.id.weight);
        input2 = findViewById(R.id.height);
        t1 = findViewById(R.id.result);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double weight = Double.parseDouble(input1.getText().toString());
                    double height = Double.parseDouble(input2.getText().toString());
                    double bmi = weight / (height * height);

                    t1.setText(String.format("BMI: %.2f", bmi));

                    if (bmi < 18) {
                        LayoutInflater alert1 = getLayoutInflater();
                        View obj1 = alert1.inflate(R.layout.custom_layout2, findViewById(R.id.lay3));
                        Toast toast = new Toast(MainActivity.this);
                        toast.setGravity(Gravity.BOTTOM, 0, 100);
                        toast.setView(obj1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();

                    } else if (bmi > 18 & bmi < 25) {
                        LayoutInflater alert2 = getLayoutInflater();
                        View obj2 = alert2.inflate(R.layout.custom_lay3, findViewById(R.id.lay3));
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(obj2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                } catch (NumberFormatException e) {
                    t1.setText("Please enter valid numbers.");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
