package ir.daak.escpos.model;

public class Command {
    private Byte[] command;
    private boolean hasParameter = false;
    private boolean notPrintableCode = false;

    public Command(Byte[] command, boolean hasParameter) {
        this(command);
        this.hasParameter = hasParameter;
    }

    public Command(Byte[] command) {
        this.command = command;
    }

    public Command(boolean hasParameter, boolean notPrintableCode) {
        this(null, hasParameter);
        this.notPrintableCode = notPrintableCode;
    }

    public Byte[] getCommand() {
        return command;
    }

    public void setCommand(Byte[] command) {
        this.command = command;
    }

    public boolean isHasParameter() {
        return hasParameter;
    }

    public void setHasParameter(boolean hasParameter) {
        this.hasParameter = hasParameter;
    }

    public boolean isNotPrintableCode() {
        return notPrintableCode;
    }

    public void setNotPrintableCode(boolean notPrintableCode) {
        this.notPrintableCode = notPrintableCode;
    }
}
