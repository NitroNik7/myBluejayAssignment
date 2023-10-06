/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.bluejaydeliveryassignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author nitro
 */
public class EmployeeVo {
    String positionId;
    String positionStatus;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }
    private String Fname;
    private String Lname;

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }
    private Date PayCycleStart;
    private Date PayCycleEnd;
    
    private HashMap<Date, ArrayList<ShiftVo>> DateShift = new HashMap<>();
//    ArrayList<ShiftVo> shifts = new ArrayList<>();

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public Date getPayCycleStart() {
        return PayCycleStart;
    }

    public void setPayCycleStart(Date PayCycleStart) {
        this.PayCycleStart = PayCycleStart;
    }

    public Date getPayCycleEnd() {
        return PayCycleEnd;
    }

    public void setPayCycleEnd(Date PayCycleEnd) {
        this.PayCycleEnd = PayCycleEnd;
    }

    public HashMap<Date, ArrayList<ShiftVo>> getDateShift() {
        return DateShift;
    }

    public void setDateShift(HashMap<Date, ArrayList<ShiftVo>> dateShifts) {
        this.DateShift.clear();
//        this.DateShift = dateShifts;
        this.DateShift.putAll(dateShifts);
    }
        
    
}
