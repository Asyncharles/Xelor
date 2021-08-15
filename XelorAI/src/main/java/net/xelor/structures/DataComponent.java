package net.xelor.structures;

import net.xelor.time.DayTime;
import net.xelor.time.Season;
import net.xelor.weather.WeatherData;

public class DataComponent {
    public static DataComponent getDataComponent() {
        return new DataComponent(
                DayTime.getCurrentDayTime(),
                Season.getCurrentSeason()
        );
    }

    public static DataComponent getDataComponent(long timestamp) {
        //TODO : Add day time and season values that corresponds to the timestamp
        return new DataComponent(
                timestamp,
                DayTime.getCurrentDayTime(),
                Season.getCurrentSeason()
        );
    }

    private final long timestamp;
    private final DayTime dayTime;
    private final Season season;
    private final WeatherData weatherData;

    private DataComponent(DayTime dayTime, Season season) {
        this(System.currentTimeMillis(), dayTime, season);
    }

    private DataComponent(long timestamp, DayTime dayTime, Season season) {
        this.timestamp = timestamp;
        this.dayTime = dayTime;
        this.season = season;
        this.weatherData = WeatherData.loadWeatherData();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public Season getSeason() {
        return season;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }
}
