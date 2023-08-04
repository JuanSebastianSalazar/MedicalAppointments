/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicalappointments.UI;

import com.mycompany.medicalappointments.model.Doctor;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author skitt
 */
public class UIPatientMenu {
    public static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome");
            System.out.println("Welcome " + UIMenu.patientlogged.getName());
            System.out.println("1. Book an appointment");
            System.out.println("2. My Appointment");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientMyAppointments();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }
        }while (response != 0);
    }
    //este metodo solo debe ser accecible desde aqui
    private static void showBookAppointmentMenu(){
        int response = 0;
        do {
            System.out.println(":: Book an appointment");
            System.out.println(":: Select date: ");
            //una coleccion de objetos por lo que un tipodato primitivo no funcionara
            //numeracion de la lista de fechas
            //El integer lleva el indice de la fecha que seleccione nuestro paciente
            //[doctors]
            //El tercer elemento doctor sera la row completa
            // 1.-doctor1
            // - 1 fecha1
            //- 2 fecha2
            // 2.-doctor2
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            //variable que su scope sea mucho mas alto que los demas ya que tengo for anidados
            int k = 0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvailableAppointments.size(); i++) {
                //Recorremos si el doctor tiene listas disponibles y cuando encuentre uno
                //le pido las fechas o citas que tiene el doctor y las asigno en el objeto de fechas disponibles
                ArrayList<Doctor.AvailableAppointment> availableAppointments
                        = UIDoctorMenu.doctorsAvailableAppointments.get(i).getDoctorsavailableAppointments();
                //El treemap nos da una estructura de arbol
                Map<Integer, Doctor> doctorAppointments = new TreeMap<>();

                for (int j = 0; j < availableAppointments.size(); j++) {
                    //la incrementamos aca ya que aca es donde saltare las listas de las fechas
                    k++;
                    System.out.println(k + ". " + availableAppointments.get(j).getDate());
                    //lo usamos para que capture el indice de la fecha y por otro lado el objeto doctor de la fecha seleccionada
                    //Estructura de datos que nos va a ayudar a manipular mejor la salida
                    doctorAppointments.put(Integer.valueOf(j), UIDoctorMenu.doctorsAvailableAppointments.get(i));
                    doctors.put(Integer.valueOf(k), doctorAppointments);
                }
            }

            Scanner sc = new Scanner(System.in);
            //es cuando ya obtenga la fecha seleccionada sirve para yo saber que doctor le pertenece
            //esa fecha que el detecto
            int responseDateSelected = Integer.valueOf(sc.nextLine());

            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("", "", "");

            //aca podemos tener acceso para poder recorred el map
            for (Map.Entry<Integer, Doctor> doc : doctorAvailableSelected.entrySet()) {
                //con getkey obtengo el key y con doctor obtengo el value
                indexDate = doc.getKey();
                doctorSelected = doc.getValue();
            }
            //obtengo el nombre, el tiempo y fecha
            System.out.println(doctorSelected.getName() + "Date: " + doctorSelected.getDoctorsavailableAppointments().get(indexDate).getDate()
                    + ". Time: " + doctorSelected.getDoctorsavailableAppointments().get(indexDate).getTime());

            System.out.println("Confirm your appointment: \n1. YES  \n2. Change Data");
            response = Integer.valueOf(sc.nextLine());

            //pasamos para nullo el date porque nos devuelve un objeto de tipo date que es el que se necesita
            //para el schedule de la cita
            if (response == 1){
                UIMenu.patientlogged.addAppointmentDoctors(
                        doctorSelected,
                        doctorSelected.getDoctorsavailableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getDoctorsavailableAppointments().get(indexDate).getTime());

                showPatientMenu();
            }



        }while (response != 0);
    }

    private static void showPatientMyAppointments(){
        int response = 0;
        do {
            System.out.println("::My Appointments");

            //si el paciente logeado tiene citas disponibles
            if (UIMenu.patientlogged.getAppointmentDoctors().size() == 0){
                System.out.println("Don't have appointments");
                break;
            }

            //Si tiene me muestre las citas que tiene el patient
            for (int i = 0; i < UIMenu.patientlogged.getAppointmentDoctors().size(); i++) {
                //Auxiliar para mostrar la numeracion de la cantidad de citas que tiene
                int j = i + 1;
                //Muestra fecha y hora y nombre del doctor
                System.out.println(j + ". " + "Date: " + UIMenu.patientlogged.getAppointmentDoctors().get(i).getDate() +
                        "Time: " + UIMenu.patientlogged.getAppointmentDoctors().get(i).getTime() +
                        "\nDoctor: " + UIMenu.patientlogged.getAppointmentDoctors().get(i).getDoctor().getName());
            }

            System.out.println("0. Return");
        }while (response != 0);
    }
}
