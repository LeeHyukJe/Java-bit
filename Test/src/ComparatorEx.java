import java.util.*;
public class ComparatorEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] strArr= {"cast", "Doges","lioness","tigersss"};
		
		Arrays.sort(strArr);
		System.out.println("strArr="+Arrays.toString(strArr));
		
		Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
		System.out.println("strArr"+Arrays.toString(strArr));
		
		
		Arrays.sort(strArr, new Descending());
		System.out.println("strArr="+Arrays.toString(strArr));
		
		Arrays.sort(strArr,new MunjaSort());
		System.out.println("strArr="+Arrays.toString(strArr));
		
	}
}

class Descending implements Comparator{
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable c1=(Comparable)o1;
			Comparable c2=(Comparable)o2;
			return c1.compareTo(c2)*-1;
		}
		return -1;
	}
}

class MunjaSort implements Comparator{
	int len=0;
	public int compare(Object o1, Object o2) {
		if(o1 instanceof String && o2 instanceof String) {
			int c1 = ((String)o1).length();
			int c2 = ((String)o2).length();
			//return c1 <c2 ? -1:(c1 == c2 ? 0 : 1);
			return c1+c2;
		}
		return -1;
	}
}
