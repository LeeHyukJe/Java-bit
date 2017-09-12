import java.util.*;
public class PropertiesEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties sysProp=System.getProperties();
		System.out.println("java.version: "+sysProp.getProperty("java.version"));
		System.out.println("user.language: "+sysProp.getProperty("user.language"));
		sysProp.list(System.out);
	}

}
