package com.company.CollegeShop.Services;

import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Struk;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StrukService {

    private static String _filePath = "csv/T_STRUK.csv";

    public static void insertStruk(Struk struk) {
        DataHelper.appendCsv(struk, _filePath);
    }

    public static List<Struk> getStrukByTanggal(Date tanggalAwal, Date tanggalAkhir) {

        // get list struk
        List<Struk> listStruk = DataHelper.readCSV(Struk.class, _filePath);

        if (tanggalAwal != null) {
            listStruk = listStruk.stream().filter(p
                    -> p.tanggalPembuatanStruk.equals(tanggalAwal)
                    || p.tanggalPembuatanStruk.after(tanggalAwal)).collect(Collectors.toList());
        }

        if (tanggalAkhir != null) {
            listStruk = listStruk.stream().filter(p
                    -> p.tanggalPembuatanStruk.equals(tanggalAkhir)
                    || p.tanggalPembuatanStruk.before(tanggalAkhir)).collect(Collectors.toList());
        }

        return listStruk;
    }
}
