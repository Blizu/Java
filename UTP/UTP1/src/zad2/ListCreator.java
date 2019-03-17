

package zad2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

public class ListCreator<T extends Collection<E>, E, S>{
    private List<E> collection;

    private ListCreator(T collection){
        this.collection = new ArrayList<>(collection);
    }

    static <T extends Collection<E>, E, S> ListCreator<T, E, S> collectFrom(T collection){
        return new ListCreator<>(collection);
    }

    ListCreator when(Function<E, Boolean> function){
        collection = collection.stream()
                .filter(function::apply)
                .collect(Collectors.toList());
        return this;
    }

    List<S> mapEvery(Function<E, S> function){
        return collection.stream()
                .map(function::apply)
                .collect(Collectors.toList());
    }
}
