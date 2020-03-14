package nerijus.life.view;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class DisplayOptions {
	private int cellSize = 50;
	private int iterationTimeInMillis = 500;

	private static final List<Integer> cellSizes = Arrays.asList(10, 20, 30, 50, 75, 100, 150, 200, 250, 300);
	private static final List<Integer> possibleIterationTime = Arrays.asList(0, 10, 25, 50, 100, 200, 300, 400, 500, 750, 1000);

	public void increaseCellSize() {
		cellSize = cellSizes.stream()
			.filter(size -> size > cellSize)
			.min(Integer::compareTo)
			.orElse(cellSize);
	}

	public void decreaseCellSize() {
		cellSize = cellSizes.stream()
			.filter(size -> size < cellSize)
			.max(Integer::compareTo)
			.orElse(cellSize);
	}

	public void increaseIterationTime() {
		iterationTimeInMillis = possibleIterationTime.stream()
			.filter(iterationTime -> iterationTime > iterationTimeInMillis)
			.min(Integer::compareTo)
			.orElse(iterationTimeInMillis);
	}

	public void decreaseIterationTime() {
		iterationTimeInMillis = possibleIterationTime.stream()
			.filter(iterationTime -> iterationTime < iterationTimeInMillis)
			.max(Integer::compareTo)
			.orElse(iterationTimeInMillis);
	}
}
