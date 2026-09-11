package designPatterns.behavioral.observer;

// A design pattern that allows an object (the subject/observable/publisher)
// to notify a list of dependent objects (the observers/dependents/subscribers)
// when its state changes.
// This is useful for implementing event-driven systems,
// where multiple components need to react to changes in a central object.
// Real life example:
// Weather Station (Subject) notifies multiple Weather Displays (Observers) when the weather data changes.
// Social Media Feeds (Subject) notify users (Observers) when new posts are made.
// Stock Market (Subject) notifies investors (Observers) when stock prices change.
// Subscription Services (Subject) notify subscribers (Observers) when new content is available.

// Two Models of Observer Pattern:
// 1. Push Model: The subject pushes the state change to the observers.
// 2. Pull Model: The observers pull the state change from the subject when notified.

import java.util.ArrayList;
import java.util.List;

// Push Model Example:
interface WeatherObservable {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
    void setWeatherData(float temperature, float humidity, float pressure);
}

class WeatherStation implements WeatherObservable {
    private List<WeatherObserver> observers;
    private WeatherData weatherData;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(weatherData);
        }
    }

    @Override
    public void setWeatherData(float temperature, float humidity, float pressure) {
        this.weatherData = new WeatherData(temperature, humidity, pressure);
        notifyObservers();
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }
}

interface WeatherObserver {
    void update(WeatherData data);
}

class CurrentConditionsDisplay implements WeatherObserver {
    @Override
    public void update(WeatherData data) {
        System.out.println("Current conditions: " +
                data.getTemperature() + "°C, " +
                data.getHumidity() + "% humidity, " +
                data.getPressure() + " hPa");
    }
}

class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

public class ObserverPushModelExample {
    static void main() {
        WeatherStation weatherStation = new WeatherStation();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        CurrentConditionsDisplay currentDisplay2 = new CurrentConditionsDisplay();

        weatherStation.addObserver(currentDisplay);
        weatherStation.addObserver(currentDisplay2);

        weatherStation.setWeatherData(25.0f, 65.0f, 1013.0f);
        weatherStation.setWeatherData(26.5f, 70.0f, 1012.5f);
    }
}
