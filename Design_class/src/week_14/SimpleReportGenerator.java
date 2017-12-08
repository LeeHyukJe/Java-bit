package week_14;

import java.util.List;

public class SimpleReportGenerator extends ReportGenerator {

	@Override
	protected boolean customerReportCondition(Customer customer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String getReportHeader(List<Customer> customers) {
		// TODO Auto-generated method stub
		return String.format("고객 수 :%d명\n", customers.size());
	}

	@Override
	protected String getReportForCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return String.format("%s: %d\n", customer.getName(), customer.getPoint());
	}

	@Override
	protected String getReportFooter(List<Customer> customers) {
		// TODO Auto-generated method stub
		return "";
	}
	
	

}
