public class HighScores {
	float width = 270, height, xPos, yPos;
	boolean displayHighScores = false;

	HighScores (Paddle paddle) {
		yPos = paddle.yPos - 100;
	}

	public void display (App main, Paddle paddle) {
		xPos = paddle.xPos + paddle.width + 10;
		main.fill(255, 255, 255);
		main.text("Press H for high scores", main.width - 375, main.height - main.margin);

		if (displayHighScores) {
			main.rect(xPos, yPos - 50 * main.highScoresList.size(), width, 40 + 50 * main.highScoresList.size());
			main.fill(0, 0, 0);
			main.textSize(32);
			String highScoreText = "";
			for (String score : main.highScoresList) {
				highScoreText += score;
				highScoreText += "\n";
			}
			main.text("High scores: ", xPos + 15, yPos + 35 - 50 * main.highScoresList.size());
			main.text(highScoreText, xPos + 15, yPos + 80 - 50 * main.highScoresList.size());
		}
	}
}
