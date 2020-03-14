package nerijus.life;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nerijus.life.domain.Board;
import nerijus.life.domain.GameStatus;
import nerijus.life.view.DisplayOptions;
import nerijus.life.view.GameView;

public class GameActivity extends AppCompatActivity {
	private Board board;
	private Handler handler = new Handler();
	private Runnable runnable;
	private boolean running;
	private DisplayOptions displayOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
			View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
			View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
			View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
			View.SYSTEM_UI_FLAG_FULLSCREEN |
			View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
		);

		init();
		configureBackButton();
		configureIterationSpeedChangeClicks();
	}

	private void init() {
		running = false;
		displayOptions = MainActivity.displayOptions;

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int width = displayMetrics.widthPixels;
		int height = displayMetrics.heightPixels - 172;

		board = new Board(
			width / displayOptions.getCellSize(),
			height / displayOptions.getCellSize()
		);
		GameView gameView = findViewById(R.id.gameView);
		gameView.setDisplayOptions(displayOptions);

		gameView.invalidate();
		gameView.setGameStatus(board.getGameStatus());

		TextView textView = findViewById(R.id.iterationText);
		textView.setText(String.valueOf(board.getGameStatus().getIteration()));

		runnable = new Runnable() {
			@Override
			public void run() {
				iteration();
				handler.postDelayed(this, displayOptions.getIterationTimeInMillis());
			}
		};

		configureStartStop();
		configureRestartButton();
	}

	private void iteration() {
		GameView gameView = findViewById(R.id.gameView);

		board.makeIteration();
		GameStatus gameStatus = board.getGameStatus();

		TextView textView = findViewById(R.id.iterationText);
		textView.setText(String.valueOf(gameStatus.getIteration()));

		gameView.invalidate();
		gameView.setGameStatus(gameStatus);
	}

	private void configureBackButton() {
		Button enterActionButton = findViewById(R.id.backButton);
		enterActionButton.setOnClickListener(v -> finish());
	}

	private void configureIterationSpeedChangeClicks() {
		findViewById(R.id.iterationSpeedDecrease).setOnClickListener(v -> {
			displayOptions.decreaseIterationTime();
			updateIterationTimeLabel();
		});
		findViewById(R.id.iterationSpeedIncrease).setOnClickListener(v -> {
			displayOptions.increaseIterationTime();
			updateIterationTimeLabel();
		});
	}

	private void configureRestartButton() {
		findViewById(R.id.restartButton).setOnClickListener(v -> {
			handler.removeCallbacks(runnable);
			init();
			onResume();
		});
	}

	private void configureStartStop() {
		findViewById(R.id.gameView).setOnClickListener(v -> {
			if (running) {
				handler.removeCallbacks(runnable);
				Toast.makeText(getApplicationContext(), "Evolution paused. Click to continue!", Toast.LENGTH_SHORT).show();
			} else {
				runnable.run();
			}

			running = !running;
		});
	}

	@Override
	protected void onResume() {
		updateIterationTimeLabel();
		if (running) {
			runnable.run();
		} else {
			Toast.makeText(getApplicationContext(), "Click to start evolution!", Toast.LENGTH_SHORT).show();
		}
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}

	private void updateIterationTimeLabel() {
		TextView text = findViewById(R.id.iterationTimeLabel);
		text.setText(String.valueOf(displayOptions.getIterationTimeInMillis()));
	}
}
