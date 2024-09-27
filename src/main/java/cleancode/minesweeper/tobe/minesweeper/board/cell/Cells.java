package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Arrays;
import java.util.List;

/**
 * 일급 컬렉션 - 컬렉션 외에 다른 멤버 변수가 없다: 일급 컬렉션은 오직 컬렉션만을 멤버 변수로 가집니다.
 */
public class Cells {

    private final List<Cell> cells;

    private Cells(List<Cell> cells) {
        this.cells = cells;
    }

    public static Cells of(List<Cell> cells) {
        return new Cells(cells);
    }

    public static Cells from(Cell[][] cells) {
        List<Cell> cellList = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .toList();
        return of(cellList);
    }

    public boolean isAllChecked() {
        return cells.stream().allMatch(Cell::isChecked);
    }

}
