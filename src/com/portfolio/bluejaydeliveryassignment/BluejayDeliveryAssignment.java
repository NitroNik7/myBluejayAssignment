/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.portfolio.bluejaydeliveryassignment;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author nitro
 */
// This class will be used to call all required functions
public class BluejayDeliveryAssignment {

    public static void main(String[] args) {
        System.out.println("Bluejay Delivery Assignment\n");
        FileParser parser = new FileParser();
        TreeMap<Integer, EmployeeVo> fileEmpDetails = parser.readFile();

        int days = 7;
        System.out.println("Employees who have worked for atleast 7 consecutive days are :");
        System.out.println("PositionID,\tPosition Status,\tName ");
        consecutiveDays(days, fileEmpDetails);
        
        System.out.println("\n");

        System.out.println("Employees who have less than 10 hours of time between shifts but greater than 1 hour :");
        System.out.println("PositionID,\tPosition Status,\tName ");
        betweenShifts(fileEmpDetails);
        
        System.out.println("\n");

        System.out.println("Employee who Who has worked for more than 14 hours in a single shift :");
        System.out.println("PositionID,\tPosition Status,\tName ");
        moreThanFourteenHourShift(fileEmpDetails);
    }

    public static void displayAll(TreeMap<Integer, EmployeeVo> data) {
        for (int i : data.keySet()) {
            for (Date d : data.get(i).getDateShift().keySet()) {
                if (d == null) {
                    System.out.println("Null");
                }
                for (ShiftVo vo : data.get(i).getDateShift().get(d)) {
                    System.out.println(data.get(i).positionId + "\t" + data.get(i).positionStatus + "\t" + data.get(i).getFname() + "\t" + data.get(i).getLname() + "\t" + d + "\t" + vo.getTimeIn() + "\t" + vo.getTimeOut());
                }
            }
        }
    }

    public static void consecutiveDays(int days, TreeMap<Integer, EmployeeVo> fileEmpDetails) {
//        System.out.println(data.keySet());
        for (int i : fileEmpDetails.keySet()) {
            ArrayList<Date> dates = new ArrayList<>(fileEmpDetails.get(i).getDateShift().keySet());
            Collections.sort(dates);

            // Consecutive Date logic starts here
            int consecutiveCount = 1;
            for (int j = 1; j < dates.size(); j++) {
                long diffInMilliseconds = dates.get(j).getTime() - dates.get(j - 1).getTime();
                long diffInDays = diffInMilliseconds / (24 * 60 * 60 * 1000);

                // Check if the difference between consecutive dates is 1 day
                if (diffInDays == 1) {
                    consecutiveCount++;
                } else {
                    consecutiveCount = 1; // Reset the count if not consecutive
                }

                // If we find 7 consecutive days, return true
                if (consecutiveCount == days) {
                    System.out.println(fileEmpDetails.get(i).positionId + ",\t" + fileEmpDetails.get(i).positionStatus + ",\t\t\t" + fileEmpDetails.get(i).getFname() + ", " + fileEmpDetails.get(i).getLname());
                }
            }

        }
    }

    public static void betweenShifts(TreeMap<Integer, EmployeeVo> fileEmpDetails) {
        for (int i : fileEmpDetails.keySet()) {
            for (Date d : fileEmpDetails.get(i).getDateShift().keySet()) {
                ArrayList<ShiftVo> shifts = fileEmpDetails.get(i).getDateShift().get(d);
                if (shifts.size() > 1);
                for (int j = 1; j < shifts.size(); j++) {
                    ShiftVo currVo = shifts.get(j - 1);
                    ShiftVo nextVo = shifts.get(j);
                    LocalTime currShiftTimeOut = currVo.getTimeOut();
                    LocalTime nextShiftTimeIn = nextVo.getTimeIn();

                    long timeBetweenShifts = Duration.between(currShiftTimeOut, nextShiftTimeIn).toMinutes();
                    // Compare the parameters (in this example, we are using integers)
                    if (timeBetweenShifts > 60 && timeBetweenShifts < 600) {
                        System.out.println(d + " " + fileEmpDetails.get(i).positionId + ",\t" + fileEmpDetails.get(i).positionStatus + ",\t\t\t" + fileEmpDetails.get(i).getFname() + ", " + fileEmpDetails.get(i).getLname());
                    }
                }
            }
        }
    }

    public static void moreThanFourteenHourShift(TreeMap<Integer, EmployeeVo> fileEmpDetails) {
        for (int i : fileEmpDetails.keySet()) {
            for (Date d : fileEmpDetails.get(i).getDateShift().keySet()) {
                for (ShiftVo vo : fileEmpDetails.get(i).getDateShift().get(d)) {
                    if (vo != null) {
                        if (vo.getTimecardHours() != null) {
                            
                            long shiftTime = Duration.between(LocalTime.MIN, vo.getTimecardHours()).toMinutes();
                            if (shiftTime > 840) {
                                System.out.println(fileEmpDetails.get(i).positionId + ",\t" + fileEmpDetails.get(i).positionStatus + ",\t\t\t" + fileEmpDetails.get(i).getFname() + ", " + fileEmpDetails.get(i).getLname());
                            }
                        }
                    }

                }
            }
        }
    }
}
