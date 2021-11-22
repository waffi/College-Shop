package com.company.CollegeShop;

import com.company.CollegeShop.Commands.AnalyticCommand;
import com.company.CollegeShop.Commands.StrukCommand;
import com.company.CollegeShop.Helpers.CommandHelper;

public class Main {

//        sample struk command
//        - CREATE_STRUK
//        - INSERT "Pepsodent" 10
//        - INSERT "Aqua Botol 1 Liter" 1
//        - INSERT "Indomie Goreng" 5
//        - INSERT Indomie Goreng 5
//        - INSERT Pepsodent 10
//        - CALCULATE_STRUK
//        - PAYMENT 500
//        - PAYMENT 50000
//        - INSERT "Aqua Botol 1 Liter" 5
//        - CANCEL_STRUK

//        sample analytic command
//        - DISPLAY_STRUK
//        - DISPLAY_STRUK 1-Agustus-2020 10-Agustus-2020
//        - DISPLAY_STRUK 1-Agustus-2020
//        - DISPLAY_PEAK
//        - DISPLAY_PEAK 1-Agustus-2020 10-Agustus-2020
//        - DISPLAY_PEAK 1-Agustus-2020
//        - BEST_PRODUCT
//        - BEST_PRODUCT 1-Agustus-2020 10-Agustus-2020
//        - BEST_PRODUCT 1-Agustus-2020

    public static void main(String[] args) {

        CommandHelper command = new CommandHelper(System.in);
        StrukCommand strukCommand = new StrukCommand(command);
        AnalyticCommand analyticCommand = new AnalyticCommand(command);

        Boolean running = true;

        while(running) {

            try {
                System.out.println("\nEnter command: ");
                command.readCommand();

                switch(command.getCommandName()) {

                    case "CREATE_STRUK":
                        strukCommand.CreateStruk();
                        break;

                    case "INSERT":
                        strukCommand.Insert();
                        break;

                    case "CALCULATE_STRUK":
                        strukCommand.CalculateStruk();
                        break;

                    case "PAYMENT":
                        strukCommand.Payment();
                        break;

                    case "CANCEL_STRUK":
                        strukCommand.CancelStruck();
                        break;

                    case "DISPLAY_STRUK":
                        analyticCommand.DisplayStruk();
                        break;

                    case "DISPLAY_PEAK":
                        analyticCommand.DisplayPeak();
                        break;

                    case "BEST_PRODUCT":
                        analyticCommand.BestProduct();
                        break;

                    case "exit":
                        running = false;
                        System.out.println("Application Closed!");
                        break;

                    default:
                        System.err.println("Command not recognized!");
                        break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        command.close();
    }
}
