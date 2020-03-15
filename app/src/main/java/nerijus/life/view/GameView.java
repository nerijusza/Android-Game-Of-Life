package nerijus.life.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import nerijus.life.domain.GameState;
import nerijus.life.domain.GameStatus;

public class GameView extends View {
	private GameStatus gameStatus;
	private DisplayOptions displayOptions;
	private Rect rect = new Rect();
	Paint baseColor = new Paint();
	Paint oldColor = new Paint();
	Paint newColor = new Paint();

	List<GameState.Cell> previousGenerationCells;

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
		baseColor.setColor(Color.BLACK);
		oldColor.setColor(0xFFD7D7D7);
		newColor.setColor(0xFF215124);
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setDisplayOptions(DisplayOptions displayOptions) {
		this.displayOptions = displayOptions;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (displayOptions.isManualIteration()) {
			drawWithNewOldCellHighlighted(canvas);
		} else {
			drawBasicBoard(canvas);
		}
	}

	private void drawBasicBoard(Canvas canvas) {
		int size = displayOptions.getCellSize();

		for (GameState.Cell cell: gameStatus.getGameState().getCells()) {
			rect.left = cell.getX() * size;
			rect.top = cell.getY() * size;
			rect.right = cell.getX() * size + size;
			rect.bottom = cell.getY() * size + size;

			if (cell.isActive()) canvas.drawRect(rect, baseColor);
		}
	}

	private void drawWithNewOldCellHighlighted(Canvas canvas) {
		int size = displayOptions.getCellSize();
		List<GameState.Cell> cells = gameStatus.getGameState().getCells();
		if (previousGenerationCells == null || gameStatus.getIteration() == 1) {
			previousGenerationCells = cells;
		}

		for (int i = 0; i < cells.size(); i++) {
			GameState.Cell cell = cells.get(i);
			// for performance do not check cell position
			GameState.Cell previousGenerationCell = previousGenerationCells.get(i);

			rect.left = cell.getX() * size;
			rect.top = cell.getY() * size;
			rect.right = cell.getX() * size + size;
			rect.bottom = cell.getY() * size + size;

			if (cell.isActive() && previousGenerationCell.isActive()) {
				canvas.drawRect(rect, baseColor);
			} else {
				if (cell.isActive()) canvas.drawRect(rect, newColor);
				if (previousGenerationCell.isActive()) canvas.drawRect(rect, oldColor);
			}
		}

		previousGenerationCells = cells;
	}
}
