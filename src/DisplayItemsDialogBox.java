/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Defines the Add Item Dialog Box. Used for adding
 * new items to the ListContainer.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class DisplayItemsDialogBox extends JDialog {
	
		/**
		 * Create the dialog.
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public DisplayItemsDialogBox(ListContainer lc) {
			setAlwaysOnTop(true);
		// FORM OPTIONS
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("List Display");
		setBounds(100, 100, 576, 345);
					
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
		// CREATE AND PLACE FORM ELEMENTS
		
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(6);
		flowLayout.setHgap(6);
		getContentPane().add(panel);
		
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

				// re adjust scroll panes to their maximum if scrollbar is at maximum
				if (y >= scrollPane_3.getVerticalScrollBar().getMaximum() - scrollPane_3.getVerticalScrollBar().getVisibleAmount()) {
					scrollPane_1.getVerticalScrollBar().setValue(scrollPane_1.getVerticalScrollBar().getMaximum());
					scrollPane_2.getVerticalScrollBar().setValue(scrollPane_2.getVerticalScrollBar().getMaximum());
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				}
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
		
		// MouseWheel Listener
		addMouseWheelListener(new MouseWheelListener() {
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
}