package models;
import exceptions.InvalidMove;
import lombok.experimental.*;
import lombok.*;
@AllArgsConstructor
@Getter
@SuperBuilder
public abstract class Player {
    private Symbol symbol;
    public abstract void play(Board b) throws InvalidMove;

}
