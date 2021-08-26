package com.patanhospital.mis.forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.dao.impl.AppUserDAOImpl;
import com.patanhospital.mis.model.AppUser;
import com.patanhospital.mis.server.Session;
import com.patanhospital.mis.server.UserSessionImpl;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Login extends JDialog {
	/**
	 * @author sijan
	 */
	private static final long serialVersionUID = 7389039421742859836L;
	private static final Logger logger = LoggerFactory.getLogger(AdmissionPanel.class);
	private JTextField txtFldUsername;
	private JPasswordField txtFldPassword;
	private AppUserDAOImpl appUserDAOImpl;
	private UserSessionImpl userSession;

	/**
	 * Create the dialog.
	 */
	public Login() {
		appUserDAOImpl = new AppUserDAOImpl();
		userSession = new UserSessionImpl();
		setResizable(false);
		setTitle("Patan Hospital");
		setBounds(100, 100, 426, 270);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.RED);
		panelTitle.setPreferredSize(new Dimension(10, 40));
		getContentPane().add(panelTitle, BorderLayout.NORTH);
		panelTitle.setLayout(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		// panel.setLayout(new MigLayout("", "[72px][][247px]", "[19px][19px][][]"));

		JLabel lblUsername = new JLabel("Username");
		panel.add(lblUsername, "cell 1 2,alignx left,aligny center");

		txtFldUsername = new JTextField();
		panel.add(txtFldUsername, "cell 2 2,alignx center,aligny top");
		txtFldUsername.setColumns(15);

		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword, "cell 1 3,growx,aligny center");

		txtFldPassword = new JPasswordField();
		txtFldPassword.setColumns(15);
		panel.add(txtFldPassword, "cell 2 3,alignx center,aligny top");

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 40));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				AppUser user = new AppUser();
				user.setFldUserName(txtFldUsername.getText());
				user.setFldUserPassword(txtFldPassword.getText());

				if (!appUserDAOImpl.validateAppUser(user)) {
					logger.error("Authentication Failed");
					JOptionPane.showMessageDialog(null, "Invalid UserName/Password", "Authentication Failed",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// TODO Return New File Descriptor
					Session session = userSession.setUserSession(user);
					dispose();
					new JRootApp(session);
				}
			}
		});
		btnLogin.setBounds(297, 0, 117, 25);
		panel_1.add(btnLogin);
	}
}
