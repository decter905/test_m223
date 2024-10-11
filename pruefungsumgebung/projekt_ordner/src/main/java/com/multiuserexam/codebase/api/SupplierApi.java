package com.multiuserexam.codebase.api;

import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;

import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SupplierApi {

    private long currentOrderNum;
    private final Random rand = new Random();

    private static final Logger apiLogger = createApiLogger();

    public SupplierApi() {
        currentOrderNum = rand.nextInt(1000);
        apiLogger.info(String.format("API Initialized with last order ar ID %d", currentOrderNum));
    }

    /**
     * Returns a Spring Data-Pair, which contains the order number and whether the order can be fulfilled by
     * the supplier.
     *
     * @param ISBN the ISBN code of the book to order
     * @param quantity the quantity of the book to order
     * @return a Pair containing the order id and whether that order has successfully been made
     */
    public synchronized Pair<String, Boolean> enterOrder(String ISBN, int quantity) {
        currentOrderNum++;
        boolean orderGood = true;
        Pair<String, Boolean> orderResponse = Pair.of(String.valueOf(currentOrderNum), orderGood);
        apiLogger.info(String.format("API registered Order for (Book %s, Quantity %d): Response (%d, %b)",
            ISBN, quantity, currentOrderNum, orderGood));
        return orderResponse;
    }

    /**
     * Tries to roll back an order that has been made through enterOrder.
     *
     * @param orderId the id of the order that should be rolled back
     * @return whether the order could successfully be rolled back
     */
    public synchronized Boolean rollbackOrder(String orderId) {
        boolean rollbackPossible = rand.nextBoolean();
        apiLogger.info(String.format("API Rollback Call for Order %s: Response %b", orderId, rollbackPossible));
        return rollbackPossible;
    }

    private static Logger createApiLogger() {
        Logger logger = Logger.getLogger(SupplierApi.class.getName());

        try {
            // Set up file handler
            FileHandler fileHandler = new FileHandler("API_calls.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Set up logger
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException | SecurityException e) {
            LoggerFactory.getLogger(SupplierApi.class).warn("Failed to set up file logger. Reason: {}", e.getMessage());
        }

        return logger;
    }
}