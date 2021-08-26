package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.dao.impl.WardTypeDAOImpl;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.WardType;

public class WardTypesSetupDialogBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	private JPanel panelEditWardTypes;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private JTextField txtFldWardId;
	private JTextField txtFldWardDesc;
	private JLabel lblBedCount;
	private JTextField txtFldBedCount;

	private JButton addNewButton;
	private JButton prevButton;
	private JButton nextButton;
	private JButton okButton;

	private DatabaseAssistant databaseAssistant = null;

	private int i = -1;

	/**
	 * Create the dialog.
	 */
	public WardTypesSetupDialogBox() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Hospital Details");
		setAlwaysOnTop(true);

		setBounds(100, 100, width / 3, 2 * height / 3);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		WardTypeDAOImpl ward = new WardTypeDAOImpl();
		databaseAssistant = new DatabaseAssistant();

		panelEditWardTypes = new JPanel();
		panelEditWardTypes.setBounds(377, 105, 383, 429);
		getContentPane().add(panelEditWardTypes);
		panelEditWardTypes.setLayout(null);

		JLabel lblWardId = new JLabel("Ward ID");
		lblWardId.setHorizontalAlignment(SwingConstants.CENTER);
		lblWardId.setBounds(33, 36, 114, 35);
		panelEditWardTypes.add(lblWardId);
		
		txtFldWardId = new JTextField();
		txtFldWardId.setBounds(66, 63, 114, 29);
		panelEditWardTypes.add(txtFldWardId);
		txtFldWardId.setColumns(10);
		txtFldWardId.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
				String search = txtFldWardId.getText();
				//System.out.println(ward.WardInfo().get(12).getFldWardID().toLowerCase());
				try {
				for (int j = 0; j < ward.WardInfo().size(); ++j) {
					
					if (ward.WardInfo().get(j).getFldWardID().toLowerCase().equals(search)
							|| ward.WardInfo().get(j).getFldWardID().toUpperCase().equals(search)
							|| ward.WardInfo().get(j).getFldWardID()
									.equals(search.substring(0, 1).toUpperCase() + search.substring(1))) {
//						okButton.setEnabled(true);
						txtFldWardId.setText(ward.WardInfo().get(j).getFldWardID());
						txtFldWardDesc.setText(ward.WardInfo().get(j).getFldWardDesc());
						txtFldBedCount.setText(ward.WardInfo().get(j).getFldBedCount() + "");
						i = j;

						addNewButton.setEnabled(false);
						okButton.setEnabled(true);
						break;

					} else {
						addNewButton.setEnabled(true);
						okButton.setEnabled(false);

					}
				}}catch(StringIndexOutOfBoundsException ex) {
					System.out.println(ex);
				}
				
				}catch(NumberFormatException ex) {
					System.out.println(ex);
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				txtFldWardId.setText("");
				txtFldWardDesc.setText("");
				txtFldBedCount.setText("");

			}
		});

		JLabel lblWardDescription = new JLabel("Ward Description");
		lblWardDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblWardDescription.setBounds(33, 104, 179, 35);
		panelEditWardTypes.add(lblWardDescription);

		txtFldWardDesc = new JTextField();
		txtFldWardDesc.setColumns(30);
		txtFldWardDesc.setBounds(66, 130, 194, 29);
		panelEditWardTypes.add(txtFldWardDesc);

		lblBedCount = new JLabel("Bed Count");
		lblBedCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblBedCount.setBounds(33, 173, 140, 35);
		panelEditWardTypes.add(lblBedCount);

		txtFldBedCount = new JTextField();
		txtFldBedCount.setColumns(2);
		txtFldBedCount.setBounds(66, 205, 194, 29);
		panelEditWardTypes.add(txtFldBedCount);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			addNewButton = new JButton("Add New");
			addNewButton.setActionCommand("Add New");
			addNewButton.setEnabled(false);
			buttonPane.add(addNewButton);
			getRootPane().setDefaultButton(addNewButton);
			
			nextButton = new JButton("Next");
			nextButton.setActionCommand("Next");
			buttonPane.add(nextButton);
			getRootPane().setDefaultButton(nextButton);
			
			prevButton = new JButton("Prev");
			prevButton.setActionCommand("Prev");
			buttonPane.add(prevButton);
			getRootPane().setDefaultButton(prevButton);
			
			okButton = new JButton("Update");
			okButton.setActionCommand("Update");
			okButton.setEnabled(false);
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
			
			

			addNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ArrayList<WardType> wardData=new ArrayList<WardType>();
					WardType wardData = new WardType();
					wardData.setFldWardID(txtFldWardId.getText());
					wardData.setFldWardDesc(txtFldWardDesc.getText());
					wardData.setFldBedCount(Short.parseShort(txtFldBedCount.getText()));
					ward.addWardType(wardData);
					

				}
			});

			

			nextButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (i < ward.WardInfo().size() - 1) {
						i = i + 1;

						txtFldWardId.setText(ward.WardInfo().get(i).getFldWardID());
						txtFldWardDesc.setText(ward.WardInfo().get(i).getFldWardDesc());
						txtFldBedCount.setText(ward.WardInfo().get(i).getFldBedCount() + "");

					} else {
						JOptionPane.showMessageDialog(null, "not more data", "Data Ended", JOptionPane.ERROR_MESSAGE);
						System.out.println("not more data");
					}

				}

			});

			

			prevButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// System.out.println(i);
					if (i > 0) {
						i = i - 1;
						// System.out.println(i);
						txtFldWardId.setText(ward.WardInfo().get(i).getFldWardID());
						txtFldWardDesc.setText(ward.WardInfo().get(i).getFldWardDesc());
						txtFldBedCount.setText(ward.WardInfo().get(i).getFldBedCount() + "");

					} else {
						JOptionPane.showMessageDialog(null, "not more data Prev", "Data Ended",
								JOptionPane.ERROR_MESSAGE);
						System.out.println("not more data Prev");
					}

				}

			});

			

			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String msg = "";
					String sql = "UPDATE tblWardType SET fldWardID='" + txtFldWardId.getText() + "',fldWardDesc='"
							+ txtFldWardDesc.getText() + "',fldBedCount='" + txtFldBedCount.getText()
							+ "' WHERE fldWardID='" + txtFldWardId.getText() + "' ";

					AppMessage db = databaseAssistant.execUpdate(sql);
					if (!db.getCODE().equals("0")) {
						msg = "Update Failed";
						System.out.println(db.getMSG());
					} else {
						msg = "Update Success";
						System.out.println(db.getMSG());
					}
					JOptionPane.showMessageDialog(null, msg, "Update ", JOptionPane.INFORMATION_MESSAGE);

					System.out.println(sql);

				}
			});
		}
	}

}