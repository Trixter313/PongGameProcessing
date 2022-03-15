public class Scoreboard {

	float xPos, yPos, width = 210f, height = 50f;
	String scoreText;

	public void display (App main) {
		main.fill(255, 255, 255);
		main.rect(xPos, yPos, width, height);

		if (!main.isGameOver) {
			if (!main.isPaused) {
				xPos = main.margin;
				yPos = main.height - height - main.margin;
			} else {
				xPos = main.width/2 - (width/2);
				yPos = main.height/2 - (height/2);
				height = 100f;
				main.textSize(45);
				main.fill(0, 0, 0);
				main.text("PAUSED", xPos + 15, yPos + 80);
			}
		} else {
			xPos = main.width/2 - (width/2);
			yPos = main.height/2 - (height/2);
		}

		main.fill(0, 0, 0);
		main.textSize(32);
		scoreText = "Score: " + main.score;
		main.text(scoreText, xPos + 15, yPos + 35);
	}
}
