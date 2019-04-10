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
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
	 * Saves the info of every To-Do list item in a .txt file
	 * @throws IOException
	 */
	public void saveItems() throws IOException 
	{
		FileWriter fw = new FileWriter("To-Do List.txt");
		PrintWriter writer = new PrintWriter(fw);
		for(int i = 0; i < size; i++) 
		{
			ListItem currentItem = listItems.get(i);
			writer.print(currentItem.getDescription() + "-");
			writer.print(currentItem.getDate() + "-");
			writer.print(currentItem.getStatus() + "-");
			writer.println(Integer.toString(currentItem.getPriority()));
		}
		writer.flush();
		writer.close();
	}
	
	/**
	 * Loads the info from To-Do list.txt
	 * @throws IOException
	 */
	public void loadItems() throws IOException 
	{
		Scanner scanner = new Scanner(new File("To-Do List.txt"));
		listItems.clear();
		size = 0;
		
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			String[] parts = line.split("-");
			if(parts.length == 4)
			{
				addItem(parts[0], parts[1], parts[2], Integer.valueOf(parts[3]));
			}
			else
			{
				throw new IllegalArgumentException("Missing Property in 'To-Do List.txt'");
			}
		}
		scanner.close();
	}
	
	/**
	 * Returns the size of the list
	 * @return size, the size of the list
	 */
	public int getSize() {
		return this.size;
	}
}
