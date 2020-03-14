package nerijus.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nerijus.life.view.DisplayOptions;

public class MainActivity extends AppCompatActivity {

	public static DisplayOptions displayOptions = new DisplayOptions();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureGoToGameButton();
		configureCellSizeSpinner();
	}

	private void configureGoToGameButton() {
		Button enterActionButton = findViewById(R.id.goToGameButton);
		enterActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GameActivity.class));
			}
		});
	}

	private void configureCellSizeSpinner() {
		Spinner spinner = findViewById(R.id.cellSizeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
			R.array.cell_size, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();

				displayOptions.setCellSize(Integer.parseInt(String.valueOf(parent.getItemAtPosition(position))));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}
}
