package com.pluralsight.utility;

import com.pluralsight.datamodels.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {

    private static final String FILE_PATH = "src/main/resources/receipts/";

    public String getTimestampFormat() {
        // creates format and passes it to the current date and time and then adds .txt to the end
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        return LocalDateTime.now().format(formatter) + ".txt";
    }

    public void saveReceipt(Order order) {
        // creates buffered and file writer that takes in file path and custom timestamp and writes order details to new file
        try (BufferedWriter receiptWriter = new BufferedWriter(new FileWriter(FILE_PATH + getTimestampFormat()))){
            receiptWriter.write(order.getOrder());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
