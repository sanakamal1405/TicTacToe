package strategies;

import models.Board;
import java.util.*;
import java.util.stream.Collectors;

import models.BoardCell;
import models.Symbol;
import strategies.PlayingStrategy;


public class RandomPlayingStrategy implements PlayingStrategy {

    @Override
    public void makeMove(Board b, Symbol s)
    {
        List<BoardCell> emptyCells=b.getBoard()
                                    .stream()
                                    .flatMap(List::stream) // Gets the stream for each array and then combines it
                                    .filter(cell -> cell.getSymbol() == null)
                                    .collect(Collectors.toList());


        Random r=new Random();
        int x=r.nextInt(emptyCells.size());

        b.getBoard().get(emptyCells.get(x).getX()).set(emptyCells.get(x).getY(),new BoardCell(emptyCells.get(x).getX(),emptyCells.get(x).getY(),s));

    }
}
