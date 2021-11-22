package com.company.CollegeShop.Models;

import com.opencsv.bean.CsvBindByName;

public class Barang {

    public static String header = "ID_barang,nama_barang,harga_barang";

    @CsvBindByName(column = "ID_barang")
    public String id;

    @CsvBindByName(column = "nama_barang")
    public String nama;

    @CsvBindByName(column = "harga_barang")
    public Integer harga;

    public Barang() {

    }

    @Override
    public String toString() {
        return id + "," + nama + "," + harga;
    }
}
