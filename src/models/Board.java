package models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.IntStream;

@Getter
@Setter
public class Board {

    private List<List<BoardCell>> board= new ArrayList<>();
    public Board(int rows, int columns)
    {
        List<List<BoardCell>> cells = new ArrayList<>();
        IntStream.range(0, rows).forEach(row -> {
            List<BoardCell> rowCells = new ArrayList<>();
            IntStream.range(0, rows).forEach(column -> rowCells.add(new BoardCell(row, column,null)));
            cells.add(rowCells);
        });

        this.board=cells;


    }







}
