package com.example.pricedb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.zxing_barcode_scanner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        zxing_barcode_scanner.setOnClickListener {
            IntentIntegrator(this).initiateScan();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: ${result.formatName}, ${result.contents}", Toast.LENGTH_LONG).show();

                result.contents.let {
                    if (Regex("^(http|https)://").containsMatchIn(it)) {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        startActivity(browserIntent)
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
