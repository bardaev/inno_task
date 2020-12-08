package part1.lesson10.task01.game;

import part1.lesson10.task01.model.Config;
import part1.lesson10.task01.model.Coord;
import part1.lesson10.task01.utils.FileOperation;

import java.util.ArrayList;

public class Controller {

    private Config config;
    private WindowField windowField;
    private LifeField lifeField;
    private int steps;
    private boolean isStarted = false;
    private ArrayList<Coord> listToChangeUnit = new ArrayList<>();

    public Controller(String pathToConfig) {
        this.config = FileOperation.readConfig(pathToConfig);
        this.windowField = new WindowField(config.getField().getRows(), config.getField().getCols(), this);
        this.lifeField = new LifeField(config.getField(), config.getCoords(), windowField);
        this.steps = config.getSteps();
    }

    public void start() {
        if (!isStarted) {
            for (int i = 0; i < steps; i++) {
                if (i == 1) {
                    i = i;
                }
                scanField();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            FileOperation.writeConfig(lifeField.getResultCoord());
            isStarted = true;
        }
    }

    private void scanField() {
        for (int i = 0; i < lifeField.getRowSize(); i++) {
            for (int j = 0; j < lifeField.getColSize(); j++) {
                checkLifeField(new Coord(i, j));
            }
        }
        listToChangeUnit.forEach((c) -> {
            lifeField.setLifeUnit(c, !lifeField.isAlive(c));
        });
        listToChangeUnit.clear();
    }

    private void checkLifeField(Coord coord) {
        if (lifeField.isAlive(coord)) {
            if (isNeedKillOrNeedBorn(coord, false)) listToChangeUnit.add(coord);
        } else {
            if (isNeedKillOrNeedBorn(coord, true)) listToChangeUnit.add(coord);
        }
    }

    /**
     *
     * @param coord
     * @param flag true - need born, flag false - need kill
     * @return
     */
    private boolean isNeedKillOrNeedBorn(Coord coord, boolean flag) {
        int alive = 0;

        int row = coord.getRow();
        int col = coord.getCol();
        int[][] coords = {
                {row, col - 1},
                {row - 1, col - 1},
                {row - 1, col},
                {row - 1, col + 1},
                {row, col + 1},
                {row + 1, col + 1},
                {row + 1, col},
                {row + 1, col - 1}};

        for (int i = 0; i < coords.length; i++) {
            if (coords[i][0] < 0 || coords[i][0] >= lifeField.getRowSize() ||
                    coords[i][1] < 0 || coords[i][1] >= lifeField.getColSize()) continue;
            if (lifeField.isAlive(new Coord(coords[i][0], coords[i][1]))) alive++;
        }

        if (flag) {
            return alive == 3;
        } else {
            return !(alive == 2 || alive == 3);
        }
    }
}
