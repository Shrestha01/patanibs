package com.patanhospital.mis.forms;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;

import com.patanhospital.mis.util.StringUtils;

public class StartMIS {
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				//System.setProperty("logfile.name", "log/" + StringUtils.getCardNumber());
				try {
					BasicConfigurator.configure();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					// UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					Login dialog = new Login();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
