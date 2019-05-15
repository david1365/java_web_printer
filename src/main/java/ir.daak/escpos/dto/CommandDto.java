package ir.daak.escpos.dto;

import javax.validation.constraints.NotEmpty;

public class CommandDto {
    @NotEmpty(message = "slipPrinter.CommandName")
    private String name;
    private String param;

    public CommandDto() {
    }

    public CommandDto(String name, String value) {
        this.name = name;
        this.param = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
