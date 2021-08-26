package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import com.patanhospital.mis.dao.impl.DistrictDAOImpl;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.District;

public class DistrictSetupDialogBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	private JPanel panelEditDistrict;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private JLabel lblDistrictCode;
	private JTextField txtFldDistrictCode;
	private JLabel lblDistrictName;
	private JTextField txtFldDistrictName;

	private int i = -1;

	private JButton btnAddDistrict;
	private JButton btnNextDistrict;
	private JButton btnPrevDistrict;
	private JButton btnUpdateDistrict;

	DatabaseAssistant databaseAssistant = null;

	/**
	 * Create the dialog.
	 */
	public DistrictSetupDialogBox() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Hospital Details");
		setAlwaysOnTop(true);

		setBounds(100, 100, width / 3, 2 * height / 3);
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		databaseAssistant = new DatabaseAssistant();

		DistrictDAOImpl district = new DistrictDAOImpl();
		District districtInfo = new District();

		System.out.println(district.listAllDistrictName().get(i + 1).getFldDistrictCode()
				+ district.listAllDistrictName().get(i + 1).getFldDistrictName());
		// txtFldDistrictCode.setText(district.listAllDistrictName().get(i).getFldDistrictCode()
		// + "");
		// txtFldDistrictName.setText(district.listAllDistrictName().get(i).getFldDistrictName());
		panelEditDistrict = new JPanel();
		panelEditDistrict.setBounds(377, 105, 383, 429);
		getContentPane().add(panelEditDistrict);
		panelEditDistrict.setLayout(null);

		lblDistrictCode = new JLabel("District Code");
		lblDistrictCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrictCode.setBounds(33, 36, 156, 35);
		panelEditDistrict.add(lblDistrictCode);

		txtFldDistrictCode = new JTextField();
		txtFldDistrictCode.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
				short districtCode = Short.parseShort(txtFldDistrictCode.getText());

				for (int j = 0; j < district.listAllDistrictName().size(); ++j) {

					if (district.listAllDistrictName().get(j).getFldDistrictCode() == districtCode) {

						txtFldDistrictName.setText(district.listAllDistrictName().get(j).getFldDistrictName());
						btnAddDistrict.setEnabled(false);
						btnUpdateDistrict.setEnabled(true);
						i = j;
						break;
					} else {
						btnAddDistrict.setEnabled(true);
						btnUpdateDistrict.setEnabled(false);
					}
				}

			}catch(NumberFormatException ex) {
				System.out.println(ex);
			}}

			public void focusGained(FocusEvent e) {
				txtFldDistrictCode.setEditable(true);
				txtFldDistrictCode.setText("");
				txtFldDistrictName.setText("");
			}
		});
		txtFldDistrictCode.setBounds(66, 63, 114, 29);
		panelEditDistrict.add(txtFldDistrictCode);
		txtFldDistrictCode.setColumns(10);

		lblDistrictName = new JLabel("District Name");
		lblDistrictName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrictName.setBounds(33, 104, 179, 35);
		panelEditDistrict.add(lblDistrictName);

		txtFldDistrictName = new JTextField();
		txtFldDistrictName.setColumns(10);
		txtFldDistrictName.setBounds(66, 130, 194, 29);
		panelEditDistrict.add(txtFldDistrictName);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnAddDistrict = new JButton("Add New");
			btnAddDistrict.setActionCommand("Add New");
			buttonPane.add(btnAddDistrict);
			getRootPane().setDefaultButton(btnAddDistrict);

			btnAddDistrict.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String msg = "";

					districtInfo.setFldDistrictCode(Short.parseShort(txtFldDistrictCode.getText()));
					districtInfo.setFldDistrictName(txtFldDistrictName.getText());
					// district.addNewDistrict(districtInfo);

					if (!district.addNewDistrict(districtInfo).getCODE().equals("0")) {
						msg = "Insertion Failed";
						System.out.println(msg);
						// logger.error(district.addNewDistrict(districtInfo).getMSG());

					} else {
						msg = "Inserted Successfully";
						System.out.println(msg);
						// btnAddDistrict.setEnabled(false);

					}

					JOptionPane.showMessageDialog(null, msg, "District Insertion", JOptionPane.INFORMATION_MESSAGE);

				}
			});

			btnPrevDistrict = new JButton("Prev");
			btnPrevDistrict.setActionCommand("Prev");
			buttonPane.add(btnPrevDistrict);
			getRootPane().setDefaultButton(btnPrevDistrict);

			btnPrevDistrict.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (i > 0) {
						i = i - 1;
						txtFldDistrictCode.setText(district.listAllDistrictName().get(i).getFldDistrictCode() + "");
						txtFldDistrictName.setText(district.listAllDistrictName().get(i).getFldDistrictName());
					} else {
						JOptionPane.showMessageDialog(null, "No more Prev Data", "District", JOptionPane.ERROR_MESSAGE);
					}

				}
			});

			btnNextDistrict = new JButton("Next");
			btnNextDistrict.setActionCommand("Next");
			buttonPane.add(btnNextDistrict);
			getRootPane().setDefaultButton(btnNextDistrict);

			btnNextDistrict.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (i < district.listAllDistrictName().size() - 1) {
						i = i + 1;
						txtFldDistrictCode.setText(district.listAllDistrictName().get(i).getFldDistrictCode() + "");
						txtFldDistrictName.setText(district.listAllDistrictName().get(i).getFldDistrictName());
					} else {
						JOptionPane.showMessageDialog(null, "No More Data", "District", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			btnUpdateDistrict = new JButton("OK");
			btnUpdateDistrict.setActionCommand("OK");
			buttonPane.add(btnUpdateDistrict);
			getRootPane().setDefaultButton(btnUpdateDistrict);

			btnUpdateDistrict.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String msg = "";

					districtInfo.setFldDistrictCode(Short.parseShort(txtFldDistrictCode.getText()));
					districtInfo.setFldDistrictName(txtFldDistrictName.getText());

					if (txtFldDistrictName.getText().isEmpty()) {
						msg = "Insert District Name";
					} else {

						if (!district.updateDistrict(districtInfo).getCODE().equals("0")) {
							msg = "Update Fail";
//							logger.error(district.addNewDistrict(districtInfo).getMSG());
						} else {
							msg = "Updated Successfully";

						}

					}
					JOptionPane.showMessageDialog(null, msg, "Update", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}

	}

}
