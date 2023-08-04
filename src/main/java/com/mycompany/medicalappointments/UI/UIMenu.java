/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicalappointments.UI;

import com.mycompany.medicalappointments.model.Doctor;
import com.mycompany.medicalappointments.model.Patient;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author skitt
 */
public class UIMenu {
    public static final String[] MONTHS = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    //declaro una variable mucho mas global q sera de tipo estatica y me manejara el doctor que este logeado
    public static Doctor doctorlogged;
    public static Patient patientlogged;
    
    public static void showMenu(){
        System.out.println("Welcome to My Appointments");
        System.out.println("Selecciona la opción deseada");

        int response = 0;
        do {
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("3. Nurse");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    System.out.println("Doctor");
                    //ponemos la variable response en 0 para que ya se salga del menu
                    //y se complete el ciclo de vida de este metodo
                    response = 0;
                    authUser(1);
                    break;
                case 2:
                    response = 0;
                    authUser(2);

                    break;
                case 0:
                    System.out.println("Thank you for you visit");
                    break;
                default:
                    System.out.println("Please select a correct answer");
            }
        }while (response != 0);
    }

    private static void authUser(int userType) {
        //userType = 1 Doctor
        //userType = 2 Patient

        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Paula Sanchez", "Doctora", "lunadangelis123@gmail.com"));
        doctors.add(new Doctor("Juan Caicedo", "Pediatra", "juansebasalazar019@gmail.com"));
        doctors.add(new Doctor("Josepth Nava", "Neurologo", "josnava57@gmail.com"));

        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Andres Lopez", "andresl@gmail.com"));
        patients.add(new Patient("Felipe Salinas", "fsalinas@gmail.com"));
        patients.add(new Patient("Amairany Castro", "amac@gmail.com"));

        //variable booleana que por defecto estara en falso
        //al momento de verificacion se cambiara automaticamente a true
        boolean emailCorrect = false;
        do {
            System.out.println("Insert Your Email: [@gmail.com]");
            Scanner sc  = new Scanner(System.in);
            String email = sc.nextLine();
            //Estas validaciones extra de una base de datos estas validaciones pueden estar en el query
            //esta funcion me buscara en todos los doctores hasta encontrar el correo q recibi
            //haga match con algunos de los correos que estan definidos arriba
            if (userType == 1){
                for (Doctor doc: doctors) {
                    //metodo equals pertenece a la clase object lo cual compara con el email q recibimos
                    if(doc.getEmail().equals(email)){
                        emailCorrect = true;
                        //Obtener el usuario logeado
                        doctorlogged = doc;
                        //showDoctorMenu me mostrara el menu del doctor
                        UIDoctorMenu.showDoctorMenu();
                    }
                }
            }
            //esto no es persistencia pero lo que vemos es como manejar estas validaciones desde el codigo
            //si estuvieramos viendo persistencia todos estos filtros los pondriamos como parte del query
            if (userType == 2){
                for (Patient pat: patients) {
                    if (pat.getEmail().equals(email)){
                        emailCorrect = true;
                        patientlogged = pat;
                        UIPatientMenu.showPatientMenu();
                    }
                }
            }
        } while(!emailCorrect);
    }

    static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Model.Patient");
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointments");
            System.out.println("0. Return");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    System.out.println("::Book an appointment");
                    for (int i = 1; i < 4; i++) {
                        System.out.println(i +". " + MONTHS[i]);
                    }
                    break;
                case 2:
                    System.out.println("::My appointments");
                    break;
                case 0:
                    showMenu();
                    break;
            }
        }while (response != 0);
    }
}
