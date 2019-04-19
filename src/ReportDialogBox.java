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
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ReportDialogBox extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public ReportDialogBox(ListContainer lc) {
		String[][] items = lc.getItems();
		
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("List Report");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{544, 0};
		gbl_contentPane.rowHeights = new int[] {130, 241, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(null);
		
		JLabel lblReport = new JLabel("Report");
		lblReport.setForeground(Color.BLACK);
		lblReport.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReport.setBounds(10, 4, 80, 23);
		panel.add(lblReport);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 25, 524, 94);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblItems = new JLabel("List Items: " + lc.getSize());
		lblItems.setForeground(Color.BLACK);
		panel_2.add(lblItems);
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		JLabel lblNotStarted = new JLabel("Not Started: " + countNotStarted);
		lblNotStarted.setForeground(Color.BLACK);
		panel_3.add(lblNotStarted);
		lblNotStarted.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblInProgress = new JLabel("In Progress: " + countInProgress);
		lblInProgress.setForeground(Color.BLACK);
		panel_3.add(lblInProgress);
		lblInProgress.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblCompleted = new JLabel("Completed: " + countCompleted);
		lblCompleted.setForeground(Color.BLACK);
		panel_3.add(lblCompleted);
		lblCompleted.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 544, 241);
		panel_1.add(scrollPane);
		
		JTextPane reportTxtPane = new JTextPane();
		reportTxtPane.setEditable(false);
		scrollPane.setViewportView(reportTxtPane);
		reportTxtPane.setFont(new Font("monospaced", Font.BOLD, 14));
		
		// find the longest string for clean spacing
		int maxLength = 0;
		for (int i = 0; i < lc.getSize(); i++) {
			if (items[0][i].length() > maxLength) {
				maxLength = items[0][i].length();
			}
		}
		maxLength += 4;
		String text = "";
		// compile the monospaced text, then place into the report text pane
		for (int i = 0; i < lc.getSize(); i++) {
			text += String.format("%-" + maxLength + "s%-15s%-15s%s\n", items[0][i], items[1][i], items[2][i], items[3][i]);
		}
		reportTxtPane.setText(text);
		
		JButton btnNewButton = new JButton("Copy List to Clipboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringSelection stringSelect = new StringSelection(reportTxtPane.getText());
				Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
				cp.setContents(stringSelect, null);
			}
		});
		scrollPane.setColumnHeaderView(btnNewButton);;
		
	}
}
