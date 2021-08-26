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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.impl.DepositCRHeadingDAOImpl;
import com.patanhospital.mis.dao.impl.PatientDAOImpl;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.forms.report.ReportView;
import com.patanhospital.mis.model.CashBillModel;
import com.patanhospital.mis.model.InPatient;
import com.patanhospital.mis.server.Session;
import com.patanhospital.mis.util.DateConverter;
import com.patanhospital.mis.util.KeyValue;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CashDepositPanel extends JPanel {
	/**
	 * 
	 */

	private static final long serialVersionUID = -6906010779785003856L;
	private JTextField txtFldAdmissionDate;
	private JTextField txtFldPatientId;
	private JTextField txtFldAdmissionNo;
	private JTextField txtFldAdmissionYear;
	private JTextField txtFldAdmissionMonth;
	private JTextField txtFldAdmissionDay;
	private JTextField txtFldEstimatedAmount;
	private JLabel lblYearbs;
	private JTextField txtFldChargeYear;
	private JTextField txtFldChargeMonth;
	private JTextField txtFldChargeDay;
	private JTextField txtFldPatientName;
	private JTable tblAllCharges;
	private JTextField txtAutoNumber;
	private JTable tableNewEntry;
	private JLabel lblTotalCharges;
	private JButton btnPrintAllCharges;
	private JTextField txtFldCreditAmount;
	private JTextField txtFldCreditRemark;
	private JTextField txtFldChargeDate;
	private JButton btnNewReceiptAdd;
	private JScrollPane scrollPaneNewCharge;

	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPaneCharges;
	private JLabel lblNewEntry;
	private JLabel lblPreviousTranscations;
	private JLabel lblTotalDeposit;
	private JTextField txtFldTotalDeposit;
	private DepositCRHeadingDAOImpl depositDAO;
	private PatientDAOImpl patientDAO;
	private DatabaseAssistant dbAssistant;

	String receiptHeadingValue;
	int tableModelRowCount;
	String msg = "";
	private JTextField txtFldEnterAmount;
	private JTextField txtFldCashReturn;
	private JTextField txtFldReceiptTotal;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 150;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;
	private int admissionID;

	/**
	 * Create the panel.
	 */
	public CashDepositPanel(Session session) {
		System.out.println("inside cash deposit panel");
		System.out.println(session.getUserId());
		depositDAO = new DepositCRHeadingDAOImpl();
		patientDAO = new PatientDAOImpl();
		dbAssistant = new DatabaseAssistant();
		setLayout(null);
		setSize(width, height);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setSize(width, height);
		add(panel);
		panel.setLayout(null);

		JLabel lblAdmissionNo = new JLabel("Admission No.");
		lblAdmissionNo.setBounds(54, 50, 100, 25);
		panel.add(lblAdmissionNo);

		txtFldAdmissionNo = new JTextField();
		txtFldAdmissionNo.setBounds(170, 48, 110, 25);
		txtFldAdmissionNo.setColumns(10);
		panel.add(txtFldAdmissionNo);
		txtFldAdmissionNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkIntegerNumber(e);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

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
		txtFldPatientName.setBounds(737, 50, 232, 25);
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

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Receipt No.", "Credit Heading Name", "Amount", "Heading Group", "Credit Remark" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tableModel1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Receipt No.", "Credit Heading Name", "Amount", "Heading Group", "Credit Remark" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tblAllCharges = new JTable(tableModel);
		tblAllCharges.setBounds(54, 443, 927, 141);

		scrollPaneCharges = new JScrollPane(tblAllCharges);
		scrollPaneCharges.setBounds(54, 466, 808, 126);
		panel.add(scrollPaneCharges);

		JLabel lblChargeId = new JLabel("Receipt ID");
		lblChargeId.setBounds(54, 178, 84, 15);
		panel.add(lblChargeId);

		txtAutoNumber = new JTextField();
		txtAutoNumber.setText("");
		txtAutoNumber.setEditable(false);
		txtAutoNumber.setBounds(170, 176, 110, 19);
		panel.add(txtAutoNumber);
		txtAutoNumber.setColumns(10);

		tableNewEntry = new JTable();
		tableNewEntry.setModel(tableModel1);
		tableNewEntry.setBounds(54, 290, 927, 141);
		tableNewEntry.setCellSelectionEnabled(false);
		tableNewEntry.setDragEnabled(false);

		scrollPaneNewCharge = new JScrollPane(tableNewEntry);
		scrollPaneNewCharge.setBounds(54, 308, 808, 123);
		panel.add(scrollPaneNewCharge);

		lblTotalCharges = new JLabel("Total Charges");
		lblTotalCharges.setBounds(874, 309, 136, 15);
		panel.add(lblTotalCharges);

		JButton btnCalculate = new JButton("Done");
		btnCalculate.setBounds(222, 621, 117, 25);
		panel.add(btnCalculate);
		btnCalculate.setEnabled(false);

		btnPrintAllCharges = new JButton("Print All Receipts");
		btnPrintAllCharges.setBounds(434, 621, 166, 25);
		btnPrintAllCharges.setEnabled(false);
		btnPrintAllCharges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<CashBillModel> cashList = new ArrayList<CashBillModel>();
				double totalamt = 0;
				int tableCount = tableModel1.getRowCount();
				for (int i = 0; i < tableCount; ++i) {
					String crHeadVal[] = tableModel1.getValueAt(i, 1).toString().split(",");
					int fldCrHeadingNo = Integer.parseInt(crHeadVal[1]);
					Double crAmount = Double.parseDouble(tableModel1.getValueAt(i, 2).toString());
					if (fldCrHeadingNo == 12) {
						crAmount = crAmount - 2 * crAmount;
					}
					totalamt = totalamt + crAmount;

					CashBillModel cashBill = new CashBillModel();
					cashBill.setPaymentHeading(crHeadVal[0]);
					cashBill.setPaymentNo(fldCrHeadingNo);
					cashBill.setAmount(crAmount);
					cashBill.setHeadingGrp(tableModel1.getValueAt(i, 3).toString());
					cashBill.setRemark(tableModel1.getValueAt(i, 4).toString());

					cashList.add(cashBill);
				}
				try {
					JasperReport report = JasperCompileManager.compileReport("res/inpatient_receipt.jrxml");
					JRDataSource source = new JREmptyDataSource();
					JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(cashList);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("receiptNo", txtAutoNumber.getText());
					map.put("date", txtFldAdmissionDate.getText());
					map.put("bs", txtFldAdmissionYear.getText() + "/" + txtFldAdmissionMonth.getText() + "/"
							+ txtFldAdmissionDay.getText());
					map.put("cashier", session.getUserName());
					map.put("patientId", txtFldPatientId.getText());
					map.put("name", txtFldPatientName.getText());
					map.put("ipNo", txtFldAdmissionNo.getText());
					map.put("ItemDataSource", itemsJRBean);
					map.put("sum", totalamt + "");
					map.put("cashGiven", txtFldEnterAmount.getText());
					map.put("cashReturn", txtFldCashReturn.getText());

					// Loads Report into New JFrame
					new ReportView(report, map, source);
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnPrintAllCharges);

		JLabel lblCreditHeading = new JLabel("Receipt Heading");
		lblCreditHeading.setBounds(54, 215, 129, 15);
		panel.add(lblCreditHeading);

		JComboBox comboBoxCrHeading = new JComboBox();
		comboBoxCrHeading.setBounds(169, 210, 110, 24);
		panel.add(comboBoxCrHeading);

		ArrayList<KeyValue> crHeadinglist = depositDAO.listCrHeading();
		for (int i = 0; i < crHeadinglist.size(); i++) {
			comboBoxCrHeading.addItem(crHeadinglist.get(i));
		}

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(285, 215, 70, 15);
		panel.add(lblAmount);

		txtFldCreditAmount = new JTextField();
		txtFldCreditAmount.setBounds(433, 213, 114, 19);

		panel.add(txtFldCreditAmount);
		txtFldCreditAmount.setColumns(10);
		txtFldCreditAmount.addKeyListener(new KeyListener() {

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

		JLabel lblCreditRemark = new JLabel("Receipt Remark");
		lblCreditRemark.setBounds(599, 215, 129, 15);
		panel.add(lblCreditRemark);

		txtFldCreditRemark = new JTextField();
		txtFldCreditRemark.setColumns(25);
		txtFldCreditRemark.setBounds(742, 210, 232, 25);
		panel.add(txtFldCreditRemark);

		JLabel lblChargeDate = new JLabel("Receipt Date");
		lblChargeDate.setBounds(54, 139, 114, 25);
		panel.add(lblChargeDate);

		txtFldChargeDate = new JTextField();
		txtFldChargeDate.setEditable(false);
		txtFldChargeDate.setColumns(10);
		txtFldChargeDate.setBounds(170, 139, 110, 25);
		panel.add(txtFldChargeDate);

		btnNewReceiptAdd = new JButton("Add ");
		btnNewReceiptAdd.setBounds(857, 248, 117, 25);
		panel.add(btnNewReceiptAdd);

		lblNewEntry = new JLabel("New Entry");
		lblNewEntry.setBounds(54, 288, 108, 15);
		panel.add(lblNewEntry);

		lblPreviousTranscations = new JLabel("Previous Transcations");
		lblPreviousTranscations.setBounds(54, 443, 212, 15);
		panel.add(lblPreviousTranscations);

		lblTotalDeposit = new JLabel("Total Deposit");
		lblTotalDeposit.setBounds(600, 139, 95, 25);
		panel.add(lblTotalDeposit);

		txtFldTotalDeposit = new JTextField();
		txtFldTotalDeposit.setBounds(737, 137, 114, 25);
		txtFldTotalDeposit.setEditable(false);
		txtFldTotalDeposit.setColumns(10);
		panel.add(txtFldTotalDeposit);

		JLabel lblEnterAmount = new JLabel("Cash Given");
		lblEnterAmount.setBounds(874, 372, 136, 42);
		panel.add(lblEnterAmount);

		txtFldEnterAmount = new JTextField();
		txtFldEnterAmount.setBounds(874, 409, 116, 22);

		panel.add(txtFldEnterAmount);
		txtFldEnterAmount.setColumns(10);

		txtFldCashReturn = new JTextField();
		txtFldCashReturn.setColumns(10);
		txtFldCashReturn.setBounds(874, 490, 116, 22);
		txtFldCashReturn.setEditable(false);
		panel.add(txtFldCashReturn);
		txtFldCashReturn.addKeyListener(new KeyListener() {

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

		JLabel lblAmountReturn = new JLabel("Return");
		lblAmountReturn.setBounds(874, 453, 136, 42);
		panel.add(lblAmountReturn);

		txtFldReceiptTotal = new JTextField();
		txtFldReceiptTotal.setBounds(874, 342, 116, 25);
		txtFldReceiptTotal.setEnabled(false);
		panel.add(txtFldReceiptTotal);
		txtFldReceiptTotal.setColumns(10);

		btnNewReceiptAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double receiptAmount = 0;
				receiptHeadingValue = ((KeyValue) comboBoxCrHeading.getSelectedItem()).getValue();
				String receiptHeadingKey = ((KeyValue) comboBoxCrHeading.getSelectedItem()).getKey() + ","
						+ receiptHeadingValue;
				int amount;
				String remarks = txtFldCreditRemark.getText();

				if (!receiptHeadingValue.equals("0")) {
					if (txtFldCreditAmount.getText().isEmpty()) {
						amount = 0;
					} else {
						amount = Integer.parseInt(txtFldCreditAmount.getText());
					}
					tableModel1.insertRow(0,
							new Object[] { txtAutoNumber.getText(), receiptHeadingKey, amount, "cash", remarks });

					int tablecount = tableModel1.getRowCount();
					for (int i = 0; i < tablecount; ++i) {
						String crHeadVal[] = tableModel1.getValueAt(i, 1).toString().split(",");
						int fldCrHeadingNo = Integer.parseInt(crHeadVal[1]);
						Double crAmount = Double.parseDouble(tableModel1.getValueAt(i, 2).toString());
						if (fldCrHeadingNo == 12) {
							crAmount = crAmount - 2 * crAmount;
						}
						receiptAmount = receiptAmount + crAmount;

					}

					txtFldReceiptTotal.setText(receiptAmount + "");

					String query = "SELECT fldDischarged FROM tblInPatient where fldAdmissionNo='" + admissionID
							+ "' AND fldDischarged=" + 1;
					DBMessage db = dbAssistant.execQuery(query);

					if (!db.getCODE().equals("0")) {

					} else {

						try {
							ResultSet rs = db.getResultSet();
							if (rs.next()) {
								btnCalculate.setEnabled(false);
							} else {
								btnCalculate.setEnabled(true);
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}

				} else {

					JOptionPane.showMessageDialog(null, "Please Select Data Properly", "Format Mismatch",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		txtFldAdmissionNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				int discharge = 0;
				admissionID = Integer.parseInt(txtFldAdmissionNo.getText());
//				if(admissionID==admis) {
//					JOptionPane.showMessageDialog(null, "Please Insert Admission ID First", "Error",
//							JOptionPane.ERROR_MESSAGE);
//				}else {
				String query = "SELECT fldDischarged FROM tblInPatient where fldAdmissionNo='" + admissionID + "'";
				DBMessage db = dbAssistant.execQuery(query);
				if (!db.getCODE().equals("0")) {
					System.out.println(db.getMSG());
				} else {

					try {
						ResultSet rset = db.getResultSet();
						if (rset.next()) {
							discharge = rset.getInt("fldDischarged");
						}
						System.out.println("dicharge " + admissionID + discharge);

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				if (txtFldAdmissionNo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter AdmissionID first", "Format Mismatch",
							JOptionPane.ERROR_MESSAGE);
				} else if (discharge == 1) {
					JOptionPane.showMessageDialog(null, "Patient Already Discharged", "Discharge Status",
							JOptionPane.ERROR_MESSAGE);
				}

				else {

					int randomNum = 100 + (int) (Math.random() * 1000);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");
					LocalDateTime localDate = LocalDateTime.now();
					DateConverter dateConverter = new DateConverter();
					dateConverter.setEnglishDateString(dtf.format(localDate));
					InPatient inpatient_new = patientDAO.searchCompPatientDetail(admissionID);
					if (inpatient_new != null) {
						String receiptQquerry = "select fldReceiptNo from tblIPbillCredit order by fldReceiptNo desc Limit 0,1";
						DBMessage dbMsgg = dbAssistant.execQuery(receiptQquerry);
						ResultSet rset = dbMsgg.getResultSet();
						try {
							if(rset.next()) {
								txtAutoNumber.setText(rset.getInt("fldReceiptNo") + 1 + "");
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						txtFldChargeDate.setText((dtf.format(localDate)));
						txtFldChargeYear.setText(dateConverter.getNepaliYear() + "");
						txtFldChargeMonth.setText(dateConverter.getNepaliMonth() + "");
						txtFldChargeDay.setText(dateConverter.getNepaliDate() + "");
						txtFldPatientId.setText(inpatient_new.getPatient().getFldPatientId() + "");
						txtFldPatientName.setText(inpatient_new.getPatient().getFldPatientName() + " "
								+ inpatient_new.getPatient().getFldPatientLastName());
						txtFldAdmissionDate.setText(inpatient_new.getFldAdmissionDate());
						txtFldEstimatedAmount.setText(inpatient_new.getFldEstimatedAmt() + "");
						txtFldAdmissionYear.setText(inpatient_new.getFldAdmissionYear() + "");
						txtFldAdmissionMonth.setText(inpatient_new.getFldAdmissionMonth() + "");
						txtFldAdmissionDay.setText(inpatient_new.getFldAdmissionDay() + "");

						String selectQuery = "SELECT * FROM tblIPbillCredit,tblIPbillCreditDetail,tblCrHeading WHERE tblIPbillCredit.fldReceiptNo=tblIPbillCreditDetail.fldReceiptNo AND "
								+ "tblCrHeading.fldCrHeadingNo=tblIPbillCreditDetail.fldCrHeadingNo AND "
								+ "tblIPbillCredit.fldAdmissionNo='" + admissionID + "' ";
						DBMessage dbMsg = dbAssistant.execQuery(selectQuery);
						if (!dbMsg.getCODE().equals("0")) {
							// TODO logger rakha yeha msg print garauna
						} else {
							ResultSet rs = dbMsg.getResultSet();
							try {
								while (rs.next()) {
									tableModel.insertRow(0,
											new Object[] { rs.getString("tblipbillcredit.fldReceiptNo"),
													rs.getString("tblcrheading.fldCrHeadingName"),
													rs.getString("tblipbillcreditdetail.fldAmount"),
													rs.getString("tblcrheading.fldCrHeadingGroup"),
													rs.getString("tblipbillcreditdetail.fldCrHeadingRemark") });

								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						tableModelRowCount = tableModel.getRowCount();
						double sum = 0;

						for (int i = 0; i < tableModelRowCount; ++i) {

							double valueAtRow = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
							sum = sum + valueAtRow;
						}
						txtFldTotalDeposit.setText(sum + "");

					} else {
						JOptionPane.showMessageDialog(null, "Please Enter valid AdmissionID first", "Error",
								JOptionPane.ERROR_MESSAGE);

					}

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				clear();

			}
		});

		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int discharge = 0;
				// int admissionID = Integer.parseInt(txtFldAdmissionNo.getText());
				String query = "SELECT fldDischarged FROM tblInPatient where fldAdmissionNo='" + admissionID + "'";
				DBMessage db = dbAssistant.execQuery(query);
				if (!db.getCODE().equals("0")) {
					System.out.println(db.getMSG());
				} else {

					try {
						ResultSet rset = db.getResultSet();
						if (rset.next()) {
							discharge = rset.getInt("fldDischarged");
						}
						System.out.println("dicharge" + discharge);

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					// if (discharge == 0) { for discharge
					Double totalamt = 0.0;
					JOptionPane frame = null;

					try {
						if (receiptHeadingValue == null) {
							msg = "No Previous Transcation";

						} else {

							System.out.println(tableModel1.getValueAt(0, 0));
							String insertQuery_1 = "INSERT INTO "
									+ "tblIPbillCredit (fldReceiptNo,fldAdmissionNo,fldPmtDate,fldPmtYear,fldPmtMonth,fldPmtDay,fldUserID) VALUES('"
									+ tableModel1.getValueAt(0, 0) + "' ,'" + admissionID + "','"
									+ txtFldChargeDate.getText() + "','" + txtFldChargeYear.getText() + "','"
									+ txtFldChargeMonth.getText() + "','" + txtFldChargeDay.getText() + "','"
									+ session.getUserName() + "')";

							AppMessage appMsg = dbAssistant.execAction(insertQuery_1);
							msg = appMsg.getMSG();
						}

						int tablecount = tableModel1.getRowCount();
						for (int i = 0; i < tablecount; ++i) {
							String crHeadVal[] = tableModel1.getValueAt(i, 1).toString().split(",");
							int fldCrHeadingNo = Integer.parseInt(crHeadVal[1]);
							Double crAmount = Double.parseDouble(tableModel1.getValueAt(i, 2).toString());
							if (fldCrHeadingNo == 12) {
								crAmount = crAmount - 2 * crAmount;
							}
							totalamt = totalamt + crAmount;

							String insertQuery_2 = "INSERT INTO "
									+ "tblIPbillCreditDetail(fldReceiptNo,fldCrHeadingNo,fldCrHeadingRemark,fldAmount)VALUES('"
									+ tableModel1.getValueAt(i, 0) + "' ,'" + fldCrHeadingNo + "','"
									+ tableModel1.getValueAt(i, 4).toString() + "','" + crAmount + "')";

							AppMessage appMsg = dbAssistant.execAction(insertQuery_2);
							msg = "Insertion Successufull";
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(frame, msg, "Transcation Completed", JOptionPane.PLAIN_MESSAGE);
				}
				// else { discharge
				// JOptionPane.showMessageDialog(null, "Already Discharged Patient",
				// "Transcation Completed",
				// JOptionPane.ERROR_MESSAGE);
				//
				// }
				//
				// }
			}

		});

		txtFldEnterAmount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
					double receiptAmt = Double.parseDouble(txtFldReceiptTotal.getText());
					System.out.println(receiptAmt);
					double cashGiven = Double.parseDouble(txtFldEnterAmount.getText());
					double returnAmt = cashGiven - receiptAmt;

					txtFldCashReturn.setText(returnAmt + "");
					btnPrintAllCharges.setEnabled(true);
				} catch (NumberFormatException ex) {
					System.out.println(ex);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void clear() {
		txtFldAdmissionNo.setText("");
		txtFldPatientId.setText("");
		txtFldPatientName.setText("");
		txtFldAdmissionDate.setText("");
		txtFldEstimatedAmount.setText("");
		txtFldAdmissionYear.setText("");
		txtFldAdmissionMonth.setText("");
		txtFldAdmissionDay.setText("");
		txtFldReceiptTotal.setText("");
		txtFldEnterAmount.setText("");
		txtFldCashReturn.setText("");
		tableModel.setRowCount(0);
		tableModel1.setRowCount(0);
	}

	public void checkIntegerNumber(KeyEvent keyEvt) {
		char c = keyEvt.getKeyChar();
		if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
			getToolkit().beep();
			keyEvt.consume();
		}
	}
}
