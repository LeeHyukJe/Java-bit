package polymorphismEx;

class Employee{
	protected String name;
	protected String idnum;
	protected String title;
	protected int salary;
	
	public Employee(String name, String idnum) {
		this.name=name;
		this.idnum=idnum;
	}
	
	public void setSalary(int salary) {
		this.salary=salary;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void work() {
		System.out.println("Employee"+name+" does his best");
	}
	
}

class Manager extends Employee{
	private String dept;
	
	public Manager(String name, String idnum, String dept) {
		super(name,idnum);
		this.dept=dept;
	}
	
	@Override
	public void work() {
		System.out.println("Employee"+name+" works hard with his subordinates in "+dept+" dept.");
	}
}
public class Company {
	protected Employee[] employee;
	
	public Company() {
		Manager jmchoi=new Manager("최정명","f998845","Finance");
		Manager yckim=new Manager("김최영","a384752","Developer");
		Employee park=new Employee("park","f39459");
		Employee lee=new Employee("lee","f394859");
		
		employee=new Employee[4];
		employee[0]=jmchoi;
		employee[1]=yckim;
		employee[2]=park;
		employee[3]=lee;
	}
	
	public void makeMoney() {
		for(int i=0;i<employee.length;i++) {
			//employee[i].work();
			if(employee[i] instanceof Manager ) {
				employee[i].work();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Company company=new Company();
		company.makeMoney();
	}

}
