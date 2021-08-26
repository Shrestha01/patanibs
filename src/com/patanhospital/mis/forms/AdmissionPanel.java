package com.patanhospital.mis.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.dao.impl.DistrictDAOImpl;
import com.patanhospital.mis.dao.impl.EthnicGroupDAOImpl;
import com.patanhospital.mis.dao.impl.PatientDAOImpl;
import com.patanhospital.mis.dao.impl.WardTypeDAOImpl;
import com.patanhospital.mis.model.District;
import com.patanhospital.mis.model.EthnicGroup;
import com.patanhospital.mis.model.InPatient;
import com.patanhospital.mis.model.Patient;
import com.patanhospital.mis.util.DateConverter;
import com.patanhospital.mis.util.KeyValue;

public class AdmissionPanel extends JPanel {

	private static final long serialVersionUID = -5948663753842279520L;
	private JTextField txtFldPatientId;
	private JTextField txtFldAdmissionNo;
	private JTextField txtFldAdmissionDate;
	private JTextField txtFldAdmissionYear;
	private JTextField txtFldAdmissionMonth;
	private JTextField txtFldAdmissionDay;
	private JTextField txtFldDischargeDate;
	private JTextField txtFldDischargeYear;
	private JTextField txtFldDischargeMonth;
	private JTextField txtFldDischargeDay;
	private JTextField txtFldEstimatedAmount;
	private JTextField txtFldPatientCount;
	private JTextField txtFldBedNo;
	private JTextField txtFldFirstName;
	private JTextField txtFldLastName;
	private JTextField txtFldSex;
	private JTextField txtFldVillage;
	private JTextField txtFldBirthDateAD;
	private JTextField txtFldGuardian;
	private JTextField txtFieldRelation;
	private JTextField txtFldWardNo;
	private JTextField txtFldAge;
	private JTextField txtFldYear;
	private JTextField txtFldMonth;
	private JTextField txtFldDay;
	private JTable tablePreviousAdmission;
	private JComboBox<KeyValue> comboBoxWardId;
	private JComboBox<String> comboBoxEthnicGroup;
	private JComboBox<String> comboBoxDistrict;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPaneAdmissions;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 150;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private WardTypeDAOImpl wardTypeImpl;
	private ArrayList<KeyValue> keyValWardType;
	private ArrayList<EthnicGroup> listOfEthnicGrp;
	private ArrayList<District> listOfDistrictName;
	private PatientDAOImpl patientDAOImpl;
	private static final Logger logger = LoggerFactory.getLogger(AdmissionPanel.class);

	/**
	 * Create the panel.
	 */
	public AdmissionPanel() {
		patientDAOImpl = new PatientDAOImpl();
		wardTypeImpl = new WardTypeDAOImpl();
		keyValWardType = wardTypeImpl.getAllWardType();
		listOfEthnicGrp = new EthnicGroupDAOImpl().listAllEthnicGrp();
		listOfDistrictName = new DistrictDAOImpl().listAllDistrictName();
		initialize();
	}

	public void initialize() {
		setLayout(null);
		setSize(width, height);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setSize(width, height);
		add(panel);
		panel.setLayout(null);

		JPanel panelInPatientInfo = new JPanel();
		panelInPatientInfo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"In Patient Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelInPatientInfo.setBounds(12, 12, width / 2 - 30, height - 380);
		panel.add(panelInPatientInfo);
		panelInPatientInfo.setLayout(null);

		JLabel label_1 = new JLabel("Patient ID");
		label_1.setBounds(12, 50, 70, 15);
		panelInPatientInfo.add(label_1);

		JLabel label_2 = new JLabel("Admission No.");
		label_2.setBounds(12, 77, 119, 24);
		panelInPatientInfo.add(label_2);

		JLabel label_3 = new JLabel("Admission Date (AD)");
		label_3.setBounds(12, 113, 157, 17);
		panelInPatientInfo.add(label_3);

		JLabel label_4 = new JLabel("Admission Date (BS)");
		label_4.setBounds(12, 142, 157, 15);
		panelInPatientInfo.add(label_4);

		JLabel label_5 = new JLabel("Discharge Date (AD)");
		label_5.setBounds(12, 169, 174, 15);
		panelInPatientInfo.add(label_5);

		JLabel label_6 = new JLabel("Discharge Date (BS)");
		label_6.setBounds(12, 196, 141, 15);
		panelInPatientInfo.add(label_6);

		JLabel label_7 = new JLabel("Ward ID");
		label_7.setBounds(12, 223, 70, 15);
		panelInPatientInfo.add(label_7);

		JLabel label_8 = new JLabel("Bed No.");
		label_8.setBounds(12, 250, 70, 15);
		panelInPatientInfo.add(label_8);

		JLabel label_9 = new JLabel("Patient Count");
		label_9.setBounds(12, 277, 141, 15);
		panelInPatientInfo.add(label_9);

		JLabel label_10 = new JLabel("Estimated Amount(NRP)");
		label_10.setBounds(12, 304, 187, 15);
		panelInPatientInfo.add(label_10);

		JLabel label_11 = new JLabel("Discharged");
		label_11.setBounds(12, 331, 141, 15);
		panelInPatientInfo.add(label_11);

		txtFldPatientId = new JTextField();
		txtFldPatientId.setColumns(10);
		txtFldPatientId.setBounds(207, 48, 100, 19);
		txtFldPatientId.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkIntegerNumber(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		txtFldPatientId.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
					int patientId = Integer.parseInt(txtFldPatientId.getText());
					Patient patientInfo = patientDAOImpl.searchPatient(patientId);
					clear();
					if (patientInfo == null) {
						System.out.println("No Patient Found");
					} else {
						logger.info(patientInfo.toString());
						txtFldFirstName.setText(patientInfo.getFldPatientName());
						txtFldLastName.setText(patientInfo.getFldPatientLastName());
						txtFldSex.setText(patientInfo.getFldSex());
						comboBoxEthnicGroup.setSelectedItem(patientInfo.getFldEthnicGroup());
						txtFldVillage.setText(patientInfo.getFldVillageTown());
						txtFldWardNo.setText(patientInfo.getFldWardNo() + "");
						comboBoxDistrict.setSelectedItem(patientInfo.getFldDistrict());
						txtFldBirthDateAD.setText(patientInfo.getFldBirthDate());
						txtFldYear.setText(patientInfo.getFldYearBorn() + "");
						txtFldMonth.setText(patientInfo.getFldMonthBorn() + "");
						txtFldDay.setText(patientInfo.getFldDayBorn() + "");
						txtFldGuardian.setText(patientInfo.getFldGuardiansName());
						txtFieldRelation.setText(patientInfo.getFldRelation());

						// Fetching Previous Admission Details and Showing in Table
						populateTable(patientId);
					}
				} catch (NumberFormatException numFormatExe) {
					JOptionPane.showMessageDialog(null, "Not a valid Patient Id", "Invalid Format",
							JOptionPane.ERROR_MESSAGE);
					numFormatExe.printStackTrace();
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		panelInPatientInfo.add(txtFldPatientId);

		txtFldAdmissionNo = new JTextField();
		txtFldAdmissionNo.setEditable(false);
		txtFldAdmissionNo.setColumns(7);
		txtFldAdmissionNo.setBounds(206, 80, 101, 19);
		panelInPatientInfo.add(txtFldAdmissionNo);

		txtFldAdmissionDate = new JTextField();
		txtFldAdmissionDate.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");
				LocalDateTime localDate = LocalDateTime.now();
				txtFldAdmissionDate.setText((dtf.format(localDate)));

				DateConverter dateConverter = new DateConverter();
				dateConverter.setEnglishDateString(dtf.format(localDate));
				txtFldAdmissionYear.setText(dateConverter.getNepaliYear() + "");
				txtFldAdmissionMonth.setText(dateConverter.getNepaliMonth() + "");
				txtFldAdmissionDay.setText(dateConverter.getNepaliDate() + "");
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		txtFldAdmissionDate.setColumns(10);
		txtFldAdmissionDate.setBounds(207, 112, 157, 19);
		panelInPatientInfo.add(txtFldAdmissionDate);

		txtFldAdmissionYear = new JTextField();
		txtFldAdmissionYear.setEditable(false);
		txtFldAdmissionYear.setText("YYYY");
		txtFldAdmissionYear.setColumns(10);
		txtFldAdmissionYear.setBounds(206, 140, 43, 19);
		panelInPatientInfo.add(txtFldAdmissionYear);

		txtFldAdmissionMonth = new JTextField();
		txtFldAdmissionMonth.setEditable(false);
		txtFldAdmissionMonth.setText("MM");
		txtFldAdmissionMonth.setColumns(10);
		txtFldAdmissionMonth.setBounds(250, 140, 28, 19);
		panelInPatientInfo.add(txtFldAdmissionMonth);

		txtFldAdmissionDay = new JTextField();
		txtFldAdmissionDay.setEditable(false);
		txtFldAdmissionDay.setText("DD");
		txtFldAdmissionDay.setColumns(10);
		txtFldAdmissionDay.setBounds(279, 140, 28, 19);
		panelInPatientInfo.add(txtFldAdmissionDay);

		txtFldDischargeDate = new JTextField();
		txtFldDischargeDate.setEditable(false);
		txtFldDischargeDate.setText("Enter Date of Discharge");
		txtFldDischargeDate.setColumns(10);
		txtFldDischargeDate.setBounds(207, 167, 157, 19);
		panelInPatientInfo.add(txtFldDischargeDate);

		txtFldDischargeYear = new JTextField();
		txtFldDischargeYear.setText("YYYY");
		txtFldDischargeYear.setColumns(10);
		txtFldDischargeYear.setBounds(207, 196, 43, 19);
		panelInPatientInfo.add(txtFldDischargeYear);

		txtFldDischargeMonth = new JTextField();
		txtFldDischargeMonth.setText("MM");
		txtFldDischargeMonth.setColumns(10);
		txtFldDischargeMonth.setBounds(251, 196, 28, 19);
		panelInPatientInfo.add(txtFldDischargeMonth);

		txtFldDischargeDay = new JTextField();
		txtFldDischargeDay.setText("DD");
		txtFldDischargeDay.setColumns(10);
		txtFldDischargeDay.setBounds(280, 196, 28, 19);
		panelInPatientInfo.add(txtFldDischargeDay);

		JCheckBox checkBoxDischarged = new JCheckBox("Yes");
		checkBoxDischarged.setBounds(207, 327, 50, 23);
		panelInPatientInfo.add(checkBoxDischarged);

		txtFldEstimatedAmount = new JTextField();
		txtFldEstimatedAmount.setColumns(10);
		txtFldEstimatedAmount.setBounds(207, 302, 157, 19);
		panelInPatientInfo.add(txtFldEstimatedAmount);

		txtFldPatientCount = new JTextField();
		txtFldPatientCount.setText("1");
		txtFldPatientCount.setColumns(10);
		txtFldPatientCount.setBounds(207, 275, 19, 19);
		txtFldPatientCount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				checkIntegerNumber(arg0);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		panelInPatientInfo.add(txtFldPatientCount);

		txtFldBedNo = new JTextField();
		txtFldBedNo.setColumns(10);
		txtFldBedNo.setBounds(207, 248, 100, 19);
		txtFldBedNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				checkIntegerNumber(arg0);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		panelInPatientInfo.add(txtFldBedNo);

		JButton btnAddInfo = new JButton("Add Information");
		btnAddInfo.setBounds(207, 368, 157, 25);
		btnAddInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleBtnAddInfoEvent(e);
			}
		});
		panelInPatientInfo.add(btnAddInfo);

		comboBoxWardId = new JComboBox<KeyValue>();
		comboBoxWardId.setBounds(207, 218, 157, 24);
		panelInPatientInfo.add(comboBoxWardId);
		for (int index = 0; index < keyValWardType.size(); index++) {
			comboBoxWardId.addItem(keyValWardType.get(index));
		}

		JPanel panelPatientDetails = new JPanel();
		panelPatientDetails.setBorder(
				new TitledBorder(null, "Patient Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPatientDetails.setBounds(width / 2, 12, width / 2 - 20, height - 380);
		panel.add(panelPatientDetails);
		panelPatientDetails.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(31, 44, 139, 15);
		panelPatientDetails.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(31, 71, 115, 15);
		panelPatientDetails.add(lblLastName);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(31, 98, 70, 15);
		panelPatientDetails.add(lblSex);

		JLabel lblEthnicGroup = new JLabel("Ethnic Group");
		lblEthnicGroup.setBounds(31, 123, 129, 15);
		panelPatientDetails.add(lblEthnicGroup);

		JLabel lblVillagetown = new JLabel("Village/Town");
		lblVillagetown.setBounds(31, 150, 115, 15);
		panelPatientDetails.add(lblVillagetown);

		JLabel lblWardNo = new JLabel("Ward No.");
		lblWardNo.setBounds(31, 177, 70, 15);
		panelPatientDetails.add(lblWardNo);

		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setBounds(31, 204, 70, 15);
		panelPatientDetails.add(lblDistrict);

		JLabel lblBirthDatead = new JLabel("Birth Date (AD)");
		lblBirthDatead.setBounds(31, 258, 115, 15);
		panelPatientDetails.add(lblBirthDatead);

		JLabel lblBirthDatebs = new JLabel("Birth Date (BS)");
		lblBirthDatebs.setBounds(31, 285, 115, 15);
		panelPatientDetails.add(lblBirthDatebs);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(31, 231, 70, 15);
		panelPatientDetails.add(lblAge);

		JLabel lblGuardian = new JLabel("Guardian");
		lblGuardian.setBounds(31, 312, 115, 15);
		panelPatientDetails.add(lblGuardian);

		JLabel lblRelation = new JLabel("Relation");
		lblRelation.setBounds(31, 339, 115, 15);
		panelPatientDetails.add(lblRelation);

		txtFldFirstName = new JTextField();
		txtFldFirstName.setBounds(164, 42, 141, 19);
		panelPatientDetails.add(txtFldFirstName);
		txtFldFirstName.setColumns(10);

		txtFldLastName = new JTextField();
		txtFldLastName.setColumns(10);
		txtFldLastName.setBounds(164, 71, 141, 19);
		panelPatientDetails.add(txtFldLastName);

		txtFldSex = new JTextField();
		txtFldSex.setBounds(164, 96, 28, 19);
		panelPatientDetails.add(txtFldSex);
		txtFldSex.setColumns(10);

		txtFldVillage = new JTextField();
		txtFldVillage.setColumns(10);
		txtFldVillage.setBounds(164, 148, 141, 19);
		panelPatientDetails.add(txtFldVillage);

		txtFldBirthDateAD = new JTextField();
		txtFldBirthDateAD.setColumns(10);
		txtFldBirthDateAD.setBounds(164, 256, 141, 19);
		txtFldBirthDateAD.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				String fullBirthDate = txtFldBirthDateAD.getText();
				DateConverter dateConverter = new DateConverter();
				dateConverter.setEnglishDateString(fullBirthDate);

				txtFldYear.setText(dateConverter.getNepaliYear() + "");
				txtFldMonth.setText(dateConverter.getNepaliMonth() + "");
				txtFldDay.setText(dateConverter.getNepaliDate() + "");
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		panelPatientDetails.add(txtFldBirthDateAD);

		txtFldGuardian = new JTextField();
		txtFldGuardian.setColumns(10);
		txtFldGuardian.setBounds(164, 310, 141, 19);
		panelPatientDetails.add(txtFldGuardian);

		txtFieldRelation = new JTextField();
		txtFieldRelation.setColumns(10);
		txtFieldRelation.setBounds(164, 337, 141, 19);
		panelPatientDetails.add(txtFieldRelation);

		txtFldWardNo = new JTextField();
		txtFldWardNo.setColumns(10);
		txtFldWardNo.setBounds(164, 175, 28, 19);
		txtFldWardNo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				checkIntegerNumber(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		panelPatientDetails.add(txtFldWardNo);

		txtFldAge = new JTextField();
		txtFldAge.setColumns(10);
		txtFldAge.setBounds(164, 229, 28, 19);
		txtFldAge.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				checkIntegerNumber(arg0);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		txtFldAge.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
					int age = Integer.parseInt(txtFldAge.getText());
					LocalDateTime localDate = LocalDateTime.now();
					int birthYear = localDate.getYear() - age;
					String fullBirthDate = birthYear + "-" + localDate.getMonthValue() + "-"
							+ localDate.getDayOfMonth();

					DateConverter dateConverter = new DateConverter();
					dateConverter.setEnglishDateString(fullBirthDate);

					txtFldBirthDateAD.setText(fullBirthDate);
					txtFldYear.setText(dateConverter.getNepaliYear() + "");
					txtFldMonth.setText(dateConverter.getNepaliMonth() + "");
					txtFldDay.setText(dateConverter.getNepaliDate() + "");
				} catch (NumberFormatException numFormatExe) {
					JOptionPane.showMessageDialog(null, "Incorrect Age", "Format Mismatch", JOptionPane.ERROR_MESSAGE);
					numFormatExe.printStackTrace();
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		panelPatientDetails.add(txtFldAge);

		txtFldYear = new JTextField();
		txtFldYear.setText("YYYY");
		txtFldYear.setColumns(10);
		txtFldYear.setBounds(164, 281, 43, 19);
		panelPatientDetails.add(txtFldYear);

		txtFldMonth = new JTextField();
		txtFldMonth.setText("MM");
		txtFldMonth.setColumns(10);
		txtFldMonth.setBounds(208, 281, 28, 19);
		panelPatientDetails.add(txtFldMonth);

		txtFldDay = new JTextField();
		txtFldDay.setText("DD");
		txtFldDay.setColumns(10);
		txtFldDay.setBounds(237, 281, 28, 19);
		panelPatientDetails.add(txtFldDay);

		JButton btnAddDetails = new JButton("Add Details");
		btnAddDetails.setBounds(164, 368, 139, 25);
		panelPatientDetails.add(btnAddDetails);
		btnAddDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleBtnAddDetaisEvent(e);
			}
		});

		comboBoxEthnicGroup = new JComboBox<String>();
		comboBoxEthnicGroup.setBounds(164, 118, 157, 24);
		panelPatientDetails.add(comboBoxEthnicGroup);
		for (int index = 0; index < listOfEthnicGrp.size(); index++) {
			comboBoxEthnicGroup.addItem(listOfEthnicGrp.get(index).getFldEthnicGroup());
		}

		comboBoxDistrict = new JComboBox<String>();
		comboBoxDistrict.setBounds(164, 199, 157, 24);
		panelPatientDetails.add(comboBoxDistrict);
		for (int index = 0; index < listOfDistrictName.size(); index++) {
			comboBoxDistrict.addItem(listOfDistrictName.get(index).getFldDistrictName());
		}

		JPanel panelPreviousAdmission = new JPanel();
		panelPreviousAdmission.setBorder(
				new TitledBorder(null, "Previous Admissions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPreviousAdmission.setBounds(12, height - 350, width, (height / 3) + 20);
		panel.add(panelPreviousAdmission);
		panelPreviousAdmission.setLayout(null);

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Patient ID", "Admission No.", "Admission Date", "Year", "Month", "Day", "Age", "Ward",
						"Bed No", "Dis Date", "Dis Year", " Dis Month", "Dis Day", "Discharged?" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		panelPreviousAdmission.setLayout(null);

		tablePreviousAdmission = new JTable(tableModel);
		tablePreviousAdmission.setEnabled(false);
		tablePreviousAdmission.setRowMargin(8);
		tablePreviousAdmission.setRowHeight(30);
		tablePreviousAdmission.setShowVerticalLines(false);
		tablePreviousAdmission.setShowHorizontalLines(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < 14; ++i) {
			tablePreviousAdmission.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		tablePreviousAdmission.getColumnModel().getColumn(0).setWidth(20);
		tablePreviousAdmission.getColumnModel().getColumn(0).setResizable(false);
		scrollPaneAdmissions = new JScrollPane(tablePreviousAdmission);
		scrollPaneAdmissions.setBorder(new EmptyBorder(0, 0, 0, 0));
		tablePreviousAdmission.setBorder(null);
		// tablePreviousAdmission.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneAdmissions.setBounds(55, 25, width - 200, height / 4);
		panelPreviousAdmission.add(scrollPaneAdmissions);

	}

	/**
	 * @author Sijan
	 * @detail Function to add new InPatient details
	 * @param event
	 * @return void
	 */
	public void handleBtnAddInfoEvent(ActionEvent event) {
		final int NUM_FIELDS = 7;
		int numCorrectFields = 0;
		String errorMsg = "";
		int patientId = 0;
		String admissionDate = "";
		short nepaliYear = 0;
		short nepaliMonth = 0;
		short nepaliDay = 0;
		short bedNo = 0;
		Double estimatedAmt = 0.0;

		if (txtFldPatientId.getText().isEmpty()) {
			errorMsg += "Patient id is missing.\n";
		} else {
			numCorrectFields++;
			patientId = Integer.parseInt(txtFldPatientId.getText());
		}

		if (txtFldAdmissionDate.getText().isEmpty()) {
			errorMsg += "Admission Date is empty\n";
		} else {
			numCorrectFields++;
			admissionDate = txtFldAdmissionDate.getText();
		}

		if (txtFldAdmissionYear.getText().isEmpty()) {
			errorMsg += "Admission Year(BS) is empty\n";
		} else {
			numCorrectFields++;
			nepaliYear = Short.parseShort(txtFldAdmissionYear.getText());
		}

		if (txtFldAdmissionMonth.getText().isEmpty()) {
			errorMsg += "Admission Month(BS) is empty\n";
		} else {
			numCorrectFields++;
			nepaliMonth = Short.parseShort(txtFldAdmissionMonth.getText());
		}

		if (txtFldAdmissionDay.getText().isEmpty()) {
			errorMsg += "Admission Day(BS) is empty\n";
		} else {
			numCorrectFields++;
			nepaliDay = Short.parseShort(txtFldAdmissionDay.getText());
		}

		if (txtFldPatientCount.getText().isEmpty()) {
			errorMsg += "Patient Count is empty\n";
		} else {
			numCorrectFields++;
			bedNo = Short.parseShort(txtFldPatientCount.getText());
		}

		if (txtFldEstimatedAmount.getText().isEmpty()) {
			errorMsg += "Estimated Amount is empty\n";
		} else {
			try {
				numCorrectFields++;
				estimatedAmt = Double.parseDouble(txtFldEstimatedAmount.getText());
			} catch (NumberFormatException ex) {
				errorMsg += "Format Mismath for estimated amount";
			}
		}

		String wardId = ((KeyValue) comboBoxWardId.getSelectedItem()).getValue();

		if (numCorrectFields < NUM_FIELDS) {
			JOptionPane.showMessageDialog(null, errorMsg, "Incomplete Data Entered", JOptionPane.ERROR_MESSAGE);
		} else {
			InPatient new_inPatient = new InPatient();
			new_inPatient.setFldPatientId(patientId);
			new_inPatient.setFldAdmissionDate(admissionDate);
			new_inPatient.setFldAdmissionYear(nepaliYear);
			new_inPatient.setFldAdmissionMonth(nepaliMonth);
			new_inPatient.setFldAdmissionDay(nepaliDay);
			new_inPatient.setFldWardID(wardId);
			new_inPatient.setFldBedNo(bedNo);
			new_inPatient.setFldEstimatedAmt(estimatedAmt);
			logger.info(new_inPatient.toString());

			int return_val = patientDAOImpl.addInPatient(new_inPatient);
			if (return_val < 0) {
				JOptionPane.showMessageDialog(null, "Error Creating new Patient", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, return_val + "", "Patient Admission Number",
						JOptionPane.INFORMATION_MESSAGE);
				clear();
				populateTable(patientId);
			}
		}
	}

	/**
	 * @author Sijan
	 * @detail Function to add new Patient details
	 * @param ActionEvent
	 *            evt
	 * @return void
	 */
	public void handleBtnAddDetaisEvent(ActionEvent evt) {
		final int NUM_FIELDS = 12;
		int numCorrectFields = 0;
		String errorMsg = "";
		int patientId = 0;
		String firstName = "";
		String lastName = "";
		String sex = "";
		String villageTown = "";
		short wardNo = 0;
		String birthDateAD = "";
		short birthDateYear = 0;
		short birthDateMonth = 0;
		short birthDateDay = 0;
		String guardian = "";
		String relation = "";

		if (txtFldPatientId.getText().isEmpty()) {
			errorMsg += "Patient id is missing.\n";
		} else {
			numCorrectFields++;
			patientId = Integer.parseInt(txtFldPatientId.getText());
		}

		if (txtFldFirstName.getText().isEmpty()) {
			errorMsg += "Patient first name is missing.\n";
		} else {
			numCorrectFields++;
			firstName = txtFldFirstName.getText();
		}

		if (txtFldLastName.getText().isEmpty()) {
			errorMsg += "Patient last name is missing.\n";
		} else {
			numCorrectFields++;
			lastName = txtFldLastName.getText();
		}

		if (txtFldSex.getText().isEmpty()) {
			errorMsg += "Patient gender is missing.\n";
		} else {
			numCorrectFields++;
			sex = txtFldSex.getText();
		}

		if (txtFldVillage.getText().isEmpty()) {
			errorMsg += "Village/Town is missing.\n";
		} else {
			numCorrectFields++;
			villageTown = txtFldVillage.getText();
		}

		if (txtFldWardNo.getText().isEmpty()) {
			errorMsg += "Ward No is missing.\n";
		} else {
			numCorrectFields++;
			wardNo = Short.parseShort(txtFldWardNo.getText());
		}

		if (txtFldBirthDateAD.getText().isEmpty()) {
			errorMsg += "Birth Date AD is missing.\n";
		} else {
			numCorrectFields++;
			birthDateAD = txtFldBirthDateAD.getText();
		}

		if (txtFldYear.getText().isEmpty()) {
			errorMsg += "Birth year is missing.\n";
		} else {
			numCorrectFields++;
			birthDateYear = Short.parseShort(txtFldYear.getText());
		}

		if (txtFldMonth.getText().isEmpty()) {
			errorMsg += "Birth month is missing.\n";
		} else {
			numCorrectFields++;
			birthDateMonth = Short.parseShort(txtFldMonth.getText());
		}

		if (txtFldDay.getText().isEmpty()) {
			errorMsg += "Birth day is missing.\n";
		} else {
			numCorrectFields++;
			birthDateDay = Short.parseShort(txtFldDay.getText());
		}

		if (txtFldGuardian.getText().isEmpty()) {
			errorMsg += "Guardian is missing.\n";
		} else {
			numCorrectFields++;
			guardian = txtFldGuardian.getText();
		}

		if (txtFieldRelation.getText().isEmpty()) {
			errorMsg += "Relation is missing.\n";
		} else {
			numCorrectFields++;
			relation = txtFieldRelation.getText();
		}

		if (numCorrectFields < NUM_FIELDS) {
			JOptionPane.showMessageDialog(null, errorMsg, "Incomplete Data Entered", JOptionPane.ERROR_MESSAGE);
		} else {
			String ethnicGrp = (String) comboBoxEthnicGroup.getSelectedItem();
			String district = (String) comboBoxDistrict.getSelectedItem();
			Patient new_patient = new Patient();
			new_patient.setFldPatientId(patientId);
			new_patient.setFldPatientName(firstName);
			new_patient.setFldPatientLastName(lastName);
			new_patient.setFldSex(sex);
			new_patient.setFldEthnicGroup(ethnicGrp);
			new_patient.setFldVillageTown(villageTown);
			new_patient.setFldWardNo(wardNo);
			new_patient.setFldDistrict(district);
			new_patient.setFldBirthDate(birthDateAD);
			new_patient.setFldYearBorn(birthDateYear);
			new_patient.setFldMonthBorn(birthDateMonth);
			new_patient.setFldDayBorn(birthDateDay);
			new_patient.setFldGuardiansName(guardian);
			new_patient.setFldRelation(relation);

			logger.info(new_patient.toString());
			AppMessage appMsg = patientDAOImpl.addPatient(new_patient);
			if (!appMsg.getCODE().equals("0")) {
				logger.debug(appMsg.getMSG());
				JOptionPane.showMessageDialog(this, appMsg.getMSG(), "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "New Patient Added", "Success", JOptionPane.INFORMATION_MESSAGE);
				clear();
			}
		}
	}

	/**
	 * @author Sijan
	 * @detail Clears all field in JPanel
	 */
	public void clear() {
		txtFldAdmissionNo.setText("");
		txtFldAdmissionDate.setText("");
		txtFldAdmissionYear.setText("");
		txtFldAdmissionMonth.setText("");
		txtFldAdmissionDay.setText("");
		txtFldDischargeDate.setText("");
		txtFldDischargeYear.setText("");
		txtFldDischargeMonth.setText("");
		txtFldDischargeDay.setText("");
		txtFldEstimatedAmount.setText("");
		txtFldPatientCount.setText("");
		txtFldBedNo.setText("");
		txtFldFirstName.setText("");
		txtFldLastName.setText("");
		txtFldSex.setText("");
		txtFldVillage.setText("");
		txtFldBirthDateAD.setText("");
		txtFldGuardian.setText("");
		txtFieldRelation.setText("");
		txtFldWardNo.setText("");
		txtFldAge.setText("");
		txtFldYear.setText("");
		txtFldMonth.setText("");
		txtFldDay.setText("");

		DefaultTableModel model = (DefaultTableModel) tablePreviousAdmission.getModel();
		model.setRowCount(0);
	}

	/**
	 * @author Sijan
	 * @detail Function to load InPatient Admission Details into JTable
	 * @param patientId
	 * @return void
	 */
	public void populateTable(int patientId) {
		ArrayList<InPatient> inpatient_list = patientDAOImpl.searchInPatient(patientId);
		System.out.println(inpatient_list.size());
		DefaultTableModel tblPreviousAdmisssionModel = (DefaultTableModel) tablePreviousAdmission.getModel();
		Object tblRowData[] = new Object[14];

		for (int i = 0; i < inpatient_list.size(); i++) {
			tblRowData[0] = inpatient_list.get(i).getFldPatientId();
			tblRowData[1] = inpatient_list.get(i).getFldAdmissionNo();
			tblRowData[2] = inpatient_list.get(i).getFldAdmissionDate();
			tblRowData[3] = inpatient_list.get(i).getFldAdmissionYear();
			tblRowData[4] = inpatient_list.get(i).getFldAdmissionMonth();
			tblRowData[5] = inpatient_list.get(i).getFldAdmissionDay();
			tblRowData[6] = inpatient_list.get(i).getFldAge();
			tblRowData[7] = inpatient_list.get(i).getFldWardID();
			tblRowData[8] = inpatient_list.get(i).getFldBedNo();
			tblRowData[9] = inpatient_list.get(i).getFldDishargeDate();
			tblRowData[10] = inpatient_list.get(i).getFldDischargeYear();
			tblRowData[11] = inpatient_list.get(i).getFldDischargeMonth();
			tblRowData[12] = inpatient_list.get(i).getFldDischargeDay();
			tblRowData[13] = inpatient_list.get(i).isFldDischarged() == false ? "NO" : "YES";

			tblPreviousAdmisssionModel.addRow(tblRowData);
		}
		logger.info(inpatient_list.toString());
	}

	/**
	 * @author Sijan
	 * @detail Function to check for integer numerical value
	 * @param keyEvt
	 * @return void
	 */
	public void checkIntegerNumber(KeyEvent keyEvt) {
		char c = keyEvt.getKeyChar();
		if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
			getToolkit().beep();
			keyEvt.consume();
		}
	}
}
