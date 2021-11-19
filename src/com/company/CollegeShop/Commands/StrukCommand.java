package com.company.CollegeShop.Commands;

import com.company.CollegeShop.Helpers.CommandHelper;
import com.company.CollegeShop.Models.Barang;
import com.company.CollegeShop.Models.Struk;
import com.company.CollegeShop.Services.BarangService;
import com.company.CollegeShop.Services.StrukService;

import java.util.Optional;

public class StrukCommand {

    private CommandHelper _command;
    private Struk _activeStuck;

    public StrukCommand(CommandHelper command) {
        _command = command;
    }

    public Boolean CreateStruk() {

        // create struk
        _activeStuck = new Struk();
        _activeStuck.printStruk();

        return true;
    }

    public Boolean Insert() {

        // validate args
        String[] requiredArgs = {"nama_barang", "jumlah_barang"};
        if (!_command.checkArgs(requiredArgs))
            return false;

        // parse args
        String[] commandArgs = _command.getCommandArgs();
        String namaBarang = commandArgs[0].replaceAll("\"","");
        Integer jumlahBarang = Integer.parseInt(commandArgs[1]);

        // get barang by nama
        Optional<Barang> barang = BarangService.getBarangByNama(namaBarang);
        if (!barang.isPresent())
            return false;

        // insert transaksi
        _activeStuck.insertTransaksi(barang.get(), jumlahBarang);
        _activeStuck.printStruk();

        return true;
    }

    public Boolean CalculateStruk() {

        // calculate struk
        _activeStuck.calculateStruk();
        _activeStuck.printStruk();

        return true;
    }

    public Boolean Payment() {

        // validate args
        String[] requiredArgs = {"nominal"};
        if (!_command.checkArgs(requiredArgs))
            return false;

        // parse args
        String[] commandArgs = _command.getCommandArgs();
        Integer nominal = Integer.parseInt(commandArgs[0]);

        // pay struk
        _activeStuck.payStruk(nominal);
        _activeStuck.printStruk();

        // insert struk
        StrukService.insertStruk(_activeStuck);

        return true;
    }

    public Boolean CancelStruck() {

        // cancel struck
        _activeStuck = null;

        return true;
    }
}
