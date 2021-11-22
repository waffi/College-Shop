package com.company.CollegeShop.Models;

import com.opencsv.bean.CsvBindByName;

public class Transaksi {

    public static String header = "ID_barang,ID_struk,jumlah_barang,subtotal";

    @CsvBindByName(column = "ID_barang")
    public String idStruk;

    @CsvBindByName(column = "ID_struk")
    public String idBarang;

    @CsvBindByName(column = "jumlah_barang")
    public Integer jumlahBarang;

    @CsvBindByName(column = "subtotal")
    public Integer subtotal;

    public Barang barang;

    public Transaksi() {

    }

    @Override
    public String toString() {
        return idStruk + "," + idBarang + "," + jumlahBarang + "," + subtotal;
    }
}
