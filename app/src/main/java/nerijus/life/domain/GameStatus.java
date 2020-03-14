package nerijus.life.domain;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
	private List<GameState> lastStates = new ArrayList<>();
	private int iteration = 0;
	private boolean finished = false;

	void addGameState(GameState gameState) {
		if (!finished) {
			if (lastStates.contains(gameState)) {
				finished = true;
			} else {
				iteration++;
			}
		}

		if (lastStates.size() > 2) lastStates.set(2, lastStates.get(1));
		if (lastStates.size() > 1) lastStates.set(1, lastStates.get(0));
		lastStates.add(0, gameState);
	}

	public int getIteration() {
		return iteration;
	}

	public boolean isFinished() {
		return finished;
	}

	public GameState getGameState() {
		return lastStates.get(0);
	}
}
