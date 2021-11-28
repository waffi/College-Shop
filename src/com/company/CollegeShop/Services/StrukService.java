package com.company.CollegeShop.Services;

import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Struk;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

public class StrukService {

    private static String _filePath = "csv/T_STRUK.csv";

    public static void insertStruk(Struk struk) {
        DataHelper.appendCsv(struk, _filePath);
    }

    public static List<Struk> getListStrukByTanggal(Date tanggalAwal, Date tanggalAkhir) {

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

    public static Map<Date, List<Struk>> getPeakByTanggal(Date tanggalAwal, Date tanggalAkhir) {

        // get list struk by  tanggal
        List<Struk> listStruk = StrukService.getListStrukByTanggal(tanggalAwal, tanggalAkhir);

        // group by tanggal
        // {22-November-2021 : [{},{},{},{}]} type per item: Map.Entry<Date, List<Struk>>
        // {23-November-2021 : [{},{}]}
        // {24-November-2021 : [{},{},{}]}
        Map<Date, List<Struk>> listStrukGrouped = listStruk.stream().collect(Collectors.groupingBy(g -> g.tanggalPembuatanStruk));

        // order by size https://stackoverflow.com/questions/30853117/how-can-i-sort-a-map-based-upon-on-the-size-of-its-collection-values
        listStrukGrouped = listStrukGrouped.entrySet().stream()
                .sorted(
                        comparingInt(
                            e -> ((Map.Entry<Date, List<Struk>>)e).getValue().size()
                        ).reversed()
                )
                .limit(10)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        return listStrukGrouped;
    }
}
