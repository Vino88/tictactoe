package tictactoe.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class BoardUtil {

    public static List<List<String>> createEmpty(int boardSize) {
        List<List<String>> rows = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
            List<String> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < boardSize; columnIndex++) {
                row.add(BoardTile.EMPTY.toString());

            }
            rows.add(row);
        }

        return rows;
    }

    public static String getRandomAvailableTile(List<List<String>> rows) {
        List<String> available = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            List<String> row = rows.get(rowIndex);

            for (int columnIndex = 0; columnIndex < rows.size(); columnIndex++) {
                String tileValue = row.get(columnIndex);
                if (tileValue.isEmpty()) {
                    available.add(rowIndex + "-" + columnIndex);
                }
            }
        }

        if (available.isEmpty()) {
            return null;
        }

        int randomNum = new Random().nextInt(available.size());
        return available.get(randomNum);

    }

    public static List<List<String>> getAllLines(List<List<String>> rows, int boardSize) {
        List<List<String>> lines = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
            lines.add(rows.get(rowIndex));
        }

        for (int columnIndex = 0; columnIndex < boardSize; columnIndex++) {
            List<String> columnLine = new ArrayList<>();
            for (List<String> row : rows) {
                columnLine.add(row.get(columnIndex));
            }
            lines.add(columnLine);
        }

        List<String> diagonal1 = Arrays.asList(rows.get(0).get(0), rows.get(1).get(1), rows.get(2).get(2));
        lines.add(diagonal1);

        List<String> diagonal2 = Arrays.asList(rows.get(0).get(2), rows.get(1).get(1), rows.get(2).get(0));
        lines.add(diagonal2);

        return lines;
    }
}
