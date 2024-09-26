package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.board.GameBoard;
import cleancode.minesweeper.tobe.exception.GameException;

public interface OutputHandler {

    void showGameStartComments();

    void showBoard(GameBoard board);

    void showGameWinningComment();

    void showGameLosingComment();

    void showCommentForSelectingCell();

    void showCommentForUserAction();

    void showExceptionMessage(GameException e);

    void showSimpleMessage(String message);
}
