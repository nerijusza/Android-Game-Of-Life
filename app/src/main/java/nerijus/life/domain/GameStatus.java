package nerijus.life.domain;

import java.util.List;

import lombok.Value;

@Value
public class GameStatus {
	int sizeX;
	int sizeY;
	List<Cell> cells;

	@Value
	public static class Cell {
		int x;
		int y;
		boolean active;
	}
}
