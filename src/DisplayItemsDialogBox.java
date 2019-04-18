/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Defines the Add Item Dialog Box. Used for adding
 * new items to the ListContainer.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("serial")
public class DisplayItemsDialogBox extends JDialog {

		private final JPanel contentPanel = new JPanel();
	
		/**
		 * Create the dialog.
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public DisplayItemsDialogBox(ListContainer lc) {
		// FORM OPTIONS
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("List Display");
		setBounds(100, 100, 576, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
					
		// attempt to auto-load from file
		try {
			lc.loadItems();
		}
		catch (Exception e) {
			// do nothing
		}
		String[][] items = lc.getItems();
		
		// ------------------------
		// CONSTRUCT LIST MODELS FROM
		// LIST CONTAINER
		DefaultListModel listModel = new DefaultListModel();
		for (String current : items[0]) {
			listModel.addElement(current);
		}
		
		DefaultListModel listModel1 = new DefaultListModel();
		for (String current : items[1]) {
			listModel1.addElement(current);
		}
		
		DefaultListModel listModel2 = new DefaultListModel();
		for (String current : items[2]) {
			listModel2.addElement(current);
		}
		
		DefaultListModel listModel3 = new DefaultListModel();
		for (String current : items[3]) {
			listModel3.addElement(current);
		}
		
		// ------------------------
		// CREATE AND PLACE FORM
		// ELEMENTS
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{570, 0};
		gridBagLayout.rowHeights = new int[] {312, 140, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(6);
		flowLayout.setHgap(6);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200,300));
		panel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JList list = new JList(listModel);
		list.setVisibleRowCount(0);
		list.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setOpaque(true);
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setColumnHeaderView(lblDescription);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(120,300));
		panel.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JList list_1 = new JList(listModel1);
		list_1.setVisibleRowCount(0);
		list_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		//center list text
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)list_1.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		//end center list text
		scrollPane_1.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setOpaque(true);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane_1.setColumnHeaderView(lblDate);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(120,300));
		panel.add(scrollPane_2);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JList list_2 = new JList(listModel2);
		list_2.setVisibleRowCount(0);
		list_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		//center list text
		renderer = (DefaultListCellRenderer)list_2.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		//end center list text
		scrollPane_2.setViewportView(list_2);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setOpaque(true);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane_2.setColumnHeaderView(lblStatus);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setPreferredSize(new Dimension(100,300));
		panel.add(scrollPane_3);
		JList list_3 = new JList(listModel3);
		list_3.setVisibleRowCount(0);
		list_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		//center list text
		renderer = (DefaultListCellRenderer)list_3.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		//end center list text
		scrollPane_3.setViewportView(list_3);
		list_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setOpaque(true);
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriority.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane_3.setColumnHeaderView(lblPriority);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);

		
	}
}