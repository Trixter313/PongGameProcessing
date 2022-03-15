public class Ball {

	int color;
	float xVelocity = 6, yVelocity = 7, xVelocityModifier = 0.015f, yVelocityModifier = 0.015f, radius, diameter, xPos,
			yPos;

	// Constructor
	Ball(int tempColor, int ballSize, float tempXpos, float tempYpos, App main) {
		color = tempColor;
		xPos = tempXpos;
		yPos = tempYpos;
		if (ballSize == 1) {
			radius = (float) (main.height * .01);
			diameter = radius * 2;
		} else if (ballSize == 2) {
			radius = (float) (main.height * .02);
			diameter = radius * 2;
		} else if (ballSize == 3) {
			radius = (float) (main.height * .03);
			diameter = radius * 2;
		}
	}

	public void display(App main, Paddle paddle) {
		main.fill(color);
		main.ellipse(xPos, yPos, diameter, diameter);
		xPos += xVelocity;
		yPos += yVelocity;

		// For wall collision
		if (yPos - radius <= 0) {
			yVelocity *= -1;
		} else if (xPos - radius <= 0 || xPos + radius >= main.width) {
			xVelocity *= -1;
		} else if (yPos + radius >= main.height) {
			main.isGameOver = true;
			main.highScoresList.add(main.username + " - " + main.score);
			// yVelocity *= -1;
		}

		// For paddle collision
		if (xPos >= main.mouseX - paddle.width / 2 && xPos <= main.mouseX + paddle.width / 2
				&& yPos + radius >= paddle.getYpos()) {
			yVelocity *= -1;
		}

		// For brick collision
		for (Brick brick : main.bricks) {
			if (xPos + radius >= brick.getXpos() && xPos - radius <= brick.getXpos() + Brick.width
					&& yPos + radius >= brick.getYpos() && yPos - radius <= brick.getYpos() + brick.height) {
				if (xPos + radius - 10 < brick.getXpos() || xPos - radius + 10 > brick.getXpos() + Brick.width) {
					xVelocity *= -1 - xVelocityModifier;
					yVelocity *= 1 + yVelocityModifier;
				} else {
					xVelocity *= 1 + xVelocityModifier;
					yVelocity *= -1 - yVelocityModifier;
				}
				brick.scheduleDeletion(main);
				main.brickNeedsDeletion = true;
				// For score tracking
				if (brick.getYpos() == 600) {
					main.score += 1;
				} else if (brick.getYpos() == 500) {
					main.score += 2;
				} else if (brick.getYpos() == 400) {
					main.score += 3;
				} else if (brick.getYpos() == 300) {
					main.score += 4;
				} else if (brick.getYpos() == 200) {
					main.score += 5;
				}
			}
		}
	}

	public void setPos(float x, float y) {
		xPos = x;
		yPos = y;
	}

	public void setVelocity(float tempXvelocity, float tempYvelocity) {
		xVelocity = tempXvelocity;
		yVelocity = tempYvelocity;
	}
}
