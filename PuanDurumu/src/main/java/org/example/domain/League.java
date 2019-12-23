package org.example.domain;

import javax.persistence.*;

@Entity
@Table(name = "league")
public class League {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private int point;

    @Column
    private int tie;

    @Column
    private int defeat;

    @Column
    private int victory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TEAM", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "TEAM_ID"))
    private Teams teams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getDefeat() {
        return defeat;
    }

    public void setDefeat(int defeat) {
        this.defeat = defeat;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }
}
