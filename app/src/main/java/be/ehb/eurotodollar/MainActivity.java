package be.ehb.eurotodollar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //UI components
    private EditText editEuro;
    private  EditText editDollar;
    private TextInputLayout euroLayout;
    private Button btnConvert;

    //Listener op UI components
    private View.OnClickListener convertListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkEuro()) {
                String euroStr = editEuro.getText().toString();
                //convert to double
                double euro = Double.parseDouble(euroStr);
                //convert to dollar
                double conversionRate = 1.18;
                double dollar = euro * conversionRate;
                //modify dollartext
                editDollar.setText(String.format(Locale.getDefault(), "%.2f", dollar));
            }
        }
    };

    private TextWatcher euroTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            checkEuro();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkEuro();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private boolean checkEuro() {
        String euro = editEuro.getText().toString();
        if (euro.length() <= 0) {
            euroLayout.setError("Field must be filled in!");
            return false;
        } else {
            euroLayout.setError(null);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEuro = findViewById(R.id.edit_euro);
        editDollar = findViewById(R.id.edit_dollar);
        btnConvert = findViewById(R.id.convertButton);
        euroLayout = findViewById(R.id.til_euro);
        btnConvert.setOnClickListener(convertListener);
        editEuro.addTextChangedListener(euroTextWatcher);
    }
}