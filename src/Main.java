import exceptions.InvalidGame;
import exceptions.InvalidMove;
import exceptions.InvalidSymbol;
import models.*;
import strategies.RandomPlayingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static final int BOARD_SIZE=3;
    public static final int NO_OF_PLAYERS=2;
    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to TicTacToe");

        //System.out.println("Enter no. of human players");

             Human h1=getHumanPlayer();




        Game game= createGame(h1);

        game.start();
        printBoard(game.getBoard());

        while(game.getGameStatus()==GameStatus.RUNNING)
        {
            Player p=game.getPlayers().get(game.getNextPlayerIndex());
            System.out.println("Enter move for the player "+ p.getSymbol());
            try
            {
                p.play(game.getBoard());
            }
            catch (InvalidMove e)
            {
                throw e;
            }
            checkGameStatus(game);
            printBoard(game.getBoard());

            game.setNextPlayerIndex((game.getNextPlayerIndex()+1)% game.getNoOfPlayer());
        }





    }

    public static void printBoard(Board board) {
        List<List<BoardCell>> cells=board.getBoard();
        for (int i = 0; i < cells.size(); ++i) {
            for (int j = 0; j < cells.size(); ++j) {
                Symbol symbol = cells.get(i).get(j).getSymbol();

                if (symbol == null) {
                    System.out.printf(" | - | ");
                } else {
                    System.out.printf(" | " + symbol + " | ");
                }
            }
            System.out.printf("\n");
        }
    }

    static void checkGameStatus(Game g)
    {
        checkRowWinner(g);
        checkColumnWinner(g);
        checkDiagonalWinner(g);
        checkDraw(g);


    }

    static void checkDiagonalWinner(Game g)
    {
        int x=0,o=0;
        for(int i=0;i<BOARD_SIZE;i++)
        {
            if(g.getBoard().getBoard().get(i).get(i).getSymbol()==Symbol.O)
                o++;
            if(g.getBoard().getBoard().get(i).get(i).getSymbol()==Symbol.X)
                x++;


        }

        if(o==BOARD_SIZE)
        {
            System.out.println("Game is won by player with symbol O");
            g.setGameStatus(GameStatus.WON);
        }

        if(x==BOARD_SIZE)
        {
            System.out.println("Game is won by player with symbol X");
            g.setGameStatus(GameStatus.WON);
        }

        int i=0,j=2;
        x=0;o=0;
        while(j>=0)
        {
            if(g.getBoard().getBoard().get(i).get(j).getSymbol()==Symbol.O)
                o++;
            if(g.getBoard().getBoard().get(i).get(j).getSymbol()==Symbol.X)
                x++;

            j--;
            i++;
        }

        if(o==BOARD_SIZE)
        {
            System.out.println("Game is won by player with symbol O");
            g.setGameStatus(GameStatus.WON);
        }

        if(x==BOARD_SIZE)
        {
            System.out.println("Game is won by player with symbol X");
            g.setGameStatus(GameStatus.WON);
        }

    }

    static void checkRowWinner(Game g)
    {

        for(List<BoardCell> x : g.getBoard().getBoard())
        {
            //x has first row
            int i=0,j=0;
            for(BoardCell y: x)
            {
                if(y.getSymbol()==Symbol.O)
                    i++;

                if(y.getSymbol()==Symbol.X)
                    j++;

            }

            if(i==BOARD_SIZE)
            {
                System.out.println("Player with symbol O won");
                g.setGameStatus(GameStatus.WON);
                break;
            }

            if(j==BOARD_SIZE)
            {
                System.out.println("Player with symbol X won");
                g.setGameStatus(GameStatus.WON);
                break;

            }


        }



    }

    static void checkColumnWinner(Game g)
    {

        for(int i=0;i<BOARD_SIZE;i++)
        {
            int x=0,y=0;
            for(int j=0;j<BOARD_SIZE;j++)
            {
                if(g.getBoard().getBoard().get(j).get(i).getSymbol()!=null && g.getBoard().getBoard().get(j).get(i).getSymbol()==Symbol.X)
                    x++;
                if(g.getBoard().getBoard().get(j).get(i).getSymbol()!=null && g.getBoard().getBoard().get(j).get(i).getSymbol()==Symbol.O)
                    y++;
            }

            if(x==BOARD_SIZE)
            {
                System.out.println("Player with symbol X won");
                g.setGameStatus(GameStatus.WON);
                break;
            }

            if(y==BOARD_SIZE)
            {
                System.out.println("Player with symbol O won");
                g.setGameStatus(GameStatus.WON);
                break;

            }


        }


    }

    static void checkDraw(Game g)
    {
        int count=0;

        for(int i=0;i<BOARD_SIZE;i++)
        {

            for(int j=0;j<BOARD_SIZE;j++)
            {
                if(g.getBoard().getBoard().get(j).get(i).getSymbol()==null)
                    count++;
            }
        }

        if(count==0)
        {
            g.setGameStatus(GameStatus.DRAW);
            System.out.println("Game is drawn");
        }

    }

    static Human getHumanPlayer() throws InvalidSymbol{
        Scanner s1 = new Scanner(System.in);

        System.out.println("Enter name");

        String name = s1.nextLine();

        System.out.println("Enter email");

        String email = s1.nextLine();

        User u = new User(name, email);

        System.out.println("Enter symbol");

            Symbol s;
        try {
             s = Symbol.valueOf(s1.next());
        }
        catch(IllegalArgumentException e)
        {
            throw  new InvalidSymbol("Entered symbol is invalid.");
        }

        return new Human(u, s);
    }


static Game createGame(Human h) throws InvalidGame
{
    try {
        return Game
                .builder()
                .board(BOARD_SIZE, BOARD_SIZE)
                .noOfPlayer(NO_OF_PLAYERS)
                .players(h)
                .players(
                        Bot.builder()
                                .level(Level.EASY)
                                .symbol(decideSymbolAsPerHumanPlayer(h))
                                .p(new RandomPlayingStrategy())
                                .build())
                .gameStatus(GameStatus.RUNNING)
                .build();
    }
    catch(InvalidGame e)
    {
        throw e;
    }

}


static Symbol decideSymbolAsPerHumanPlayer(Human h)
{
    if(h.getSymbol()==Symbol.X)
        return Symbol.O;
    else
        return Symbol.X;
}





}