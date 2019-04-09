import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ComboBoxModel;

public class AddItemDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Create the dialog.
	 */
	public AddItemDialogBox(ListContainer lc) {
		setResizable(false);
		setTitle("Add Item");
		setBounds(100, 100, 300, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_1.setSelectedIndex(LocalDate.now().getDayOfMonth()-1);
		comboBox_1.setBounds(112, 73, 64, 20);
		contentPanel.add(comboBox_1);
		
		DefaultComboBoxModel dateModel = new DefaultComboBoxModel();
		for (int i = LocalDate.now().getYear(); i > 1900 ; i--) {
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
						try {
							// try to add the item
							Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(comboBox.getSelectedItem().toString());
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							String dateString = "" + (cal.get(Calendar.MONTH)+1) + "/" + comboBox_1.getSelectedItem().toString() + "/" + comboBox_2.getSelectedItem().toString();
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
	}
}
