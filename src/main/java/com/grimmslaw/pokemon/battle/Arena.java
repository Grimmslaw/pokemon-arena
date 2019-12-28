package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.teams.Team;

public class Arena {

    private Supervisor supervisor;
    private Weather currentWeather;
    private Team team1;
    private Team team2;

    public Arena() {
        supervisor = Supervisor.getInstance();
        currentWeather = new Weather();
        team1 = new Team();
        team2 = new Team();
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void tick() {
        // TODO: do one full turn
    }

}
