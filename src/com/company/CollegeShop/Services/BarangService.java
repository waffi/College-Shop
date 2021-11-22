package com.company.CollegeShop.Services;

import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Barang;

import java.util.List;
import java.util.Optional;

public class BarangService {

    private static String _filePath = "csv/T_BRG.csv";

    public static Optional<Barang> getBarangByNama(String nama) {

        // get list barang
        List<Barang> listBarang = DataHelper.readCSV(Barang.class, _filePath);

        // find first by nama
        Optional<Barang> barang = listBarang.stream().filter(p -> p.nama.equals(nama)).findFirst();

        return barang;
    }
}
