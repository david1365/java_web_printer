package ir.daak.escpos;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:slp_messages_fa.properties",
        "classpath:slp_messages_en.properties",
        "classpath:slp_messages.properties"
})
public class EscposBeanConfig {
    @Value("${slipPrinter.portName:COM1}")
    private String portName;

    @Value("${slipPrinter.bitsPerSecond:9600}")
    private int bitsPerSecond;

    @Value("${slipPrinter.dataBits:8}")
    private int dataBits;

    @Value("${slipPrinter.stopBits:1}")
    private int stopBits;

    @Value("${slipPrinter.parity:0}")
    private int parity;

    @Value("${slipPrinter.flowControl:0}")
    private int flowControl;

    @Bean
    public PrinterService printerService() {
        return new PrinterService(portName, bitsPerSecond, dataBits, stopBits, parity, flowControl);
    }
}
