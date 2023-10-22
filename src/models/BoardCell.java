package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardCell {
    private Integer x;
    private Integer y;
    private Symbol symbol;

}
