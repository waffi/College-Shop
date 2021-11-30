package com.company.CollegeShop.Commands;

import com.company.CollegeShop.Helpers.CommandHelper;
import com.company.CollegeShop.Models.Barang;
import com.company.CollegeShop.Models.Struk;
import com.company.CollegeShop.Models.Transaksi;
import com.company.CollegeShop.Services.BarangService;
import com.company.CollegeShop.Services.StrukService;
import com.company.CollegeShop.Services.TransaksiService;

import java.util.Optional;

public class StrukCommand {

    private CommandHelper _command;
    private Struk _activeStuck;

    public StrukCommand(CommandHelper command) {
        _command = command;
    }

    public void CreateStruk() {

        // create struk
        _activeStuck = new Struk(); // membuat struk baru di memori. Struk yang baru dibuat adalah struk yang aktif
        _activeStuck.printStruk();

        System.out.println("CREATE_STRUK sukses. ID Struk: " + _activeStuck.id + ". Struk aktif: " + _activeStuck.id + ".");
    }

    public void Insert() {
        // TODO: validate active struck
        if (_activeStuck==null){
            System.err.println("INSERT gagal. Tidak ada struk aktif. Silakan membuat struk.");
        }
        else {

            String[] commandArgs = _command.getCommandArgs();

            //checking format insert INSERT "BARANG" JUMLAH
            if (commandArgs.length > 2){
                System.err.println("INSERT pada struk \""+ _activeStuck.id +"\" gagal. Sintaks salah.");
                return;
            }

            // parse args
            String namaBarangRaw = commandArgs[0];
            String namaBarang = namaBarangRaw.replaceAll("\"","");
            Integer jumlahBarang = Integer.parseInt(commandArgs[1]);

            // TODO: validate format nama
            if (!namaBarangRaw.startsWith("\"") ||!namaBarangRaw.endsWith("\"")  ){
                System.err.println("INSERT pada struk \""+ _activeStuck.id +"\" gagal. Sintaks salah.");
                return;
            }

            // get barang by nama
            Optional<Barang> barang = BarangService.getBarangByNama(namaBarang); // memeriksa apakah nama barang yang diinput ada di dalam database.
            if (!barang.isPresent()) { // jika barang ada di database, proses input barang dilanjutkan. jika barang tidak ada di dalam database, barang tidak diinput
                System.err.println("INSERT pada struk \""+ _activeStuck.id +"\" gagal. Barang \"" + namaBarang + "\" tidak dikenal.");
                return;
            }

            // insert transaksi
            _activeStuck.insertTransaksi(barang.get(), jumlahBarang); // menambah barang pada struk yang sedang aktif.
            _activeStuck.printStruk();

            System.out.println("INSERT pada struk \""+ _activeStuck.id +"\" sukses. Barang \"" + namaBarang + "\". Jumlah barang \"" + jumlahBarang + "\".");
        }

    }

    public void CalculateStruk() {

        // TODO: validate active struck
        if (_activeStuck==null){
            System.err.println("CALCULATE_STRUK gagal. Tidak ada struk aktif. Silakan membuat struk.");
        } else {
            // calculate struk
            _activeStuck.calculateStruk(); // menghitung total pembelian pada struk yang sedang aktif
            _activeStuck.printStruk();

            System.out.println("CALCULATE_STRUK pada struk \""+ _activeStuck.id +"\" sukses. Total pembelian adalah \"" + _activeStuck.totalPembelian + "\".");
        }
    }

    public void Payment() {

        // TODO: validate active struck
        if (_activeStuck==null){
            System.err.println("PAYMENT gagal. Tidak ada struk aktif. Silakan membuat struk.");
        }else {
            // parse args
            String[] commandArgs = _command.getCommandArgs();
            Integer nominal = Integer.parseInt(commandArgs[0]);

            // TODO: validate calculate
            if (_activeStuck.totalPembelian==0){
                System.err.println("Calculate Gagal, silahkan melakukan Calculate terlebih dahulu");
                return;
            }

            // TODO: validate minimum nominal
            if (nominal < _activeStuck.totalPembelian){
                System.err.println("PAYMENT pada struk \""+ _activeStuck.id +"\" gagal. Pembayaran tidak cukup.");
            }
            else {
                // pay struk
                _activeStuck.payStruk(nominal); // menyimpan nilai uang pembayaran dari konsumen, menghitung jumlah kembalian
                _activeStuck.printStruk();

                System.out.println("PAYMENT pada struk \""+ _activeStuck.id +"\" sukses. " +
                        "Pembayaran \"" + _activeStuck.totalpembayaran + "\". " +
                        "Total Pembelian \"" + _activeStuck.totalPembelian + "\". " +
                        "Kembalian \"" + _activeStuck.kembalian + "\".");

                // insert struk
                StrukService.insertStruk(_activeStuck); // menyimpan struk dalam database
                for (Transaksi transaksi: _activeStuck.transaksiList) {
                    TransaksiService.insertTransaksi(transaksi); // menyimpan transaksi dalam database
                }

                // remove struk
                _activeStuck = null; // struk dihapus dari struk aktif

                System.out.println("Struk berhasil disimpan dan dihapus dari struk aktif.");
            }

        }
    }

    public void CancelStruck() {

        // cancel struck
        _activeStuck = null;

        System.out.println("Struk berhasil dihapus dari struk aktif.");
    }
}
