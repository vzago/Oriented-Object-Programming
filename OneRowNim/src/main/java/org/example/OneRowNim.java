package org.example;
import java.io.*;
/*
 * OneRowNim.java
 * The full version of One Row Nim. This version can be CLUIPlayable
 */

import java.awt.*;

class NimPlayerSuper implements IPlayer{
    private OneRowNim game;

    public NimPlayerSuper(OneRowNim game){
        this.game = game;
    }

    public String makeAMove(String prompt) {
        return "" + op_move();
    }

    public int op_move(){
        int sticksLeft = game.getSticks();
        if(sticksLeft == 2){
            return 1;
        }else if(sticksLeft == 3){
            return 2;
        }else if(sticksLeft > 3){
            return 3;
        }
        return 1;
    }

    public String toString() {
        return " " + this.getClass().getSimpleName();
    }

}

class NimPlayerBad implements IPlayer {
    private OneRowNim game;

    public NimPlayerBad(OneRowNim game) {
        this.game = game;
    }

    public String makeAMove(String prompt) {
        return "" + randomMove();
    }

    private int randomMove() {
        int sticksLeft = game.getSticks();
        return 1 + (int) (Math.random() * Math.min(sticksLeft, game.MAX_PICKUP));
    }

    public String toString() {
        return " " + this.getClass().getSimpleName();
    }
} // NimPlayerBad

public class OneRowNim extends TwoPlayerGame implements CLUIPlayableGame {
    public static final int MAX_PICKUP = 3;
    public static final int MAX_STICKS = 11;

    private int nSticks = MAX_STICKS;

    public OneRowNim() {
    } // Constructors

    public OneRowNim(int sticks) {
        nSticks = sticks;
    }

    public OneRowNim(int sticks, int starter) {
        nSticks = sticks;
        setPlayer(starter);
    }

    public boolean takeSticks(int num) {
        if (num < 1 || num > MAX_PICKUP || num > nSticks)
            return false; // Error
        else // Valid move
        {
            nSticks = nSticks - num;
            return true;
        } // else
    }// takeSticks

    public int getSticks() {
        return nSticks;
    } // getSticks

    public String getRules() {
        return "\n*** The Rules of One Row Nim ***\n" +
                "(1) A number of sticks between 7 and " + MAX_STICKS + " is chosen.\n" +
                "(2) Two players alternate making moves.\n" +
                "(3) A move consists of subtracting between 1 and\n\t" +
                MAX_PICKUP + " sticks from the current number of sticks.\n" +
                "(4) A player who cannot leave a positive\n\t" +
                " number of sticks for the other player loses.\n";
    }

    public boolean gameOver() { /** From TwoPlayerGame */
        return (nSticks <= 0);
    } // gameOver()


    public String getWinner() { /** From TwoPlayerGame */
        if (gameOver()) // {
            return "" + getPlayer() + " Nice game.";
        return "The game is not over yet."; // Game is not over
    } // getWinner()

    public String getGamePrompt() {
        return "\nYou can pick up between 1 and " + Math.min(MAX_PICKUP, nSticks) + " : ";
    }

    public String reportGameState() {
        if (!gameOver())
            return ("\nSticks left: " + getSticks() + " Who's turn: Player " + getPlayer());
        else
            return ("\nSticks left: " + getSticks() + " Game over! Winner is Player " + getWinner() + "\n");

    } // reportGameState()

    /** From CLUIPlayableGame interface */
    public void play(UserInterface ui) {
        int sticks = 0;
        ui.prompt(getRules());
        if (computer1 != null)
            ui.report("\nPlayer 1 is a " + computer1.toString());
        if (computer2 != null)
            ui.report("\nPlayer 2 is a " + computer2.toString());

        while (!gameOver()) {
            IPlayer computer = null; // Assume no computers playing
            ui.report(reportGameState());
            switch (getPlayer()) {
                case PLAYER_ONE: // Player 1's turn
                    computer = computer1;
                    break;
                case PLAYER_TWO: // Player 2's turn
                    computer = computer2;
                    break;
            } // cases

            if (computer != null) { // If computer's turn
                sticks = Integer.parseInt(computer.makeAMove(""));
                ui.report(computer.toString() + " takes " + sticks + " sticks.\n");
            } else { // otherwise, user's turn
                ui.report(getGamePrompt());
                sticks = Integer.parseInt(ui.getUserInput()); // Get user's move
            }
            if (takeSticks(sticks)) // If a legal move
                changePlayer();
        } // while
        ui.report(reportGameState());// The game is now over
        ui.reportWinner(getWinner()); // The game is now over

    }

    public String submitUserMove(String theMove) {
        int sticks = Integer.parseInt(theMove);
        if (takeSticks(sticks)) {
            changePlayer();
            if (gameOver()) {
                return reportGameState() + "\nGame won by player" + getWinner() + "\n";
            } else {
                return reportGameState() + getGamePrompt();
            }
        }
        return "\nOops. " + sticks + " is an illegal move." + getGamePrompt();
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        for (int k = 1; k <= nSticks; k++) {
            g.drawLine(10 + k * 10, 10, 10 + k * 10, 60);
        }
    }

    /**
     * A OneRowNim Keyboard Application Program
     */
    public static void main(String args[]) {
        KeyboardReader kb = new KeyboardReader();
        CLUIPlayableGame game = new OneRowNim();

        kb.report("How many computers are playing, 0, 1, or 2? \n");
        int m = kb.getKeyboardInteger();
        for (int k = 0; k < m; k++) {
            kb.report("Choose a player: \n1 - NimPlayerBad\n2 - NimPlayerSuper\n");
            int typePlayer = kb.getKeyboardInteger();
            if(typePlayer == 1){
                IPlayer computer = new NimPlayerBad((OneRowNim)game);
                ((TwoPlayerGame) game).addComputerPlayer(computer);
            }else if(typePlayer == 2){
                IPlayer computer= new NimPlayerSuper((OneRowNim)game);
                ((TwoPlayerGame) game).addComputerPlayer(computer);
            }else{
                throw new IllegalArgumentException("Invalid type of player");
            }

        }
        game.play(kb);
    } // main()
} // OneRowNim class

interface IPlayer {
    /**
     * makeAMove() defines how a move is made. It is meant
     * to be implemented by any object that plays the game.
     *
     * @param prompt is a String that prompts the player for a move.
     * @return a String that describes the player's move
     */
    public String makeAMove(String prompt);
}

interface IGame {
    public String getGamePrompt();

    public String reportGameState();
} // IGame

interface UserInterface {

    public String getUserInput();
    public void prompt(String message);
    public void report(String s);
    public void reportWinner(String winner);
}

interface CLUIPlayableGame extends IGame {

    /**
     * play() implements the play loop for a game that
     * interfaces to a UserInterface. It is meant to
     * be implented by games that support a command-line
     * interface.
     */
    public abstract void play(UserInterface ui);
}

abstract class TwoPlayerGame {
    public static final int PLAYER_ONE = 1; // Class constants
    public static final int PLAYER_TWO = 2;

    protected boolean onePlaysNext = true; // Player 1 plays next
    protected int nComputers = 0; // How many computer players
    protected IPlayer computer1, computer2; // Computers are IPlayers

    /**
     * setPlayer() sets which player makes the first move.
     *
     * @param starter, an int representing PLAYER_ONE or PLAYER_TWOI
     */
    public void setPlayer(int starter) {
        if (starter == PLAYER_TWO)
            onePlaysNext = false;
        else
            onePlaysNext = true;
    } // setPlayer()

    /**
     * getPlayer() returns an int representing whose turn it it.
     *
     * @return an int representing PLAYER_ONE or PLAYER_TWO.
     */
    public int getPlayer() {
        if (onePlaysNext)
            return PLAYER_ONE;
        else
            return PLAYER_TWO;
    } // getPlayer()

    /**
     * changePlayer() toggles between PLAYER_ONE or PLAYER_TWO.
     */
    public void changePlayer() {
        onePlaysNext = !onePlaysNext;
    } // changePlayer

    /**
     * getNComputers() returns the number of computers playing
     *
     * @return an int representing the number of computer players
     */
    public int getNComputers() {
        return nComputers;
    }

    /**
     * getRules() returns a String describing the rules of the game.
     *
     * @return a String giving the game's rules.
     */
    public String getRules() {
        return "The rules of this game are: ";
    }

    /**
     * addComputerPlayer() adds to the number of computer players.
     *
     * @param player is an IPlayer
     */
    public void addComputerPlayer(IPlayer player) {
        if (nComputers == 0)
            computer2 = player;
        else if (nComputers == 1)
            computer1 = player;
        else
            return; // No more than 2 players
        ++nComputers;
    }

    /**
     * gameOver() defines when the game is over. It is meant to be
     * implemented in subclasses.
     */
    public abstract boolean gameOver(); // Abstract Methods

    /**
     * getWinner() defines who wins the game. It is meant to be
     * implemented in subclasses.
     */
    public abstract String getWinner();
} // TwoPlayerGame

class KeyboardReader implements UserInterface {
    private BufferedReader reader;

    public void reportWinner(String winner){
        System.out.println(">>>>>>>>>WINNER IS PLAYER " + winner + " <<<<<<<<<");
    }

    /**
     * KeyboardReader() constructor creates a BufferedReader that
     * is used for command-line and console I/O.
     */
    public KeyboardReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * getKeyboardInput() returns input read from the keyboard.
     */
    public String getKeyboardInput() {
        return readKeyboard();
    }

    /**
     * getKeyboardInput() returns an integer read from the keyboard.
     */
    public int getKeyboardInteger() {
        return Integer.parseInt(readKeyboard());
    }

    /**
     * getKeyboardInput() returns a double value read from the keyboard.
     */
    public double getKeyboardDouble() {
        return Double.parseDouble(readKeyboard());
    }

    /**
     * getUserInput() is a method of the UserInterface class. It
     * returns input read from the keyboard.
     */
    public String getUserInput() {
        return getKeyboardInput();
    }

    /**
     * report() is a method of the UserInterface class. It
     * prints its parameter.
     *
     * @param s is the String prompt
     */
    public void report(String s) {
        System.out.print(s);
    }

    public void prompt(String message) {
        // Implementação para exibir a mensagem de prompt
        System.out.println(message);
    }

    /**
     * display() prints its parameter.
     */
    public void display(String s) {
        System.out.print(s);
    }

    /**
     * readKeyboard() reads a line of input from the keyboard
     * where reader is a BufferedReader.
     *
     * @return a String storing a line read from the keyboard.
     */
    private String readKeyboard() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}