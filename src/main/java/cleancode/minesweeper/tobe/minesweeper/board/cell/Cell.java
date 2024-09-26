package cleancode.minesweeper.tobe.board.cell;

public interface Cell {

    boolean isLandMine();

    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

}
