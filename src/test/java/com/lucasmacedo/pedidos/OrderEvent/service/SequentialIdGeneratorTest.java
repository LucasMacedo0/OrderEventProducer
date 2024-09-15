package com.lucasmacedo.pedidos.OrderEvent.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SequentialIdGeneratorTest {

     @Test
     @DisplayName("Deve gerar IDs sequenciais corretamente")
     void MustGenerateSequentialIdsCorrectly(){
         SequentialIdGenerator idGenerator = new SequentialIdGenerator();

         long firstId = idGenerator.generateNextId();
         long secondId = idGenerator.generateNextId();
         long thirdId = idGenerator.generateNextId();

         assertEquals(1L, firstId);
         assertEquals(2L, secondId);
         assertEquals(3L, thirdId);
     }
}
