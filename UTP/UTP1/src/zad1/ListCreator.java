/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListCreator<T extends Collection<E>, E, S>{
    private List<E> collection;

    private ListCreator(T collection){
        this.collection = new ArrayList<>(collection);
    }

    static <T extends Collection<E>, E, S> ListCreator<T, E, S> collectFrom(T collection){
        return new ListCreator<>(collection);
    }

    ListCreator when(Selector<E> sel){
        collection = collection.stream()
                .filter(sel::select)
                .collect(Collectors.toList());
        return this;
    }

    List<S> mapEvery(Mapper<E, S> mapper){
        return collection.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}
