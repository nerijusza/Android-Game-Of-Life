package nerijus.life;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import nerijus.life.domain.Board;
import nerijus.life.view.GameView;

public class GameActivity extends AppCompatActivity {

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

		configureBackButton();

		GameView gameView = (GameView) findViewById(R.id.gameView);
		gameView.setGameStatus(new Board(70, 120).getStatus());
	}

	private void configureBackButton() {
		Button enterActionButton = findViewById(R.id.backButton);
		enterActionButton.setOnClickListener(v -> finish());
	}
}
