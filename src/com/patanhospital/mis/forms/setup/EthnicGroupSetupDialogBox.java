package com.patanhospital.mis.forms.setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.patanhospital.mis.dao.impl.EthnicGroupDAOImpl;
import com.patanhospital.mis.model.EthnicGroup;

public class EthnicGroupSetupDialogBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069207288323977019L;

	private JPanel panelEthnicGroup;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;

	private JLabel lblEthnicId;
	private JTextField txtFldEthnicId;
	private JLabel lblEthnicGroupName;
	private JTextField txtFldEthnicGroupName;
	private JButton btnAddEthnic;
	private JButton btnNextEthnic;
	private JButton btnPrevEthnic;
	private JButton btnUpdateEthnic;
	private int i = -1;

	/**
	 * Create the dialog.
	 */
	public EthnicGroupSetupDialogBox() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Ethnic Group");
		setAlwaysOnTop(true);

		setBounds(100, 100, width / 3, 2 * height / 3);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		EthnicGroupDAOImpl ethnicGroup = new EthnicGroupDAOImpl();
		EthnicGroup ethnicGrp = new EthnicGroup();

		panelEthnicGroup = new JPanel();
		panelEthnicGroup.setBounds(376, 29, width / 3, 2 * height / 3);
		getContentPane().add(panelEthnicGroup, BorderLayout.CENTER);
		panelEthnicGroup.setLayout(null);

		lblEthnicId = new JLabel("Ethnic Id");
		lblEthnicId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEthnicId.setBounds(33, 36, 156, 35);
		panelEthnicGroup.add(lblEthnicId);

		txtFldEthnicId = new JTextField();
		txtFldEthnicId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				checkIntegerNumber(e);
			}
		});
		txtFldEthnicId.setBounds(66, 63, 114, 29);
		panelEthnicGroup.add(txtFldEthnicId);
		txtFldEthnicId.setColumns(10);

		txtFldEthnicId.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				try {
					int ethnicID = Integer.parseInt(txtFldEthnicId.getText());

					for (int j = 0; j < ethnicGroup.listAllEthnicGrp().size(); ++j) {

						if (ethnicGroup.listAllEthnicGrp().get(j).getFldEthnicID() == ethnicID) {

							txtFldEthnicGroupName.setText(ethnicGroup.listAllEthnicGrp().get(j).getFldEthnicGroup());
							i = j;
							btnUpdateEthnic.setEnabled(true);
							btnAddEthnic.setEnabled(false);
							break;
						} else {
							btnUpdateEthnic.setEnabled(false);
							btnAddEthnic.setEnabled(true);
						}
					}

				} catch (NumberFormatException ex) {
					System.out.println(ex);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				txtFldEthnicId.setText("");
				txtFldEthnicGroupName.setText("");
				txtFldEthnicId.setEditable(true);
				btnUpdateEthnic.setEnabled(false);
				btnAddEthnic.setEnabled(false);
			}

		});

		lblEthnicGroupName = new JLabel("Ethnic Group");
		lblEthnicGroupName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEthnicGroupName.setBounds(33, 104, 179, 35);
		panelEthnicGroup.add(lblEthnicGroupName);

		txtFldEthnicGroupName = new JTextField();
		txtFldEthnicGroupName.setColumns(10);
		txtFldEthnicGroupName.setBounds(66, 130, 194, 29);
		panelEthnicGroup.add(txtFldEthnicGroupName);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnAddEthnic = new JButton("Add New");
			btnAddEthnic.setActionCommand("Add New");
			buttonPane.add(btnAddEthnic);
			getRootPane().setDefaultButton(btnAddEthnic);

			btnAddEthnic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					ethnicGrp.setFldEthnicID(Short.parseShort(txtFldEthnicId.getText()));
					ethnicGrp.setFldEthnicGroup(txtFldEthnicGroupName.getText());
					if (!ethnicGroup.addNewEthnicGroup(ethnicGrp).getCODE().equals("0")) {

						// logger.error(ethnicGroup.addNewEthnicGroup(ethnicGrp).getMSG());
						JOptionPane.showMessageDialog(null, "Failed", "Data Insertion", JOptionPane.ERROR_MESSAGE);
					} else {

						JOptionPane.showMessageDialog(null, "Success", "Data Insertion",
								JOptionPane.INFORMATION_MESSAGE);
					}
					panelEthnicGroup.repaint();
					panelEthnicGroup.revalidate();

				}

			});

			btnPrevEthnic = new JButton("Prev");
			btnPrevEthnic.setActionCommand("Prev");
			buttonPane.add(btnPrevEthnic);
			getRootPane().setDefaultButton(btnPrevEthnic);

			btnPrevEthnic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (i > 0) {
						i = i - 1;
						txtFldEthnicId.setText(ethnicGroup.listAllEthnicGrp().get(i).getFldEthnicID() + "");
						txtFldEthnicGroupName.setText(ethnicGroup.listAllEthnicGrp().get(i).getFldEthnicGroup() + "");
					} else {
						System.out.println("not data prev");
					}
					panelEthnicGroup.repaint();
					panelEthnicGroup.revalidate();
				}
			});

			btnNextEthnic = new JButton("Next");
			btnNextEthnic.setActionCommand("Next");
			buttonPane.add(btnNextEthnic);
			getRootPane().setDefaultButton(btnNextEthnic);

			btnNextEthnic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (i < ethnicGroup.listAllEthnicGrp().size() - 1) {
						i = i + 1;
						txtFldEthnicId.setText(ethnicGroup.listAllEthnicGrp().get(i).getFldEthnicID() + "");
						txtFldEthnicGroupName.setText(ethnicGroup.listAllEthnicGrp().get(i).getFldEthnicGroup() + "");
					} else {
						System.out.println("not data more");

					}
					panelEthnicGroup.repaint();
					panelEthnicGroup.revalidate();
				}
			});

			btnUpdateEthnic = new JButton("Update");
			btnUpdateEthnic.setActionCommand("Update");
			buttonPane.add(btnUpdateEthnic);
			getRootPane().setDefaultButton(btnUpdateEthnic);

			btnUpdateEthnic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String msg = "";
					if (txtFldEthnicGroupName.getText().isEmpty()) {
						msg = "Insert EthnicGroup Name";
					} else {
						ethnicGrp.setFldEthnicID(Short.parseShort(txtFldEthnicId.getText()));
						ethnicGrp.setFldEthnicGroup(txtFldEthnicGroupName.getText());

						if (!ethnicGroup.updateNewEthnicGroup(ethnicGrp).getCODE().equals("0")) {
							msg = "failed";
							System.out.println(ethnicGroup.updateNewEthnicGroup(ethnicGrp).getMSG());
							// logger.error(ethnicGroup.updateNewEthnicGroup(ethnicGrp).getMSG());
						} else {
							msg = "success";
							System.out.println(ethnicGroup.updateNewEthnicGroup(ethnicGrp).getMSG());
						}
					}
					JOptionPane.showMessageDialog(null, msg, "Update", JOptionPane.INFORMATION_MESSAGE);

					panelEthnicGroup.repaint();
					panelEthnicGroup.revalidate();
				}
			});
		}
	}

	public void checkIntegerNumber(KeyEvent keyEvt) {
		char c = keyEvt.getKeyChar();
		if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
			getToolkit().beep();
			keyEvt.consume();
		}
	}

}
