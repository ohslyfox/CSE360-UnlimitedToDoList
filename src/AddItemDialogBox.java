import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddItemDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
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
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(10, 73, 264, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblDate = new JLabel("Date:");
			lblDate.setBounds(10, 55, 83, 14);
			contentPanel.add(lblDate);
		}
		
		JCheckBox chckbxComplete = new JCheckBox("Complete");
		chckbxComplete.setBounds(182, 121, 92, 23);
		contentPanel.add(chckbxComplete);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 122, 166, 20);
		contentPanel.add(textField_2);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(10, 104, 83, 14);
		contentPanel.add(lblPriority);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lc.addItem(textField.getText(), textField_1.getText(), chckbxComplete.isEnabled(), Integer.parseInt(textField_2.getText()));
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
