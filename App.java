import java.util.ArrayList;
import processing.core.PApplet;

public class App extends PApplet {

	// Declaration
	int score = 0, bricksPerRow = 15, margin = (int) (height * .1), ySpacer = 100, indexToRemove;
	float xSpacer;
	UsernameInput usernameInput;
	String username = "";
	Ball ball;
	Paddle paddle;
	Scoreboard scoreboard;
	HighScores highScores;
	boolean isGameOver = false, isFirstRun = true, hasGameStarted = false, brickNeedsDeletion = false, isPaused = false;
	ArrayList<String> highScoresList = new ArrayList<String>();
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	public void settings () {
		size(3000,2000);
	}

	public void setup () {
		noCursor();
		// fullScreen();
		surface.setTitle("Breakout");
		surface.setResizable(true);
		// Initialization
		ball = new Ball(color(255, 64, 129), 3, (float) ((Math.random() * (width - 100)) + 50), 800, this);
		paddle = new Paddle(this);
		scoreboard = new Scoreboard();
		highScores = new HighScores(paddle);
		usernameInput = new UsernameInput(width, height);
		addBricks();
		reset();
	}

	public void draw () {
		background(50, 100, 200);
		scoreboard.display(this);

		if (!hasGameStarted) {
			usernameInput.display(this);
		}

		if (hasGameStarted) {
			highScores.display(this, paddle);
			fill(255,255,255);
			text("Press P to pause", width - 375, height - margin - 90);
			text("Press R to restart", width - 375, height - margin - 45);
		}

		for (Brick brick : bricks) {
			brick.display(this);
		}

		if (!isGameOver && hasGameStarted && !isPaused) {
			ball.display(this, paddle);
			paddle.display(this);
		} else if (isGameOver) {
			isFirstRun = false;
		}

		// Remove hit brick (Done here instead of in Brick deletion method to avoid ConcurrentModificationException)
		if (brickNeedsDeletion) {
			bricks.remove(indexToRemove);
			brickNeedsDeletion = false;
		}

		// Check if all bricks are destroyed & end game if so
		if (bricks.isEmpty()) {
			highScoresList.add(username + " - " + score);
			isGameOver = true;
		}
	}

	public void keyPressed() {
		if (hasGameStarted && key == 'H' || key == 'h') {
			highScores.displayHighScores = !highScores.displayHighScores;
		}

		if (hasGameStarted && key == 'R' || key == 'r') {
			reset();
		}

		if (!isGameOver && key == 'P' || key == 'p') {
			isPaused = !isPaused;
		}

		if (!hasGameStarted) {
			if (key == BACKSPACE) {
				if (username.length() > 0) {
					username = username.substring(0, username.length() - 1);
				}
			} else if (key == ENTER) {
				hasGameStarted = true;
				reset();
			} else {
				username = username + key;
			}
		}
	}
	public static void main(String[] args) {
		PApplet.main("App");
	}

	public void reset() {
		bricks.clear();
		addBricks();
		score = 0;
		ball.setVelocity(6, 7);
		ball.setPos((float) ((Math.random() * (width - (2 * ball.radius))) + ball.radius), 800);
		isGameOver = false;
	}

	public void addBricks () {
		xSpacer = Brick.spaceWidth;
		for (int j = 0; j < bricksPerRow; j++) {
			bricks.add(new Brick(color(103, 58, 183), xSpacer, 200, this));
			bricks.add(new Brick(color(33, 150, 243), xSpacer, 300, this));
			bricks.add(new Brick(color(76, 175, 80), xSpacer, 400, this));
			bricks.add(new Brick(color(239, 108, 0), xSpacer, 500, this));
			bricks.add(new Brick(color(211, 47, 47), xSpacer, 600, this));
			xSpacer += Brick.width + Brick.spaceWidth;
		}
	}
}
