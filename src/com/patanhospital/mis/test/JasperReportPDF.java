package com.patanhospital.mis.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.patanhospital.mis.model.CashBillModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class JasperReportPDF {
	private List<CashBillModel> paymentDetail;
	private List<CashBillModel> chargeDetail;

	public JasperReportPDF() {
		paymentDetail = new ArrayList<>();
		chargeDetail = new ArrayList<>();
		CashBillModel s1 = new CashBillModel();
		s1.setPaymentNo(1);
		s1.setPaymentHeading("i");
		s1.setRemark("");
		s1.setAmount(400);

		CashBillModel s2 = new CashBillModel();
		s2.setPaymentNo(12);
		s2.setPaymentHeading("i");
		s2.setRemark("");
		s2.setAmount(400);

		CashBillModel s3 = new CashBillModel();
		s3.setPaymentNo(1);
		s3.setPaymentHeading("i");
		s3.setRemark("");
		s3.setAmount(400);

		CashBillModel s4 = new CashBillModel();
		s4.setPaymentNo(1);
		s4.setPaymentHeading("i");
		s4.setRemark("");
		s4.setAmount(400);

		CashBillModel s5 = new CashBillModel();
		s5.setPaymentNo(1);
		s5.setPaymentHeading("i");
		s5.setRemark("");
		s5.setAmount(400);

		paymentDetail.add(s1);
		paymentDetail.add(s2);
		paymentDetail.add(s3);
		paymentDetail.add(s4);
		paymentDetail.add(s5);
		paymentDetail.add(s1);
		
		chargeDetail.add(s5);
		chargeDetail.add(s4);
		chargeDetail.add(s5);
		chargeDetail.add(s1);
		chargeDetail.add(s2);
		chargeDetail.add(s3);
		
	}

	public void generate() {
		try {
			JasperReport report = JasperCompileManager.compileReport("res/discharge_bill.jrxml");
			JRDataSource source = new JREmptyDataSource();
			JRBeanCollectionDataSource paymentDetailJRBean = new JRBeanCollectionDataSource(paymentDetail);
			JRBeanCollectionDataSource chargeDetailJRBean = new JRBeanCollectionDataSource(chargeDetail);
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("receiptNo", 9001);
//			map.put("date", "2017/2/12");
//			map.put("bs", "2071/2/12");
//			map.put("cashier", "demo");
//			map.put("patientId", 1);
//			map.put("name", "test test");
//			map.put("ipNo", 89);
			map.put("ChargeDetailSource", chargeDetailJRBean);
			map.put("PaymentDetailSource", paymentDetailJRBean);
//			map.put("sum", 1000.0);
//			map.put("cashGiven", 1050.0);
//			map.put("cashReturn", 50.0);

			// JasperPrint print = JasperFillManager.fillReport(report, map, source);
			// JasperExportManager.exportReportToPdfFile(print, "res/final_bill_test.pdf");

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, source);

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFrame frame = new JFrame("Report");
			frame.setSize(500, 500);
			frame.getContentPane().add(new JRViewer(jasperPrint));
			frame.pack();
			frame.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new JasperReportPDF().generate();
	}
}
