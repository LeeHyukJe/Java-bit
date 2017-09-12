import java.util.*;

public class HashMapEx3 {
	static HashMap PhoneBook=new HashMap();
	static HashMap PhoneBooks=new HashMap();

	static void addGroup(String groupName) {
		if(!PhoneBook.containsKey(groupName))
			PhoneBook.put(groupName, new HashMap()); //PhoneBook이라는 해쉬맵에 그룹 이름을 키 값으로 집어 넣음, 값은 다시 해시맵을 인스턴스함
	}
	
	static void addPhoneNo(String name, String tel) {
		addPhoneNo("기타",name,tel); //그룹 이름을 넣지 않으면 기타 항목 그룹으로 지정
	}
	static void addPhoneNo(String groupName, String name, String tel ) {
		addGroup(groupName);
		HashMap group= (HashMap)PhoneBook.get(groupName); //groupName 키에 대한 값을 반환
		group.put(tel, name); //값에 넣을 자리가 해시맵이므로 값이 다시 키가 됨.
	}
	
	static void printList() {
		Set set=PhoneBook.entrySet(); //키와 값의 쌍을 Map.Entry타입으로 set에 저장(Map은 키와 값이 쌍으로 되어 있기에) 키와 값의 집합을 set변수에 넣음
		Iterator it=set.iterator();
		
		while(it.hasNext()) { //그룹 entry를 참조하기 위한 while문
			Map.Entry e=(Map.Entry)it.next();
			Set subSet=((HashMap)e.getValue()).entrySet(); //(그룹map의 값이 subSet의 키이므로) subSet의 키와 값의 집합을 subSet에 저장
			Iterator subIt=subSet.iterator();
			System.out.println(e.getKey()+","+subSet.size());
			while(subIt.hasNext()) { //그룹 안의 subSet의 entry를 참조하기 위한 while문
				Map.Entry subE=(Map.Entry)subIt.next(); //subSet의 키와 쌍을 subE에 저장
				String telNo=(String)subE.getKey();
				String name=(String)subE.getValue();
				System.out.println(name+" "+telNo);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addPhoneNo("친구","이이이","010-333-3333");
		addPhoneNo("친구","ddd","010-222-3333");
		printList();
	}

}
