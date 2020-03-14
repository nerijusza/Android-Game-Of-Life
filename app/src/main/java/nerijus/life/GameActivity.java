package nerijus.life;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nerijus.life.domain.Board;
import nerijus.life.domain.GameStatus;
import nerijus.life.view.DisplayOptions;
import nerijus.life.view.GameView;

public class GameActivity extends AppCompatActivity {
	private Board board;
	private Handler handler = new Handler();
	private Runnable runnable;

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

	}

	private void init() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int width = displayMetrics.widthPixels;
		int height = displayMetrics.heightPixels - 172;

		DisplayOptions displayOptions = MainActivity.displayOptions;

		board = new Board(
			width / displayOptions.getCellSize(),
			height / displayOptions.getCellSize()
		);
		GameView gameView = findViewById(R.id.gameView);
		gameView.setDisplayOptions(displayOptions);

		gameView.invalidate();
		gameView.setGameStatus(board.getGameStatus());

		runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(">>>>> 555");
				iteration();
				handler.postDelayed(this, displayOptions.getSpeedInMillis());
			}
		};
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

	@Override
	protected void onResume() {
		super.onResume();
		runnable.run();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}
}
