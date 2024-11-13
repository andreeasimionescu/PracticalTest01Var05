package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {
    public static final String RESULT_KEY = "result_key";
    public static final int RESULT_VERIFY = 1;
    public static final int RESULT_CANCEL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        TextView textTemplate = findViewById(R.id.textTemplate);
        Intent intent = getIntent();
        String template = intent.getStringExtra("template_text");
        textTemplate.setText(template);

        // Setează butonul Verify pentru a returna un rezultat
        Button buttonVerify = findViewById(R.id.buttonVerify);
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setăm rezultatul și închidem activitatea
                Intent resultIntent = new Intent();
                resultIntent.putExtra(RESULT_KEY, RESULT_VERIFY);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Setează butonul Cancel pentru a returna un rezultat diferit
        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setăm rezultatul și închidem activitatea
                Intent resultIntent = new Intent();
                resultIntent.putExtra(RESULT_KEY, RESULT_CANCEL);
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}