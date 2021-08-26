package com.patanhospital.mis.forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.impl.DrHeadingDAOImpl;
import com.patanhospital.mis.dao.impl.PatientDAOImpl;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.database.DatabaseConnectionManager;
import com.patanhospital.mis.model.InPatient;
import com.patanhospital.mis.util.DateConverter;
import com.patanhospital.mis.util.KeyValue;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ChargeEntryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8693288103935433767L;
	private JTextField txtFldAdmissionDate;
	private JTextField txtFldPatientId;
	private JTextField txtFldAdmissionNo;
	private JTextField txtFldAdmissionYear;
	private JTextField txtFldAdmissionMonth;
	private JTextField txtFldAdmissionDay;
	private JTextField txtFldWardDesc;
	private JTextField txtFldBedNo;
	private JTextField txtFldEstimatedAmount;
	private JLabel lblYearbs;
	private JTextField txtFldChargeYear;
	private JTextField txtFldChargeMonth;
	private JTextField txtFldChargeDay;
	private JLabel lblTotalCharge;
	private JTextField txtFldTotalCharge;
	private JTextField txtFldPatientName;
	private JTable tblAllCharges;
	private JTextField txtAutoChargeID;
	private JTable tableNewCharge;
	private JLabel lblTotalCharges;
	private JTextField txtFldTotalAmount;
	private JButton btnPrintAllCharges;
	private JTextField txtFldCreditAmount;
	private JTextField txtFldCreditRemark;
	private JTextField txtFldChargeDate;
	private JButton btnNewChargeAdd;
	private JComboBox comboBoxCrHeading;
	private DefaultTableModel newChargeModel;
	private DefaultTableModel allChargeModel;
	private JScrollPane scrollPaneNewCharge;
	private JScrollPane scrollPaneCharges;
	private DatabaseAssistant dbAssitant;
	String receiptHeadingValue;

	/**
	 * Create the panel.
	 */
	public ChargeEntryPanel() {
		dbAssitant = new DatabaseAssistant();
		setLayout(null);
		setBounds(0, 0, 1080, 638);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(24, 0, 1080, 638);
		add(panel);
		panel.setLayout(null);

		JLabel lblAdmissionNo = new JLabel("Admission No.");
		lblAdmissionNo.setBounds(54, 50, 100, 25);
		panel.add(lblAdmissionNo);

		txtFldAdmissionNo = new JTextField();
		txtFldAdmissionNo.setBounds(170, 48, 110, 25);
		txtFldAdmissionNo.setColumns(10);
		panel.add(txtFldAdmissionNo);

		DatabaseConnectionManager data = new DatabaseConnectionManager();
		java.sql.Connection con = data.getMysqlConnection().getCurrentConnection();

		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setBounds(286, 50, 70, 25);
		panel.add(lblPatientId);

		txtFldPatientId = new JTextField();
		txtFldPatientId.setBounds(434, 48, 114, 25);
		txtFldPatientId.setEditable(false);
		panel.add(txtFldPatientId);
		txtFldPatientId.setColumns(10);

		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setBounds(600, 50, 96, 25);
		panel.add(lblPatientName);

		txtFldPatientName = new JTextField();
		txtFldPatientName.setBounds(737, 48, 250, 25);
		txtFldPatientName.setEditable(false);
		txtFldPatientName.setColumns(25);
		panel.add(txtFldPatientName);

		JLabel lblAdmissionDate = new JLabel("Admission Date");
		lblAdmissionDate.setBounds(54, 73, 110, 25);
		panel.add(lblAdmissionDate);

		txtFldAdmissionDate = new JTextField();
		txtFldAdmissionDate.setBounds(170, 73, 110, 25);
		txtFldAdmissionDate.setEditable(false);
		panel.add(txtFldAdmissionDate);
		txtFldAdmissionDate.setColumns(10);

		JLabel lblAdmissionDatebs = new JLabel("Admission Date (BS)");
		lblAdmissionDatebs.setBounds(286, 75, 142, 25);
		panel.add(lblAdmissionDatebs);

		txtFldAdmissionYear = new JTextField();
		txtFldAdmissionYear.setBounds(434, 73, 48, 25);
		txtFldAdmissionYear.setEditable(false);
		txtFldAdmissionYear.setText("YYYY");
		panel.add(txtFldAdmissionYear);
		txtFldAdmissionYear.setColumns(4);

		txtFldAdmissionMonth = new JTextField();
		txtFldAdmissionMonth.setBounds(500, 73, 26, 25);
		txtFldAdmissionMonth.setEditable(false);
		txtFldAdmissionMonth.setText("MM");
		txtFldAdmissionMonth.setColumns(2);
		panel.add(txtFldAdmissionMonth);

		txtFldAdmissionDay = new JTextField();
		txtFldAdmissionDay.setBounds(544, 73, 26, 25);
		txtFldAdmissionDay.setEditable(false);
		txtFldAdmissionDay.setText("DD");
		txtFldAdmissionDay.setColumns(2);
		panel.add(txtFldAdmissionDay);

		JLabel lblWard = new JLabel("Ward");
		lblWard.setBounds(54, 100, 38, 25);
		panel.add(lblWard);

		txtFldWardDesc = new JTextField();
		txtFldWardDesc.setBounds(170, 98, 110, 25);
		txtFldWardDesc.setEditable(false);
		panel.add(txtFldWardDesc);
		txtFldWardDesc.setColumns(10);

		JLabel lblBedNo = new JLabel("Bed No");
		lblBedNo.setBounds(286, 100, 50, 25);
		panel.add(lblBedNo);

		txtFldBedNo = new JTextField();
		txtFldBedNo.setBounds(434, 98, 37, 25);
		txtFldBedNo.setEditable(false);
		panel.add(txtFldBedNo);
		txtFldBedNo.setColumns(3);

		JLabel lblEstimatedAmount = new JLabel("Estimated Amount");
		lblEstimatedAmount.setBounds(600, 100, 129, 25);
		panel.add(lblEstimatedAmount);

		txtFldEstimatedAmount = new JTextField();
		txtFldEstimatedAmount.setBounds(737, 98, 114, 25);
		txtFldEstimatedAmount.setEditable(false);
		txtFldEstimatedAmount.setColumns(10);
		panel.add(txtFldEstimatedAmount);

		lblYearbs = new JLabel("Year (BS)");
		lblYearbs.setBounds(286, 139, 64, 25);
		panel.add(lblYearbs);

		txtFldChargeYear = new JTextField();
		txtFldChargeYear.setBounds(434, 137, 48, 25);
		txtFldChargeYear.setText("YYYY");
		txtFldChargeYear.setEditable(false);
		txtFldChargeYear.setColumns(4);
		panel.add(txtFldChargeYear);

		txtFldChargeMonth = new JTextField();
		txtFldChargeMonth.setBounds(500, 137, 26, 25);
		txtFldChargeMonth.setText("MM");
		txtFldChargeMonth.setEditable(false);
		txtFldChargeMonth.setColumns(2);
		panel.add(txtFldChargeMonth);

		txtFldChargeDay = new JTextField();
		txtFldChargeDay.setBounds(544, 137, 26, 25);
		txtFldChargeDay.setText("DD");
		txtFldChargeDay.setEditable(false);
		txtFldChargeDay.setColumns(2);
		panel.add(txtFldChargeDay);

		lblTotalCharge = new JLabel("Total Charge");
		lblTotalCharge.setBounds(600, 139, 95, 25);
		panel.add(lblTotalCharge);

		txtFldTotalCharge = new JTextField();
		txtFldTotalCharge.setBounds(737, 137, 114, 25);
		txtFldTotalCharge.setEditable(false);
		txtFldTotalCharge.setColumns(10);
		panel.add(txtFldTotalCharge);

		newChargeModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Charge ID", "Charge Heading Name", "Amount", "Heading Group", "Credit Remark" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		allChargeModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Charge ID", "Charge Heading Name", "Amount", "Heading Group", "Credit Remark" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tblAllCharges = new JTable(allChargeModel);
		tblAllCharges.setBounds(54, 443, 927, 141);
		scrollPaneCharges = new JScrollPane(tblAllCharges);
		scrollPaneCharges.setBounds(54, 455, 915, 126);
		panel.add(scrollPaneCharges);

		JLabel lblChargeId = new JLabel("Charge ID");
		lblChargeId.setBounds(54, 178, 84, 15);
		panel.add(lblChargeId);

		txtAutoChargeID = new JTextField();
		txtAutoChargeID.setText("AUTO NUMBER");
		txtAutoChargeID.setEditable(false);
		txtAutoChargeID.setBounds(170, 176, 110, 19);
		panel.add(txtAutoChargeID);
		txtAutoChargeID.setColumns(10);

		tableNewCharge = new JTable(newChargeModel);
		tableNewCharge.setBounds(54, 290, 927, 141);
		scrollPaneNewCharge = new JScrollPane(tableNewCharge);
		scrollPaneNewCharge.setBounds(54, 308, 921, 123);
		panel.add(scrollPaneNewCharge);

		lblTotalCharges = new JLabel("Total Charges");
		lblTotalCharges.setBounds(74, 593, 136, 15);
		panel.add(lblTotalCharges);

		txtFldTotalAmount = new JTextField();
		txtFldTotalAmount.setBounds(191, 591, 114, 19);
		panel.add(txtFldTotalAmount);
		txtFldTotalAmount.setColumns(10);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(332, 588, 117, 25);
		panel.add(btnCalculate);

		btnPrintAllCharges = new JButton("Print All Charges");
		btnPrintAllCharges.setBounds(481, 588, 152, 25);
		panel.add(btnPrintAllCharges);

		JLabel lblCreditHeading = new JLabel("Credit Heading");
		lblCreditHeading.setBounds(54, 215, 129, 15);
		panel.add(lblCreditHeading);

		comboBoxCrHeading = new JComboBox();
		comboBoxCrHeading.setBounds(169, 210, 110, 24);
		panel.add(comboBoxCrHeading);

		DrHeadingDAOImpl drHeading = new DrHeadingDAOImpl();
		ArrayList<KeyValue> drList = drHeading.drHeadingList();

		for (int i = 0; i < drList.size(); i++) {

			comboBoxCrHeading.addItem(drList.get(i));
		}

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(285, 215, 70, 15);
		panel.add(lblAmount);

		txtFldCreditAmount = new JTextField();
		txtFldCreditAmount.setBounds(433, 213, 114, 19);
		panel.add(txtFldCreditAmount);
		txtFldCreditAmount.setColumns(10);

		JLabel lblCreditRemark = new JLabel("Credit Remark");
		lblCreditRemark.setBounds(599, 215, 129, 15);
		panel.add(lblCreditRemark);

		txtFldCreditRemark = new JTextField();
		txtFldCreditRemark.setColumns(25);
		txtFldCreditRemark.setBounds(724, 210, 250, 25);
		panel.add(txtFldCreditRemark);

		JLabel lblChargeDate = new JLabel("Charge Date");
		lblChargeDate.setBounds(54, 139, 114, 25);
		panel.add(lblChargeDate);

		txtFldChargeDate = new JTextField();
		txtFldChargeDate.setEditable(false);
		txtFldChargeDate.setColumns(10);
		txtFldChargeDate.setBounds(170, 139, 110, 25);
		panel.add(txtFldChargeDate);

		btnNewChargeAdd = new JButton("Add ");
		btnNewChargeAdd.setBounds(861, 247, 117, 25);
		panel.add(btnNewChargeAdd);

		btnNewChargeAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String chargeID = txtAutoChargeID.getText();
					String amount = txtFldCreditAmount.getText();
					String remark = txtFldCreditRemark.getText();
					String drGroup = "";
					String crHeadingNo = ((KeyValue) comboBoxCrHeading.getSelectedItem()).getValue();
					String crHeadingName = ((KeyValue) comboBoxCrHeading.getSelectedItem()).getKey() + ","
							+ crHeadingNo;
					// System.out.println(crHeadingNo);
					String getDrGroupQuery = "SELECT fldDrGroup FROM tblDrHeading WHERE fldDrHeadingName='"
							+ crHeadingName + "'";
					DBMessage data = dbAssitant.execQuery(getDrGroupQuery);
					ResultSet rs = data.getResultSet();
					if (rs.next()) {
						drGroup = rs.getString("fldDrGroup");
						System.out.println(drGroup);
					}
					newChargeModel.insertRow(0, new Object[] { chargeID, crHeadingName, amount, drGroup, remark });

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		txtFldAdmissionNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
					int admissionNo = Integer.parseInt(txtFldAdmissionNo.getText());
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");
					LocalDateTime localDate = LocalDateTime.now();
					txtFldChargeDate.setText((dtf.format(localDate)));

					DateConverter dateConverter = new DateConverter();
					dateConverter.setEnglishDateString(dtf.format(localDate));
					txtFldChargeYear.setText(dateConverter.getNepaliYear() + "");
					txtFldChargeMonth.setText(dateConverter.getNepaliMonth() + "");
					txtFldChargeDay.setText(dateConverter.getNepaliDate() + "");

					PatientDAOImpl patientDAO = new PatientDAOImpl();
					InPatient newInpatient = new InPatient();
					newInpatient = patientDAO
							.searchCompPatientDetail(Integer.parseInt(txtFldAdmissionNo.getText()));

					txtFldPatientId.setText((newInpatient.getFldPatientId() + ""));
					txtFldPatientName.setText(newInpatient.getPatient().getFldPatientName() + " "
							+ newInpatient.getPatient().getFldPatientLastName());
					txtFldAdmissionDate.setText(newInpatient.getFldAdmissionDate());
					txtFldAdmissionYear.setText(newInpatient.getFldAdmissionYear() + "");
					txtFldAdmissionMonth.setText(newInpatient.getFldAdmissionMonth() + "");
					txtFldAdmissionDay.setText(newInpatient.getFldAdmissionDay() + "");
					txtFldWardDesc.setText(newInpatient.getWard().getFldWardDesc());
					txtFldBedNo.setText(newInpatient.getFldBedNo() + "");
					txtFldEstimatedAmount.setText(newInpatient.getFldEstimatedAmt() + "");

					int id = 100 + (int) (Math.random() * 1000);
					txtAutoChargeID.setText(id + "");
					
					populateChargeTable(txtFldAdmissionNo.getText());
					

				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});

		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = newChargeModel.getRowCount();
				double totalAmount = 0;
				String addIPChargeQuery = "INSERT INTO tblIPCharge(fldChargeID, fldAdmissionNo, fldChargeDate,"
						+ "fldChargeYear, fldChargeMonth, fldChargeDay,fldUserID)  VALUES('" + txtAutoChargeID.getText()
						+ "','" + txtFldAdmissionNo.getText() + "','" + txtFldChargeDate.getText() + "','"
						+ txtFldChargeYear.getText() + "','" + txtFldChargeMonth.getText() + "','"
						+ txtFldChargeDay.getText() + "','Admin')";
				AppMessage chargeData = dbAssitant.execAction(addIPChargeQuery);
				if (!chargeData.getCODE().equals(0)) {
					System.out.println(chargeData.getMSG());
				} else {
					System.out.println(chargeData.getMSG());
				}

				for (int i = 0; i < rowCount; i++) {
					String chargeHeading = tableNewCharge.getValueAt(i, 1).toString();
					String charge[] = chargeHeading.split(",");
					String addChargeQuery = "INSERT INTO tblIPChargeDetail VALUES('" + tableNewCharge.getValueAt(i, 0)
							+ "','" + charge[1] + "','" + tableNewCharge.getValueAt(i, 4) + "','"
							+ tableNewCharge.getValueAt(i, 2) + "')";
					AppMessage data = dbAssitant.execAction(addChargeQuery);
					Double crAmount = Double.parseDouble(tableNewCharge.getValueAt(i, 2).toString());
					totalAmount = totalAmount + crAmount;
					
					
					

					if (!data.getCODE().equals(0)) {
						System.out.println(data.getMSG());

					} else {
						System.out.println(data.getMSG());
					}
				}
				txtFldTotalAmount.setText(totalAmount + "");
				if(!txtFldTotalAmount.getText().isEmpty()) {
					populateChargeTable(txtFldAdmissionNo.getText());
					newChargeModel.setRowCount(0);
				}
			}
		});

	}

	public void populateChargeTable(String admissionNo) {
		System.out.println("abcd");
		String searchChargeQuery = "SELECT ipcd.fldChargeID, drh.fldDrHeadingName,drh.fldDrGroup, ipcd.fldChargeHeadingRemark,"
				+ "ipcd.fldAmount FROM tblIPcharge ipc, tblIPchargeDetail ipcd, tblDrHeading drh WHERE ipcd.fldChargeHeadingNo=drh.fldDrHeadingNo AND "
				+ "ipc.fldChargeID = ipcd.fldChargeID AND ipc.fldAdmissionNo = " + admissionNo;
		DBMessage chargeDetails = dbAssitant.execQuery(searchChargeQuery);
		ResultSet resultSet = chargeDetails.getResultSet();
		try {
			while (resultSet.next()) {
				allChargeModel.insertRow(0,
						new Object[] { resultSet.getString("fldChargeID"), resultSet.getString("fldDrHeadingName"),
								resultSet.getString("fldAmount"), resultSet.getString("fldDrGroup"),
								resultSet.getString("fldChargeHeadingRemark") });
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
}
