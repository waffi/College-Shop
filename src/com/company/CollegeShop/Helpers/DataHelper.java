package com.company.CollegeShop.Helpers;

import com.opencsv.bean.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class DataHelper{

    public static <T> List<T> readCSV(Class<T> type, String filePath){

        List listObject = new LinkedList<>();

        try {
            listObject = new CsvToBeanBuilder(new FileReader(filePath))
                    .withType(type)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listObject;
    }

    public static <T> void writeCsv(String headers, List<T> listObject, String filePath) {

        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.println(headers);
            for (Object object : listObject) {
                writer.println(object.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> void appendCsv(T object, String filePath) {

        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(filePath,true));
            writer.println(object.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}