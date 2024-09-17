package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private final Cell[][] board;
    private final int landMineCount;

    public GameBoard(GameLevel level) {
        int rowSize = level.getRowSize();
        int colSize = level.getColSize();
        board = new Cell[rowSize][colSize];

        landMineCount = level.getLandMineCount();
    }

    public void flag(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        cell.flag();
    }

    public void open(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        cell.open();
    }

    public void openSurroundedCells(int rowIndex, int colIndex) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        if (rowIndex < 0 || rowIndex >= rowSize || colIndex < 0 || colIndex >= colSize) {
            return;
        }
        if (isOpenedCell(rowIndex, colIndex)) {
            return;
        }
        if (isLandMineCell(rowIndex, colIndex)) {
            return;
        }

        open(rowIndex, colIndex);
        if (doesCellHaveLandMineCount(rowIndex, colIndex)) {
            return;
        }

        openSurroundedCells(rowIndex - 1, colIndex - 1);
        openSurroundedCells(rowIndex - 1, colIndex);
        openSurroundedCells(rowIndex - 1, colIndex + 1);
        openSurroundedCells(rowIndex, colIndex - 1);
        openSurroundedCells(rowIndex, colIndex + 1);
        openSurroundedCells(rowIndex + 1, colIndex - 1);
        openSurroundedCells(rowIndex + 1, colIndex);
        openSurroundedCells(rowIndex + 1, colIndex + 1);
    }

    private boolean doesCellHaveLandMineCount(int rowIndex, int colIndex) {
        return findCell(rowIndex, colIndex).hasLandMineCount();
    }

    private boolean isOpenedCell(int rowIndex, int colIndex) {
        return findCell(rowIndex, colIndex).isOpened();
    }

    public void initializeGame() {
        int rowSize = getRowSize();
        int colSize = getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                board[row][col] = new EmptyCell();
            }
        }

        for (int i = 0; i < landMineCount; i++) {
            int landMineRow = new Random().nextInt(rowSize);
            int landMineCol = new Random().nextInt(colSize);
            board[landMineRow][landMineCol] = new LandMineCell();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(row, col)) {
                    continue;
                }

                int count = countNearbyLandMines(row, col);
                if (count == 0) {
                    continue;
                }
                board[row][col] = new NumberCell(count);
            }
        }
    }

    private Cell findCell(int rowIndex, int colIndex) {
        return board[rowIndex][colIndex];
    }

    public String getSign(int rowIndex, int colIndex) {
        Cell cell = findCell(rowIndex, colIndex);
        return cell.getSign();
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    private int countNearbyLandMines(int row, int col) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        int count = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < colSize && isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < colSize && isLandMineCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMineCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < rowSize && col + 1 < colSize && isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

    public boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        Cell cell = findCell(selectedRowIndex, selectedColIndex);
        return cell.isLandMine();
    }

    public boolean isAllCellChecked() {
        return Arrays.stream(board)
            .flatMap(Arrays::stream)
            .allMatch(Cell::isChecked);
    }

}
