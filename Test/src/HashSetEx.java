import java.util.*;

public class HashSetEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] objArr= {"1",new Integer(1),"2","3","4","4","5","6",new Integer(2)}; //�ʱ�ȭ�� ������� ����� ���� ����(����!)
		Set set=new HashSet();
		
		for(int i=0;i<objArr.length;i++) {
			set.add(objArr[i]);
		}
		if(!set.add(objArr[0]))
			System.out.println("�ߺ� �� ���� ������");
		System.out.println(set);
	}

}
