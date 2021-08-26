package com.patanhospital.mis.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.patanhospital.mis.forms.setup.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetupPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778563769486229240L;

	// private static final Logger logger = Logger.getLogger(SetupPanel.class);

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) Math.round(screenSize.getWidth()) - 190;
	private int height = (int) Math.round(screenSize.getHeight()) - 100;
	private JPanel setupSelectionPanel;
	private JPanel panelBillingDate;
	private JPanel panelHospitalInformation;
	private JPanel panelEthnicGroup;
	private JPanel panelDistrict;
	private JPanel panelWardTypes;
	private JPanel panelCrHeadingItems;
	private JPanel panelDrHeadingItems;
	private JButton btnHospitalInformation;
	private JButton btnEthnicGroup;
	private JButton btnDistrict;
	private JButton btnWardTypes;
	private JButton btnCrHeadings;
	private JButton btnDrHeadings;

	/**
	 * Create the panel.
	 */
	public SetupPanel() {

		setSize(width, height);
		setLayout(null);

		setupSelectionPanel = new JPanel();
		setupSelectionPanel.setBounds(0, 0, 1176, 668);
		add(setupSelectionPanel);
		setupSelectionPanel.setLayout(null);

		panelBillingDate = new JPanel();
		panelBillingDate.setBounds(width / 4, height / 5, width / 6, height / 9 + 20);
		setupSelectionPanel.setLayout(null);
		setupSelectionPanel.add(panelBillingDate);
		panelBillingDate.setLayout(null);

		JButton btnBillingDate = new JButton("Billing Date");
		btnBillingDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBillingDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BillingDateSetupDialogBox billingDateFrame = new BillingDateSetupDialogBox();
				billingDateFrame.setVisible(true);

			}
		});
		btnBillingDate.setBackground(new Color(51, 153, 255));
		btnBillingDate.setBounds(0, 10, width / 6, height / 9);
		panelBillingDate.add(btnBillingDate);

		panelHospitalInformation = new JPanel();
		panelHospitalInformation.setBounds(5 * width / 12 + 20, height / 5, width / 6, height / 9 + 20);
		setupSelectionPanel.add(panelHospitalInformation);
		panelHospitalInformation.setLayout(null);

		btnHospitalInformation = new JButton("Hospital Information");
		btnHospitalInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				HospitalInformationSetupFrame hospitalFrame = new HospitalInformationSetupFrame();
				hospitalFrame.setVisible(true);
			}
		});
		btnHospitalInformation.setBackground(new Color(51, 153, 255));
		btnHospitalInformation.setBounds(0, 10, width / 6, height / 9);
		panelHospitalInformation.add(btnHospitalInformation);

		panelEthnicGroup = new JPanel();
		panelEthnicGroup.setBounds(7 * width / 12 + 40, height / 5, width / 6, height / 9 + 20);
		setupSelectionPanel.add(panelEthnicGroup);
		panelEthnicGroup.setLayout(null);

		btnEthnicGroup = new JButton("Ethnic Group");
		btnEthnicGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EthnicGroupSetupDialogBox ethnicGroupFrame = new EthnicGroupSetupDialogBox();
				ethnicGroupFrame.setVisible(true);
			}
		});
		btnEthnicGroup.setBackground(new Color(51, 153, 255));
		btnEthnicGroup.setBounds(0, 10, width / 6, height / 9);
		panelEthnicGroup.add(btnEthnicGroup);

		panelDistrict = new JPanel();
		panelDistrict.setBounds(width / 3, 23 * height / 56 - 10, width / 6, height / 9 + 20);
		setupSelectionPanel.add(panelDistrict);
		panelDistrict.setLayout(null);

		btnDistrict = new JButton("District");
		btnDistrict.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DistrictSetupDialogBox districtFrame = new DistrictSetupDialogBox();
				districtFrame.setVisible(true);
			}
		});
		btnDistrict.setBackground(new Color(51, 153, 255));
		btnDistrict.setBounds(0, 10, width / 6, height / 9);
		panelDistrict.add(btnDistrict);

		panelWardTypes = new JPanel();
		panelWardTypes.setBounds(width / 2 + 20, 23 * height / 56 - 10, width / 6, height / 9 + 20);
		setupSelectionPanel.add(panelWardTypes);
		panelWardTypes.setLayout(null);

		btnWardTypes = new JButton("Ward Types");
		btnWardTypes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WardTypesSetupDialogBox wardTypesFrame = new WardTypesSetupDialogBox();
				wardTypesFrame.setVisible(true);
			}
		});
		btnWardTypes.setBackground(new Color(51, 153, 255));
		btnWardTypes.setBounds(0, 10, width / 6, height / 9);
		panelWardTypes.add(btnWardTypes);

		panelCrHeadingItems = new JPanel();
		panelCrHeadingItems.setBounds(width / 3, 263 * height / 504 + 40, width / 6, height / 9 + 20);
		setupSelectionPanel.add(panelCrHeadingItems);
		panelCrHeadingItems.setLayout(null);

		btnCrHeadings = new JButton("Cr Headings");
		btnCrHeadings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CrHeadingSetupDialogBox crHeadingFrame = new CrHeadingSetupDialogBox();
				crHeadingFrame.setVisible(true);
			}
		});
		btnCrHeadings.setBackground(new Color(51, 153, 255));
		btnCrHeadings.setBounds(0, 10, width / 6, height / 9);
		panelCrHeadingItems.add(btnCrHeadings);

		panelDrHeadingItems = new JPanel();
		panelDrHeadingItems.setBounds(width / 2 + 20, 263 * height / 504 + 40, width / 6, height / 9 + 20);
		setupSelectionPanel.add(panelDrHeadingItems);
		panelDrHeadingItems.setLayout(null);

		btnDrHeadings = new JButton("Dr Headings");
		btnDrHeadings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DrHeadingSetupDialogBox drHeadingFrame = new DrHeadingSetupDialogBox();
				drHeadingFrame.setVisible(true);
			}
		});

		btnDrHeadings.setBackground(new Color(51, 153, 255));
		btnDrHeadings.setBounds(0, 10, width / 6, height / 9);
		panelDrHeadingItems.add(btnDrHeadings);

	}
}