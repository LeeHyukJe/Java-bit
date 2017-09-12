package ch14;

import java.util.*;
import java.util.function.*;

public class LambdaEx5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Supplier<Integer> s=()->(int)(Math.random()*100+1);
		Consumer<Integer> c=i->System.out.println(i+", ");
		Predicate<Integer>p=i->i%2==0;
		Function<Integer, Integer> f=i->i/10*10;
		List<Integer> list=new ArrayList<>();
		makeRandomList(s,list);
	}
	
	static <T> List<T> doSomething(Function<T,T> f, List<T> list){
		List<T> newList=new ArrayList<T>(list.size());
		for(T i:list) {
			newList.add(f.apply(i));
		}
		return newList;
	}
	
	static <T> void makeRandomList(Supplier<T>s, List<T> list) {
		for(int i=0;i<list.size();i++) {
			list.add(s.get());
		}
	}
	
}
