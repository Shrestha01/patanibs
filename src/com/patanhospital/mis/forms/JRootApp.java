package com.patanhospital.mis.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.patanhospital.mis.server.Session;

public class JRootApp {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private JPanel contentPanel;
	private JPanel footerPanel;
	private JPanel lblChargeEntryPanel;
	private JPanel lblCashDepositPanel;
	private JPanel lblReportingPanel;
	private JPanel lblDischargePanel;
	private JPanel lblMaintenancePanel;
	private JPanel lblSetupPanel;
	private JPanel lblExitPanel;
	private JPanel lblAdmissionPanel;
	private JLabel lblAdmission;
	private JPanel lblLogoPanel;
	private JLabel lblCashier;
	private JLabel lblCashierName;
	private String appuserName;
	private Session session;

	/**
	 * Create the application.
	 */

	public JRootApp(Session s) {
		session = s;
		appuserName = s.getUserName() + " ( " + s.getUerIP() + " )";
		initialize();
		userPermissionPanel(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/logo.png"));
		frame.setResizable(false);
		frame.setTitle("PATAN HOSPITAL");
		// frame.setUndecorated(true);

		// size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());

		int width = (int) Math.round(screenSize.getWidth()) - scnMax.left - scnMax.right;
		int height = (int) Math.round(screenSize.getHeight()) - scnMax.bottom - scnMax.top;

		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.CYAN);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		sidePanel = new JPanel();
		sidePanel.setBackground(new Color(102, 0, 102));
		mainPanel.add(sidePanel, BorderLayout.WEST);
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setPreferredSize(new Dimension(width / 5, height - 75));

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.GRAY);

		contentPanel.setSize(width, height);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		footerPanel = new JPanel();
		footerPanel.setPreferredSize(new Dimension(10, 75));
		footerPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(null);

		lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(53, 29, 70, 15);
		footerPanel.add(lblCashier);

		lblCashierName = new JLabel(appuserName);
		lblCashierName.setEnabled(false);
		lblCashierName.setBounds(156, 29, 250, 15);
		footerPanel.add(lblCashierName);

		lblLogoPanel = new JPanel();
		lblLogoPanel.setLayout(null);
		lblLogoPanel.setBorder(null);
		lblLogoPanel.setBackground(new Color(102, 0, 102));
		lblLogoPanel.setPreferredSize(new Dimension(220, 110));
		sidePanel.add(lblLogoPanel);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("res/logo.png"));
		label.setBounds(12, 12, 196, 155);
		lblLogoPanel.add(label);

		lblAdmissionPanel = new JPanel();
		lblAdmissionPanel.setLayout(null);
		lblAdmissionPanel.setBorder(null);
		lblAdmissionPanel.setBackground(new Color(102, 0, 102));

		lblAdmission = new JLabel("Admission");
		lblAdmission.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new AdmissionPanel());
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblAdmissionPanel.setBackground(new Color(153, 51, 153));
			}
		});
		lblAdmission.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmission.setForeground(new Color(255, 255, 224));
		lblAdmission.setBounds(36, 0, 208, 56);
		lblAdmissionPanel.add(lblAdmission);

		lblChargeEntryPanel = new JPanel();
		lblChargeEntryPanel.setLayout(null);
		lblChargeEntryPanel.setBorder(null);
		lblChargeEntryPanel.setBackground(new Color(102, 0, 102));
		lblChargeEntryPanel.setBounds(12, 230, 226, 42);

		JLabel lblChargeEntry = new JLabel("Charge Entry ");
		lblChargeEntry.setHorizontalAlignment(SwingConstants.CENTER);
		lblChargeEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new ChargeEntryPanel());
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblChargeEntryPanel.setBackground(new Color(153, 51, 153));

			}
		});
		lblChargeEntry.setForeground(new Color(255, 255, 224));
		lblChargeEntry.setBounds(36, 0, 208, 56);
		lblChargeEntryPanel.add(lblChargeEntry);

		lblCashDepositPanel = new JPanel();
		lblCashDepositPanel.setLayout(null);
		lblCashDepositPanel.setBorder(null);
		lblCashDepositPanel.setBackground(new Color(102, 0, 102));
		lblCashDepositPanel.setBounds(12, 284, 226, 42);

		JLabel lblCashDeposit = new JLabel("Cash Deposit ");
		lblCashDeposit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashDeposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new CashDepositPanel(session));
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblCashDepositPanel.setBackground(new Color(153, 51, 153));
			}

		});
		lblCashDeposit.setForeground(new Color(255, 255, 224));
		lblCashDeposit.setBounds(36, 0, 208, 56);
		lblCashDepositPanel.add(lblCashDeposit);

		lblReportingPanel = new JPanel();
		lblReportingPanel.setLayout(null);
		lblReportingPanel.setBorder(null);
		lblReportingPanel.setBackground(new Color(102, 0, 102));
		lblReportingPanel.setBounds(12, 338, 226, 42);

		JLabel lblReporting = new JLabel("Reporting ");
		lblReporting.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new ReportingPanel());
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblReportingPanel.setBackground(new Color(153, 51, 153));
			}
		});
		lblReporting.setForeground(new Color(255, 255, 224));
		lblReporting.setBounds(36, 0, 208, 56);
		lblReportingPanel.add(lblReporting);

		lblDischargePanel = new JPanel();
		lblDischargePanel.setLayout(null);
		lblDischargePanel.setBorder(null);
		lblDischargePanel.setBackground(new Color(102, 0, 102));
		lblDischargePanel.setBounds(12, 392, 226, 42);

		JLabel lblDischarge = new JLabel("Discharge ");
		lblDischarge.setHorizontalAlignment(SwingConstants.CENTER);
		lblDischarge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new DischargePanel());
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblDischargePanel.setBackground(new Color(153, 51, 153));
			}
		});
		lblDischarge.setForeground(new Color(255, 255, 224));
		lblDischarge.setBounds(36, 0, 208, 56);
		lblDischargePanel.add(lblDischarge);

		lblMaintenancePanel = new JPanel();
		lblMaintenancePanel.setLayout(null);
		lblMaintenancePanel.setBorder(null);
		lblMaintenancePanel.setBackground(new Color(102, 0, 102));
		lblMaintenancePanel.setBounds(12, 446, 226, 42);

		JLabel lblMaintenance = new JLabel("Maintenance ");
		lblMaintenance.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaintenance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new MaintenancePanel());
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblMaintenancePanel.setBackground(new Color(153, 51, 153));
			}
		});
		lblMaintenance.setForeground(new Color(255, 255, 224));
		lblMaintenance.setBounds(36, 0, 208, 56);
		lblMaintenancePanel.add(lblMaintenance);

		lblSetupPanel = new JPanel();
		lblSetupPanel.setLayout(null);
		lblSetupPanel.setBorder(null);
		lblSetupPanel.setBackground(new Color(102, 0, 102));
		lblSetupPanel.setBounds(12, 500, 226, 42);

		JLabel lblSetup = new JLabel("Setup ");
		lblSetup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.add(new SetupPanel());
				contentPanel.repaint();
				contentPanel.revalidate();

				clear();
				lblSetupPanel.setBackground(new Color(153, 51, 153));
			}
		});
		lblSetup.setForeground(new Color(255, 255, 224));
		lblSetup.setBounds(36, 0, 208, 56);
		lblSetupPanel.add(lblSetup);

		lblExitPanel = new JPanel();
		lblExitPanel.setLayout(null);
		lblExitPanel.setBorder(null);
		lblExitPanel.setBackground(new Color(102, 0, 102));
		lblExitPanel.setBounds(12, 554, 226, 42);

		JLabel lblExit = new JLabel("Exit ");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblExit.setForeground(new Color(255, 255, 224));
		lblExit.setBounds(36, 0, 208, 44);
		lblExitPanel.add(lblExit);

	}

	private void clear() {
		lblAdmissionPanel.setBackground(null);
		lblChargeEntryPanel.setBackground(null);
		lblCashDepositPanel.setBackground(null);
		lblReportingPanel.setBackground(null);
		lblDischargePanel.setBackground(null);
		lblMaintenancePanel.setBackground(null);
		lblSetupPanel.setBackground(null);
		lblExitPanel.setBackground(null);
	}

	public void userPermissionPanel(Session s) {
		sidePanel.add(lblAdmissionPanel);
		sidePanel.add(lblChargeEntryPanel);
		sidePanel.add(lblCashDepositPanel);
		sidePanel.add(lblDischargePanel);
		if (s.getUserRole().equals("Administrator role")) {
			sidePanel.add(lblReportingPanel);
			sidePanel.add(lblMaintenancePanel);
			sidePanel.add(lblSetupPanel);
		}
		sidePanel.add(lblExitPanel);
	}
}