package com.company.CollegeShop.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Struk {
    public String id;
    public Date tanggalPembuatanStruk;
    public Integer totalPembelian;
    public Integer totalpembayaran;
    public Integer kembalian;

    public List<Transaksi> transaksiList;

    public Struk() {
        Date today = new Date();

        id = "STRUK-" + today.getTime();
        tanggalPembuatanStruk = today;
        totalPembelian = 0;
        totalpembayaran = 0;
        kembalian = 0;
        transaksiList = new LinkedList<>();
    }

    public void insertTransaksi(Barang barang, Integer jumlahBarang) {
        Transaksi transaksi = new Transaksi();

        transaksi.idStruk = id;
        transaksi.idBarang = barang.id;
        transaksi.jumlahBarang = jumlahBarang;
        transaksi.subtotal = barang.harga * jumlahBarang;
        transaksi.barang = barang;

        transaksiList.add(transaksi);
    }

    public void calculateStruk() {
        totalPembelian = 0;
        for (Transaksi transaksi:transaksiList) {
            totalPembelian += transaksi.subtotal;
        }
    }

    public void payStruk(Integer nominal) {
        totalpembayaran = nominal;
        kembalian = nominal - totalPembelian;
    }

    public void printStruk(){
        String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        System.out.println("==================================================");
        System.out.println("ID struk: " + id);
        System.out.println("Tanggal pembuatan struk: " + simpleDateFormat.format(tanggalPembuatanStruk));
        for (Transaksi transaksi: transaksiList) {
            System.out.println("--------------------------------------------------");
            System.out.println("ID barang: " + transaksi.barang.id);
            System.out.println("Nama barang: " + transaksi.barang.nama);
            System.out.println("Jumlah barang: " + transaksi.jumlahBarang);
            System.out.println("Harga Satuan: " + transaksi.barang.harga);
            System.out.println("Subtotal: " + transaksi.subtotal);
        }
        if (totalPembelian > 0)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("Total pembelian: " + totalPembelian);
        }
        if (totalpembayaran > 0)
        {
            System.out.println("Total pembayaran: " + totalpembayaran);
            System.out.println("Kembalian: " + kembalian);
        }
        System.out.println("==================================================");
    }
}
