package com.company.CollegeShop.Services;

import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Transaksi;

public class TransaksiService {

    private static String _filePath = "csv/T_TRANS.csv";

    public static void insertTransaksi(Transaksi transaksi) {
        DataHelper.appendCsv(transaksi, _filePath);
    }
}
