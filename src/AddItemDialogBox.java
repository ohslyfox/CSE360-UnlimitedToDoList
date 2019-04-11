/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Defines the Add Item Dialog Box. Used for adding
 * new items to the ListContainer.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("serial")
public class AddItemDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddItemDialogBox(ListContainer lc) {
		// FORM OPTIONS
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Add Item");
		setBounds(100, 100, 300, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// FORM ELEMENTS
		textField = new JTextField();
		textField.setBounds(10, 24, 264, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 6, 83, 14);
		contentPanel.add(lblDescription);
		{
			JLabel lblDate = new JLabel("Date:");
			lblDate.setBounds(10, 55, 83, 14);
			contentPanel.add(lblDate);
		}
		
		textField_2 = new JTextField();
		textField_2.setText("" + (lc.getSize()+1));
		textField_2.setColumns(10);
		textField_2.setBounds(186, 122, 88, 20);
		contentPanel.add(textField_2);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(186, 104, 83, 14);
		contentPanel.add(lblPriority);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox.setSelectedIndex(LocalDate.now().getMonthValue()-1);
		comboBox.setBounds(10, 73, 92, 20);
		contentPanel.add(comboBox);
		
		// Populate days combo box with appropriate numbers of
		// days given the particular year and particular month
		DefaultComboBoxModel daysModel = new DefaultComboBoxModel();
		drawDaysComboBox(daysModel, LocalDate.now().getYear(), LocalDate.now().getMonthValue());
		JComboBox comboBox_1 = new JComboBox(daysModel);
		comboBox_1.setSelectedIndex(LocalDate.now().getDayOfMonth()-1);
		comboBox_1.setBounds(112, 73, 64, 20);
		contentPanel.add(comboBox_1);
		// Populate the Year combo box with the current year up to 100 years in the future
		DefaultComboBoxModel dateModel = new DefaultComboBoxModel();
		for (int i = LocalDate.now().getYear(); i <= LocalDate.now().getYear()+100; i++) {
			dateModel.addElement(i);
		}
		JComboBox comboBox_2 = new JComboBox(dateModel);
		comboBox_2.setBounds(186, 73, 88, 20);
		contentPanel.add(comboBox_2);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 104, 83, 14);
		contentPanel.add(lblStatus);
		
		JComboBox comboBox_3 = new JComboBox(new DefaultComboBoxModel(new String[] {"Not Started", "In Progress", "Completed"}));
		comboBox_3.setBounds(10, 122, 166, 20);
		contentPanel.add(comboBox_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// OK BUTTON EVENT LISTENER
						try {
							if (textField.getText().trim().isEmpty()) {
								throw new IllegalArgumentException("Description cannot be empty.");
							}
							
							// try to add the item
							// Convert the date text to number
							Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(comboBox.getSelectedItem().toString());
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							// compile the date string
							String dateString = "" + (cal.get(Calendar.MONTH)+1) + "/" + comboBox_1.getSelectedItem().toString() + "/" + comboBox_2.getSelectedItem().toString();
							
							// check if entered date is before current date 
							/*SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
							Date d1 = sdf.parse(dateString);
							Date d2 = sdf.parse(LocalDate.now().getMonthValue() + "/" + LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getYear());
							if (d1.before(d2)) {
								throw new IllegalArgumentException("Date cannot be in the past.");
							}*/
							
							// add the item to the list container
							lc.addItem(textField.getText(), dateString, comboBox_3.getSelectedItem().toString(), Integer.parseInt(textField_2.getText()));
							setVisible(false);
							dispose();
						}
						catch (Exception exc) {
							// error message
							JOptionPane.showMessageDialog(contentPanel, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//combo box listeners for date checking/drawing
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int previousIndex = comboBox_1.getSelectedIndex();
				drawDaysComboBox(daysModel, Integer.parseInt(comboBox_2.getSelectedItem().toString()), comboBox.getSelectedIndex()+1);
				if (previousIndex < comboBox_1.getItemCount()) { 
					comboBox_1.setSelectedIndex(previousIndex);
				}
			}
		});
		
		comboBox_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int previousIndex = comboBox_1.getSelectedIndex();
				drawDaysComboBox(daysModel, Integer.parseInt(comboBox_2.getSelectedItem().toString()), comboBox.getSelectedIndex()+1);
				if (previousIndex < comboBox_1.getItemCount()) { 
					comboBox_1.setSelectedIndex(previousIndex);
				}
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawDaysComboBox(DefaultComboBoxModel cm, int yearValue, int monthValue) {
		YearMonth yearMonth = YearMonth.of(yearValue, monthValue);
		int daysInMonth = yearMonth.lengthOfMonth();
		cm.removeAllElements();
		for (int i = 0; i < daysInMonth; i++) {
			cm.addElement(i+1);
		}
	}
}
