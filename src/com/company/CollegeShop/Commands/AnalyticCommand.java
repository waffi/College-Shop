package com.company.CollegeShop.Commands;

import com.company.CollegeShop.Helpers.CommandHelper;
import com.company.CollegeShop.Models.Struk;
import com.company.CollegeShop.Models.Transaksi;
import com.company.CollegeShop.Services.StrukService;

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

            List<Struk> listStruk = StrukService.getStrukByTanggal(tanggalAwal, tanggalAkhir);

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

        // parse args
        String[] commandArgs = _command.getCommandArgs();
        String tanggalAwal = commandArgs.length > 0 ? commandArgs[0] : null;
        String tanggalAkhir = commandArgs.length > 1 ? commandArgs[1] : null;
    }
}
