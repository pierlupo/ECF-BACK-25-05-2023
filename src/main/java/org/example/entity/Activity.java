package org.example.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member members;

    @Temporal(TemporalType.TIME)
    private Time Timetable;

    public Activity() {
    }

    public Activity(String name, int duration, Time timetable) {
        this.name = name;
        this.duration = duration;
        Timetable = timetable;
    }

    public Time getTimetable() {

        return Timetable;
    }

    public void setTimetable(Time timetable) {
        Timetable = timetable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Member getMembers() {
        return members;
    }

    public void setMembers(Member members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", members=" + members +
                '}';
    }
}
