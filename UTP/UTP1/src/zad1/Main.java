/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
	  List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
      System.out.println(test1(src1));
      List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
      System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
      Selector sel = new Selector<Integer>() {
          @Override
          public boolean select(Integer input) {
              return input < 10;
          }
      };

      Mapper map = new Mapper<Integer, Integer>() {
          @Override
          public Integer map(Integer input) {
              return input + 10;
          }
      };
      return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
      Selector sel = new Selector<String>() {
          @Override
          public boolean select(String input) {
              return input.length()>3;
          }
      };
      Mapper<String, Integer> map = new Mapper<String, Integer>() {
          @Override
          public Integer map(String input) {
              return input.length() + 10;
          }
      };

      return   ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }


	  public static void main(String[] args) {
	  new Main();
	  }
}
