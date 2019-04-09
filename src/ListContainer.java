
import java.util.ArrayList;

public class ListContainer {
	private ArrayList<ListItem> listItems;
	private int size;
	
	public ListContainer() {
		listItems = new ArrayList<ListItem>();
		size = 0;
	}
	
	public void addItem(String description, String date, String status, int priority) {
		if (priority == size+1) {
			size++;
			listItems.add(new ListItem(description, date, status, priority));
		}
		else if (priority < size && priority > 0) {
			//dateList.
			size++;
			listItems.add(new ListItem(description, date, status, priority));
			
			for (int i = size-1; i >= priority; i--) {
				ListItem temp = listItems.get(i-1);
				temp.setPriority(temp.getPriority()+1);
				listItems.set(i-1, listItems.get(i));
				listItems.set(i, temp);
			}
		}
		else {
			throw new IllegalArgumentException("Priority must be in sequence.");
		}
	}
	
	public void removeItem(int index) {
		if (index >= size || index < 0) {
			throw new IllegalArgumentException("Invalid index.");
		}
		listItems.remove(index);
		size--;
		for (int i = size-1; i >= index; i--) {
			listItems.get(i).setPriority(listItems.get(i).getPriority()-1);
		}
		
	}
	
	public String[][] getItems() {
		String[][] returnList = new String[4][size];
		for (int i = 0; i < size; i++) {
			ListItem currentItem = listItems.get(i);
			returnList[0][i] = currentItem.getDescription();
			returnList[1][i] = currentItem.getDate();
			returnList[2][i] = currentItem.getStatus();
			returnList[3][i] = Integer.toString(currentItem.getPriority());
		}
		return returnList;
	}
	
	public int getSize() {
		return this.size;
	}
}
