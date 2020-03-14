package nerijus.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nerijus.life.view.DisplayOptions;

public class MainActivity extends AppCompatActivity {

	public static DisplayOptions displayOptions = new DisplayOptions();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureGoToGameButton();
		configureCellSizeChanges();
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

	@Override
	protected void onStart() {
		updateCellSizeLabel();
		updateIterationTimeLabel();

		super.onStart();
	}

	private void configureCellSizeChanges() {
		findViewById(R.id.cellSizeDecrease).setOnClickListener(v -> {
			displayOptions.decreaseCellSize();
			updateCellSizeLabel();
		});

		findViewById(R.id.cellSizeIncrease).setOnClickListener(v -> {
			displayOptions.increaseCellSize();
			updateCellSizeLabel();
		});

		findViewById(R.id.iterationTimeDecrease).setOnClickListener(v -> {
			displayOptions.decreaseIterationTime();
			updateIterationTimeLabel();
		});

		findViewById(R.id.iterationTimeIncrease).setOnClickListener(v -> {
			displayOptions.increaseIterationTime();
			updateIterationTimeLabel();
		});
	}

	private void updateCellSizeLabel() {
		TextView text = findViewById(R.id.cellSizeLabel);
		text.setText(String.valueOf(displayOptions.getCellSize()));
	}

	private void updateIterationTimeLabel() {
		TextView text = findViewById(R.id.iterationTimeLabel);
		text.setText(String.valueOf(displayOptions.getIterationTimeInMillis()));
	}
}
