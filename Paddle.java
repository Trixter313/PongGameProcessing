public class Paddle {

	int colorR = 255, colorG = 255, colorB = 255;
	float xPos, yPos, width, height;

	Paddle (App main) {
		width = (float) (main.width * .1);
		height = (float) (main.height * .03);
		yPos = (float) (main.height - height - (main.height * .01));
	}

	public void display (App main) {
		xPos = main.mouseX - width / 2;

		main.fill(colorR, colorG, colorB);
		main.rect(xPos, yPos, width, height);
	}

	public float getYpos () {
		return yPos;
	}
}
