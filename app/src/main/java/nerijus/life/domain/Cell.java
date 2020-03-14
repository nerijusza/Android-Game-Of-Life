package nerijus.life.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Cell {
	private final int x;
	private final int y;

	private boolean status = false;
	private boolean nextStatus = false;

	private Set<Cell> neighbors = new HashSet<>();

	void addNeighbor(Cell cell) {
		neighbors.add(cell);
	}

	boolean makeActiveIfAllowed()
	{
		if (status) return false;

		status = true;

		if (getActiveNeighbors().size() > 3) {
			status = false;
			return false;
		}

		for (Cell neighbor: getActiveNeighbors()) {
			if (neighbor.isActive() && neighbor.getActiveNeighbors().size() > 3) {
				status = false;
				return false;
			}
		}

		return true;
	}

	boolean isActive() {
		return status;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setNextStatus(boolean nextStatus) {
		this.nextStatus = nextStatus;
	}

	public void makeTransition() {
		status = nextStatus;
	}

	public long activeNeighbors() {
		return neighbors.stream().filter(Cell::isActive).count();
	}

	private Set<Cell> getActiveNeighbors() {
		return neighbors.stream()
			.filter(Cell::isActive)
			.collect(Collectors.toSet());
	}
}
