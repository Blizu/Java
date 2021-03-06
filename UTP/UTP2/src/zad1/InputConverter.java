package zad1;

import java.util.function.Function;

public class InputConverter<T> {
	private T input;
    InputConverter(T input) {
        this.input = input;
    }

    <R> R convertBy(Function ... functions) {
        Object tmp;
        tmp = input;
        for (Function function : functions){
            tmp = function.apply(tmp);
        }
        return (R)tmp;
    }
}
