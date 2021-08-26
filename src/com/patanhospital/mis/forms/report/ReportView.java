package com.patanhospital.mis.forms.report;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class ReportView {
	
	public ReportView(JasperReport report, Map<String, Object> map, JRDataSource source) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, source);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame frame = new JFrame("Report");
			frame.setSize(1024, 1024);
			frame.getContentPane().add(new JRViewer(jasperPrint));
			frame.pack();
			frame.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
