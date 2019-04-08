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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ToDoList {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoList window = new ToDoList();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 545, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("To-do List");
		lblNewLabel.setBounds(10, 11, 74, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		// list container
		ListContainer lc = new ListContainer();
		lc.addItem("test", "02/02/2019", true, 1);
		lc.addItem("test2", "02/02/2019", true, 2);
		lc.addItem("test3", "02/02/2019", false, 3);
		lc.addItem("test4", "02/02/2019", false, 4);
		lc.addItem("test5", "02/02/2019", false, 5);
		String[][] items = lc.getItems();
		
		// define lists
		DefaultListModel listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (String current : lc.getItems()[0]) {
			listModel.addElement(current);
		}
		list.setBounds(10, 36, 218, 305);
		frame.getContentPane().add(list);
		
		DefaultListModel listModel1 = new DefaultListModel();
		JList list_1 = new JList(listModel1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (String current : lc.getItems()[1]) {
			listModel1.addElement(current);
		}
		list_1.setBounds(238, 36, 139, 305);
		frame.getContentPane().add(list_1);
		
		DefaultListModel listModel2 = new DefaultListModel();
		JList list_2 = new JList(listModel2);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (String current : lc.getItems()[2]) {
			listModel2.addElement(current);
		}
		list_2.setBounds(387, 36, 49, 305);
		frame.getContentPane().add(list_2);
		
		DefaultListModel listModel3 = new DefaultListModel();
		JList list_3 = new JList(listModel3);
		list_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (String current : lc.getItems()[3]) {
			listModel3.addElement(current);
		}
		list_3.setBounds(446, 36, 61, 305);
		frame.getContentPane().add(list_3);
		
		// buttons
		
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddItemDialogBox dialog = new AddItemDialogBox(lc);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent evt) {
							String[][] items = lc.getItems();
							int n = lc.getSize();
							listModel.addElement(items[0][n-1]);
							listModel1.addElement(items[1][n-1]);
							listModel2.addElement(items[2][n-1]);
							listModel3.addElement(items[3][n-1]);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 352, 218, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		// define list event listeners
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list.getSelectedIndex());
				list_2.setSelectedIndex(list.getSelectedIndex());
				list_3.setSelectedIndex(list.getSelectedIndex());
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
		
		list_2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				list_1.setSelectedIndex(list_2.getSelectedIndex());
				list.setSelectedIndex(list_2.getSelectedIndex());
				list_3.setSelectedIndex(list_2.getSelectedIndex());
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
		
	}
}
