package section14seleniumjavastreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PracticeStreams {

	static void countWordsUsingArrayList() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("Abcd");
		al.add("def");
		int count = 0;

		for (String s : al) {
			if (s.startsWith("A")) {
				count++;
			}
		}
		System.out.println("Total words starts with A :" + count);
	}

	static void streamsArrayList() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("Abcd");
		al.add("def");
		System.out.println(al.stream().filter(s -> s.startsWith("A")).count());
		al.stream().filter(s -> s.length() > 1).forEach(s -> System.out.println("length greater than 1 " + s));
		al.stream().filter(s -> s.length() > 1).limit(1).forEach(s -> System.out.println(s));
		;
	}

	static void streamMap() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("Aef");
		al.add("Abc");
		al.add("def");
		al.stream().filter(x -> x.startsWith("A")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

		System.out.println("=============Functional Interface===================");

		// Functional Interface
		ArrayList<String> al2 = al.stream().filter(s -> s.startsWith("A")).map(s -> s.toUpperCase())
				.collect(Collectors.toCollection(ArrayList::new));
		al2.forEach(x -> System.out.println(x));

		System.out.println("====sort and convert to upper which has starting letter A====");

		// sort and convert to upper which has starting letter A
		List<String> als = Arrays.asList("Aef", "Abc", "def");
		List<String> al3 = als.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase())
				.collect(Collectors.toList());
		al3.forEach(s -> System.out.println(s));

		System.out.println("====Merging two arraylist====");

		// merging two lists
		ArrayList<String> al4 = new ArrayList<String>();
		al4.add("Aef");
		al4.add("Abc");
		al4.add("def");

		ArrayList<String> al5 = new ArrayList<String>();
		al5.add("Zef");
		al5.add("Xbc");
		al5.add("Mef");

		Stream<String> al6 = Stream.concat(al4.stream(), al5.stream());
		al6.forEach(s->System.out.println(s));
		
		//printing unique numbers
		System.out.println("====printing unique numbers====");
		List<Integer> ls=Arrays.asList(1,7 ,7,9,4,0,9);
		ls.stream().distinct().forEach(s->System.out.print(s+" "));
		System.out.println();
		
		//sorting and getting 3rd index value
		System.out.println("============Sorting===============");
		List<Integer> lsor=Arrays.asList(1,7 ,7,9,4,0,9);
		lsor.stream().sorted().forEach(s->System.out.print(s+" "));
		System.out.println();
		
		//sorting and getting 3rd index value
		System.out.println("====sorting and getting 3rd index value====");
		System.out.println(ls.stream().sorted().collect(Collectors.toList()).get(3));
		
		//Checking string existence in a list
		System.out.println("====Checking string existence in a list====");
		ArrayList<String> al7 = new ArrayList<String>();
		al7.add("Zef");
		al7.add("Xbc");
		al7.add("Mef");
		boolean flag=al7.stream().anyMatch(s->s.equals("Zef"));
		System.out.println("String existence in array list: "+flag);		
	}	

	public static void main(String[] args) {
		// countWordsUsingArrayList();
		// streamsArrayList();
		streamMap();
	}

}
