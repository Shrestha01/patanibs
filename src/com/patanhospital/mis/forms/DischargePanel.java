package com.patanhospital.mis.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.impl.PatientDAOImpl;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.util.DateConverter;

public class DischargePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1137719014793669202L;
	private JTextField txtFldAdmissionNo;
	private JTextField txtFldPatientId;
	private JTextField txtFldDischargeDate;
	private JTextField txtFldAdmissionDate;
	private JTextField txtFldAdmissionYear;
	private JTextField txtFldDischargeYear;
	private JTextField txtFldAdmissionMonth;
	private JTextField txtFldAdmissionDay;
	private JTextField txtFldDischargeMonth;
	private JTextField txtFldDischargeDay;
	private JTextField txtPatientName;
	private JTextField txtFldWard;
	private JTextField txtFldBedNo;
	private JTextField txtFldEstimatedAmount;
	private JTable tableChargeEntry;
	private JTable tableDepositEntry;
	private DefaultTableModel tableModelChargeEntry;
	private DefaultTableModel tableModelDepositEntry;
	private JScrollPane scrollPaneChargeEntry;
	private JScrollPane scrollPaneDepositEntry;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 150;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;
	private JTextField txtFldAmountCharged;
	private JTextField txtFldAmountDeposited;
	private JTextField txtFldCashRefund;
	private PatientDAOImpl patient_ID;
	private DatabaseAssistant databaseAssistant = null;
	private static final Logger logger = LoggerFactory.getLogger(DischargePanel.class);
	int admissionID;
	int active;

	/**
	 * Create the panel.
	 */
	public DischargePanel() {
		databaseAssistant = new DatabaseAssistant();
		patient_ID = new PatientDAOImpl();
		setBounds(new Rectangle(10, 10, 0, 0));
		setLayout(null);

		setSize(width, height);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(0, 0, width, height);

		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel recordEntryPanel = new JPanel();
		recordEntryPanel.setPreferredSize(new Dimension(10, 200));
		recordEntryPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(recordEntryPanel, BorderLayout.NORTH);
		recordEntryPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Admission No");
		lblNewLabel.setBounds(12, 12, 151, 15);

		recordEntryPanel.add(lblNewLabel);

		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setBounds(319, 12, 106, 15);
		recordEntryPanel.add(lblPatientId);

		JLabel lblAdmissionDate = new JLabel("Admission Date");
		lblAdmissionDate.setBounds(319, 53, 131, 15);
		recordEntryPanel.add(lblAdmissionDate);

		txtFldAdmissionNo = new JTextField();
		txtFldAdmissionNo.setBounds(130, 10, 114, 19);
		recordEntryPanel.add(txtFldAdmissionNo);
		txtFldAdmissionNo.setColumns(10);
		txtFldAdmissionNo.addKeyListener(new KeyListener() {

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

		txtFldPatientId = new JTextField();
		txtFldPatientId.setEditable(false);
		txtFldPatientId.setColumns(10);
		txtFldPatientId.setBounds(416, 10, 114, 19);
		recordEntryPanel.add(txtFldPatientId);

		JLabel lblDischargeDate = new JLabel("Discharge Date");
		lblDischargeDate.setBounds(319, 80, 131, 15);

		recordEntryPanel.add(lblDischargeDate);

		txtFldDischargeDate = new JTextField();
		txtFldDischargeDate.setText("System Date");
		txtFldDischargeDate.setColumns(10);
		txtFldDischargeDate.setBounds(468, 78, 114, 19);
		txtFldDischargeDate.setEditable(false);
		recordEntryPanel.add(txtFldDischargeDate);

		txtFldAdmissionDate = new JTextField();
		txtFldAdmissionDate.setColumns(10);
		txtFldAdmissionDate.setBounds(468, 51, 114, 19);
		txtFldAdmissionDate.setEditable(false);
		recordEntryPanel.add(txtFldAdmissionDate);

		JLabel label1 = new JLabel("Year(BS)");
		label1.setBounds(643, 53, 70, 15);
		recordEntryPanel.add(label1);

		txtFldAdmissionYear = new JTextField();
		txtFldAdmissionYear.setBounds(731, 51, 70, 19);
		txtFldAdmissionYear.setEditable(false);
		recordEntryPanel.add(txtFldAdmissionYear);
		txtFldAdmissionYear.setColumns(5);

		JLabel label = new JLabel("Year(BS)");
		label.setBounds(643, 82, 70, 15);
		recordEntryPanel.add(label);

		txtFldDischargeYear = new JTextField();
		txtFldDischargeYear.setColumns(5);
		txtFldDischargeYear.setBounds(731, 80, 70, 19);
		txtFldDischargeYear.setEditable(false);
		recordEntryPanel.add(txtFldDischargeYear);

		JLabel lblMonth_1 = new JLabel("Month");
		lblMonth_1.setBounds(815, 51, 70, 15);
		recordEntryPanel.add(lblMonth_1);

		txtFldAdmissionMonth = new JTextField();
		txtFldAdmissionMonth.setColumns(5);
		txtFldAdmissionMonth.setBounds(903, 49, 70, 19);
		txtFldAdmissionMonth.setEditable(false);
		recordEntryPanel.add(txtFldAdmissionMonth);

		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(989, 53, 70, 15);
		recordEntryPanel.add(lblDay);

		txtFldAdmissionDay = new JTextField();
		txtFldAdmissionDay.setColumns(5);
		txtFldAdmissionDay.setBounds(1077, 51, 50, 19);
		txtFldAdmissionDay.setEditable(false);
		recordEntryPanel.add(txtFldAdmissionDay);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(819, 80, 70, 15);
		recordEntryPanel.add(lblMonth);

		txtFldDischargeMonth = new JTextField();
		txtFldDischargeMonth.setColumns(5);
		txtFldDischargeMonth.setBounds(903, 78, 70, 19);
		txtFldDischargeMonth.setEditable(false);
		recordEntryPanel.add(txtFldDischargeMonth);

		JLabel lblDay_1 = new JLabel("Day");
		lblDay_1.setBounds(989, 78, 70, 15);
		recordEntryPanel.add(lblDay_1);

		txtFldDischargeDay = new JTextField();
		txtFldDischargeDay.setColumns(5);
		txtFldDischargeDay.setBounds(1077, 76, 50, 19);
		txtFldDischargeDay.setEditable(false);
		recordEntryPanel.add(txtFldDischargeDay);

		txtPatientName = new JTextField();
		txtPatientName.setText("Patient Name");
		txtPatientName.setBounds(643, 10, 371, 19);
		txtPatientName.setEditable(false);
		recordEntryPanel.add(txtPatientName);
		txtPatientName.setColumns(10);

		JLabel lblWard = new JLabel("Ward");
		lblWard.setBounds(380, 125, 70, 15);

		recordEntryPanel.add(lblWard);

		txtFldWard = new JTextField();

		txtFldWard.setColumns(10);
		txtFldWard.setBounds(468, 123, 114, 19);
		txtFldWard.setEditable(false);
		recordEntryPanel.add(txtFldWard);

		txtFldBedNo = new JTextField();

		txtFldBedNo.setColumns(3);
		txtFldBedNo.setBounds(735, 123, 70, 19);
		txtFldBedNo.setEditable(false);
		recordEntryPanel.add(txtFldBedNo);

		JLabel lblBedNo = new JLabel("Bed No.");
		lblBedNo.setBounds(647, 125, 70, 15);

		recordEntryPanel.add(lblBedNo);

		JLabel lblEstimatedAmount = new JLabel("Estimated Amount");
		lblEstimatedAmount.setBounds(815, 125, 158, 15);
		recordEntryPanel.add(lblEstimatedAmount);

		txtFldEstimatedAmount = new JTextField();
		txtFldEstimatedAmount.setColumns(3);
		txtFldEstimatedAmount.setBounds(963, 123, 114, 19);
		txtFldEstimatedAmount.setEditable(false);
		recordEntryPanel.add(txtFldEstimatedAmount);

		JPanel DischargedPanel = new JPanel();
		DischargedPanel.setBounds(28, 78, 236, 46);
		recordEntryPanel.add(DischargedPanel);
		DischargedPanel.setLayout(null);

		JLabel lblDischarged = new JLabel("DISCHARGED");
		lblDischarged.setHorizontalAlignment(SwingConstants.CENTER);
		lblDischarged.setBounds(0, 0, 236, 46);

		DischargedPanel.add(lblDischarged);
		DischargedPanel.setVisible(false);

		tableModelChargeEntry = new DefaultTableModel(new Object[][] {},
				new String[] { "Charge ID", "Charge Heading No.", "Charge Amount", "Charge Group", "Charge Remark" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tableModelDepositEntry = new DefaultTableModel(new Object[][] {},
				new String[] { "Receipt No.", "Deposit Heading Name", "Deposit Amount", "Deposit Remark" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		JPanel chargeEntryPanel = new JPanel();
		chargeEntryPanel.setPreferredSize(new Dimension(width / 2, height - 40));
		panel.add(chargeEntryPanel, BorderLayout.WEST);
		chargeEntryPanel.setLayout(null);

		tableChargeEntry = new JTable(tableModelChargeEntry);
		scrollPaneChargeEntry = new JScrollPane(tableChargeEntry);
		scrollPaneChargeEntry.setBounds(0, 47, 608, 200);
		chargeEntryPanel.add(scrollPaneChargeEntry);

		JPanel depositEntryPanel = new JPanel();
		chargeEntryPanel.setBounds(0, 10, width / 2, height - 40);

		JLabel lblCharge = new JLabel("Charge");
		lblCharge.setFont(new Font("Dialog", Font.BOLD, 22));
		lblCharge.setBounds(25, 12, 152, 36);
		chargeEntryPanel.add(lblCharge);

		JLabel lblAmountCharged = new JLabel("Amount Charged");
		lblAmountCharged.setBounds(336, 20, 162, 15);
		chargeEntryPanel.add(lblAmountCharged);

		txtFldAmountCharged = new JTextField();
		txtFldAmountCharged.setColumns(10);
		txtFldAmountCharged.setBounds(482, 16, 114, 19);
		txtFldAmountCharged.setEditable(false);
		chargeEntryPanel.add(txtFldAmountCharged);

		JLabel lblCashRefund = new JLabel("Cash Refund");
		lblCashRefund.setBounds(25, 278, 114, 15);
		chargeEntryPanel.add(lblCashRefund);

		txtFldCashRefund = new JTextField();
		txtFldCashRefund.setBounds(157, 276, 114, 19);
		txtFldCashRefund.setEditable(false);
		chargeEntryPanel.add(txtFldCashRefund);
		txtFldCashRefund.setColumns(10);
		
		JButton btnPrintBill = new JButton("Print Bill");
		btnPrintBill.setBounds(157, 414, 97, 25);
		chargeEntryPanel.add(btnPrintBill);
		
		JButton btnPrintChargeDetail = new JButton("Print Charge Detail");
		btnPrintChargeDetail.setBounds(282, 414, 168, 25);
		chargeEntryPanel.add(btnPrintChargeDetail);
		
		JButton btnPrintDepositDetails = new JButton("Print Deposit Details");
		btnPrintDepositDetails.setBounds(482, 414, 175, 25);
		chargeEntryPanel.add(btnPrintDepositDetails);
		panel.add(depositEntryPanel, BorderLayout.CENTER);
		depositEntryPanel.setLayout(null);

		tableDepositEntry = new JTable(tableModelDepositEntry);
		scrollPaneDepositEntry = new JScrollPane(tableDepositEntry);
		scrollPaneDepositEntry.setBounds(0, 48, 608, 200);
		depositEntryPanel.add(scrollPaneDepositEntry);

		JLabel lblDeposit = new JLabel("Deposit");
		lblDeposit.setFont(new Font("Dialog", Font.BOLD, 22));
		lblDeposit.setBounds(12, 12, 152, 36);
		depositEntryPanel.add(lblDeposit);

		JLabel lblAmountDeposited = new JLabel("Amount Deposited");
		lblAmountDeposited.setBounds(339, 19, 152, 15);
		depositEntryPanel.add(lblAmountDeposited);

		txtFldAmountDeposited = new JTextField();
		txtFldAmountDeposited.setColumns(10);
		txtFldAmountDeposited.setBounds(482, 17, 114, 19);
		txtFldAmountDeposited.setEditable(false);
		depositEntryPanel.add(txtFldAmountDeposited);

		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.YELLOW);
		panelButton.setPreferredSize(new Dimension(20, 80));
		panel.add(panelButton, BorderLayout.SOUTH);
		panelButton.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(533, 12, 135, 33);
		panelButton.add(panel_1);
		panel_1.setLayout(null);

		JButton btnCalculate = new JButton("Discharge");
		btnCalculate.setHorizontalAlignment(SwingConstants.CENTER);
		btnCalculate.setBounds(0, 0, 135, 33);
		panel_1.add(btnCalculate);
		btnCalculate.setEnabled(false);

		txtFldAdmissionNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				if (txtFldAdmissionNo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter AdmissionID first", "Format Mismatch",
							JOptionPane.ERROR_MESSAGE);

				} else {
					admissionID = Integer.parseInt(txtFldAdmissionNo.getText());
					String sql = "SELECT * FROM tblInPatient,tblPatient where tblInPatient.fldPatientID =tblPatient.fldPatientID AND tblInPatient.fldAdmissionNo='"
							+ admissionID + "'";

					DBMessage dB = databaseAssistant.execQuery(sql);
					if (!dB.getCODE().equals("0")) {
						System.out.println("error");
					} else {
						ResultSet rs = dB.getResultSet();
						try {
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");
							LocalDateTime localDate = LocalDateTime.now();
							DateConverter dateConverter = new DateConverter();
							dateConverter.setEnglishDateString(dtf.format(localDate));
							if (rs.next()) {

								active = Integer.parseInt(rs.getString("tblInPatient.fldDischarged"));
								txtFldPatientId.setText(rs.getString("fldPatientID"));
								txtPatientName.setText(rs.getString("fldName") + " " + rs.getString("fldLastname"));
								txtFldAdmissionDate.setText(rs.getString("fldAdmissionDate"));
								txtFldAdmissionYear.setText(rs.getString("fldAdmissionYear"));
								txtFldAdmissionMonth.setText(rs.getString("fldAdmissionMonth"));
								txtFldAdmissionDay.setText(rs.getString("fldAdmissionDay"));
								txtFldDischargeDate.setText(dtf.format(localDate));
								txtFldDischargeYear.setText(dateConverter.getNepaliYear() + "");
								txtFldDischargeMonth.setText(dateConverter.getNepaliMonth() + "");
								txtFldDischargeDay.setText(dateConverter.getNepaliDate() + "");
								txtFldWard.setText(rs.getString("fldWardID"));
								txtFldBedNo.setText(rs.getString("fldBedNo"));
								txtFldEstimatedAmount.setText(rs.getString("fldEstimatedAmt"));
								// System.out.println(rs.getString("fldDischarged"));
								if (rs.getString("fldDischarged").equals("1")) {
									DischargedPanel.setVisible(true);

								}
							} else {
								JOptionPane.showMessageDialog(null, "Patient Not Found", "Format Mismatch",
										JOptionPane.ERROR_MESSAGE);

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					String select = "SELECT tblIPbillCreditDetail.fldReceiptNo,tblIPbillCreditDetail.fldCrHeadingRemark,tblCrHeading.fldCrHeadingName,tblIPbillCreditDetail.fldAmount FROM tblIPbillCreditDetail,tblIPbillCredit,tblCrHeading WHERE tblIPbillCreditDetail.fldReceiptNo=tblIPbillCredit.fldReceiptNo AND tblCrHeading.fldCrHeadingNo=tblIPbillCreditDetail.fldCrHeadingNo AND fldAdmissionNo='"
							+ admissionID + "'";
					DBMessage db = databaseAssistant.execQuery(select);

					if (!db.getCODE().equals("0")) {

					} else {

						try {
							ResultSet rs = db.getResultSet();
							while (rs.next()) {
								tableModelDepositEntry.insertRow(0,
										new Object[] { rs.getString("fldReceiptNo"), rs.getString("fldCrHeadingName"),
												rs.getString("fldAmount"), rs.getString("fldCrHeadingRemark") });

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					int rowCount = tableModelDepositEntry.getRowCount();
					Double depositAmt = 0.0;
					for (int i = 0; i < rowCount; ++i) {
						depositAmt = depositAmt
								+ Double.parseDouble(tableModelDepositEntry.getValueAt(i, 2).toString());
					}

					txtFldAmountDeposited.setText(depositAmt + "");

					String chargeQuerry = "SELECT tblIPchargeDetail.fldChargeID,tblDrHeading.fldDrHeadingName,tblIPchargeDetail.fldAmount,tblIPchargeDetail.fldChargeHeadingRemark,tblDrHeading.fldDrGroup FROM tblDrHeading,tblIPcharge,tblIPchargeDetail WHERE tblIPchargeDetail.fldChargeID=tblIPcharge.fldChargeID AND tblDrHeading.fldDrHeadingNo=tblIPchargeDetail.fldChargeHeadingNo AND tblIPcharge.fldAdmissionNo='"
							+ admissionID + "'";
					DBMessage chargeEntryOBj = databaseAssistant.execQuery(chargeQuerry);
					if (!chargeEntryOBj.getCODE().equals("0")) {
						System.out.println(chargeEntryOBj.getMSG());
					} else {

						try {
							ResultSet rs = chargeEntryOBj.getResultSet();
							while (rs.next()) {
								tableModelChargeEntry.insertRow(0,
										new Object[] { rs.getString("fldChargeID"), rs.getString("fldDrHeadingName"),
												rs.getString("tblIPchargeDetail.fldAmount"), rs.getString("fldDrGroup"),
												rs.getString("fldChargeHeadingRemark") });
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					int chargerowCount = tableModelChargeEntry.getRowCount();
					double chargeAmt = 0;
					for (int i = 0; i < chargerowCount; ++i) {
						chargeAmt = chargeAmt + Double.parseDouble(tableModelChargeEntry.getValueAt(i, 2).toString());

					}
					txtFldAmountCharged.setText(chargeAmt + "");

					double refundAmt = depositAmt - chargeAmt;

					txtFldCashRefund.setText(refundAmt + "");
					if (txtFldCashRefund.getText().equals("0.0") && active == 0) {
						btnCalculate.setEnabled(true);
					} else {
						btnCalculate.setEnabled(false);
					}

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				DischargedPanel.setVisible(false);
				clear();

			}
		});

		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(admissionID);
				String sql = "UPDATE tblInPatient SET fldDischarged='1' WHERE fldAdmissionNo='" + admissionID + "'";

				AppMessage appMsg = databaseAssistant.execUpdate(sql);
				logger.info(appMsg.getMSG());
				if (!appMsg.getCODE().equals("0")) {
					DischargedPanel.setVisible(false);
					JOptionPane.showMessageDialog(null, "Something Went Wrong!", "FAILED", JOptionPane.ERROR_MESSAGE);
				} else {
					DischargedPanel.setVisible(true);
					JOptionPane.showMessageDialog(null, "Patient Discharged Successfully!", "SUCCESS",
							JOptionPane.INFORMATION_MESSAGE);
				}
				clear();

			}
		});

	}

	public void clear() {
		txtFldAdmissionNo.setText("");
		txtFldPatientId.setText("");
		txtFldDischargeDate.setText("");
		txtFldAdmissionDate.setText("");
		txtFldAdmissionYear.setText("");
		txtFldDischargeYear.setText("");
		txtFldAdmissionMonth.setText("");
		txtFldAdmissionDay.setText("");
		txtFldDischargeMonth.setText("");
		txtFldDischargeDay.setText("");
		txtPatientName.setText("");
		txtFldWard.setText("");
		txtFldBedNo.setText("");
		txtFldEstimatedAmount.setText("");

		tableModelDepositEntry.setRowCount(0);
		tableModelChargeEntry.setRowCount(0);

	}

	public void checkIntegerNumber(KeyEvent keyEvt) {
		char c = keyEvt.getKeyChar();
		if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
			getToolkit().beep();
			keyEvt.consume();
		}
	}
}
