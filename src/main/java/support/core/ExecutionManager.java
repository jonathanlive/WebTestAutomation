package support.core;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import model.Car;
import org.openqa.selenium.remote.DesiredCapabilities;
import support.util.FileUtil;
import java.lang.reflect.Type;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecutionManager {

    private DesiredCapabilities caps;
    private static ExecutionManager instance;
    private static List<Car> cars = new ArrayList<>();
    private String projectFolder = FileSystems.getDefault().getPath("").toAbsolutePath().toString();

    private ExecutionManager() {

    }

    public static ExecutionManager getInstance() {
        if (instance == null)
            instance = new ExecutionManager();

        return instance;
    }

    public void startExecution() {
        DriverManager.getInstance().getDriver();
    }

    public void stopExecution() {
        DriverManager.getInstance().stopDriver();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void saveCarsOnFile(){
        Gson gson = new Gson();
        String cars = gson.toJson(getCars());
        FileUtil.write(cars,projectFolder + "/target/reports/report.txt");
    }

    public List<Car> restoreCarsFromFile(){
        Type carListType = new TypeToken<ArrayList<Car>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(FileUtil.read(projectFolder + "/target/reports/report.txt"), carListType);
    }
}


