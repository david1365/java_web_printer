package ir.daak.escpos.model;

public class Command {
    private byte[] command;
    private boolean hasParameter = false;
    private boolean notPrintableCode = false;
    private boolean texted = false;

    public Command(byte[] command, boolean hasParameter) {
        this(command);
        this.hasParameter = hasParameter;
    }

    public Command(byte[] command) {
        this.command = command;
    }

    public Command(boolean texted) {
        this(true, true);
        this.texted = texted;
    }

    public Command(boolean hasParameter, boolean notPrintableCode) {
        this(null, hasParameter);
        this.notPrintableCode = notPrintableCode;
    }

    public byte[] getCommand() {
        return command;
    }

    public void setCommand(byte[] command) {
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

    public boolean isTexted() {
        return texted;
    }

    public void setTexted(boolean texted) {
        this.texted = texted;
    }
}
