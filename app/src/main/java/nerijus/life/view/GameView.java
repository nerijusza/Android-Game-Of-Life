package nerijus.life.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import nerijus.life.domain.GameStatus;

public class GameView extends View {
	private GameStatus gameStatus;

	public GameView(Context context) {
		super(context);

		init(null);
	}

	public GameView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		init(attrs);
	}

	public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init(attrs);
	}

	public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);

		init(attrs);
	}

	private void init(@Nullable AttributeSet attributeSet) {

	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.GRAY);

		Paint paintGreen = new Paint();
		paintGreen.setColor(Color.GREEN);

		Paint paintRed = new Paint();
		paintGreen.setColor(Color.RED);

		Rect rect;
		int size = 10;

		for (GameStatus.Cell cell: gameStatus.getCells()) {
			rect = new Rect(cell.getX() * size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);

			if (cell.isActive()) {
				canvas.drawRect(rect, paintGreen);
			} else {
				canvas.drawRect(rect, paintRed);
			}
		}
	}
}
