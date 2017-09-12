package ch14;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StreamEx6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student[] stuArr= {
				new Student("이지바",true,1,1,330),
				new Student("이아",false,1,2,100),
				new Student("이바",true,2,1,120),
				new Student("이놔",true,3,2,230),
				new Student("몽쉴",false,4,3,400),
				new Student("처커",true,2,2,200),
				new Student("아알아",true,6,1,100),
				new Student("이좢",false,1,2,334)
		};
		List<String> names=Stream.of(stuArr).map(Student::getName).collect(Collectors.toList());
		System.out.println(names);
		
		Student[] stuArr2=Stream.of(stuArr).toArray(Student[]::new);
		for(Student s:stuArr2) {
			System.out.println(s);
		}
		
		Map<String,Student> stuMap=Stream.of(stuArr).collect(Collectors.toMap(s->s.getName(),p->p));//키는 이름 값은 student객체
		
		for(String name:stuMap.keySet())
			System.out.println(name+"-"+stuMap.get(name));
		
		long count=Stream.of(stuArr).collect(counting()); //count 계산
		long totalScore=Stream.of(stuArr).collect(summingInt(Student::getTotalScore)); //total Score 계산
		
		System.out.println("using Collectors class counting count: "+count); //최종연산
		System.out.println("using Collectors class summingInt totalScore: "+totalScore);
		
		totalScore=Stream.of(stuArr).collect(reducing(0,Student::getTotalScore,Integer::sum)); //Collectors클래스 메소드 이용
		System.out.println("using Collectors class reducing totalScore: "+totalScore);
		
		Optional<Student> topStudent=Stream.of(stuArr).collect(maxBy(Comparator.comparingInt(Student::getTotalScore)));
		System.out.println("using Collectors class maxBy topStudent: "+topStudent.get());
		
		IntSummaryStatistics stat=Stream.of(stuArr).collect(summarizingInt(Student::getTotalScore));
		System.out.println("using Collectors class summarizingInt: "+stat);
		
		
		String one=Stream.of(stuArr).map(Student::getName).collect(joining(" ")); //하나의 문자열로 연결해서 반환
		System.out.println(one);
		
		System.out.println("----------------------------------------------------");
		
		System.out.println("1. 단순분할(성별로 분할)");
		Map<Boolean,List<Student>> stuBysex=Stream.of(stuArr).collect(partitioningBy(Student::isMale));
		
		List<Student> maleStudent=stuBysex.get(true); //남학생 리스트
		List<Student> femaleStudent=stuBysex.get(false); //여학생 리스트
		
		for(Student s:maleStudent) {
			System.out.println(s);
		}
		for(Student f:femaleStudent) {
			System.out.println(f);
		}
		
		System.out.println("2. 단순분할+통계");
		Map<Boolean,Long> stuNumSex= Stream.of(stuArr).collect(partitioningBy(Student::isMale,counting()));
		
		System.out.println("남학생 수: "+stuNumSex.get(true));
		System.out.println("여학생 수: "+stuNumSex.get(false));
		
		System.out.println("3. 단순분할+통계(성별 1등)");
		
	}

}

class Student implements Comparable<Student>{
	String name;
	int ban;
	int totalScore;
	int hak;
	boolean isMale;
	
	Student(String name, boolean isMale,int hak, int ban, int totalScore){
		this.name=name;
		this.ban=ban;
		this.totalScore=totalScore;
		this.hak=hak;
		this.isMale=isMale;
	}
	
	public String toString() {
		return String.format("[%s, %s, %d, %d %d]", name,isMale? "남":"여",hak,ban,totalScore).toString();
	}
	
	enum Level{
		HIGH,MID,LOW
	}
	
	String getName() {
		return name;
	}
	
	int getBan() {return ban;}
	int getTotalScore() {return totalScore;}
	boolean isMale() {return isMale;}
	int gethak() {return hak;}
	public int compareTo(Student s) {
		return s.totalScore-this.totalScore;
	}
}
