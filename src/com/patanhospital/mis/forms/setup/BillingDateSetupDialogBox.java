package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BillingDateSetupDialogBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	private JPanel panelBillingDate;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private JTextField txtFldBsYear;
	private JTextField txtFldBsMonth;
	private JTextField txtFldBsDay;
	private JTextField txtFldAdDate;

	/**
	 * Create the dialog.
	 */
	public BillingDateSetupDialogBox() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Hospital Details");
		setAlwaysOnTop(true);

		setBounds(100, 100, width / 3, height / 4);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		panelBillingDate = new JPanel();
		panelBillingDate.setBounds(376, 29, width / 3, height / 4);
		getContentPane().add(panelBillingDate, BorderLayout.CENTER);
		panelBillingDate.setLayout(null);

		JLabel lblBsyyyymmdd = new JLabel("B.S. (yyyy/mm/dd)");
		lblBsyyyymmdd.setBounds(10, 25, 132, 32);
		panelBillingDate.add(lblBsyyyymmdd);

		txtFldBsYear = new JTextField();
		txtFldBsYear.setBounds(147, 25, 52, 19);
		panelBillingDate.add(txtFldBsYear);
		txtFldBsYear.setColumns(4);

		txtFldBsMonth = new JTextField();
		txtFldBsMonth.setBounds(211, 25, 26, 19);
		panelBillingDate.add(txtFldBsMonth);
		txtFldBsMonth.setColumns(2);

		txtFldBsDay = new JTextField();
		txtFldBsDay.setColumns(2);
		txtFldBsDay.setBounds(249, 25, 26, 19);
		panelBillingDate.add(txtFldBsDay);

		JLabel lblAdddmmyyyy = new JLabel("A.D. (dd/mm/yyyy)");
		lblAdddmmyyyy.setBounds(12, 55, 130, 15);
		panelBillingDate.add(lblAdddmmyyyy);

		JLabel label = new JLabel("/");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(167, 25, 70, 15);
		panelBillingDate.add(label);

		JLabel label_1 = new JLabel("/");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(211, 25, 70, 15);
		panelBillingDate.add(label_1);

		txtFldAdDate = new JTextField();
		txtFldAdDate.setBounds(157, 55, 114, 19);
		panelBillingDate.add(txtFldAdDate);
		txtFldAdDate.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
	}

}