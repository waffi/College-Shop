package com.company.CollegeShop.Services;

import com.company.CollegeShop.Models.Barang;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BarangService {
    public static Optional<Barang> getBarangByNama(String nama) {

        // fake list barang
        List<Barang> listBarang = new LinkedList<>();

        Barang barang1 = new Barang();
        barang1.id = "PEPSO";
        barang1.nama = "Pepsodent";
        barang1.harga = 1500;
        listBarang.add(barang1);

        Barang barang2 = new Barang();
        barang2.id = "AQUA";
        barang2.nama = "Aqua Botol 1 Liter";
        barang2.harga = 5000;
        listBarang.add(barang2);

        Barang barang3 = new Barang();
        barang3.id = "MIE_1";
        barang3.nama = "Indomi Goreng";
        barang3.harga = 2000;
        listBarang.add(barang3);

        return listBarang.stream().filter(p -> p.nama.equals(nama)).findFirst();
    }
}
