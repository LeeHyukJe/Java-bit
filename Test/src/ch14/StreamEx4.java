package ch14;

import java.util.*;
import java.util.stream.*;

public class StreamEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<String[]> strArrStrm=Stream.of(new String[] {"abc","avvv","ddd"},
				new String[] {"123","334","ere"}); //stream»ý¼º
		
		//Stream<Stream<String>> strStrmStrm=strArrStrm.map(Arrays::stream);
		Stream<String> strStrm=strArrStrm.flatMap(Arrays::stream);
		strStrm.map(String::toLowerCase).distinct().forEach(System.out::println);
		System.out.println();
		
		String[] lineArr= {	"Believe or not It is true","Do or do not There is no try"};
		Stream<String> lineStream=Arrays.stream(lineArr);  //Stream<Stream<String>>
		lineStream.flatMap(line->Stream.of(line.split(" +"))).map(String::toLowerCase).distinct().forEach(System.out::println);
		System.out.println();
		
		Stream<String> strStrm1=Stream.of("aa","bb","cc","dd");
		Stream<String> strStrm2=Stream.of("bbb","ccc","ddd");
		
		Stream<Stream<String>> strStrmStrm=Stream.of(strStrm1,strStrm2);
		
	}

}
