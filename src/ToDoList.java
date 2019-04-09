/**
 * @Author Patrick Finger
 * @Author Danlin Li
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
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class ToDoList {

	private JFrame frmUnlimitedTodoList;

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
		frmUnlimitedTodoList.setTitle("Unlimited To-Do List");
		frmUnlimitedTodoList.setResizable(false);
		frmUnlimitedTodoList.setBounds(100, 100, 524, 444);
		frmUnlimitedTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnlimitedTodoList.getContentPane().setLayout(null);
		
		// ------------------------
		// CONSTRUCT LIST CONTAINER
		// list container
		ListContainer lc = new ListContainer();
		for (int i = 0; i < 50; i++) {
			lc.addItem(("test" + Integer.toString(i+1)), "5/10/2019", "Not Started", i+1);
		}
		String[][] items = lc.getItems();
		
		// define list models
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
		
		// ----------------------------
		// CREATE & PLACE FORM ELEMENTS
		
		// Option Pane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBackground(SystemColor.scrollbar);
		layeredPane.setBorder(new LineBorder(new Color(100, 100, 100)));
		layeredPane.setBounds(6, 324, 504, 86);
		frmUnlimitedTodoList.getContentPane().add(layeredPane);
		
		// Option Pane Items
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.setBounds(10, 29, 234, 23);
		layeredPane.add(btnNewButton);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(10, 56, 234, 23);
		layeredPane.add(btnRemoveItem);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOptions.setBounds(10, 2, 88, 23);
		layeredPane.add(lblOptions);
		
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.setBounds(260, 29, 234, 23);
		layeredPane.add(btnEditItem);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(260, 56, 65, 23);
		layeredPane.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(329, 56, 65, 23);
		layeredPane.add(btnLoad);
		
		JButton btnReport = new JButton("Report");
		btnReport.setBounds(398, 56, 96, 23);
		layeredPane.add(btnReport);
		
		// List Pane
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBackground(SystemColor.scrollbar);
		layeredPane_1.setOpaque(true);
		layeredPane_1.setBackground(SystemColor.scrollbar);
		layeredPane_1.setBorder(new LineBorder(SystemColor.windowBorder));
		layeredPane_1.setBounds(6, 7, 504, 312);
		frmUnlimitedTodoList.getContentPane().add(layeredPane_1);
		
		// Individual Scroll Panes & Labels
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 4, 208, 305);
		layeredPane_1.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JList list = new JList(listModel);
		list.setFont(new Font("Tahoma", Font.BOLD, 12));
		//center list text
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)list.getCellRenderer();
		//end center list text
		list.setBackground(SystemColor.window);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setOpaque(true);
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setColumnHeaderView(lblDescription);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(214, 4, 102, 305);
		layeredPane_1.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JList list_1 = new JList(listModel1);
		list_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		//center list text
		renderer = (DefaultListCellRenderer)list_1.getCellRenderer();
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
		scrollPane_2.setBounds(318, 4, 90, 305);
		layeredPane_1.add(scrollPane_2);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		JList list_2 = new JList(listModel2);
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
		scrollPane_3.setBounds(410, 4, 90, 305);
		layeredPane_1.add(scrollPane_3);
		JList list_3 = new JList(listModel3);
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
		
		// ----------------------
		// DEFINE EVENT LISTENERS
		
		// define list & scroll pane event listeners
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
		
		// button listeners
		// Remove Item Button
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selected = list.getSelectedIndex();
					lc.removeItem(selected);
					drawList(lc, listModel, listModel1, listModel2, listModel3);
					list.setSelectedIndex(selected);
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(frmUnlimitedTodoList, "Error: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Add Item Button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int n = lc.getSize();
					AddItemDialogBox dialog = new AddItemDialogBox(lc);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent evt) {
							
							String[][] items = lc.getItems();
							if (n < lc.getSize()) {
								listModel.addElement(items[0][n]);
								listModel1.addElement(items[1][n]);
								listModel2.addElement(items[2][n]);
								listModel3.addElement(items[3][n]);
								drawList(lc, listModel, listModel1, listModel2, listModel3);
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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
	public void drawList(ListContainer lc, DefaultListModel lm, DefaultListModel lm1, DefaultListModel lm2, DefaultListModel lm3) {
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
