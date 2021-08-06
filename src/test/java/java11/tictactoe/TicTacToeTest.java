package java11.tictactoe;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;


public class TicTacToeTest {
    @Test
    @Disabled
    public void testPrintGameBoard() {
        TicTacToe.printGameBoard();
    }


    @Test
    @Disabled
    public void testPlayerChoosePosition() {
        TicTacToe.playerChoosePosition(2);

        TicTacToe.printGameBoard();
    }


    @Test
    @Disabled
    public void testComputerChoosePosition() {
        for (var i = 1; i <= 100; i++) {
            System.out.print(TicTacToe.computerChoosePosition());
        }
    }


    @Test
    @Disabled
    public void testInputChoosePosition() {
        while (true) {
            TicTacToe.printGameBoard();

            var playerPosition = TicTacToe.viewPlayerChoose();
            TicTacToe.inputChoosePosition("PLAYER", playerPosition);

            var computerPosition = TicTacToe.computerChoosePosition();
            TicTacToe.inputChoosePosition("COMPUTER", computerPosition);

        }
    }


    @Test
    @Disabled
    public void testCheckWinner() {
        TicTacToe.printGameBoard();

        TicTacToe.inputChoosePosition("PLAYER",1);
        TicTacToe.inputChoosePosition("COMPUTER", TicTacToe.computerChoosePosition());
        TicTacToe.printGameBoard();

        TicTacToe.inputChoosePosition("PLAYER",2);
        TicTacToe.inputChoosePosition("COMPUTER", TicTacToe.computerChoosePosition());
        TicTacToe.printGameBoard();

        TicTacToe.inputChoosePosition("PLAYER",3);
        TicTacToe.inputChoosePosition("COMPUTER", TicTacToe.computerChoosePosition());
        TicTacToe.printGameBoard();

        var result = TicTacToe.checkWinner();
        System.out.println(result);

    }
}
