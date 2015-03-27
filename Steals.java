
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

public class Steals implements Strategy {

	@Override
	public boolean activate() {
		if (!Inventory.isFull()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		int curLvl;
		Tile safe = new Tile(2660,3316);
		SceneObject stall = null;
		int stallActCode = 0;
		curLvl = Skill.THIEVING.getRealLevel();

		if (Main.powerT == true) {
			if( curLvl < 20 ) {
				stall = SceneObjects.getClosest(1616);
				stallActCode = 34384;
			} // Bread
			if( curLvl >= 20 && curLvl < 35 ) {
				stall = SceneObjects.getClosest(1615);
				stallActCode = 34383;
			} // Silk
			if( curLvl >= 35 && curLvl < 50 ) {
				stall = SceneObjects.getClosest(1619);
				stallActCode = 34387;
			} // Fur
			if( curLvl >= 50 && curLvl < 65 ) {
				stall = SceneObjects.getClosest(1614);
				stallActCode = 34382;
			} // Silver
			if( curLvl >= 65 && curLvl < 75 ) {
				stall = SceneObjects.getClosest(1618);
				stallActCode = 34386;
			} // Spice
			if( curLvl >= 75 ) {
				stall = SceneObjects.getClosest(1617);
				stallActCode = 34385;
			} // Gems
		} else {
			stall = SceneObjects.getClosest(Main.stallID);
			stallActCode = Main.actID;
		}

		if (stall != null && Players.getMyPlayer().getAnimation() == -1) {
			if(stallActCode == 34382) {
				try{

					safe.walkTo();
					Time.sleep(3000);
					Menu.sendAction(900, stall.getHash(), stall.getLocalRegionX(),
							stall.getLocalRegionY(),stallActCode, 3);
					Time.sleep(200);
				} catch (Exception a) {

				}
			}
			if(stallActCode != 34382) {
				try{
					Menu.sendAction(900, stall.getHash(), stall.getLocalRegionX(),
							stall.getLocalRegionY(),stallActCode, 3);
					Time.sleep(200);
				} catch (Exception b) {

				}
			}
		}

	}

}
