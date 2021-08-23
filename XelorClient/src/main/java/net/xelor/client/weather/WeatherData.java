package net.xelor.client.weather;

import net.xelor.client.XelorModule;

public class WeatherData {
    private static final WeatherDataProvider provider = XelorModule.getInstance().getWeatherDataProvider();

    public static WeatherData loadWeatherData() {
        return new WeatherData(
                provider.getRawTemperature(),
                provider.getTemperatureInCelsius(),
                provider.getTemperatureInFahrenheit(),
                provider.getHumidity(),
                provider.getPressure()
        );
    }

    private final float rawTemperature;
    private final float temperatureCelsius;
    private final float temperatureFahrenheit;
    private final int humidity;
    private final int pressure;

    private WeatherData(float rawTemperature, float temperatureCelsius, float temperatureFahrenheit, int humidity, int pressure) {
        this.rawTemperature = rawTemperature;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = temperatureFahrenheit;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public float getRawTemperature() {
        return rawTemperature;
    }

    public float getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public float getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }
}
