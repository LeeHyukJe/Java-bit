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
		
		Iterator it=list.iterator();// list�� ������ Ŭ������ �ν��Ͻ��� ��ȯ�ؼ� it�� �־�� �Ѵ�.
		while(it.hasNext()) {
			Object obj=it.next();
			System.out.println(obj);
		}
	}
}


