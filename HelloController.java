package org.example.homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    //HOME PAGE
        // Top Tab Button
            public void homeTab(ActionEvent e) {
                System.out.println("Home Tab");
            }
            public void foodTab(ActionEvent e) {
                System.out.println("food Tab");
            }
            // Dorm Tab Menus
            public void dormInfo(ActionEvent e) {
                System.out.println("Dormitories > dorm info");
            }
            public void dormTransfer(ActionEvent e) {
                System.out.println("Dormitories > dorm transfer");
            }
            public void dormTab(ActionEvent e) {
                System.out.println("Home Tab");
            }
            public void socialTab(ActionEvent e) {
                System.out.println("social Tab");
            }
            public void forumTab(ActionEvent e) {
                System.out.println("forum tab");
            }
            // Profile Tab Menus
            public void profile(ActionEvent e) {
                System.out.println("Profile > Profile");
            }
            public void liked(ActionEvent e) {
                System.out.println("Profile > liked");
            }
            public void contactUs(ActionEvent e) {
                System.out.println("Profile > contact us");
            }
            public void logout(ActionEvent e) {
                System.out.println("Profile > logout");
            }

}