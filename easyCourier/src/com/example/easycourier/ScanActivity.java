package com.example.easycourier;

import android.app.Activity;
import android.os.Bundle;

public class ScanActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		getActionBar().setDisplayShowHomeEnabled(false);
    }
}
