package org.example.domain;

import org.example.common.BaseDomain;

import javax.persistence.*;


@Entity
@Table(name = "team")
public class Teams extends BaseDomain {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String TeamName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }

    @Override
    public String toString() {
        return TeamName;
    }
}