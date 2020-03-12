package nerijus.life.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
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

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.RED);

		Rect rect = new Rect(200, 200, 300, 300);

		Paint paint = new Paint();
		paint.setColor(Color.GREEN);

		canvas.drawRect(rect, paint);
	}
}
