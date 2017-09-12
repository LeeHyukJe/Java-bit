import java.util.*;

public class HashSetEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] objArr= {"1",new Integer(1),"2","3","4","4","5","6",new Integer(2)}; //초기화한 순서대로 출력이 되지 않음(주의!)
		Set set=new HashSet();
		
		for(int i=0;i<objArr.length;i++) {
			set.add(objArr[i]);
		}
		if(!set.add(objArr[0]))
			System.out.println("중복 된 것이 존재함");
		System.out.println(set);
	}

}
