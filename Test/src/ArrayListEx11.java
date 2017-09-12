import java.util.ArrayList;

public class ArrayListEx11 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list=new ArrayList();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.add(new Integer(111));
		list.add(222);
		System.out.println(list);
		
		list.add(0,"000");
		System.out.println(list);
		
		System.out.println("index="+list.indexOf("333"));
		
		System.out.println(list.remove("333"));
		
		System.out.println(list.remove("222"));
		
		for(int i=0;i<list.size();i++) {
			list.set(i, i+"");
		}
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i)+" ");
		}
	}

}
