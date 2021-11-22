package com.company.CollegeShop;

import com.company.CollegeShop.Commands.AnalyticCommand;
import com.company.CollegeShop.Commands.StrukCommand;
import com.company.CollegeShop.Helpers.CommandHelper;
import com.company.CollegeShop.Helpers.DataHelper;
import com.company.CollegeShop.Models.Barang;
import com.company.CollegeShop.Models.Struk;
import com.company.CollegeShop.Models.Transaksi;

import java.util.List;

public class Main {

//        sample command
//        - CREATE_STRUK
//        - INSERT "Pepsodent" 10
//        - INSERT "Aqua Botol 1 Liter" 1
//        - INSERT "Indomi Goreng" 5
//        - CALCULATE_STRUK
//        - PAYMENT 50000
//        - CANCEL_STRUK

    public static void main(String[] args) {

        CommandHelper command = new CommandHelper(System.in);
        StrukCommand strukCommand = new StrukCommand(command);
        AnalyticCommand analyticCommand = new AnalyticCommand(command);

        System.out.println("Enter command: ");

        String message = "";
        Boolean running = true;

        while(running) {

            try {
                command.readCommand();

                switch(command.getCommandName()) {

                    case "CREATE_STRUK":
                        message = strukCommand.CreateStruk()
                                ? "Struk Created!"
                                : "Create Struk Failed!";
                        break;

                    case "INSERT":
                        message = strukCommand.Insert()
                                ? "Transaksi Inserted!"
                                : "Insert Transaksi Failed!";
                        break;

                    case "CALCULATE_STRUK":
                        message = strukCommand.CalculateStruk()
                                ? "Struk Calculated!"
                                : "Calculate Struk Failed!";
                        break;

                    case "PAYMENT":
                        message = strukCommand.Payment()
                                ? "Struk Payed!"
                                : "Pay Struk Failed!";
                        break;

                    case "CANCEL_STRUK":
                        message = strukCommand.CancelStruck()
                                ? "Struk Canceled!"
                                : "Cancel Struk Failed!";
                        break;

                    case "DISPLAY_STRUK":
                        message = analyticCommand.DisplayStruk()
                                ? "Success!"
                                : "Failed!";
                        break;

                    case "DISPLAY_PEAK":
                        message = analyticCommand.DisplayPeak()
                                ? "Success!"
                                : "Failed!";
                        break;

                    case "BEST_PRODUCT":
                        message = analyticCommand.BestProduct()
                                ? "Success!"
                                : "Failed!";
                        break;

                    case "exit":
                        running = false;
                        message = "Application Closed!";
                        break;

                    default:
                        message = "Command not recognized!";
                        break;
                }
            }
            catch (Exception e) {
                message = "Error!";
                e.printStackTrace();
            }

            System.out.println(message);
        }

        command.close();

        List<Barang> listBarang = DataHelper.readCSV(Barang.class, "D:\\Github\\College Shop\\csv\\T_BRG.csv");
        DataHelper.writeCsv(Barang.header, listBarang, "D:\\Github\\College Shop\\csv\\T_BRG_Copy.csv");
        DataHelper.appendCsv(listBarang.get(0), "D:\\Github\\College Shop\\csv\\T_BRG_Copy.csv");

        List<Struk> listStruk = DataHelper.readCSV(Struk.class, "D:\\Github\\College Shop\\csv\\T_STRUK.csv");
        DataHelper.writeCsv(Struk.header, listStruk, "D:\\Github\\College Shop\\csv\\T_STRUK_Copy.csv");
        DataHelper.appendCsv(listStruk.get(0), "D:\\Github\\College Shop\\csv\\T_STRUK_Copy.csv");

        List<Transaksi> listTransaksi = DataHelper.readCSV(Transaksi.class, "D:\\Github\\College Shop\\csv\\T_TRANS.csv");
        DataHelper.writeCsv(Transaksi.header, listTransaksi, "D:\\Github\\College Shop\\csv\\T_TRANS_Copy.csv");
        DataHelper.appendCsv(listTransaksi.get(0), "D:\\Github\\College Shop\\csv\\T_TRANS_Copy.csv");
    }
}
