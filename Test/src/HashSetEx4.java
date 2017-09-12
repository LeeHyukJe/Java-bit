import java.util.*;

class Person {
	String name;
	int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return name + " " + age;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person temp = (Person) obj;
			return name.equals(temp.name)&& age==temp.age;
		}
		return false;
	}
}

public class HashSetEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set set = new HashSet();

		set.add("abc");
		set.add(new Person("dd", 27));
		set.add(new Person("dd", 27));
		System.out.println(set);

	}

}
