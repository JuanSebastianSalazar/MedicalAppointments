/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicalappointments.UI;

import com.mycompany.medicalappointments.UI.UIMenu;
import com.mycompany.medicalappointments.model.Doctor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author skitt
 */
public class UIDoctorMenu {
    public static ArrayList<Doctor> doctorsAvailableAppointments = new ArrayList<>();
    //Se encargara de mostrar la logica de opciones para nuestro doctor
    public static void showDoctorMenu(){
        int response = 0;
        //el do while es recomendable usarlo para hacer menus
        do {
            System.out.println("\n\n");
            System.out.println("Doctor");
            System.out.println("Welcome " + UIMenu.doctorlogged.getName());
            System.out.println("1. Add Available Appointment");
            System.out.println("2. My Scheduled appointments");
            System.out.println("3. Logout");

            //entrada de datos
            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showAddAvailableAppointmentMenu();
                    break;
                case 2:
                    break;
                case 0:
                    //aca lo deslogea y le pregunta de nuevo si es un paciente o doctor
                    UIMenu.showMenu();
                    break;
            }

        } while(response != 0);
    }

    private static void showAddAvailableAppointmentMenu(){
        int response = 0;
        do {
            System.out.println();
            System.out.println("::Add Available Appointment ");
            System.out.println(":: Select a Month");

            for (int i = 0; i < 3; i++) {
                int j = i + 1;
                System.out.println(j + ". " + UIMenu.MONTHS[i]);
            }

            System.out.println("0. Return");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            if ( response > 0 && response < 4){
                //1,2,3
                int monthSelected = response;
                System.out.println(monthSelected + " . " + UIMenu.MONTHS[monthSelected - 1]);

                System.out.println("Insert the date available : [dd/mm/yyyy]");
                //reutilizamos la clase scanner ya que su nivel de scope nos lo permite
                String date = sc.nextLine();

                System.out.println("Your date is: " + date + "\n1. Correct \n2. Change Date");
                int responseDate = Integer.valueOf(sc.nextLine());
                //continue evita la siguiente linea y no se sale del ciclo por lo cual lo devuelve
                // a que inserte su date de nuevo
                if(responseDate == 2) continue;

                //definicion para hacer la hora
                int responseTime = 0;
                //variable para capturar la hora
                String time = "";
                do {
                    System.out.println("Insert the time available for date:" + date + " [00:00]");
                    time = sc.nextLine();
                    System.out.println("Your time is: " + time + "\n1. Correct \n2. Change Time");
                    responseTime = Integer.valueOf(sc.nextLine());

                }while (responseTime == 2); //Si es = 2 nos deberia mostrar ese fragmento de codigo nada mas

                UIMenu.doctorlogged.addAvailableAppointment(date,time);
                checkDoctorAvailableAppointments(UIMenu.doctorlogged);

            }else if (response == 0){
                showDoctorMenu();
            }
        }while (response !=0);
    }

    //lo que hace es que si su size es mayor a 0 y ademas si ese doctor no existe dentro de la lista
    //si se cumplen esos dos casos voy a estar añadiendo al doctor q me esten pasando
    //ademas si constains es falso, se estara añadiendo al doctor q se entregue
    private static void checkDoctorAvailableAppointments(Doctor doctor){
        if(doctor.getDoctorsavailableAppointments().size() > 0
            && !doctorsAvailableAppointments.contains(doctor)){
            doctorsAvailableAppointments.add(doctor);
        }
    }

}
