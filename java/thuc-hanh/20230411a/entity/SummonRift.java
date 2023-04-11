package entity;

import java.util.List;

public class SummonRift{
    private String timeBegin;
    private List<Team> teams;

    public SummonRift() {
    }

    public SummonRift(String timeBegin, List<Team> teams) {
        this.timeBegin = timeBegin;
        this.teams = teams;
    }

    public String getTimeBegin() {
        return this.timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public SummonRift timeBegin(String timeBegin) {
        setTimeBegin(timeBegin);
        return this;
    }

    public SummonRift teams(List<Team> teams) {
        setTeams(teams);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " timeBegin='" + getTimeBegin() + "'" +
            ", teams='" + getTeams() + "'" +
            "}";
    }


}