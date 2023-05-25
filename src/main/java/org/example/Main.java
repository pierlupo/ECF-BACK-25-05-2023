package org.example;

import org.example.controller.Ihm;
import org.example.service.ActivityService;
import org.example.service.MemberService;

public class Main {
    public static void main(String[] args) {
        MemberService ms = new MemberService();
        ActivityService as = new ActivityService();

        new Ihm().start();
    }
}