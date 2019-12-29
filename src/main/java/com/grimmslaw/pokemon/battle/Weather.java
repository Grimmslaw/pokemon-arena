package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.exceptions.battle.WeatherException;
import com.grimmslaw.pokemon.model.Duration;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.statuses.Effectable;
import com.grimmslaw.pokemon.statuses.Turnable;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 * TODO
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class Weather implements Turnable {

    private WeatherType currentWeather;

    public Weather() {
        currentWeather = WeatherType.CLEAR_SKIES;
    }

    public Weather(WeatherType startingWeather) {
        currentWeather = startingWeather;
    }

    public enum WeatherType implements Effectable {

        // TODO
        CLEAR_SKIES(-1, (target) -> {}, () -> {}),
        HARSH_SUNLIGHT(-1, (target) -> {}, () -> {}),
        RAIN(-1, (target) -> {}, () -> {}),
        SANDSTORM(-1, (target) -> {}, () -> {}),
        HAIL(-1, (target) -> {}, () -> {}),
        FOG(-1, (target) -> {}, () -> {}),
        STRONG_WINDS(-1, (target) -> {}, () -> {});

        WeatherType(int startingDuration, Consumer<Pokemon> applyEffectMethodToSet, Runnable tickMethodToSet) {
            duration = new Duration(startingDuration);
            applyEffectMethod = applyEffectMethodToSet;
            tickMethod = tickMethodToSet;
        }

        public Duration duration;
        public Consumer<Pokemon> applyEffectMethod;
        public Runnable tickMethod;

        private void applyEffectToOneTarget(Pokemon target) {
            applyEffectMethod.accept(target);
        }

        @Override
        public void applyEffect(Pokemon... targets) {
            for (Pokemon target : targets) {
                applyEffectToOneTarget(target);
            }
        }

        @Override
        public void tick() {
            tickMethod.run();
        }

        @Override
        public int getCurrentDuration() {
            return this.duration.getCurrentDuration();
        }

    }

    public WeatherType getCurrentWeather() {
        return currentWeather;
    }

    private void setCurrentWeather(WeatherType weatherToSet) {
        // assumes all necessary checks have been made by public method
        currentWeather = weatherToSet;
    }

    @Override
    public void applyEffect(Pokemon... targets) {
        getCurrentWeather().applyEffect();
    }

    @Override
    public void tick() {
        getCurrentWeather().tick();
    }

    public void addWeather(WeatherType weatherToAdd) throws WeatherException {
        if (getCurrentWeather() == WeatherType.CLEAR_SKIES || getCurrentWeather().getCurrentDuration() < 1) {
            setCurrentWeather(weatherToAdd);
        } else {
            throw new WeatherException();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Weather.class.getSimpleName() + "[", "]")
                .add("currentWeather=" + currentWeather)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return currentWeather == weather.currentWeather;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentWeather);
    }
}
