
/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */

package zad1;

import java.util.function.Function;



public interface Mapper<inputType, outputType> { // Uwaga: interfejs musi być sparametrtyzowany
    outputType map(inputType input);
}
