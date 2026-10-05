package systemDesign.lowLevelDesign.tictactoe;

import systemDesign.lowLevelDesign.tictactoe.model.GameStatus;

public class PlayGame {
    static void main() {
        System.out.println("\n===>>> TicTacToe Game\n");
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        GameStatus status = game.startGame();
        System.out.print("\n===>>> GAME OVER: ");
        switch (status) {
            case WINNER_FOUND:
                System.out.print(game.winner.name + " won the game");
                break;
            case DRAW:
                System.out.print(" Its a Draw!");
                break;
            default:
                System.out.print(" Game Ends");
                break;
        }
    }
}
