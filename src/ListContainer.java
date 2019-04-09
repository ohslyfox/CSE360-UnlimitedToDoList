/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Contains an ArrayList of ListItems which can be
 * used for storing and displaying an instance of an
 * unlimited to-do list.
 */
import java.util.ArrayList;

public class ListContainer {
	private ArrayList<ListItem> listItems;
	private int size;
	
	public ListContainer() {
		listItems = new ArrayList<ListItem>();
		size = 0;
	}
	
	/**
	 * Adds an item to the list of items. If the priority is one greater than
	 * the last the item is simply added to the list. If the priority is less than
	 * one greater than the last the item will be inserted and all elements
	 * above it will increment the priority by one.
	 * @param description, the description of the item
	 * @param date, the date of the item
	 * @param status, the status of the item
	 * @param priority, the priority of the item
	 */
	public void addItem(String description, String date, String status, int priority) {
		if (priority == size+1) {
			size++;
			listItems.add(new ListItem(description, date, status, priority));
		}
		else if (priority < size && priority > 0) {
			//dateList.
			size++;
			listItems.add(new ListItem(description, date, status, priority));
			
			// Increment all items >= priority by one
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
	
	/**
	 * Removes the item at given index and decrements all
	 * elements above it by one.
	 * @param index, the index to remove
	 */
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
	
	/**
	 * Compiles the List Items into individual strings and returns them
	 * @return returnList, the list of items
	 */
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
	
	/**
	 * Returns the size of the list
	 * @return size, the size of the list
	 */
	public int getSize() {
		return this.size;
	}
}
