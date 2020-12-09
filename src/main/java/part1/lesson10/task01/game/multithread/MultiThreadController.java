package part1.lesson10.task01.game.multithread;

import com.google.common.base.Stopwatch;
import part1.lesson10.task01.game.ControllerInterface;
import part1.lesson10.task01.game.LifeField;
import part1.lesson10.task01.game.WindowField;
import part1.lesson10.task01.model.Config;
import part1.lesson10.task01.model.Coord;
import part1.lesson10.task01.utils.FileOperation;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class MultiThreadController implements ControllerInterface {
    private Config config;
    private WindowField windowField;
    private LifeField lifeField;
    private int steps;
    private boolean isStarted = false;
    private CopyOnWriteArrayList<Coord> listToChangeUnit = new CopyOnWriteArrayList<>();
    private ForkJoinPool fjp = new ForkJoinPool();

    public MultiThreadController(String pathToConfig) {
        this.config = FileOperation.readConfig(pathToConfig);
        this.windowField = new WindowField(config.getField().getRows(), config.getField().getCols(), this, "MultiThreadController");
        this.lifeField = new LifeField(config.getField(), config.getCoords(), windowField);
        this.steps = config.getSteps();
    }

    public void start() {
        if (!isStarted) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            for (int i = 0; i < steps; i++) {
                fjp.invoke(new ComputationTask(lifeField, listToChangeUnit, 0));
                listToChangeUnit.forEach((c) -> lifeField.setLifeUnit(c, !lifeField.isAlive(c)));
                listToChangeUnit.clear();
            }
            stopwatch.stop();
            long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("MultiThreadController: " + millis + "ms");
            FileOperation.writeConfig(lifeField.getResultCoord());
            isStarted = true;
        }
    }
}
