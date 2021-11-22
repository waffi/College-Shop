package com.company.CollegeShop.Helpers;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.exec.CommandLine;

public class CommandHelper {

    private Scanner _scanner;
    private String _commandName;
    private String[] _commandArgs;

    public CommandHelper(InputStream source) {
        _scanner = new Scanner(source);
    }

    public void readCommand() {
        CommandLine command = CommandLine.parse(_scanner.nextLine());
        _commandName = command.getExecutable();
        _commandArgs = command.getArguments();
    }

    public String getCommandName() {
        return _commandName;
    }

    public String[] getCommandArgs() {
        return _commandArgs;
    }

    public void close() {
        _scanner.close();
    }
}
