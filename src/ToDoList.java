import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Window.Type;
import javax.swing.ScrollPaneConstants;

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
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmUnlimitedTodoList = new JFrame();
		frmUnlimitedTodoList.setTitle("Unlimited To-Do List");
		frmUnlimitedTodoList.setResizable(false);
		frmUnlimitedTodoList.setBounds(100, 100, 560, 380);
		frmUnlimitedTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnlimitedTodoList.getContentPane().setLayout(null);
		
		
		// list container
		ListContainer lc = new ListContainer();
		for (int i = 0; i < 50; i++) {
			lc.addItem(("test" + Integer.toString(i+1)), "05/10/2019", i%2==0, i+1);
		}
		String[][] items = lc.getItems();
		
		// define lists
		DefaultListModel listModel = new DefaultListModel();
		for (String current : lc.getItems()[0]) {
			listModel.addElement(current);
		}
		
		DefaultListModel listModel1 = new DefaultListModel();
		for (String current : lc.getItems()[1]) {
			listModel1.addElement(current);
		}
		
		DefaultListModel listModel2 = new DefaultListModel();
		for (String current : lc.getItems()[2]) {
			listModel2.addElement(current);
		}
		
		DefaultListModel listModel3 = new DefaultListModel();
		for (String current : lc.getItems()[3]) {
			listModel3.addElement(current);
		}
		
		// buttons
		
		JButton btnNewButton = new JButton("Add Item");
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
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 320, 208, 23);
		frmUnlimitedTodoList.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 208, 305);
		frmUnlimitedTodoList.getContentPane().add(scrollPane);
		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setColumnHeaderView(lblDescription);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(230, 11, 136, 305);
		frmUnlimitedTodoList.getContentPane().add(scrollPane_1);
		JList list_1 = new JList(listModel1);
		scrollPane_1.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_1.setColumnHeaderView(lblDate);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(376, 11, 87, 305);
		frmUnlimitedTodoList.getContentPane().add(scrollPane_2);
		JList list_2 = new JList(listModel2);
		scrollPane_2.setViewportView(list_2);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_2.setColumnHeaderView(lblStatus);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(473, 11, 69, 305);
		frmUnlimitedTodoList.getContentPane().add(scrollPane_3);
		JList list_3 = new JList(listModel3);
		scrollPane_3.setViewportView(list_3);
		list_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane_3.setColumnHeaderView(lblPriority);
		
		// define list event listeners
		list_3.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list_3.getSelectedIndex());
				list_2.setSelectedIndex(list_3.getSelectedIndex());
				list.setSelectedIndex(list_3.getSelectedIndex());
				
				/**/
			}
		});
		
		list_2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list_2.getSelectedIndex());
				list.setSelectedIndex(list_2.getSelectedIndex());
				list_3.setSelectedIndex(list_2.getSelectedIndex());
				
				/**/
			}
		});
		
		list_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list.setSelectedIndex(list_1.getSelectedIndex());
				list_2.setSelectedIndex(list_1.getSelectedIndex());
				list_3.setSelectedIndex(list_1.getSelectedIndex());
				
				/**/
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
		
		// scroll pane listeners
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int y = scrollPane.getVerticalScrollBar().getValue();
				scrollPane_1.getVerticalScrollBar().setValue(y);
				scrollPane_2.getVerticalScrollBar().setValue(y);
				scrollPane_3.getVerticalScrollBar().setValue(y);
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
		
		scrollPane_2.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				int y = scrollPane_2.getVerticalScrollBar().getValue();
				scrollPane_1.getVerticalScrollBar().setValue(y);
				scrollPane.getVerticalScrollBar().setValue(y);
				scrollPane_3.getVerticalScrollBar().setValue(y);
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
		
	}
}
