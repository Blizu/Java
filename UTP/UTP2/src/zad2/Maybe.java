package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Maybe<T> {
    public T x;

    public Maybe(){}
    public Maybe(T x) {
        this.x = x;
    }

     static <T> Maybe <T> of(T x) {
         if (x != null)
            return new Maybe<>(x);
         else
            return new Maybe<>();
    }

    void ifPresent(Consumer cons) {
        if (x != null) {
            cons.accept(x);
        }
    }

    public <E> Maybe <E> map(Function<T, E> function) {
        if (isPresent()) {
            E returned = function.apply(x);
            return new Maybe<>(returned);
        }
        return new Maybe<>();
    }

    T get(){
        if (x == null){
            throw new NoSuchElementException("Maybe is empty");
        }
        return x;
    }

    boolean isPresent(){
        return x != null;
    }

    T orElse(T defVal){
        if (isPresent())
            return get();
        return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if(pred.test(x) || x == null)
            return this;
        return new Maybe<>();
    }

    public String toString(){
        if(isPresent()){
            return "Maybe has value " + x;
        }
        return "Maybe is empty";
    }
}