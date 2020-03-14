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
	private DisplayOptions displayOptions;

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

	public void setDisplayOptions(DisplayOptions displayOptions) {
		this.displayOptions = displayOptions;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.GRAY);

		Paint activeCellColor = new Paint();
		activeCellColor.setColor(Color.BLACK);

		Rect rect;
		int size = displayOptions.getCellSize();

		for (GameStatus.Cell cell: gameStatus.getCells()) {
			rect = new Rect(cell.getX() * size, cell.getY() * size, cell.getX() * size + size, cell.getY() * size + size);

			if (cell.isActive()) {
				canvas.drawRect(rect, activeCellColor);
			} else {
				//canvas.drawRect(rect, paintRed);
			}
		}
	}
}
