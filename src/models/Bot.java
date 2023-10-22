package models;

import lombok.experimental.SuperBuilder;
import strategies.PlayingStrategy;

@SuperBuilder
public class Bot extends Player{

    Level level;
    PlayingStrategy p;

    public Bot(Symbol symbol, PlayingStrategy p)
    {
        super(symbol);
        this.p=p;
    }

    @Override
    public void play(Board b)
    {
        p.makeMove(b, this.getSymbol());
    }
}
