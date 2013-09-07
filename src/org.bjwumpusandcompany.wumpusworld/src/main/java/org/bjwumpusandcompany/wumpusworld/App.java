package org.bjwumpusandcompany.wumpusworld;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	Board board = new Board(new Size(4, 4, 1));
        System.out.println(board.toString());
    }
}
