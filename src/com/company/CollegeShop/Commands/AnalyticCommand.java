package com.company.CollegeShop.Commands;

import com.company.CollegeShop.Helpers.CommandHelper;

public class AnalyticCommand {

    private CommandHelper _command;

    public AnalyticCommand(CommandHelper command) {
        _command = command;
    }

    public void DisplayStruk() {

        // parse args
        String[] commandArgs = _command.getCommandArgs();
        String tanggalAwal = commandArgs.length > 0 ? commandArgs[0] : null;
        String tanggalAkhir = commandArgs.length > 1 ? commandArgs[1] : null;
    }

    public void DisplayPeak() {

        // parse args
        String[] commandArgs = _command.getCommandArgs();
        String tanggalAwal = commandArgs.length > 0 ? commandArgs[0] : null;
        String tanggalAkhir = commandArgs.length > 1 ? commandArgs[1] : null;
    }

    public void BestProduct() {

        // parse args
        String[] commandArgs = _command.getCommandArgs();
        String tanggalAwal = commandArgs.length > 0 ? commandArgs[0] : null;
        String tanggalAkhir = commandArgs.length > 1 ? commandArgs[1] : null;
    }
}
