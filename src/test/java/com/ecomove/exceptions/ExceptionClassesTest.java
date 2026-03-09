package com.ecomove.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour les classes d'exceptions personnalisées.
 */
public class ExceptionClassesTest {

    @Test
    void testResourceNotFoundException() {
        String msg = "Not found";
        ResourceNotFoundException ex = new ResourceNotFoundException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    void testBusinessException() {
        String msg = "Business error";
        BusinessException ex = new BusinessException(msg);
        assertEquals(msg, ex.getMessage());
    }
}
