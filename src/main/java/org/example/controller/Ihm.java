package org.example.controller;

import org.example.entity.Activity;
import org.example.entity.Member;
import org.example.service.ActivityService;
import org.example.service.MemberService;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ihm {

    private MemberService memberService;
    private ActivityService activityService;
    private Scanner scanner;
    String choice;
    String memberChoice;
    String activityChoice;

    public  Ihm() {
        memberService = new MemberService();
        activityService = new ActivityService();
        scanner = new Scanner(System.in);
    }

    public  void start() {

        do {
            menu();
            choice = scanner.nextLine();
            switch (choice) {

                case "1" -> MemberMenu();
                case "2" -> ActivityMenu();
//                case "3" -> EmployeeMenu();
                case "0" ->  System.out.println("See you later, bye bye!");
            }
        }while(!choice.equals("0"));
        memberService.end();
    }
    private void menu() {

        System.out.println("########  Menu  #########");
        System.out.println("1 -- Member Menu");
        System.out.println("2 -- Activity Menu");
//        System.out.println("3 -- Employee Menu");
        System.out.println("0 -- Quit ");
    }
    // 1 - CRUD Member
    private void MemberMenu() {

        do {
            menuMember();
            memberChoice = scanner.nextLine();
            switch (memberChoice) {
                case "1" -> addMember();
                case "2" -> updateMember();
                case "3" -> deleteMember();
                case "4" -> getOneMemberById();
                case "5" -> getAllMembers();
                case "6" -> addNewActivityToMember();
                case "7" -> System.out.println("Go Back -->");
                default -> System.out.println("Invalid choice");
            }
        } while (!memberChoice.equals("6"));
    }

    //  Sous Menu Member
    private void menuMember() {

        System.out.println("#######################################");
        System.out.println(" ECF(Back)--SportCenter--Member's Menu ");
        System.out.println("#######################################");
        System.out.println("*************************");
        System.out.println("Choose an option please :");
        System.out.println("*************************");
        System.out.println("1 - Add a new member");
        System.out.println("2 - Update a member");
        System.out.println("3 - Delete a member");
        System.out.println("4 - Display one member");
        System.out.println("5 - Display all members");
        System.out.println("6 - Add an activity to a member");
        System.out.println("7 - go back");
        System.out.println("*************************");
    }

    private void addMember(){

        System.out.println("Pease enter your lastname : ");
        String lastname = scanner.nextLine();
        System.out.println("Pease enter your firstname : ");
        String firstname = scanner.nextLine();
        System.out.println("Please enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter today's date (dd/MM/yyyy) : ");
        String todaysDate = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(todaysDate);
            memberService.create(new Member(lastname,firstname,age,date));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void updateMember(){

        getAllMembers();
        System.out.println("Please enter the id of the member you want to update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Member m = memberService.findById(id);
        System.out.println("Pease enter your lastname : ");
        String lastname = scanner.nextLine();
        m.setLastName(lastname);
        System.out.println("Pease enter your firstname : ");
        String firstname = scanner.nextLine();
        m.setFirstName(firstname);
        System.out.println("Please enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        m.setAge(age);
        System.out.println("Please enter today's date (dd/MM/yyyy) : ");
        String todaysDate = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(todaysDate);
            m.setRegistrationDate(date);
            memberService.update(m);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteMember(){

        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Member m = memberService.findById(id);
        memberService.delete(m);
    }

    private void getOneMemberById(){

        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Member mb = memberService.findById(id);
        System.out.println(mb);
    }

    private void getAllMembers(){

        List<Member> members = memberService.findAll();
        for (Member mb: members) {
            System.out.println(mb);
        }
    }
    private void addNewActivityToMember(){
        getAllMembers();
        scanner.nextLine();
        getAllActivities();
        scanner.nextLine();
        System.out.println("Please enter the id of the member : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the name of the activity : ");
        String name = scanner.nextLine();
        try {
            Activity activity = new Activity(name);
            if(memberService.addActivityToMember(activity,id)){
                System.out.println("Member added to this activity successfully !");
            }else {
                System.out.println("Error");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    // 1 - CRUD Activity
    private void ActivityMenu() {

        do {
            menuActivity();
            activityChoice = scanner.nextLine();
            switch (activityChoice) {
                case "1" -> addActivity();
                case "2" -> updateActivity();
                case "3" -> deleteActivity();
                case "4" -> getOneActivityById();
                case "5" -> getAllActivities();
                case "6" -> System.out.println("Go Back -->");
                default -> System.out.println("Invalid choice");
            }
        } while (!memberChoice.equals("6"));
    }

    //  Sous Menu Activity
    private void menuActivity() {

        System.out.println("#######################################");
        System.out.println(" ECF(Back)--SportCenter--Activities' Menu ");
        System.out.println("#######################################");
        System.out.println("*************************");
        System.out.println("Choose an option please :");
        System.out.println("*************************");
        System.out.println("1 - Add a new activity");
        System.out.println("2 - Update an activity");
        System.out.println("3 - Delete an activity");
        System.out.println("4 - Display one activity");
        System.out.println("5 - Display all activities");
        System.out.println("6 - go back");
        System.out.println("*************************");
    }

    private void addActivity(){

        System.out.println("Please enter an activity's name : ");
        String name = scanner.nextLine();
        System.out.println("Please enter the activity's duration : ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the beginning hour of the activity (HH:mm:ss) : ");
        String hour = scanner.nextLine();
        try {
            Time time = (Time) new SimpleDateFormat("HH:mm:ss").parse(hour);
            activityService.create(new Activity(name,duration,time));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void updateActivity(){

        System.out.println("Please enter the id of the activity you want to update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Activity ac = activityService.findById(id);
        System.out.println("Please enter the activity's name : ");
        String name = scanner.nextLine();
        ac.setName(name);
        System.out.println("Please enter the activity's duration : ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        ac.setDuration(duration);
        System.out.println("Please enter the beginning hour of the activity (HH:mm:ss) : ");
        String hour = scanner.nextLine();
        try {
            Time time = (Time) new SimpleDateFormat("HH:mm:ss").parse(hour);
            ac.setTimetable(time);
            activityService.update(ac);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteActivity(){

        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Activity ac = activityService.findById(id);
        activityService.delete(ac);
    }

    private void getOneActivityById(){

        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Activity ac = activityService.findById(id);
        System.out.println(ac);
    }

    private void getAllActivities(){

        List<Activity> activities = activityService.findAll();
        for (Activity ac: activities) {
            System.out.println(ac);
        }
    }
}
