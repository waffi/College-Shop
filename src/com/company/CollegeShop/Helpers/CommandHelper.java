package com.company.CollegeShop.Helpers;

import java.io.InputStream;
import java.util.*;

public class CommandHelper {

    private Scanner _scanner;
    private String _commandName;
    private String[] _commandArgs;

    public CommandHelper(InputStream source) {
        _scanner = new Scanner(source);
    }

    public void readCommand() {
        String[] inputToken = translateCommandline(_scanner.nextLine());
        _commandName = inputToken[0];
        _commandArgs = Arrays.copyOfRange(inputToken, 1, inputToken.length);
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

    private String[] translateCommandline(String toProcess) {
        if (toProcess != null && toProcess.length() != 0) {
            int state = 0;
            StringTokenizer tok = new StringTokenizer(toProcess, " ", true);
            Vector v = new Vector();
            StringBuffer current = new StringBuffer();
            boolean lastTokenHasBeenQuoted = false;

            while(true) {
                while(tok.hasMoreTokens()) {
                    String nextTok = tok.nextToken();
                    switch(state) {
                        case 1:
                            if ("'".equals(nextTok)) {
                                lastTokenHasBeenQuoted = true;
                                state = 0;
                            } else {
                                current.append(nextTok);
                            }
                            continue;
                        case 2:
                            if ("\"".equals(nextTok)) {
                                lastTokenHasBeenQuoted = true;
                                state = 0;
                            } else {
                                current.append(nextTok);
                            }
                            continue;
                    }

                    if ("'".equals(nextTok)) {
                        state = 1;
                    } else if ("\"".equals(nextTok)) {
                        state = 2;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() != 0) {
                            v.addElement(current.toString());
                            current = new StringBuffer();
                        }
                    } else {
                        current.append(nextTok);
                    }

                    lastTokenHasBeenQuoted = false;
                }

                if (lastTokenHasBeenQuoted || current.length() != 0) {
                    v.addElement(current.toString());
                }

                if (state != 1 && state != 2) {
                    String[] args = new String[v.size()];
                    v.copyInto(args);
                    return args;
                }

                throw new IllegalArgumentException("Unbalanced quotes in " + toProcess);
            }
        } else {
            return new String[0];
        }
    }
}
