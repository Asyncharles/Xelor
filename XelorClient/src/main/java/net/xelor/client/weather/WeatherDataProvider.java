package net.xelor.client.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.xelor.client.devices.information.InputDevice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WeatherDataProvider {
    private final Gson gson = new GsonBuilder().serializeNulls().create();
    private final long requestLimit = 1000L;
    private final String key;

    private String urlString;
    private String city;

    private long latestRequest;
    private JsonObject latestJsonObject;

    private boolean acceptInputDeviceData;
    private Map<String, Set<InputDevice>> inputDevices;

    public WeatherDataProvider(final String key, final String city) {
        this.key = key;
        this.city = city;
        this.urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key;
    }

    public WeatherDataProvider(final String key, final String city, final boolean acceptInputDeviceData) {
        this.key = key;
        this.city = city;
        this.urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key;
        this.acceptInputDeviceData = acceptInputDeviceData;
        this.inputDevices = new HashMap<>();
        registerDomains();
    }

    public WeatherDataProvider registerNewInputDevice(String domain, InputDevice inputDevice) {
        if (acceptInputDeviceData) {
            inputDevices.computeIfPresent(domain, (str, set) -> {
                set.add(inputDevice);
                return set;
            });
        }
        return this;
    }

    public void setCity(final String city) {
        this.city = city;
        this.urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key;
    }

    public float getRawTemperature() {
        updateWeatherData();
        return latestJsonObject.get("main").getAsJsonObject().get("temp").getAsFloat();
    }

    public float getTemperatureInCelsius() {
        updateWeatherData();
        return latestJsonObject.get("main").getAsJsonObject().get("temp").getAsInt() - 273.15F;
    }

    public float getTemperatureInFahrenheit() {
        updateWeatherData();
        return latestJsonObject.get("main").getAsJsonObject().get("temp").getAsFloat() * (9F/5F) - 459.67F;
    }

    public int getHumidity() {
        updateWeatherData();
        return latestJsonObject.get("main").getAsJsonObject().get("humidity").getAsInt();
    }

    public int getPressure() {
        updateWeatherData();
        return latestJsonObject.get("main").getAsJsonObject().get("pressure").getAsInt();
    }

    private void updateWeatherData() {
        if (System.currentTimeMillis() - latestRequest > requestLimit || latestJsonObject == null) {
            try {
                URL url = new URL(urlString);

                InputStreamReader isr = new InputStreamReader(url.openStream());
                BufferedReader reader = new BufferedReader(isr);

                latestJsonObject = gson.fromJson(reader.readLine(), JsonObject.class);
                latestRequest = System.currentTimeMillis();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void registerDomains() {
        inputDevices.put("temperature", new HashSet<>());
        inputDevices.put("humidity", new HashSet<>());
        inputDevices.put("pressure", new HashSet<>());
    }
}
