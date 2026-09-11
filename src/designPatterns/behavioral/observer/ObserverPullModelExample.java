package designPatterns.behavioral.observer;

// Here observer will also have a reference to the subject and
// will pull the data from the subject when notified.
// This is known as the pull model of the observer pattern.
// Only difference is that in the push model, the subject pushes the data to the observers when notifying them,
// while in the pull model, the observers pull the data from the subject when notified.
// Observer will have dependency on the subject in the pull model.
import java.util.ArrayList;
import java.util.List;

interface WeatherObservablePull {
    void addObserver(WeatherObserverPull observer);
    void removeObserver(WeatherObserverPull observer);
    void notifyObservers();
    void setWeatherData(float temperature, float humidity, float pressure);
    WeatherData getWeatherData();
}

class WeatherStationPull implements WeatherObservablePull {
    private final List<WeatherObserverPull> observers;
    private WeatherData weatherData;

    public WeatherStationPull() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(WeatherObserverPull observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserverPull observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserverPull observer : observers) {
            observer.update();
        }
    }

    @Override
    public void setWeatherData(float temperature, float humidity, float pressure) {
        this.weatherData = new WeatherData(temperature, humidity, pressure);
        notifyObservers();
    }

    @Override
    public WeatherData getWeatherData() {
        return weatherData;
    }
}

interface WeatherObserverPull {
    void update();
}

class CurrentConditionsDisplayPull implements WeatherObserverPull {
    private final WeatherObservablePull weatherStation;

    public CurrentConditionsDisplayPull(WeatherObservablePull weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.addObserver(this);
    }

    @Override
    public void update() {
        WeatherData data = weatherStation.getWeatherData();
        System.out.println("Current conditions: " +
                data.getTemperature() + "°C, " +
                data.getHumidity() + "% humidity, " +
                data.getPressure() + " hPa pressure");
    }
}

class ForecastDisplayPull implements WeatherObserverPull {
    private final WeatherObservablePull weatherStation;

    public ForecastDisplayPull(WeatherObservablePull weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.addObserver(this);
    }

    @Override
    public void update() {
        WeatherData data = weatherStation.getWeatherData();
        System.out.println("Forecast: " +
                "Temperature: " + data.getTemperature() + "°C, " +
                "Humidity: " + data.getHumidity() + "%, " +
                "Pressure: " + data.getPressure() + " hPa");
    }
}


public class ObserverPullModelExample {
}
