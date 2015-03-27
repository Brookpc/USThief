import java.awt.Point;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.wrappers.Tile;

public class Teleport implements Strategy 
{

	@Override
	public boolean activate() {
		int distance;
		distance = (int) new Tile(2665, 3311).distanceTo();
		if (distance > 75) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		//Points to click in the game
		Point spellBook = new Point(743, 186);
		Point skillZone = new Point(641, 288);
		Point thievingSkill = new Point(260, 400);
		Point inventory = new Point(659,187);


		Mouse.getInstance().click(spellBook);
		Time.sleep(300);
		Mouse.getInstance().click(skillZone);
		Time.sleep(300);
		Mouse.getInstance().click(thievingSkill);
		Time.sleep(5000); //sleep while thieving animation finishes
		Mouse.getInstance().click(inventory);
		Time.sleep(300);
	}
}