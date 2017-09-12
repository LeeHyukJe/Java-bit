import java.util.*;
public class HashMapEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap map=new HashMap();
		map.put("lims2733","1234");
		map.put("lims2523",1234);
		map.put("lhj2733",1234);
		map.put("lhj2523",1234);
		
		Scanner s=new Scanner(System.in);
		Set set=map.entrySet();
		Iterator it=set.iterator();
		while(it.hasNext()) {
			Map.Entry e=(Map.Entry)it.next();
			System.out.println("id: "+e.getKey()+","+"psw: "+e.getValue());
		}
		
		set=map.keySet();
		System.out.println("참가자 명단: "+set);
		
		Collection value=map.values();
		it=value.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			Integer i=(Integer)it.next();
			i+=i.intValue();
		}
		
		while(true) {
			System.out.println("id와 password를 입력하십시오.");
			System.out.println("id: ");
			String id=s.nextLine();
			System.out.println("password: ");
			String password=s.nextLine().trim();
			System.out.println();
			
			
			if(!map.containsKey(id)) {
				System.out.println("you which inserted key is not exist");
				continue;
			}
			else {
				if(!map.get(id).equals(password)) {
					System.out.println("password denied");
				}
				else {
					System.out.println("password and id is correct");
					break;
				}
			}
		}
	}

}
