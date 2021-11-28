package com.company.CollegeShop.Services;

import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Struk;
import com.company.CollegeShop.Models.Transaksi;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class TransaksiService {

    private static String _filePath = "csv/T_TRANS.csv";

    public static void insertTransaksi(Transaksi transaksi) {
        DataHelper.appendCsv(transaksi, _filePath);
    }

    public static List<Transaksi> getListTransaksi() {

        // get list struk
        List<Transaksi> listTransaksi = DataHelper.readCSV(Transaksi.class, _filePath);

        return listTransaksi;
    }

    public static Map<String, List<Transaksi>> getBestProductByTanggal(Date tanggalAwal, Date tanggalAkhir) {

        // get list struk by  tanggal
        List<Struk> listStruk = StrukService.getListStrukByTanggal(tanggalAwal, tanggalAkhir);
        List<Transaksi> listTransaksi = TransaksiService.getListTransaksi();

        // join transaksi & struk (mengisi object stuck pada tiap transakasi)
        for (Transaksi transaksi: listTransaksi) {
            // find first struk by id
            Optional<Struk> struk = listStruk.stream().filter(p -> p.id.equals(transaksi.idStruk)).findFirst();

            if (struk.isPresent())
                transaksi.struk = struk.get();
        }

        // remove record with empty struk
        listTransaksi = listTransaksi.stream().filter(p -> p.struk != null).collect(Collectors.toList());

        // group by idBarang
        // {PEPSO : [{},{},{},{}]} type per item: Map.Entry<Date, List<Struk>>
        // {AQUA : [{},{}]}
        // {MIE : [{},{},{}]}
        Map<String, List<Transaksi>> listTransaksiGrouped = listTransaksi.stream().collect(Collectors.groupingBy(g -> g.idBarang));

        // order by size https://stackoverflow.com/questions/30853117/how-can-i-sort-a-map-based-upon-on-the-size-of-its-collection-values
        listTransaksiGrouped = listTransaksiGrouped.entrySet().stream()
                .sorted(
                        comparingInt(
                                e -> ((Map.Entry<String, List<Transaksi>>)e).getValue().stream().mapToInt(t -> t.jumlahBarang).sum()
                        ).reversed()
                )
                .limit(5)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        return listTransaksiGrouped;
    }
}
