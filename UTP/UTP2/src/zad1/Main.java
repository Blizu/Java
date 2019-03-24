package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
	  Function flines = (fileName) -> {
          List<String> verses = new ArrayList<>();
          BufferedReader reader;

          try {
              reader = new BufferedReader(new FileReader((String)fileName));
          } catch (Exception exception) {
              System.out.println("File name does not exists " + exception);
              return verses;
          }
          try {
              String line;
              while((line = reader.readLine()) != null) {

                  verses.add(line);
              }
          } catch (Exception exception) {
              System.out.println("Could not read lines from file or other " + exception);
          }

          return verses;
      };

      Function<List, String> join = (verses) -> {
          StringBuilder statement = new StringBuilder();

          for(Object verse: verses) {
              statement.append((String) verse);
          }

          return statement.toString();
      };
      Function collectInts = (sentence) -> {
          List<Integer> numbers = new ArrayList<>();

          Pattern pattern = Pattern.compile("-?\\d+");
          Matcher matcher = pattern.matcher((String)sentence);

          while(matcher.find()) {
              numbers.add(Integer.parseInt(matcher.group()));
          }

          return numbers;
      };
      Function<List, Integer> sum = (numbers) -> {
          int all = 0;

          for(Object number: numbers) {
              all += (Integer)number;
          }

          return all;
      };


    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
