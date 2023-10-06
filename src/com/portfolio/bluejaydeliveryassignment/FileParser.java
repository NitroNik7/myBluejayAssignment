/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.bluejaydeliveryassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nitro
 */
// Class to read the input file
public class FileParser {

    public TreeMap readFile() {

        BufferedReader br = null;
        /* TreeMap to store Map of FileNumber to EmployeeVo
            EmployeeVo stores all fields from input file for an Employee 
                - Employee Name
                - Pay Cycle Start Date
                - Pay Cycle End Date
                - HashMap to store mapping of Date to multiple Shifts (because an employee can work for multiple shifts in one day)
        
         */
        TreeMap<Integer, EmployeeVo> FileEmpDetails = new TreeMap<>();

        // ArrayList to store all employee shifts in a day
        ArrayList<ShiftVo> shifts = new ArrayList<>();

        // HashMap to store mapping of a Date to multiple Shifts (because an employee can work for multiple shifts in one day)
        int prevFileNumber = 0, newFileNumber = 0;
        // Try catch block to handle fileNotFoundException and IOException
        try {
            // Reading input file in form of CSV(Comma seperated values) so that we can split data in every line we read  
            br = new BufferedReader(new FileReader("/media/nitro/DATA (D)/Netbeans Projects/BluejayDeliveryAssignment/AssignmentInputFile/Assignment_Timecard.xlsx - Sheet1.csv"));
            // Reading 1st line
            String line = br.readLine();
            Date prevDate = null;
            Date currDate = null;

            // Splitting each line read into a String array
            String values[] = line.split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            HashMap<Date, ArrayList<ShiftVo>> dateShifts = new HashMap<>();
            int cnt = 1;
            // When loop starts, we read directly from 2nd line onwards skipping 1st line which contains column headers only
            while ((line = br.readLine()) != null) {
                String prevValues[] = values;
                if(cnt == 1){
                    cnt++;
                    prevValues = line.split(",");
                }
                values = line.split(",");
                newFileNumber = Integer.parseInt(values[9]);
                // Find out curr Date
                String dateTimeIn[] = values[2].split(" ");

                // Find out curr Date
                currDate = dateTimeIn[0].equals("") ? null : sdf.parse(dateTimeIn[0]);

                EmployeeVo emp = new EmployeeVo();
                emp.setPositionId(prevValues[0]);
                emp.setPositionStatus(prevValues[1]);

                emp.setPayCycleStart(prevValues[5].equals("")  ? null : sdf.parse(prevValues[5]));
                emp.setPayCycleEnd(prevValues[6].equals("") ? null : sdf.parse(prevValues[6]));
                
                emp.setFname(prevValues[7].replace("\"", ""));
                emp.setLname(prevValues[8].replace("\"", ""));

                if ((prevDate == null && currDate != null) || (prevDate != null && prevDate.equals(currDate) == false)) {
                    dateShifts.put(prevDate, shifts);
                    // NOTE: shifts.clear() - not using this statement because it clears values of dateShifts as well
                    shifts = new ArrayList<>();
                    if (prevFileNumber != newFileNumber) {

                        emp.setDateShift(dateShifts);
                        FileEmpDetails.put(prevFileNumber, emp);
                        dateShifts.clear();
                        prevFileNumber = newFileNumber;

                    }
//                    currEmp.clear();
                }

                

                ShiftVo singleShift = new ShiftVo();

                // Parsing time and converting it to 24-hour clock time
                singleShift.setTimeIn(dateTimeIn[0].equals("") ? null : (dateTimeIn[2].equals("PM") ? LocalTime.parse(dateTimeIn[1]).plusHours(12) : LocalTime.parse(dateTimeIn[1])));
                String dateTimeOut[] = values[3].split(" ");
                singleShift.setTimeOut(dateTimeOut[0].equals("") ? null : (dateTimeOut[2].equals("PM") ? LocalTime.parse(dateTimeOut[1]).plusHours(12) : LocalTime.parse(dateTimeOut[1])));

                // java.time.LocalTime.parse() cannot parse strings such as 3:15, so modifying them to 03:15; 
                if (values[4].equals("") == false) {
                    if (values[4].length() == 4) {
                        values[4] = "0" + values[4];
                    }
                    singleShift.setTimecardHours(LocalTime.parse(values[4]));
                }

                shifts.add(singleShift);

                prevDate = currDate;
                prevFileNumber = newFileNumber;
//                prevFileNumber = newFileNumber;
            }

        } catch (Exception ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FileEmpDetails;
    }
}
