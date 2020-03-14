package nerijus.life.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import nerijus.life.domain.GameState;
import nerijus.life.domain.GameStatus;

public class GameView extends View {
	private GameStatus gameStatus;
	private DisplayOptions displayOptions;
	private Rect rect = new Rect();
	Paint activeCellColor = new Paint();

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
		activeCellColor.setColor(Color.BLACK);
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setDisplayOptions(DisplayOptions displayOptions) {
		this.displayOptions = displayOptions;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int size = displayOptions.getCellSize();

		for (GameState.Cell cell: gameStatus.getGameState().getCells()) {
			rect.left = cell.getX() * size;
			rect.top = cell.getY() * size;
			rect.right = cell.getX() * size + size;
			rect.bottom = cell.getY() * size + size;

			if (cell.isActive()) canvas.drawRect(rect, activeCellColor);
		}
	}
}
