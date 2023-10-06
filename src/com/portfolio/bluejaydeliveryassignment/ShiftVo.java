/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.bluejaydeliveryassignment;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author nitro
 */
public class ShiftVo {
    private LocalTime TimeIn;
    private LocalTime TimeOut;
    private LocalTime TimecardHours;

    public LocalTime getTimeIn() {
        return TimeIn;
    }

    public void setTimeIn(LocalTime TimeIn) {
        this.TimeIn = TimeIn;
    }

    public LocalTime getTimeOut() {
        return TimeOut;
    }

    public void setTimeOut(LocalTime TimeOut) {
        this.TimeOut = TimeOut;
    }

    public LocalTime getTimecardHours() {
        return TimecardHours;
    }

    public void setTimecardHours(LocalTime TimecardHours) {
        this.TimecardHours = TimecardHours;
    }
    
    
    
}
