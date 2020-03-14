package nerijus.life;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import nerijus.life.domain.Board;
import nerijus.life.view.DisplayOptions;
import nerijus.life.view.GameView;

public class GameActivity extends AppCompatActivity {

	private DisplayOptions displayOptions;
	private Handler handler = new Handler();
	private Board board;

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

		displayOptions = new DisplayOptions();
		displayOptions.setCellSize(50);

		board = new Board(
			width / displayOptions.getCellSize(),
			height / displayOptions.getCellSize()
		);
		GameView gameView = findViewById(R.id.gameView);
		gameView.setDisplayOptions(displayOptions);

		gameView.invalidate();
		gameView.setGameStatus(board.getStatus());
	}

	private void iteration() {
		GameView gameView = findViewById(R.id.gameView);

		board.makeIteration();

		gameView.invalidate();
		gameView.setGameStatus(board.getStatus());
	}

	@Override
	protected void onResume() {
		super.onResume();

		Runnable aa = new Runnable() {
			@Override
			public void run() {
				System.out.println(">>>>> 555");
				iteration();
				handler.postDelayed(this, 100);
			}
		};

		handler.postDelayed(aa, 300);
	}

	private void configureBackButton() {
		Button enterActionButton = findViewById(R.id.backButton);
		enterActionButton.setOnClickListener(v -> {

			GameView gameView = findViewById(R.id.gameView);

			System.out.println("5 >>> " + gameView.getLayoutParams().width);
			System.out.println("5 >>> " + gameView.getLayoutParams().height);
			System.out.println("5 >>> " + gameView.getHeight());
			System.out.println("5 >>> " + gameView.getWidth());

			finish();
		});
	}
}
