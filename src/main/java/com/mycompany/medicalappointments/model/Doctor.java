/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicalappointments.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author skitt
 */
public class Doctor extends User {

     //Atributos
    String speciality;
    //Se dedica a incrementar la lista y a crear nuevas citas
    private ArrayList<AvailableAppointment> doctorsavailableAppointments = new ArrayList<>();

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Doctor(String name, String speciality, String email){
        super(name,email);
    }


    public void addAvailableAppointment(String date, String time){
        doctorsavailableAppointments.add(new Doctor.AvailableAppointment(date,time));
    }

    //nos devuelve la lista completa de citas
    public ArrayList<AvailableAppointment> getDoctorsavailableAppointments() {
        return doctorsavailableAppointments;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSpeciality: "+speciality+ "\nAvailable: "+ doctorsavailableAppointments.toString();
    }

    @Override
    public void showDataUser() {
        System.out.println("Empleado del Hospital: Cruz Roja");
        System.out.println("Departamento: Cancerologia");
    }

    public static class AvailableAppointment {
        private int id;
        private Date date;
        private String time;
        //esta clase nos ayuda a dar el formato que necesitamos
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        public AvailableAppointment(String date, String time) {
            try {
                //aqui ocurre un error pero para evitar que ese flujo se rompa
                //capturamos la excepcion y pues definimos que hara ese error capturado
                this.date = format.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate(String DATE) {
            return date;
        }

        //me sirve como bandera para saber que me esta devolviendo un date como un string
        public String getDate(){
            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Available Appointments \nDate: "+date+"\nTime: "+time;
        }
    }
}
