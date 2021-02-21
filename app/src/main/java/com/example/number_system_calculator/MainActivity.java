package com.example.number_system_calculator;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText decimal;
    private EditText binary;
    private EditText octal;
    private EditText hexa;
    private String value;
    private int focusViewId;

    private View.OnFocusChangeListener onFocusChangeListener;
    private TextWatcher textwatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimal = (EditText) findViewById(R.id.Decimal);
        binary = (EditText) findViewById(R.id.Binary);
        octal = (EditText) findViewById(R.id.Octal);
        hexa = (EditText) findViewById(R.id.Hexadecimal);
        Button buttonClear = (Button) findViewById(R.id.Clear);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        textwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                value = ((EditText) findViewById(focusViewId)).getText().toString().trim();
                if (value.length() > 0) {
                    convertNumber();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Toast.makeText(getApplicationContext(),"Focus In", Toast.LENGTH_SHORT).show();
                    focusViewId = v.getId();
                    ((EditText) findViewById(focusViewId)).addTextChangedListener(textwatcher);

                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.TL_BR,
                            new int[]{Color.parseColor("#3a3942"), Color.parseColor("#d8dde8")}
                    );
                    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                    gradientDrawable.setCornerRadius(10);
                    if (focusViewId == R.id.Decimal) {
                        gradientDrawable.setStroke(8, ContextCompat.getColor(getApplicationContext(), android.R.color.background_light));
                    } else {
                        gradientDrawable.setStroke(8, ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    }

                    v.setBackground(gradientDrawable);
                } else {
                    ((EditText) findViewById(focusViewId)).removeTextChangedListener(textwatcher);
                    if (focusViewId == R.id.Decimal) {
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focusview_design));
                    } else {
                        v.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.view_design));
                    }
                }
            }
        };
        decimal.setOnFocusChangeListener(onFocusChangeListener);
        binary.setOnFocusChangeListener(onFocusChangeListener);
        octal.setOnFocusChangeListener(onFocusChangeListener);
        hexa.setOnFocusChangeListener(onFocusChangeListener);
    }

    private void clearFields() {
        binary.setText("");
        decimal.setText("");
        octal.setText("");
        hexa.setText("");
    }

    private void convertNumber() {
        try {
            long number = 0;
            if (focusViewId == R.id.Decimal) {
                number = Long.parseLong(value);
                binary.setText(String.valueOf(Long.toBinaryString(number)));
                octal.setText(String.valueOf(Long.toOctalString(number)));
                hexa.setText(String.valueOf(Long.toHexString(number)));
            }
            if (focusViewId == R.id.Binary) {
                number = Long.parseLong(value, 2);
                decimal.setText(String.valueOf((number)));
                octal.setText(String.valueOf(Long.toOctalString(number)));
                hexa.setText(String.valueOf(Long.toHexString(number)));
            }
            if (focusViewId == R.id.Octal) {
                number = Long.parseLong(value, 8);
                decimal.setText(String.valueOf((number)));
                binary.setText(String.valueOf(Long.toBinaryString(number)));
                hexa.setText(String.valueOf(Long.toHexString(number)));
            }
            if (focusViewId == R.id.Hexadecimal) {
                number = Long.parseLong(value, 16);
                decimal.setText(String.valueOf((number)));
                binary.setText(String.valueOf(Long.toBinaryString(number)));
                octal.setText(String.valueOf(Long.toOctalString(number)));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}