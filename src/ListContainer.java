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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

public class ListContainer {
	private ArrayList<ListItem> listItems;
	private int size;
	private int sortMode; // 0-1 == priority sort, 2-3 == description sort, 4-5 == date sort, 6-7 == status sort
	
	public ListContainer() {
		listItems = new ArrayList<ListItem>();
		size = 0;
		this.setSortMode(0);
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
	public void addItem(String description, Date date, String status, int priority) {
		description = description.trim();
		for(ListItem item:listItems)
		{
			if(description.equals(item.getDescription()))
			{
				throw new IllegalArgumentException("Description msut be unique");
			}
		}
		this.sortPriority(true);
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
		this.sort();
	}
	
	public void editItem(String description, Date date, String status, int priority, int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("Index out of bounds.");
		}
		
		ListItem selectedItem = this.listItems.get(index);
		this.sortPriority(true);
		if (priority < size+1 && priority > 0) {
			selectedItem.setDate(date);
			selectedItem.setDescription(description);
			selectedItem.setStatus(status);
			if (priority != selectedItem.getPriority()) {
				this.listItems.get(priority-1).setPriority(selectedItem.getPriority());
				selectedItem.setPriority(priority);
			}
		}
		else {
			throw new IllegalArgumentException("Priority must be in sequence.");
		}
		this.sort();
	}
	/**
	 * Removes the item at given index and decrements all
	 * elements above it by one.
	 * @param index, the index to remove
	 */
	public void removeItem(int index) {
		int indexPriority = listItems.get(index).getPriority()-1;
		if (index >= size || index < 0) {
			throw new IllegalArgumentException("Invalid index.");
		}
		listItems.remove(index);
		this.sortPriority(true);
		size--;
		for (int i = size-1; i >= indexPriority; i--) {
			listItems.get(i).setPriority(listItems.get(i).getPriority()-1);
		}
		this.sort();
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
			returnList[1][i] = currentItem.getDateToString();
			returnList[2][i] = currentItem.getStatus();
			returnList[3][i] = Integer.toString(currentItem.getPriority());
		}
		return returnList;
	}
	
	/**
	 * Fetches the data from a list item specified by index
	 * @param index, the list item to fetch
	 * @return returnList, the array of items
	 */
	public String[] getItem(int index) {
		ListItem itemToFetch = this.listItems.get(index);
		String[] returnList = new String[4];
		returnList[0] = itemToFetch.getDescription();
		returnList[1] = itemToFetch.getDateToString();
		returnList[2] = itemToFetch.getStatus();
		returnList[3] = Integer.toString(itemToFetch.getPriority());
		
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
		this.sortPriority(true);
		writer.println(size * 4);
		for(int i = 0; i < size; i++) 
		{
			ListItem currentItem = listItems.get(i);
			writer.println(currentItem.getDescription());
			writer.println(currentItem.getDateToString());
			writer.println(currentItem.getStatus());
			writer.println(Integer.toString(currentItem.getPriority()));
		}
		writer.flush();
		writer.close();
	}
	
	/**
	 * Loads the info from To-Do list.txt
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void loadItems() throws IOException
	{
		this.setSortMode(0);
		listItems.clear();
		size = 0;
		
		Scanner lineCount = new Scanner(new File("To-Do List.txt"));
		int count = 0;
		while(lineCount.hasNextLine())
		{
			count++;
			lineCount.nextLine();
		}
		lineCount.close();
		
		Scanner scanner = new Scanner(new File("To-Do List.txt"));
		int props = scanner.nextInt();
		if(count - 1 == props)
		{
			scanner.nextLine();
			for(int i = 0; i < props / 4; i++)
			{
				String description = scanner.nextLine();
				String date = scanner.nextLine();
				String status = scanner.nextLine();
				String priority = scanner.nextLine();
				try
				{
					addItem(description, ListItem.sdf.parse(date), status, Integer.valueOf(priority));
				}
				catch(Exception e)
				{
					//do nothing
				}
			}
		}
		else
		{
			scanner.close();
			throw new IllegalArgumentException("Missing Property in 'To-Do List.txt'");
		}
		scanner.close();
	}
	
	/**
	 * Resets the To-Do List
	 * @param order
	 */
	public void reset()
	{
		for(int i = size - 1; i > 0; i--)
		{
			listItems.remove(i);
		}
		size = 0;
	}
	
	private void sortPriority(boolean order) {
		if (order) {
			Collections.sort(listItems, new PriorityComparator());
		}
		else {
			Collections.sort(listItems, Collections.reverseOrder(new PriorityComparator()));
		}
	}
	
	private void sortDescription(boolean order) {
		if (order) {
			Collections.sort(listItems, new DescriptionComparator());
		}
		else {
			Collections.sort(listItems, Collections.reverseOrder(new DescriptionComparator()));
		}
	}
	
	private void sortDate(boolean order) {
		if (order) {
			Collections.sort(listItems, new DateComparator());
		}
		else {
			Collections.sort(listItems, Collections.reverseOrder(new DateComparator()));
		}
	}
	
	private void sortStatus(boolean order) {
		if (order) {
			Collections.sort(listItems, new StatusComparator());
		}
		else {
			Collections.sort(listItems, Collections.reverseOrder(new StatusComparator()));
		}
	}
	
	public void sort()  {
		switch (this.sortMode) {
			case 0:
				this.sortPriority(true);
				break;
			case 1:
				this.sortPriority(false);
				break;
			case 2:
				this.sortDescription(true);
				break;
			case 3:
				this.sortDescription(false);
				break;
			case 4:
				this.sortDate(true);
				break;
			case 5:
				this.sortDate(false);
				break;
			case 6:
				this.sortStatus(true);
				break;
			case 7:
				this.sortStatus(false);
				break;
			default:
				break;
		}
	}
	
	public void setSortMode(int sortMode) {
		this.sortMode = sortMode;
	}
	
	/**
	 * Returns the size of the list
	 * @return size, the size of the list
	 */
	public int getSize() {
		return this.size;
	}
	
	public int getSortMode() {
		return this.sortMode;
	}
	
	// ---------------
	// COMPARATORS FOR SORTING
	public class DescriptionComparator implements Comparator<ListItem> {
		@Override
		public int compare(ListItem l1, ListItem l2) {
			return l1.getDescription().toLowerCase().compareTo(l2.getDescription().toLowerCase());
		}
		
	}
	
	public class PriorityComparator implements Comparator<ListItem> {
		@Override
		public int compare(ListItem l1, ListItem l2) {
			int p1 = l1.getPriority();
			int p2 = l2.getPriority();
			if (p1 < p2) {
				return -1;
			}
			if (p1 == p2) {
				return 0;
			}
			else {
				return 1;
			}
		}
		
	}
	
	public class DateComparator implements Comparator<ListItem> {
		@Override
		public int compare(ListItem l1, ListItem l2) {
			return l1.getDate().compareTo(l2.getDate());
		}
		
	}
	
	public class StatusComparator implements Comparator<ListItem> {
		@Override
		public int compare(ListItem l1, ListItem l2) {
			return l1.getStatus().toLowerCase().compareTo(l2.getStatus().toLowerCase());
		}
		
	}
}