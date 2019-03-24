/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */

package zad3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.function.Function;


public class Anagrams {
	
    List<String> resultFileRead = new ArrayList<>();
    List<String> inputList = new ArrayList<>();
    List<List<String>> outputList = new ArrayList<>();

    public Anagrams(String allWords) {

        try {
            List<String> readFileList = Files.readAllLines(Paths.get(allWords));

            for (String line : readFileList)
                resultFileRead.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : resultFileRead)
            inputList.addAll(Arrays.asList(s.split(" ")));
    }

    public List<List<String>> getSortedByAnQty() {
        Function<String, String> sortingChars = s -> {
                                              char [] chars = s.toCharArray();
                                              Arrays.sort(chars);
                                              return String.valueOf(chars);
                                             };



        Map<String, List<String>> sortingResult = inputList.stream()
                .collect(Collectors.groupingBy(sortingChars, Collectors.mapping(s -> s, Collectors.toList())));

            outputList = new ArrayList<>(sortingResult.values());
            outputList.sort((s1, s2) -> s2.size() - s1.size());

        return outputList;
    }

    public String getAnagramsFor(String word) {
        String matchedResults = word + ": [";

        matchedResults += outputList.stream().filter(s -> s.contains(word))
                .flatMap(Collection::stream)
                .filter(s -> !(s.equals(word)))
                .collect(Collectors.joining(", "));

        matchedResults += "]";
        return matchedResults;
    }

}
