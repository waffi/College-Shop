package com.company.CollegeShop.Commands;

import com.company.CollegeShop.Helpers.CommandHelper;
import com.company.CollegeShop.Models.Struk;
import com.company.CollegeShop.Models.Transaksi;
import com.company.CollegeShop.Services.StrukService;
import com.company.CollegeShop.Services.TransaksiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AnalyticCommand {

    private CommandHelper _command;

    public AnalyticCommand(CommandHelper command) {
        _command = command;
    }

    public void DisplayStruk() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

        try {
            // parse args
            String[] commandArgs = _command.getCommandArgs();
            Date tanggalAwal = commandArgs.length > 0 ? simpleDateFormat.parse(commandArgs[0]) : null;
            Date tanggalAkhir = commandArgs.length > 1 ? simpleDateFormat.parse(commandArgs[1]) : null;

            List<Struk> listStruk = StrukService.getListStrukByTanggal(tanggalAwal, tanggalAkhir);

            if (listStruk.size() > 0) {
                String printFormat = "| %-20s | %-20s | %20s | %20s | %20s |%n";
                System.out.format(printFormat, "ID", "Tanggal Struk", "Total Pembelian", "Total Pembayaran", "Kembalian");
                for (Struk struk: listStruk) {
                    System.out.format(printFormat, struk.id, simpleDateFormat.format(struk.tanggalPembuatanStruk), struk.totalPembelian, struk.totalpembayaran, struk.kembalian);
                }
            }
            else {
                System.out.println("Tidak ada struk.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void DisplayPeak() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

        try {
            // parse args
            String[] commandArgs = _command.getCommandArgs();
            Date tanggalAwal = commandArgs.length > 0 ? simpleDateFormat.parse(commandArgs[0]) : null;
            Date tanggalAkhir = commandArgs.length > 1 ? simpleDateFormat.parse(commandArgs[1]) : null;

            // menampilkan top 10 hari-hari terjadinya peak transaksi
            Map<Date, List<Struk>> mapStruk = StrukService.getPeakByTanggal(tanggalAwal, tanggalAkhir);

            if (!mapStruk.isEmpty() ) {
                String printFormat = "| %-20s | %20s |%n";
                System.out.format(printFormat, "Tanggal Struk", "Total Struk");
                for (Map.Entry<Date, List<Struk>> entry : mapStruk.entrySet()) {
                    System.out.format(printFormat, simpleDateFormat.format(entry.getKey()), entry.getValue().size());
                }
            }
            else {
                System.out.println("Tidak ada peak.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void BestProduct() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");

        try {
            // parse args
            String[] commandArgs = _command.getCommandArgs();
            Date tanggalAwal = commandArgs.length > 0 ? simpleDateFormat.parse(commandArgs[0]) : null;
            Date tanggalAkhir = commandArgs.length > 1 ? simpleDateFormat.parse(commandArgs[1]) : null;

            // menampilkan top 5 barang yang paling laris
            Map<String, List<Transaksi>> mapTransaksi = TransaksiService.getBestProductByTanggal(tanggalAwal, tanggalAkhir);

            if (!mapTransaksi.isEmpty() ) {
                String printFormat = "| %-20s | %20s |%n";
                System.out.format(printFormat, "ID Produk", "Total Produk");
                for (Map.Entry<String, List<Transaksi>> entry : mapTransaksi.entrySet()) {
                    System.out.format(printFormat, entry.getKey(), entry.getValue().stream().mapToInt(t -> t.jumlahBarang).sum());
                }
            }
            else {
                System.out.println("Tidak ada best product.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
