
import java.util.ArrayList;

public class ListContainer {
	private ArrayList<ListItem> listItems;
	private int size;
	
	public ListContainer() {
		listItems = new ArrayList<ListItem>();
		size = 0;
	}
	
	public void addItem(String description, String date, boolean status, int priority) {
		if (priority == size+1) {
			size++;
			listItems.add(new ListItem(description, date, status, priority));
		}
		else if (priority < size) {
			//dateList.
		}
		else {
			
		}
	}
	
	public String[][] getItems() {
		String[][] returnList = new String[4][size];
		for (int i = 0; i < size; i++) {
			ListItem currentItem = listItems.get(i);
			returnList[0][i] = currentItem.getDescription();
			returnList[1][i] = currentItem.getDate();
			returnList[2][i] = Boolean.toString(currentItem.getStatus());
			returnList[3][i] = Integer.toString(currentItem.getPriority());
		}
		return returnList;
	}
	
	public int getSize() {
		return this.size;
	}
}
