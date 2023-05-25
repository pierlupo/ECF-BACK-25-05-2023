package org.example.controller;

import org.example.entity.Activity;
import org.example.entity.Member;
import org.example.entity.Teacher;
import org.example.service.ActivityService;
import org.example.service.MemberService;
import org.example.service.TeacherService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ihm {

    private MemberService memberService;
    private ActivityService activityService;
    private TeacherService teacherService;
    private Scanner scanner;
    String choice;
    String memberChoice;
    String activityChoice;
    String teacherChoice;
    public  Ihm() {
        memberService = new MemberService();
        activityService = new ActivityService();
        teacherService =new TeacherService();
        scanner = new Scanner(System.in);
    }

    public  void start() {

        do {
            menu();
            choice = scanner.nextLine();
            switch (choice) {

                case "1" -> MemberMenu();
                case "2" -> ActivityMenu();
                case "3" -> TeacherMenu();
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
                case "7" -> displayActivitiesOfAMember();
                case "8" -> System.out.println("Go Back -->");
                default -> System.out.println("Invalid choice");
            }
        } while (!memberChoice.equals("8"));
    }

    //  Sous Menu Member
    private void menuMember() {

        System.out.println("#######################################");
        System.out.println(" ECF(Back)--SportCenter--Member's Menu ");
        System.out.println("#######################################");
        System.out.println("#########################");
        System.out.println("Choose an option please :");
        System.out.println("*************************");
        System.out.println("1 - Add a new member");
        System.out.println("2 - Update a member");
        System.out.println("3 - Delete a member");
        System.out.println("4 - Display one member");
        System.out.println("5 - Display all members");
        System.out.println("6 - Add an activity to a member");
        System.out.println("7 - Display activities of a member");
        System.out.println("8 - go back");
        System.out.println("#########################");
    }

    private void addMember(){

        System.out.println("Please enter your lastname : ");
        String lastname = scanner.nextLine();
        System.out.println("Please enter your firstname : ");
        String firstname = scanner.nextLine();
        System.out.println("Please enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter registration's date (dd/MM/yyyy) : ");
        String registrationsDate = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(registrationsDate);
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
        System.out.println("Please enter your lastname : ");
        String lastname = scanner.nextLine();
        m.setLastName(lastname);
        System.out.println("Please enter your firstname : ");
        String firstname = scanner.nextLine();
        m.setFirstName(firstname);
        System.out.println("Please enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        m.setAge(age);
        System.out.println("Please enter registration's date (dd/MM/yyyy) : ");
        String registrationsDate = scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(registrationsDate);
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
        Member mber = memberService.findById(id);
        memberService.delete(mber);
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

        System.out.println("Please enter the id of the member : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the name of the activity : ");
        String name = scanner.nextLine();
        try {
            Activity activity = new Activity(name);
            if(memberService.addActivityToMember(activity,id)){
                System.out.println("Member added to this activity successfully !");
            }else{
                System.out.println("Error");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void displayActivitiesOfAMember() {
        System.out.println("Please enter the id of the member : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<Member> members = null;
        try {
            members = memberService.getActivitiesByMemberId(id);
            for(Member mbr :  members) {
                System.out.println(mbr.getId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 2 - CRUD Activity
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
               // case "6" -> addActivitytoMember();
                case "6" -> System.out.println("Go Back -->");
                default -> System.out.println("Invalid choice");
            }
        } while (!activityChoice.equals("6"));
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
      //  System.out.println("6 - Add an activity to a member");
        System.out.println("6 - go back -->");
        System.out.println("*************************");
    }

    private void addActivity(){

        System.out.println("Please enter an activity's name : ");
        String name = scanner.nextLine();
        System.out.println("Please enter the activity's duration : ");
        String duration = scanner.nextLine();
        System.out.println("Please enter the beginning hour of the activity (example : 10h00) : ");
        String hour = scanner.nextLine();
        try {
            activityService.create(new Activity(name,duration,hour));
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
        String duration = scanner.nextLine();
        scanner.nextLine();
        ac.setDuration(duration);
        System.out.println("Please enter the beginning hour of the activity (example : 10h00) : ");
        String hour = scanner.nextLine();
        ac.setTimetable(hour);
        try {
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


//    private void addActivitytoMember() {
//        String reponse;
//        System.out.println("Please enter the name of the activity you want to registrate to : ");
//        String name = scanner.nextLine();
//        Activity activity = new Activity(name);
//        do {
//            System.out.println("registrate to this activity? ");
//            reponse = scanner.nextLine();
//            if (reponse.equals("yes")) {
//                System.out.println("Please enter the id of the member registrating to this activity  : ");
//                int id = scanner.nextInt();
//                scanner.nextLine();
//                Member member = memberService.findById(id);
//                activity.addMember(member);
//            }
//        } while (!reponse.equals("non"));
//    }

    // 3 - CRUD Teacher
    private void TeacherMenu() {

        do {
            menuTeacher();
            teacherChoice = scanner.nextLine();
            switch (teacherChoice) {
                case "1" -> addTeacher();
                case "2" -> updateTeacher();
                case "3" -> deleteTeacher();
                case "4" -> getOneTeacherById();
                case "5" -> getAllTeachers();
                case "6" -> System.out.println("Go Back -->");
                default -> System.out.println("Invalid choice");
            }
        } while (!activityChoice.equals("6"));
    }

    //  Sous Menu Teacher
    private void menuTeacher() {

        System.out.println("#######################################");
        System.out.println(" ECF(Back)--SportCenter--Teacher's Menu ");
        System.out.println("#######################################");
        System.out.println("*************************");
        System.out.println("Choose an option please :");
        System.out.println("*************************");
        System.out.println("1 - Add a new teacher");
        System.out.println("2 - Update an teacher");
        System.out.println("3 - Delete an teacher");
        System.out.println("4 - Display one teacher");
        System.out.println("5 - Display all teachers");
        System.out.println("6 - go back -->");
        System.out.println("*************************");
    }

    private void addTeacher(){

        System.out.println("Please enter your lastname : ");
        String lastname = scanner.nextLine();
        System.out.println("Please enter your firstname : ");
        String firstname = scanner.nextLine();
        System.out.println("Please enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        try {
            teacherService.create(new Teacher(lastname,firstname,age));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void updateTeacher(){

        System.out.println("Please enter the id of the teacher you want to update : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher t = teacherService.findById(id);
        System.out.println("Please enter the teacher's lastname : ");
        String lastname = scanner.nextLine();
        t.setLastName(lastname);
        System.out.println("Please enter the teacher's firstname : ");
        String firstname = scanner.nextLine();
        t.setFirstName(firstname);
        System.out.println("Please enter your age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        t.setAge(age);
        try {
            teacherService.update(t);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteTeacher(){

        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher t = teacherService.findById(id);
        teacherService.delete(t);
    }

    private void getOneTeacherById(){

        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher t = teacherService.findById(id);
        System.out.println(t);
    }

    private void getAllTeachers(){

        List<Teacher> teachers = teacherService.findAll();
        for (Teacher t: teachers) {
            System.out.println(t);
        }
    }
}
