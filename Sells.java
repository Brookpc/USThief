
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class Sells implements Strategy {

	//Item ID's {Chocolate Cake, bread, cake, silk, fur, silver pot, spice, sapphire, emerald, ruby, diamond }
	private static final int[] ITEM_IDS = {1902,2310,1892,951,959,4659,2008,1638,1640,1642,1644};

	@Override
	public boolean activate() {
		if (Inventory.isFull()) {
			return true;
		}
		return false;
	}

	public void execute() {
		Npc[] store = Npcs.getNearest(2270);
		if (Game.getOpenInterfaceId() != 3824 && Inventory.isFull()) {
			if (store != null) {
				try{
				store[0].interact(2);
				} catch (ArrayIndexOutOfBoundsException e) {
					//check
				}
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {                       
						return Game.getOpenInterfaceId() == 3824;
					}

				}, 2000);
			}
		}
		if (Game.getOpenInterfaceId() == 3824 && Inventory.isFull()) {
			if(ITEM_IDS != null) {
				for (final int item : ITEM_IDS) {
					try {
						Menu.sendAction(53, item - 1, getInventorySlot(item), 3823, 2);
						Time.sleep(1000);

					} catch(Exception e) {
						//catch null pointer exceptions
					}      
				}

				Menu.sendAction(200,0,3902,509,671,1);
			}
		}
	}

	private static int getInventorySlot(int item) {
		org.rev317.min.api.wrappers.Item[] items = Inventory.getItems(item);
		if (items != null) {
			return items[0].getSlot();
		}
		return 0;
	}
}
