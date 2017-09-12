import java.util.ArrayList;
import java.util.Iterator;

public class IteratorEx1 {
	IteratorEx1(){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list= new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		Iterator it=list.iterator();// list를 구현한 클래스의 인스턴스를 반환해서 it에 넣어야 한다.
		while(it.hasNext()) {
			Object obj=it.next();
			System.out.println(obj);
		}
	}
}



