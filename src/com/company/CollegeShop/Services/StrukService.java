package com.company.CollegeShop.Services;

import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Struk;

public class StrukService {

    private static String _filePath = "csv/T_STRUK.csv";

    public static void insertStruk(Struk struk) {
        DataHelper.appendCsv(struk, _filePath);
    }
}
