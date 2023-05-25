package org.example.entity;

import javax.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String duration;

    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "activity_member", joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Member> members = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    private String Timetable;

    public Activity() {
    }

    public Activity(String name, String duration, String timetable) {
        this.name = name;
        this.duration = duration;
        Timetable = timetable;
    }

    public Activity(List<Member> members) {
        this.members = members;
    }

    public Activity(String name) {
        this.name = name;
    }

//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }

    public String getTimetable() {

        return Timetable;
    }

    public void setTimetable(String timetable) {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addMember(Member m){
        this.members.add(m);
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
