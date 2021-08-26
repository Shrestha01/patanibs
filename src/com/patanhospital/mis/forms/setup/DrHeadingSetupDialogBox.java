package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.dao.impl.DrHeadingDAOImpl;
import com.patanhospital.mis.model.ChargeDRHeading;

public class DrHeadingSetupDialogBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	private JPanel panelEditDrHeadings;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private JLabel lblDrHeadingNo;
	private JLabel lblDrHeadingName;
	private JLabel lblHeadingGroup;
	private JLabel lblRate;
	private JTextField txtFldDrNo;
	private JTextField txtFldDrHeadingName;
	private JTextField txtFldDrGroup;
	private JTextField txtFldDrRate;
	private JTextField txtfldMainGroup;

	private int i = -1;

	private JButton btnAddNewDr;
	private JButton btnNextDr;
	private JButton btnPrevDr;
	private JButton btnUpdateDr;

	/**
	 * Create the dialog.
	 */
	public DrHeadingSetupDialogBox() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Hospital Details");
		setAlwaysOnTop(true);

		setBounds(100, 100, width / 3, 2 * height / 3);
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		DrHeadingDAOImpl drHeading = new DrHeadingDAOImpl();

		panelEditDrHeadings = new JPanel();
		panelEditDrHeadings.setBounds(377, 105, width / 3, 2 * height / 3);
		getContentPane().add(panelEditDrHeadings);
		panelEditDrHeadings.setLayout(null);

		lblDrHeadingNo = new JLabel("Dr Heading No.");
		lblDrHeadingNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrHeadingNo.setBounds(33, 36, 156, 35);
		panelEditDrHeadings.add(lblDrHeadingNo);

		lblDrHeadingName = new JLabel("Dr Heading Name");
		lblDrHeadingName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrHeadingName.setBounds(33, 104, 179, 35);
		panelEditDrHeadings.add(lblDrHeadingName);

		lblHeadingGroup = new JLabel("Heading Group");
		lblHeadingGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadingGroup.setBounds(33, 173, 156, 35);
		panelEditDrHeadings.add(lblHeadingGroup);

		lblRate = new JLabel("Rate");
		lblRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRate.setBounds(12, 241, 133, 35);
		panelEditDrHeadings.add(lblRate);

		txtFldDrNo = new JTextField();
		txtFldDrNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
				int drHeadingNo = Integer.parseInt(txtFldDrNo.getText());
				System.out.println(drHeading.drHeadingList().size());
				for (int j = 0; j < drHeading.listAllDrHeading().size(); ++j) {

					if (drHeading.listAllDrHeading().get(j).getFldDrHeadingNo() == drHeadingNo) {

						txtFldDrHeadingName.setText(drHeading.listAllDrHeading().get(j).getFldDrHeadingName());
						txtFldDrGroup.setText(drHeading.listAllDrHeading().get(j).getFldDrGroup());
						txtFldDrRate.setText(drHeading.listAllDrHeading().get(j).getFldRate() + "");

						// System.out.println("hello");
						btnAddNewDr.setEnabled(false);
						btnUpdateDr.setEnabled(true);
						i = j;
						break;
					} else {
						btnAddNewDr.setEnabled(true);
						btnUpdateDr.setEnabled(false);
						txtFldDrRate.setText("");
					}
				}}catch(NumberFormatException ex) {
					System.out.println(ex);
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtFldDrNo.setText("");
				txtFldDrHeadingName.setText("");
				txtFldDrGroup.setText("");
			}
		});
		txtFldDrNo.setBounds(66, 63, 114, 29);
		panelEditDrHeadings.add(txtFldDrNo);
		txtFldDrNo.setColumns(10);

		txtFldDrHeadingName = new JTextField();
		txtFldDrHeadingName.setColumns(10);
		txtFldDrHeadingName.setBounds(66, 130, 194, 29);
		panelEditDrHeadings.add(txtFldDrHeadingName);

		txtFldDrGroup = new JTextField();
		txtFldDrGroup.setColumns(10);
		txtFldDrGroup.setBounds(66, 205, 194, 29);
		panelEditDrHeadings.add(txtFldDrGroup);

		txtFldDrRate = new JTextField();
		txtFldDrRate.setColumns(10);
		txtFldDrRate.setBounds(66, 268, 114, 29);
		panelEditDrHeadings.add(txtFldDrRate);

		txtfldMainGroup = new JTextField();
		txtfldMainGroup.setBounds(231, 271, 116, 22);
		panelEditDrHeadings.add(txtfldMainGroup);
		txtfldMainGroup.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnAddNewDr = new JButton("Add New");
			btnAddNewDr.setActionCommand("Add New");
			buttonPane.add(btnAddNewDr);
			getRootPane().setDefaultButton(btnAddNewDr);

			btnAddNewDr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ChargeDRHeading charge = new ChargeDRHeading();

					charge.setFldDrHeadingNo(Integer.parseInt(txtFldDrNo.getText()));
					charge.setFldDrHeadingName(txtFldDrHeadingName.getText());
					charge.setFldDrGroup(txtFldDrGroup.getText());
					charge.setFldRate(Double.parseDouble(txtFldDrRate.getText()));
					charge.setFldMainGroup(txtfldMainGroup.getText());

					AppMessage appMsg = drHeading.addDrHeading(charge);
					if (!appMsg.getCODE().equals("0")) {
//						logger.error(appMsg.getMSG());
						JOptionPane.showMessageDialog(null, "Data Inserted Failed", "Data Insertion",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Data Inserted Successfully", "Data Insertion",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			});

			btnPrevDr = new JButton("Prev");
			btnPrevDr.setActionCommand("Prev");
			buttonPane.add(btnPrevDr);
			getRootPane().setDefaultButton(btnPrevDr);

			btnPrevDr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (i > 0) {
						i = i - 1;
						txtFldDrNo.setText(drHeading.listAllDrHeading().get(i).getFldDrHeadingNo() + "");
						txtFldDrHeadingName.setText(drHeading.listAllDrHeading().get(i).getFldDrHeadingName());
						txtFldDrGroup.setText(drHeading.listAllDrHeading().get(i).getFldDrGroup());
						txtFldDrRate.setText(drHeading.listAllDrHeading().get(i).getFldRate() + "");

					} else {
						JOptionPane.showMessageDialog(null, "No More Previous Data", "Previous Data",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			btnNextDr = new JButton("Next");
			btnNextDr.setActionCommand("Next");
			buttonPane.add(btnNextDr);
			getRootPane().setDefaultButton(btnNextDr);

			btnNextDr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (i < drHeading.listAllDrHeading().size() - 1) {
						i = i + 1;
						txtFldDrNo.setText(drHeading.listAllDrHeading().get(i).getFldDrHeadingNo() + "");
						txtFldDrHeadingName.setText(drHeading.listAllDrHeading().get(i).getFldDrHeadingName());
						txtFldDrGroup.setText(drHeading.listAllDrHeading().get(i).getFldDrGroup());
						txtFldDrRate.setText(drHeading.listAllDrHeading().get(i).getFldRate() + "");

					} else {
						JOptionPane.showMessageDialog(null, "No More Next Data", "Next Data",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});

			btnUpdateDr = new JButton("OK");
			btnUpdateDr.setActionCommand("OK");
			buttonPane.add(btnUpdateDr);
			getRootPane().setDefaultButton(btnUpdateDr);

			btnUpdateDr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ChargeDRHeading charge = new ChargeDRHeading();

					charge.setFldDrHeadingNo(Integer.parseInt(txtFldDrNo.getText()));
					charge.setFldDrHeadingName(txtFldDrHeadingName.getText());
					charge.setFldDrGroup(txtFldDrGroup.getText());
					charge.setFldRate(Double.parseDouble(txtFldDrRate.getText()));
					charge.setFldMainGroup(txtfldMainGroup.getText());

					AppMessage appMsg = drHeading.updateDrHeading(charge);
					if (!appMsg.getCODE().equals("0")) {
//						logger.error(appMsg.getMSG());
						JOptionPane.showMessageDialog(null, "Data Update Failed", "Update Information",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Data Update Successfully", "Update Information",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			});
		}
	}

}
