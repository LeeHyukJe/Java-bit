package ch14;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] strArr= {
				"Inheritance","Java","Lambda","stream","OptionalDouble","IntStream","count","sum"
		};
			
		Stream<String> str=Stream.of(strArr);
		str.forEach(System.out::print);
		
		Stream.of(strArr).forEach(System.out::println);
		
		boolean noEmptyStr=Stream.of(strArr).noneMatch(s->s.length()==0);
		Optional<String> sword=Stream.of(strArr).filter(s->s.charAt(0)=='s').findFirst();
		
		System.out.println("noEmptyStr: "+noEmptyStr);
		System.out.println("sword: "+sword.get());
		
		IntStream intStream1=Stream.of(strArr).mapToInt(String::length);
		IntStream intStream2=Stream.of(strArr).mapToInt(String::length);
		IntStream intStream3=Stream.of(strArr).mapToInt(String::length);
		IntStream intStream4=Stream.of(strArr).mapToInt(String::length);
		Function<Integer,Integer> f=i->i+10;
		
		int count=intStream1.reduce(0, (a,b)->a+1);
		System.out.println("using StreamClass reduce count: "+count);
		int sum=intStream2.reduce(0, (a,b)->a+b);
		System.out.println("using reduce sum: "+sum);
		
		OptionalInt max=intStream3.reduce(Integer::max);
		System.out.println("using OptionalInt max: "+max.getAsInt());
		OptionalInt min=intStream4.reduce(Integer::min);
		System.out.println("using OptionalInt min: "+min.getAsInt());
	}

}

