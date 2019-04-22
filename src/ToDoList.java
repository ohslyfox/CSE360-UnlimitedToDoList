/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Defines the entry point of the program and opens
 * the Unlimited To-Do List form. The application implements
 * an unlimited to-do list that can be manipulated through the GUI.
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ToDoList {

	private JFrame frmUnlimitedTodoList;
	private ListContainer lc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoList window = new ToDoList();
					window.frmUnlimitedTodoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ToDoList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmUnlimitedTodoList = new JFrame();
		frmUnlimitedTodoList.setResizable(false);
		frmUnlimitedTodoList.setTitle("Unlimited To-Do List");
		frmUnlimitedTodoList.setBounds(100, 100, 576, 510);
		frmUnlimitedTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ------------------------
		// CONSTRUCT LIST CONTAINER
		// list container
		lc = new ListContainer();
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
		gridBagLayout.rowHeights = new int[] {312, 150, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frmUnlimitedTodoList.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(6);
		flowLayout.setHgap(6);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frmUnlimitedTodoList.getContentPane().add(panel, gbc_panel);
		
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
		frmUnlimitedTodoList.getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Options");
		label.setForeground(Color.BLACK);
		label.setBounds(14, 4, 76, 26);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(6, 0, 556, 144);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(6, 30, 543, 81);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 6, 6));
		
		JButton btnAddItem = new JButton("Add Item");
		panel_3.add(btnAddItem);
		
		JButton btnSave = new JButton("Save");
		panel_3.add(btnSave);
		
		JButton btnEditItem = new JButton("Edit Item");
		panel_3.add(btnEditItem);
		
		JButton btnLoad = new JButton("Load");
		panel_3.add(btnLoad);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		panel_3.add(btnRemoveItem);
		
		JButton btnReset = new JButton("Reset");
		panel_3.add(btnReset);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 116, 542, 22);
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnReport = new JButton("Report");
		panel_4.add(btnReport);
		
		//Report Dialog Box
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					ReportDialogBox dialog = new ReportDialogBox(lc);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					// disable buttons
					btnAddItem.setEnabled(false);
					btnRemoveItem.setEnabled(false);
					btnEditItem.setEnabled(false);
					btnReport.setEnabled(false);
					btnSave.setEnabled(false);
					btnLoad.setEnabled(false);
					btnReset.setEnabled(false);
					
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent evt) {
							// re enable buttons
							btnAddItem.setEnabled(true);
							btnRemoveItem.setEnabled(true);
							btnEditItem.setEnabled(true);
							btnReport.setEnabled(true);
							btnSave.setEnabled(true);
							btnLoad.setEnabled(true);
							btnReset.setEnabled(true);
						}
					});
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Reset List
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					lc.reset();
					drawList(listModel, listModel1, listModel2, listModel3);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// ----------------------
		// DEFINE EVENT LISTENERS
		
		
		// button listeners
		// Remove Item Button
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selected = list.getSelectedIndex();
					lc.removeItem(selected);
					drawList(listModel, listModel1, listModel2, listModel3);
					list.setSelectedIndex(selected);
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: an item must be selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Add Item Button
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int n = lc.getSize();
					AddItemDialogBox dialog = new AddItemDialogBox(lc);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					// disable buttons
					btnAddItem.setEnabled(false);
					btnRemoveItem.setEnabled(false);
					btnEditItem.setEnabled(false);
					btnReport.setEnabled(false);
					btnSave.setEnabled(false);
					btnLoad.setEnabled(false);
					btnReset.setEnabled(false);
					
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent evt) {
							if (n < lc.getSize()) {
								String[] newItem = lc.getItem(n);
								listModel.addElement(newItem[0]);
								listModel1.addElement(newItem[1]);
								listModel2.addElement(newItem[2]);
								listModel3.addElement(newItem[3]);
								drawList(listModel, listModel1, listModel2, listModel3);
							}
							// re enable buttons
							btnAddItem.setEnabled(true);
							btnRemoveItem.setEnabled(true);
							btnEditItem.setEnabled(true);
							btnReport.setEnabled(true);
							btnSave.setEnabled(true);
							btnLoad.setEnabled(true);
							btnReset.setEnabled(true);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//Save Item Button
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					lc.saveItems();
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Load Item Button
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					lblDescription.setBackground(SystemColor.control);
					lblDate.setBackground(SystemColor.control);
					lblStatus.setBackground(SystemColor.control);
					lblPriority.setBackground(SystemColor.control);
					lc.loadItems();
					drawList(listModel, listModel1, listModel2, listModel3);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//edit item
		btnEditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					EditItemDialogBox dialog = new EditItemDialogBox(lc, list.getSelectedIndex());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					// disable buttons
					btnAddItem.setEnabled(false);
					btnRemoveItem.setEnabled(false);
					btnEditItem.setEnabled(false);
					btnReport.setEnabled(false);
					btnSave.setEnabled(false);
					btnLoad.setEnabled(false);
					btnReset.setEnabled(false);
					
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent evt) {
							drawList(listModel, listModel1, listModel2, listModel3);
							// re enable buttons
							btnAddItem.setEnabled(true);
							btnRemoveItem.setEnabled(true);
							btnEditItem.setEnabled(true);
							btnReport.setEnabled(true);
							btnSave.setEnabled(true);
							btnLoad.setEnabled(true);
							btnReset.setEnabled(true);
						}
					});
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: an item must be selected to edit.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// list and scroll pane listeners
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list.getSelectedIndex());
				list_2.setSelectedIndex(list.getSelectedIndex());
				list_3.setSelectedIndex(list.getSelectedIndex());
			}
		});
		
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int y = scrollPane.getVerticalScrollBar().getValue();
				scrollPane_1.getVerticalScrollBar().setValue(y);
				scrollPane_2.getVerticalScrollBar().setValue(y);
				scrollPane_3.getVerticalScrollBar().setValue(y);
			}
		});
		
		list_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list.setSelectedIndex(list_1.getSelectedIndex());
				list_2.setSelectedIndex(list_1.getSelectedIndex());
				list_3.setSelectedIndex(list_1.getSelectedIndex());
			}
		});
		
		scrollPane_1.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int y = scrollPane_1.getVerticalScrollBar().getValue();
				scrollPane.getVerticalScrollBar().setValue(y);
				scrollPane_2.getVerticalScrollBar().setValue(y);
				scrollPane_3.getVerticalScrollBar().setValue(y);
			}
		});
		
		list_2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list_2.getSelectedIndex());
				list.setSelectedIndex(list_2.getSelectedIndex());
				list_3.setSelectedIndex(list_2.getSelectedIndex());
			}
		});
		
		scrollPane_2.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int y = scrollPane_2.getVerticalScrollBar().getValue();
				scrollPane_1.getVerticalScrollBar().setValue(y);
				scrollPane.getVerticalScrollBar().setValue(y);
				scrollPane_3.getVerticalScrollBar().setValue(y);
			}
		});
		
		list_3.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list_3.getSelectedIndex());
				list_2.setSelectedIndex(list_3.getSelectedIndex());
				list.setSelectedIndex(list_3.getSelectedIndex());
			}
		});
		
		scrollPane_3.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int y = scrollPane_3.getVerticalScrollBar().getValue();
				scrollPane_1.getVerticalScrollBar().setValue(y);
				scrollPane_2.getVerticalScrollBar().setValue(y);
				scrollPane.getVerticalScrollBar().setValue(y);

				// re adjust scroll panes to their maximum if scrollbar is at maximum
				if (y >= scrollPane_3.getVerticalScrollBar().getMaximum() - scrollPane_3.getVerticalScrollBar().getVisibleAmount()) {
					scrollPane_1.getVerticalScrollBar().setValue(scrollPane_1.getVerticalScrollBar().getMaximum());
					scrollPane_2.getVerticalScrollBar().setValue(scrollPane_2.getVerticalScrollBar().getMaximum());
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				}
			}
		});
		
		// Description Label Click Listener for Sorting
		lblDescription.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblPriority.setBackground(SystemColor.control);
				lblDate.setBackground(SystemColor.control);
				lblStatus.setBackground(SystemColor.control);
				if (lblDescription.getBackground().getRed() == 132) {
					lblDescription.setBackground(new Color(251,131,131));
					lc.setSortMode(3);
					lc.sort();
				}
				else {
					lblDescription.setBackground(new Color(132,169,255));
					lc.setSortMode(2);
					lc.sort();
				}
				drawList(listModel, listModel1, listModel2, listModel3);
			}
		});
		
		// Date Label Click Listener for Sorting
		lblDate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblDescription.setBackground(SystemColor.control);
				lblPriority.setBackground(SystemColor.control);
				lblStatus.setBackground(SystemColor.control);
				if (lblDate.getBackground().getRed() == 132) {
					lblDate.setBackground(new Color(251,131,131));
					lc.setSortMode(5);
					lc.sort();
				}
				else {
					lblDate.setBackground(new Color(132,169,255));
					lc.setSortMode(4);
					lc.sort();
				}
				drawList(listModel, listModel1, listModel2, listModel3);
			}
		});
		
		// Status Label Click Listener for Sorting
		lblStatus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblDescription.setBackground(SystemColor.control);
				lblPriority.setBackground(SystemColor.control);
				lblDate.setBackground(SystemColor.control);
				if (lblStatus.getBackground().getRed() == 132) {
					lblStatus.setBackground(new Color(251,131,131));
					lc.setSortMode(7);
					lc.sort();
				}
				else {
					lblStatus.setBackground(new Color(132,169,255));
					lc.setSortMode(6);
					lc.sort();
				}
				drawList(listModel, listModel1, listModel2, listModel3);
			}
		});	
		
		// Priority Label Click Listener for Sorting
		lblPriority.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblDescription.setBackground(SystemColor.control);
				lblDate.setBackground(SystemColor.control);
				lblStatus.setBackground(SystemColor.control);
				if (lblPriority.getBackground().getRed() == 132) {
					lblPriority.setBackground(new Color(251,131,131));
					lc.setSortMode(1);
					lc.sort();
				}
				else {
					lblPriority.setBackground(new Color(132,169,255));
					lc.setSortMode(0);
					lc.sort();
				}
				
				
				drawList(listModel, listModel1, listModel2, listModel3);
			}
		});	
		
		// Shutdown Listener
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	try {
	        		lc.saveItems();
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
	        }
	    }));

		// MouseWheel Listener
		frmUnlimitedTodoList.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				JScrollBar vsb = scrollPane_3.getVerticalScrollBar();
				int d = arg0.getWheelRotation();
				if (d > 0) {
					vsb.setValue(vsb.getValue() + vsb.getBlockIncrement());
				}
				else if (d < 0) {
					vsb.setValue(vsb.getValue() - vsb.getBlockIncrement());
				}
			}
		});

		
		frmUnlimitedTodoList.pack();
	}
	
	/**
	 * Redraws the list to the screen
	 * @param lc, the list container
	 * @param lm, description list model
	 * @param lm1, date list model
	 * @param lm2, status list model
	 * @param lm3, priority list model
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawList(DefaultListModel lm, DefaultListModel lm1, DefaultListModel lm2, DefaultListModel lm3) {
		lm.removeAllElements();
		lm1.removeAllElements();
		lm2.removeAllElements();
		lm3.removeAllElements();
		String[][] items = lc.getItems();
		for (String current : items[0]) {
			lm.addElement(current);
		}
		
		for (String current : items[1]) {
			lm1.addElement(current);
		}
		
		for (String current : items[2]) {
			lm2.addElement(current);
		}
		
		for (String current : items[3]) {
			lm3.addElement(current);
		}
	}
}