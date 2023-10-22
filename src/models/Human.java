package models;
import exceptions.InvalidMove;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;

import java.util.*;


public class Human extends Player{
    User user;

    public Human(User u, Symbol s)
    {
        super(s);
        this.user=u;
    }
    @Override
    public void play(Board b) throws InvalidMove {

        //print board
        Scanner s1=new Scanner (System.in);
        System.out.println("Enter your move");
        int r=s1.nextInt();
        int c=s1.nextInt();
        if(validate(b,r,c))
        {
            b.getBoard().get(r).set(c,new BoardCell(r,c,this.getSymbol()));
        }
        else
        {
            throw new InvalidMove("Entered move not allowed");
        }



        //set this player's symbol in board

    }

    boolean validate(Board b, int r, int c)
    {
        return b.getBoard().get(r).get(c).getSymbol()==null;

    }
}
