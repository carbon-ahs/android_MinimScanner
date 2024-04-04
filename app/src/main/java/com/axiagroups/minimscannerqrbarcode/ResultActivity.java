package com.axiagroups.minimscannerqrbarcode;

import android.app.Activity;
import android.content.Context;
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

public class ResultActivity extends AppCompatActivity {
    private String scannedText;
    private TextView scannedTextTV;

    private Button copyBtn, scanAgainBtn, exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        scannedText = intent.getStringExtra("scannedText");

        scannedTextTV = findViewById(R.id.scannedTextTV);
        scannedTextTV.setText(scannedText);

        scanAgainBtn = findViewById(R.id.scanAgainBtn);
        scanAgainBtn.setOnClickListener(v -> {
            Intent intent12 = new Intent(ResultActivity.this, ScanActivity.class);
            startActivity(intent12);
        });

        copyBtn = findViewById(R.id.copyBtn);
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard(getApplicationContext(), scannedText);

            }
        });

        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_MAIN);
            intent1.addCategory(Intent.CATEGORY_HOME);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        });

    }

    public boolean copyToClipboard(Context context, String text) {
        try {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
                        .getSystemService(context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context
                        .getSystemService(context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
            }

            Toast.makeText(context, "Text Copied!", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}