/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.tools;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author cy-proyx1
 */
public class XTime {
    protected int anyo=2000;
    protected int mes = 1;
    protected int dia = 1;
    protected int hora = 1;
    protected int min = 1;
    protected int sec = 1;
    public static XTime time=new XTime();
    public static String [] MESES = {
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Sebptiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    };
    public static String [] MESES_ACR = {
        "Ene",
        "Feb",
        "Mar",
        "Abr",
        "May",
        "Jun",
        "Jul",
        "Ago",
        "Seb",
        "Oct",
        "Nov",
        "Dic"
    };

    public XTime(){
        getInstante();
    }

    /**
     * @return the anyo
     */
    public int getAnyo() {
        return anyo;
    }

    /**
     * @param anyo the anyo to set
     */
    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes+1;
    }
    /**
     * Retorna el nombre del mes completo
     * @return
     */
    public String getMesName() {
        return MESES[mes];
    }
    /**
     * Retorna el acronimo del nombre del mes
     * @return
     */
    public String getMesNameAcronimus() {
        return MESES_ACR[mes];
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        mes--;
        if(mes<0)
            mes=0;
        this.mes = mes%12;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        if(dia<1)
            dia=1;
        this.dia = dia%31;
    }

    /**
     * @return the hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(int hora) {
        this.hora = hora%24;
    }

    /**
     * @return the min
     */
    public int getMinutos() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMinutos(int min) {
        this.min = min%60;
    }

    /**
     * @return the sec
     */
    public int getSegundos() {
        return sec;
    }

    /**
     * @param sec the sec to set
     */
    public void setSegundos(int sec) {
        this.sec = sec%60;
    }
    
    public void getInstante(){
        Calendar c= Calendar.getInstance();
        anyo=c.get(GregorianCalendar.YEAR);
        mes=c.get(GregorianCalendar.MONTH);
        dia=c.get(GregorianCalendar.DAY_OF_MONTH);

        hora=c.get(GregorianCalendar.HOUR_OF_DAY);
        min=c.get(GregorianCalendar.MINUTE);
        sec=c.get(GregorianCalendar.SECOND);
    }
    protected String getAnyoXFormat(){
        if(getAnyo()<10)
            return "000"+getAnyo();
        if(getAnyo()<100)
            return "00"+getAnyo();
        if(getAnyo()<1000)
            return "0"+getAnyo();
        return ""+getAnyo();
    }
    protected String getMesXFormat(){
        if(getMes()<10)
            return "0"+getMes();
        return ""+getMes();
    }
    protected String getDiaXFormat(){
        if(dia<10)
            return "0"+dia;
        return ""+dia;
    }
    protected String getHoraXFormat(){
        if(hora<10)
            return "0"+hora;
        return ""+hora;
    }
    protected String getMinutosXFormat(){
        if(min<10)
            return "0"+min;
        return ""+min;
    }
    protected String getSegundosXFormat(){
        if(sec<10)
            return "0"+sec;
        return ""+sec;
    }

    /**
     * Esta funcion te devielve en un formato dd/mm/yy todo numerico;
     * @return
     */
    public String getFechaNumerical(){
        return getDiaXFormat()+"/"+getMesXFormat()+"/"+getAnyoXFormat();

    }
    /**
     * Esta funcion te devielve en un formato hh:mm:ss todo numerico;
     * @return
     */
    public String getHoraNumerical(){
        return getHoraXFormat()+":"+getMinutosXFormat()+":"+getSegundosXFormat();

    }
    /**
     * Esta funcion te devielve en un formato dd/mm/yy hh:mm:ss todo numerico;
     * @return
     */
    public String getInstantNumerical(){
        return getFechaNumerical()+" "+getHoraNumerical();

    }
    @Override
    public String toString(){
        return getFechaNumerical()+" "+getHoraNumerical();
    }
    /**
     *
     * @return
     */
    public String getTimeStampDay(){
        return getAnyoXFormat()+getMesXFormat()+getDiaXFormat();
    }
    public String getTimeStampHour(){
        return getHoraXFormat()+getMinutosXFormat()+getSegundosXFormat();
    }
    /**
     * Return the complete timestamp day+hour
     * @return 
     */
    public String getTimeStamp(){
        return getTimeStampDay()+getTimeStampHour();
    }
    public void parseDia(String timeStamp){
        timeStamp=StringManager.completeStr(timeStamp,"00000000");
        setAnyo(Integer.parseInt(timeStamp.substring(0,4)));
        setMes(Integer.parseInt(timeStamp.substring(4,6)));
        setDia(Integer.parseInt(timeStamp.substring(6,8)));

    }
    public void parseHora(String timeStamp){
        timeStamp=StringManager.completeStr(timeStamp,"000000");
        setHora(Integer.parseInt(timeStamp.substring(0,2)));
        setMinutos(Integer.parseInt(timeStamp.substring(2,4)));
        setSegundos(Integer.parseInt(timeStamp.substring(4,6)));
    }
    public void parse(String timeStamp){
        timeStamp=timeStamp+"               ";
        setAnyo(Integer.parseInt(timeStamp.substring(0,4)));
        setMes(Integer.parseInt(timeStamp.substring(4,6)));
        setDia(Integer.parseInt(timeStamp.substring(6,8)));
        setHora(Integer.parseInt(timeStamp.substring(8,10)));
        setMinutos(Integer.parseInt(timeStamp.substring(10,12)));
        setSegundos(Integer.parseInt(timeStamp.substring(12,14)));
    }
    public void clear(){
        setAnyo(0);
        setMes(0);
        setDia(0);
        setHora(0);
        setMinutos(0);
        setSegundos(0);
    }
    /**
     * Retorna un XTime del momento actual
     * @return
     */
    public static XTime getNow(){
        return new XTime();
    }
    /**
     * Esta funcion se encarga de construir un Xtime en base a una cadena de tipo
     * dd/mm/yyyy
     */
    public void parseFormatDia(String date){
        if(date == null)
            return;
        String [] v = date.split("/");
        parseDia(v[2]+v[1]+v[0]);
    }
    /**
     * Esta funcion se encarga de construir un Xtime en base a una cadena de tipo
     * hh:mm:ss
     */
    public void parseFormatHora(String date){
        if(date == null)
            return;
        String [] v = date.split(":");
        parseHora(v[0]+v[1]+v[2]);
    }
    /**
     * Esta funcion se encarga de construir un Xtime en base a una cadena de tipo
     * dd/mm/yyyy hh:mm:ss
     */
    public void parseFormat(String date){
        if(date == null)
            return;
        date=date.trim();
        String [] v = date.split(" ");
        parseFormatDia(v[0]);
        parseFormatHora(v[1]);
    }
    /**
     * Transform nanoseconds to seconds.
     */
     public static double nanoSecondsToSeconds(long nanoSeconds){
         return (nanoSeconds)/1000000.0;
     }
    /**
     * Transform miliseconds to seconds.
     */
     public static double miliSecondsToSeconds(long miliSeconds){
         return (miliSeconds)/1000.0;
     }
    /**
     * Transform nanoseconds to seconds.
     */
     public static double nanoSecondsToSeconds(float nanoSeconds){
         return (nanoSeconds)/1000000.0;
     }
    /**
     * Transform miliseconds to seconds.
     */
     public static double miliSecondsToSeconds(float miliSeconds){
         return (miliSeconds)/1000.0;
     }
    /**
     * Transform nanoseconds to seconds.
     */
     public static double nanoSecondsToSeconds(int nanoSeconds){
         return (nanoSeconds)/1000000.0;
     }
    /**
     * Transform miliseconds to seconds.
     */
     public static double miliSecondsToSeconds(int miliSeconds){
         return (miliSeconds)/1000.0;
     }
    /**
     * Transform nanoseconds to seconds.
     */
     public static double nanoSecondsToSeconds(double nanoSeconds){
         return (nanoSeconds)/1000000.0;
     }
    /**
     * Transform miliseconds to seconds.
     */
     public static double miliSecondsToSeconds(double miliSeconds){
         return (miliSeconds)/1000.0;
     }
}
