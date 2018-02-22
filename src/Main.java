import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class Main {
    public static void main(String[] arg) throws InterruptedException {

        Terminal terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));

        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);

        Board board = new Board(terminal);
       Player player = new Player(terminal);
       Monster monster = new Monster(terminal);

        boolean gameIsRunning = true;
        while (gameIsRunning) {

            Key key;
            do {
                Thread.sleep(5);
                key = terminal.readInput();
            }
            while (key == null);

            Key.Kind keyPressed = key.getKind();

            monster.moveMonster(terminal,player);
            player.movePlayer(terminal, keyPressed);
            gameIsRunning = checkPlayerAlive(monster, player);

            board.updateScreen(terminal, player, monster);


        }


    }

    public static boolean checkPlayerAlive(Monster monster, Player player){
        if(monster.getX() == player.getX() && monster.getY() == player.getY()){
            return false;
        }
        return true;
    }
}
