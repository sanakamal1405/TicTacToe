package strategies;

import models.Board;
import models.Symbol;

public interface PlayingStrategy {
    void makeMove(Board b, Symbol s);
}
