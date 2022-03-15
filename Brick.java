public class Brick {
	
	int color;
	float xPos, yPos;
	static float width, spaceWidth;
	float height = 40f;

	// Constructor
	Brick (int tempColor, float tempXpos, float tempYpos, App main) { 
		color = tempColor;
    xPos = tempXpos;
		yPos = tempYpos;
		width = (float) (main.width * .75 / main.bricksPerRow);
		height = (float) (main.height * .03);
		spaceWidth = (float) (main.width * .25 / (main.bricksPerRow + 1));
  }

	public void display (App main) {
		main.fill(color);
		main.rect(xPos, yPos, width, height);
	}
	
	public float getXpos () {
		return xPos;
	}

	public float getYpos () {
		return yPos;
	}

	public void scheduleDeletion (App main) {
		main.indexToRemove = main.bricks.indexOf(this);
	}
}
