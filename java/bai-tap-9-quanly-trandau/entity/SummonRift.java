package entity;

import java.time.LocalTime;
import java.util.List;

public class SummonRift {
    private LocalTime timeBegin;
    private List<Team> teams;

    public SummonRift() {
    }

    public SummonRift(LocalTime timeBegin, List<Team> teams) {
        this.timeBegin = timeBegin;
        this.teams = teams;
    }

    public LocalTime getTimeBegin() {
        return this.timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return 
            " timeBegin='" + getTimeBegin() + "' \n" +
            " teams='" + getTeams() + "'";
    }
    
}
