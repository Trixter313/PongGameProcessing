public class UsernameInput extends App {

	int colorR = 255, colorG = 255, colorB = 255, textColorR = 0, textColorG = 0, textColorB = 0;
	float xPos, yPos, width = 500, height = 140;

	UsernameInput (float w, float h) {
		xPos = w/2 - width/2;
		yPos = h/2 - height/2;
	}

	public void display (App main) {
		main.fill(colorR, colorG, colorB);
		main.rect(xPos, yPos, width, height);
		main.fill(textColorR, textColorG, textColorB);
		main.text("Enter a username:", xPos + 15, yPos + 50);
		// textFont(arialBold);
		main.text(main.username, xPos + 15, yPos + 100);
	}
}
