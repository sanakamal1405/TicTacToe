package models;


import exceptions.InvalidGame;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Getter
@Setter
public class Game {

    private Board board;
    private List<Player> players=new ArrayList<>();
    private int noOfPlayer;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Game()
    {

    }

    public void start()
    {
        Random r= new Random();
        int nextPlayerIndex=r.nextInt(noOfPlayer);
       this.gameStatus=GameStatus.RUNNING;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private Game g=new Game();

        public Builder board(int r, int c)
        {
            g.board =new Board(r,c);
            return this;
        }

        public Builder noOfPlayer(int n)
        {
            g.noOfPlayer =n;
            return this;
        }


        public Builder players(Player p)
        {
            g.players.add(p);
            return this;

        }

        public Builder gameStatus(GameStatus gs)
        {
            g.gameStatus=gs;
            return this;
        }

        public Game build() throws InvalidGame
        {
            Game finalGame= new Game();
            if(validate(this.g))
            {
                finalGame.gameStatus=this.g.gameStatus;
                finalGame.board=this.g.board;
                finalGame.noOfPlayer=this.g.noOfPlayer;
                finalGame.players=this.g.players;
                return finalGame;
            }
            else
            {
                throw new InvalidGame("Invalid game declartion");
            }

        }

        private boolean validate(Game g)
        {
            if(g.players.size()!=this.g.noOfPlayer)
                return false;
            if(g.players
                    .stream()
                    .map(Player::getSymbol).count() !=this.g.noOfPlayer)
                return false;

            return true;
        }
    }

}
