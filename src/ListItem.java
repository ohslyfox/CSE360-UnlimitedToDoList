/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Defines an object to contain the four parts
 * of a to-do list item: description, date, status,
 * and priority. Used to store a single to-do list item.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListItem {
	public static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private String description;
	private Date date;
	private String status;
	private int priority;
	
	public ListItem(String description, Date date, String status, int priority) {
		setDescription(description);
		setDate(date);
		setStatus(status);
		setPriority(priority);
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getDateToString() {
		return sdf.format(this.date);
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getPriority() {
		return this.priority;
	}
}
