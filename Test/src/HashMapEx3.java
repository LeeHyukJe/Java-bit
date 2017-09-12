import java.util.*;

public class HashMapEx3 {
	static HashMap PhoneBook=new HashMap();
	static HashMap PhoneBooks=new HashMap();

	static void addGroup(String groupName) {
		if(!PhoneBook.containsKey(groupName))
			PhoneBook.put(groupName, new HashMap()); //PhoneBook�̶�� �ؽ��ʿ� �׷� �̸��� Ű ������ ���� ����, ���� �ٽ� �ؽø��� �ν��Ͻ���
	}
	
	static void addPhoneNo(String name, String tel) {
		addPhoneNo("��Ÿ",name,tel); //�׷� �̸��� ���� ������ ��Ÿ �׸� �׷����� ����
	}
	static void addPhoneNo(String groupName, String name, String tel ) {
		addGroup(groupName);
		HashMap group= (HashMap)PhoneBook.get(groupName); //groupName Ű�� ���� ���� ��ȯ
		group.put(tel, name); //���� ���� �ڸ��� �ؽø��̹Ƿ� ���� �ٽ� Ű�� ��.
	}
	
	static void printList() {
		Set set=PhoneBook.entrySet(); //Ű�� ���� ���� Map.EntryŸ������ set�� ����(Map�� Ű�� ���� ������ �Ǿ� �ֱ⿡) Ű�� ���� ������ set������ ����
		Iterator it=set.iterator();
		
		while(it.hasNext()) { //�׷� entry�� �����ϱ� ���� while��
			Map.Entry e=(Map.Entry)it.next();
			Set subSet=((HashMap)e.getValue()).entrySet(); //(�׷�map�� ���� subSet�� Ű�̹Ƿ�) subSet�� Ű�� ���� ������ subSet�� ����
			Iterator subIt=subSet.iterator();
			System.out.println(e.getKey()+","+subSet.size());
			while(subIt.hasNext()) { //�׷� ���� subSet�� entry�� �����ϱ� ���� while��
				Map.Entry subE=(Map.Entry)subIt.next(); //subSet�� Ű�� ���� subE�� ����
				String telNo=(String)subE.getKey();
				String name=(String)subE.getValue();
				System.out.println(name+" "+telNo);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addPhoneNo("ģ��","������","010-333-3333");
		addPhoneNo("ģ��","ddd","010-222-3333");
		printList();
	}

}
