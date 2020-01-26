package com.runeachievements.application;

import org.jooq.Transaction;
import org.springframework.transaction.TransactionStatus;

/**
 * Adapted from https://github.com/jOOQ/jOOQ/tree/master/jOOQ-examples/jOOQ-spring-example
 */
public class SpringTransaction implements Transaction {
    final TransactionStatus tx;

    SpringTransaction(TransactionStatus tx) {
        this.tx = tx;
    }
}
