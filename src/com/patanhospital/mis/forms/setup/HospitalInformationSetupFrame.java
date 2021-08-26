package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.database.DatabaseAssistant;

//import org.apache.log4j.Logger;

public class HospitalInformationSetupFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	// private static final Logger logger =
	// Logger.getLogger(HospitalInformationSetupFrame.class);

	private final JPanel panelHospitalInformation = new JPanel();
	private JPanel panelEditHospital;
	private JLabel lblHospitalName;
	private JLabel lblHospitalAddress;
	private JLabel lblDistrict_1;
	private JLabel lblZone;
	private JLabel lblRegion;
	private JLabel lblCountry;
	private JLabel lblPhone;
	private JLabel lblPhone_1;
	private JLabel lblEmailAddress;
	private JLabel lblWebsite;
	private JTextField txtFldHospitalName;
	private JTextField txtFldAddress;
	private JTextField txtFldDistrict;
	private JTextField txtFldZone;
	private JTextField txtFldRegion;
	private JTextField txtFldCountry;
	private JTextField txtFldPhone1;
	private JTextField txtFldPhone2;
	private JTextField txtFldEmailAddress;
	private JTextField txtFldWebsite;

	private DatabaseAssistant databaseAssistant = null;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	/**
	 * Create the dialog.
	 */
	public HospitalInformationSetupFrame() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Hospital Details");
		setAlwaysOnTop(true);

		databaseAssistant = new DatabaseAssistant();

		setBounds(100, 100, width / 3, height);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		panelEditHospital = new JPanel();
		panelEditHospital.setBounds(376, 29, width / 3, height);
		getContentPane().add(panelEditHospital);
		panelEditHospital.setLayout(null);

		lblHospitalName = new JLabel("Hospital Name");
		lblHospitalName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHospitalName.setBounds(41, 12, 125, 27);
		panelEditHospital.add(lblHospitalName);

		lblHospitalAddress = new JLabel("Address");
		lblHospitalAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblHospitalAddress.setBounds(41, 61, 76, 27);
		panelEditHospital.add(lblHospitalAddress);

		lblDistrict_1 = new JLabel("District");
		lblDistrict_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrict_1.setBounds(41, 130, 76, 27);
		panelEditHospital.add(lblDistrict_1);

		lblZone = new JLabel("Zone");
		lblZone.setHorizontalAlignment(SwingConstants.CENTER);
		lblZone.setBounds(41, 188, 67, 27);
		panelEditHospital.add(lblZone);

		lblRegion = new JLabel("Region");
		lblRegion.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegion.setBounds(41, 246, 82, 27);
		panelEditHospital.add(lblRegion);

		lblCountry = new JLabel("Country");
		lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountry.setBounds(41, 303, 76, 27);
		panelEditHospital.add(lblCountry);

		lblPhone = new JLabel("Phone 1");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setBounds(41, 368, 137, 27);
		panelEditHospital.add(lblPhone);

		lblPhone_1 = new JLabel("Phone 2");
		lblPhone_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone_1.setBounds(214, 368, 137, 27);
		panelEditHospital.add(lblPhone_1);

		lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailAddress.setBounds(51, 436, 99, 27);
		panelEditHospital.add(lblEmailAddress);

		lblWebsite = new JLabel("Website");
		lblWebsite.setHorizontalAlignment(SwingConstants.CENTER);
		lblWebsite.setBounds(30, 493, 117, 27);
		panelEditHospital.add(lblWebsite);

		txtFldHospitalName = new JTextField();
		txtFldHospitalName.setBounds(51, 36, 277, 27);
		panelEditHospital.add(txtFldHospitalName);
		txtFldHospitalName.setColumns(30);

		txtFldAddress = new JTextField();
		txtFldAddress.setColumns(20);
		txtFldAddress.setBounds(51, 91, 194, 27);
		panelEditHospital.add(txtFldAddress);

		txtFldDistrict = new JTextField();
		txtFldDistrict.setColumns(10);
		txtFldDistrict.setBounds(51, 149, 194, 27);
		panelEditHospital.add(txtFldDistrict);

		txtFldZone = new JTextField();
		txtFldZone.setColumns(20);
		txtFldZone.setBounds(51, 207, 194, 27);
		panelEditHospital.add(txtFldZone);

		txtFldRegion = new JTextField();
		txtFldRegion.setColumns(20);
		txtFldRegion.setBounds(51, 272, 194, 27);
		panelEditHospital.add(txtFldRegion);

		txtFldCountry = new JTextField();
		txtFldCountry.setColumns(20);
		txtFldCountry.setBounds(51, 329, 194, 27);
		panelEditHospital.add(txtFldCountry);

		txtFldPhone1 = new JTextField();
		txtFldPhone1.setColumns(20);
		txtFldPhone1.setBounds(51, 397, 137, 27);
		panelEditHospital.add(txtFldPhone1);

		txtFldPhone2 = new JTextField();
		txtFldPhone2.setColumns(20);
		txtFldPhone2.setBounds(224, 397, 137, 27);
		panelEditHospital.add(txtFldPhone2);

		txtFldEmailAddress = new JTextField();
		txtFldEmailAddress.setColumns(20);
		txtFldEmailAddress.setBounds(51, 461, 194, 27);
		panelEditHospital.add(txtFldEmailAddress);

		txtFldWebsite = new JTextField();
		txtFldWebsite.setColumns(20);
		txtFldWebsite.setBounds(51, 516, 194, 27);
		panelEditHospital.add(txtFldWebsite);

		panelHospitalInformation.setBackground(new Color(153, 153, 255));

		String sql = "SELECT * FROM tblhospital";

		DBMessage db = databaseAssistant.execQuery(sql);

		ResultSet rs = db.getResultSet();

		try {

			if (rs.next()) {
				System.out.println(rs.getString("fldHospitalName"));
				// hospitalID=rs.getString("id");
				txtFldHospitalName.setText(rs.getString("fldHospitalName"));
				txtFldAddress.setText(rs.getString("fldAddress1"));
				txtFldDistrict.setText(rs.getString("fldDistrict"));
				txtFldZone.setText(rs.getString("fldZone"));
				txtFldRegion.setText(rs.getString("fldRegion"));
				txtFldCountry.setText(rs.getString("fldCountry"));
				txtFldPhone1.setText(rs.getString("fldPhone1"));
				txtFldPhone2.setText(rs.getString("fldPhone2"));
				txtFldEmailAddress.setText(rs.getString("fldEmail1"));
				txtFldWebsite.setText(rs.getString("fldWebSite1"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// logger.error(e1.toString());
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));

		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{

			JButton okButton = new JButton("Update");
			okButton.setActionCommand("Update");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);

			String hospitalName = txtFldHospitalName.getText();
			// System.out.println(hospitalName);
			okButton.addActionListener(new ActionListener() {
				// String hospitalID = "";

				@Override
				public void actionPerformed(ActionEvent e) {
					// hospitalID = txtFldPhone2.getText();
					// System.out.println(hospitalID);
					System.out.println(txtFldHospitalName.getText() + txtFldPhone2.getText());
					String sql = "UPDATE tblhospital SET fldHospitalName='" + txtFldHospitalName.getText()
							+ "',fldAddress1='" + txtFldAddress.getText() + "',fldDistrict='" + txtFldDistrict.getText()
							+ "',fldZone='" + txtFldZone.getText() + "',fldRegion='" + txtFldRegion.getText()
							+ "',fldCountry='" + txtFldCountry.getText() + "',fldPhone1='" + txtFldPhone1.getText()
							+ "',fldEmail1='" + txtFldEmailAddress.getText() + "',fldWebSite1='"
							+ txtFldWebsite.getText() + "',fldPhone2='" + txtFldPhone2.getText()
							+ "'  WHERE fldHospitalName='" + hospitalName + "'";
					System.out.println(sql);
					AppMessage db = databaseAssistant.execUpdate(sql);
					if (!db.getCODE().equals("0")) {
						System.out.println(db.getMSG());
						// logger.error(db.getMSG());
					} else {
						System.out.println(db.getMSG());
					}

				}
			});
		}
	}

}