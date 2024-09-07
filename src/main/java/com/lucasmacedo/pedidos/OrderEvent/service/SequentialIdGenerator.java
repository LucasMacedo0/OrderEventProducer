package com.lucasmacedo.pedidos.OrderEvent.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class SequentialIdGenerator {

    private final AtomicLong nextId = new AtomicLong(1);

    public long generateNextId() {
        return nextId.getAndIncrement();
    }
}
