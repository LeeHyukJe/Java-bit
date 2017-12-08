package week_14;

import java.util.ArrayList;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Customer> customers=new ArrayList<>();
		
		customers.add(new Customer("www",150));
		customers.add(new Customer("aaa",200));
		customers.add(new Customer("bbb",300));
		customers.add(new Customer("ccc",400));
		
		ReportGenerator simpleGenerator=new SimpleReportGenerator();
		System.out.println(simpleGenerator.getnerate(customers));
		
		ComplexReportGenerator complexGenerator=new ComplexReportGenerator();
		System.out.println(complexGenerator.getnerate(customers));
	}

}
