

package zad3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.function.Function;

public class Anagrams {

	List<String> inputList = new ArrayList<>();
	List<List<String>> outputList = new ArrayList<>();
	List<String> resultFileRead = new ArrayList<>();

	public Anagrams(String allWords) {

		try {
			List<String> readFromFile = Files.readAllLines(Paths.get(allWords));

			for (String line : readFromFile)
				resultFileRead.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String s : resultFileRead)
			inputList.addAll(Arrays.asList(s.split(" ")));
	}

	public List<List<String>> getSortedByAnQty() {
		Function<String, String> returnSortedWordCharsList = word -> {
			char[] charTab = word.toCharArray();
			Arrays.sort(charTab);
			return new String(charTab);
		};

		Map<String, List<String>> wordToAnagramsMap = inputList.stream().collect(Collectors
				.groupingBy(returnSortedWordCharsList, Collectors.mapping(word -> word, Collectors.toList())));

		outputList = new ArrayList<>(wordToAnagramsMap.values());
		outputList.sort((string1, string2) -> string2.size() - string1.size());

		return outputList;
	}

	public String getAnagramsFor(String word) {
		String result = word + ": [";

		result += outputList.stream().filter(w -> w.contains(word)).flatMap(Collection::stream)
				.filter(w -> !(w.equals(word))).collect(Collectors.joining(", "));

		result += "]";
		return result;
	}
}
