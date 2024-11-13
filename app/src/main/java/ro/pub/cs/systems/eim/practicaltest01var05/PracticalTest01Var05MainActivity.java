package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {
    private TextView textView;
    private StringBuilder buttonPresses;
    private int totalButtonPresses = 0;
    private static final String TOTAL_PRESSES_KEY = "total_presses_key";
    private static final int REQUEST_CODE_TEMPLATE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var05_main);

        textView = findViewById(R.id.textView);
        buttonPresses = new StringBuilder();

        // Restaurează numărul total de apăsări dacă există o stare salvată
        if (savedInstanceState != null) {
            totalButtonPresses = savedInstanceState.getInt(TOTAL_PRESSES_KEY, 0);
        }

        // Configurează ascultătorul pentru butoane
        setupButtonListeners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void setupButtonListeners() {
        Button buttonNavigateToSecondary = findViewById(R.id.buttonNavigateToSecondary);
        Button buttonTopLeft = findViewById(R.id.buttonTopLeft);
        Button buttonTopRight = findViewById(R.id.buttonTopRight);
        Button buttonCenter = findViewById(R.id.buttonCenter);
        Button buttonBottomLeft = findViewById(R.id.buttonBottomLeft);
        Button buttonBottomRight = findViewById(R.id.buttonBottomRight);

        // Creează și setează ascultătorul pentru fiecare buton
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                // Adaugă textul butonului la StringBuilder, urmat de virgulă
                if (buttonPresses.length() > 0) {
                    buttonPresses.append(", ");
                }
                buttonPresses.append(buttonText);

                // Incrementăm contorul pentru numărul total de apăsări
                totalButtonPresses++;

                textView.setText(buttonPresses.toString() );

                // Afișează Toast cu numărul total de apăsări
                Toast.makeText(PracticalTest01Var05MainActivity.this,
                        "Total apăsări: " + totalButtonPresses,
                        Toast.LENGTH_SHORT).show();
            }
        };

        // Asociază ascultătorul fiecărui buton
        buttonTopLeft.setOnClickListener(listener);
        buttonTopRight.setOnClickListener(listener);
        buttonCenter.setOnClickListener(listener);
        buttonBottomLeft.setOnClickListener(listener);
        buttonBottomRight.setOnClickListener(listener);

        // Ascultător pentru butonul de navigare către activitatea secundară
        buttonNavigateToSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creează intenția pentru a lansa activitatea secundară
                Intent intent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05SecondaryActivity.class);
                intent.putExtra("template_text", "Acesta este un șablon de text");
                startActivityForResult(intent, REQUEST_CODE_TEMPLATE);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TOTAL_PRESSES_KEY, totalButtonPresses);
    }

    // Restaurăm numărul total de apăsări după recrearea activității
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalButtonPresses = savedInstanceState.getInt(TOTAL_PRESSES_KEY, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TEMPLATE) {
            if (data != null) {
                int result = data.getIntExtra(PracticalTest01Var05SecondaryActivity.RESULT_KEY, PracticalTest01Var05SecondaryActivity.RESULT_CANCEL);
                if (result == PracticalTest01Var05SecondaryActivity.RESULT_VERIFY) {
                    Toast.makeText(this, "Verify was selected", Toast.LENGTH_SHORT).show();
                } else if (result == PracticalTest01Var05SecondaryActivity.RESULT_CANCEL) {
                    Toast.makeText(this, "Cancel was selected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}