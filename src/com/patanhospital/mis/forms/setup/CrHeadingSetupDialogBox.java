package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.database.DatabaseAssistant;

public class CrHeadingSetupDialogBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	private JPanel panelEditCrHeadings;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private JLabel lblCrHeadingNo;
	private JLabel lblCrHeadingName;
	private JLabel lblCrHeadingGroup;
	private JTextField txtFldCrNo;
	private JTextField txtFldCrHeadingName;
	private JTextField txtFldCrGroup;

	private int i = -1;

	private JButton btnAddCr;
	private JButton btnNextCr;
	private JButton btnPrevCr;
	private JButton btnUpdateCr;

	DatabaseAssistant databaseAssistant = null;

	/**
	 * Create the dialog.
	 */
	public CrHeadingSetupDialogBox() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Hospital Details");
		setAlwaysOnTop(true);

		setBounds(100, 100, width / 3, 2 * height / 3);
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		databaseAssistant = new DatabaseAssistant();

		panelEditCrHeadings = new JPanel();
		panelEditCrHeadings.setBounds(380, 114, 383, 429);
		getContentPane().add(panelEditCrHeadings);
		panelEditCrHeadings.setLayout(null);

		lblCrHeadingNo = new JLabel("Cr Heading No.");
		lblCrHeadingNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrHeadingNo.setBounds(33, 36, 156, 35);
		panelEditCrHeadings.add(lblCrHeadingNo);

		lblCrHeadingName = new JLabel("Cr Heading Name");
		lblCrHeadingName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrHeadingName.setBounds(33, 104, 179, 35);
		panelEditCrHeadings.add(lblCrHeadingName);

		lblCrHeadingGroup = new JLabel("Heading Group");
		lblCrHeadingGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrHeadingGroup.setBounds(33, 173, 156, 35);
		panelEditCrHeadings.add(lblCrHeadingGroup);

		txtFldCrNo = new JTextField();
		txtFldCrNo.setBounds(66, 63, 114, 29);

		panelEditCrHeadings.add(txtFldCrNo);
		txtFldCrNo.setColumns(10);

		txtFldCrHeadingName = new JTextField();
		txtFldCrHeadingName.setColumns(10);
		txtFldCrHeadingName.setBounds(66, 130, 194, 29);
		panelEditCrHeadings.add(txtFldCrHeadingName);

		txtFldCrGroup = new JTextField();
		txtFldCrGroup.setColumns(10);
		txtFldCrGroup.setBounds(66, 205, 194, 29);
		panelEditCrHeadings.add(txtFldCrGroup);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnAddCr = new JButton("Add New");
			btnAddCr.setActionCommand("Add New");
			buttonPane.add(btnAddCr);
			getRootPane().setDefaultButton(btnAddCr);

			btnAddCr.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = "";

					if (txtFldCrHeadingName.getText().isEmpty()) {
						msg = "Enter Heading Name first";
					} else if (txtFldCrGroup.getText().isEmpty()) {
						msg = "Enter Heading Group first";
					} else {
						String sql = "INSERT INTO tblcrheading(fldCrHeadingNo,fldCrHeadingName,fldCrHeadingGroup) values('"
								+ txtFldCrNo.getText() + "','" + txtFldCrHeadingName.getText() + "','"
								+ txtFldCrGroup.getText() + "')";

						System.out.println(sql);
						AppMessage db = databaseAssistant.execAction(sql);
						if (!db.getCODE().equals("0")) {
							msg = db.getMSG();
						} else {
							msg = db.getMSG();

							crHeadingNo();
						}

					}
					JOptionPane.showMessageDialog(null, msg, "Format Mismatch", JOptionPane.ERROR_MESSAGE);
					System.out.println(msg);
				}
			});

			btnPrevCr = new JButton("Prev");
			btnPrevCr.setActionCommand("Prev");
			buttonPane.add(btnPrevCr);
			getRootPane().setDefaultButton(btnPrevCr);

			btnPrevCr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			btnNextCr = new JButton("Next");
			btnNextCr.setActionCommand("Next");
			buttonPane.add(btnNextCr);
			getRootPane().setDefaultButton(btnNextCr);

			btnNextCr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			btnUpdateCr = new JButton("OK");
			btnUpdateCr.setActionCommand("OK");
			buttonPane.add(btnUpdateCr);
			getRootPane().setDefaultButton(btnUpdateCr);

			btnUpdateCr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}

	}

	public void crHeadingNo() {
		int crHeadingNo = 0;
		String st = "SELECT fldCrHeadingNo from  tblcrheading";
		DBMessage db = databaseAssistant.execQuery(st);
		ResultSet rs = db.getResultSet();
		try {
			if (rs.last()) {
				crHeadingNo = rs.getInt("fldCrHeadingNo");
				System.out.println(rs.getInt("fldCrHeadingNo"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// txtFldCrNo txtFldCrGroup txtFldCrHeadingName
		txtFldCrNo.setText((crHeadingNo + 1) + "");
		txtFldCrHeadingName.setText("");
		txtFldCrGroup.setText("");
	}
}