/**
 * @author Patrick Finger
 * @author Danlin Li
 * @author Robert Oller
 * @date 04/22/2019
 * @brief Displays a report of the list with monospaced
 * text that can be easily copied to the system clipboard.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ReportDialogBox extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public ReportDialogBox(ListContainer lc) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//set scroll bar to the top of the report pane
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
		
		String[][] items = lc.getItems();
		setResizable(false);
		setTitle("List Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// count the items then place the count into labels
		int countNotStarted = 0, countInProgress = 0, countCompleted = 0;
		for (int i = 0; i < lc.getSize(); i++) {
			switch (items[2][i]) {
				case "Not Started":
					countNotStarted++;
					break;
				case "In Progress":
					countInProgress++;
					break;
				case "Completed":
					countCompleted++;
					break;
				default:
					break;
			}
		}
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {594, 0};
		gbl_contentPane.rowHeights = new int[] {420, 24, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// -----------------------
		// Compile the report text
		String text = "Unlimited To-Do List Report\n";
		text += "---------------------------\n";
		text += String.format("%-13s%d\n", "Not Started:", countNotStarted);
		text += String.format("%-13s%d\n", "In Progress:", countInProgress);
		text += String.format("%-13s%d\n", "Completed:", countCompleted);
		text += String.format("%-13s%d\n\n", "Total:", lc.getSize());
		text += String.format("%-21s%-17s%-17s%s\n", "Description", "Date",  "Status", "Priority");
		text += String.format("%-21s%-17s%-17s%s\n", "-----------", "----",  "------", "--------");
		// compile the monospaced text, then place into the report text pane
		for (int i = 0; i < lc.getSize(); i++) {
			String desc = items[0][i];
			while (desc.length() >= 17) {
				text += desc.substring(0, 16) + "\n";
				desc = desc.substring(16, desc.length());
			}
			text += String.format("%-21s%-17s%-17s%s\n", desc, items[1][i], items[2][i], items[3][i]);
		};
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		JTextPane reportTxtPane = new JTextPane();
		reportTxtPane.setEditable(false);
		scrollPane.setViewportView(reportTxtPane);
		reportTxtPane.setFont(new Font("monospaced", Font.BOLD, 14));
		reportTxtPane.setText(text);
		
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 6, 0));
		
		// Copy button
		JButton btnNewButton = new JButton("Copy Report to Clipboard");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringSelection stringSelect = new StringSelection(reportTxtPane.getText());
				Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
				cp.setContents(stringSelect, null);
			}
		});
		
		// Print button
		JButton btnNewButton_1 = new JButton("Print Report");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					reportTxtPane.setFont(new Font("monospaced", Font.BOLD, 12));
					reportTxtPane.print(null, null, true, null, null, true);
					reportTxtPane.setFont(new Font("monospaced", Font.BOLD, 14));
				}
				catch (Exception e) {
					// do nothing
				}
				
			}
		});
		panel_2.add(btnNewButton_1);
		
	}
}
