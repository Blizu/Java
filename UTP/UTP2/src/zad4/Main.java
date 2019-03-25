

package zad4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {

		List<String> wordsList = new ArrayList<>();

		try {
			Scanner scanner = new Scanner(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream());
			while (scanner.useDelimiter("\\A").hasNextLine()) {
				wordsList.add(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;

		Map<String, List<String>> wordAnagramsMap = new HashMap<>();

		wordsList.stream()
				.collect(Collectors.groupingBy(returnSortedWordCharsList,
						Collectors.mapping(k -> k, Collectors.toList())))
				.values().stream().filter(string -> string.size() >= 2)
				.sorted((str1, str2) -> str2.size() - str1.size())
				.map(str -> str.stream().collect(Collectors.toMap(k -> k, s -> str)))
				.forEach(strList -> strList.forEach(wordAnagramsMap::put));

		wordAnagramsMap.forEach((word, anagrams) -> {
			List<String> outputList = anagrams.stream().filter(anagram -> !(anagram.equals(word)))
					.collect(Collectors.toList());

			String result = word + " "
					+ outputList.stream().reduce((anagram1, anagram2) -> anagram1 + " " + anagram2).get();
			System.out.println(result);
		});
	}

	static Function<String, String> returnSortedWordCharsList = word -> {
		char[] charTab = word.toCharArray();
		Arrays.sort(charTab);
		return new String(charTab);
	};
}