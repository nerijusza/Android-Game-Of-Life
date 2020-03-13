package nerijus.life.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Board {
	private final int sizeX;
	private final int sizeY;

	private List<Cell> cells = new ArrayList<>();

	public Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		createCells();
		addNeighbors();
		initialiseActiveCells();
	}

	public double getFillPercentage() {
		return cells.stream().filter(Cell::isActive).count() / ((double) sizeX * sizeY) * 100;
	}

	public GameStatus getStatus() {
		return new GameStatus(
			sizeX,
			sizeY,
			cells.stream()
				.map(cell -> new GameStatus.Cell(cell.getX(), cell.getY(), cell.isActive()))
				.collect(Collectors.toList())
		);
	}

	private void createCells() {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				cells.add(new Cell(x, y));
			}
		}
	}

	private void addNeighbors() {
		int index = 0;
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {

				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {

						int keyX = x + i;
						int keyY = y + j;

						if ((x != keyX || y != keyY) && keyX >= 0 && keyX < sizeX && keyY >= 0 && keyY < sizeY) {
							cells.get(index).addNeighbor(get(keyX, keyY));
						}
					}
				}
				index++;
			}
		}
	}

	private void initialiseActiveCells() {
		int iterationWithoutChange = 0;
		Random random = new Random();
		int randomX, randomY;

		do {
			randomX = random.nextInt(sizeX);
			randomY = random.nextInt(sizeY);
			if (!get(randomX, randomY).makeActiveIfAllowed()) {
				iterationWithoutChange++;
			}
		} while (iterationWithoutChange < 1000);
	}

	private Cell get(int x, int y) {
		return cells.get(sizeX * y + x);
	}
}