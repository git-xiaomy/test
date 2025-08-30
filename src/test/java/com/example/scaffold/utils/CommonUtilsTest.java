package com.example.scaffold.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Common Utils Test
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
class CommonUtilsTest {

    @Test
    void testGenerateUUID() {
        String uuid = CommonUtils.generateUUID();
        assertNotNull(uuid);
        assertEquals(32, uuid.length()); // UUID without hyphens should be 32 characters
    }

    @Test
    void testGenerateShortUUID() {
        String shortUUID = CommonUtils.generateShortUUID();
        assertNotNull(shortUUID);
        assertEquals(8, shortUUID.length());
    }

    @Test
    void testIsEmpty() {
        assertTrue(CommonUtils.isEmpty(null));
        assertTrue(CommonUtils.isEmpty(""));
        assertTrue(CommonUtils.isEmpty("   "));
        assertFalse(CommonUtils.isEmpty("test"));
    }

    @Test
    void testIsNotEmpty() {
        assertFalse(CommonUtils.isNotEmpty(null));
        assertFalse(CommonUtils.isNotEmpty(""));
        assertFalse(CommonUtils.isNotEmpty("   "));
        assertTrue(CommonUtils.isNotEmpty("test"));
    }

    @Test
    void testMaskPhone() {
        assertEquals("13*******00", CommonUtils.maskPhone("13801234000")); // 11 characters -> 13*******00
        assertEquals("123", CommonUtils.maskPhone("123")); // Too short (<=4), no masking
        assertEquals("12*45", CommonUtils.maskPhone("12345")); // 5 characters -> 12*45
    }

    @Test
    void testMaskEmail() {
        assertEquals("te**er@example.com", CommonUtils.maskEmail("tester@example.com")); // "tester" -> "te**er"  
        assertEquals("invalid-email", CommonUtils.maskEmail("invalid-email")); // No @ symbol
        assertEquals("", CommonUtils.maskEmail(""));
        assertEquals("test@example.com", CommonUtils.maskEmail("test@example.com")); // Username too short (<=4) to mask
    }

    @Test
    void testMD5() {
        String input = "test";
        String hash = CommonUtils.md5(input);
        assertNotNull(hash);
        assertEquals(32, hash.length()); // MD5 hash is 32 characters
        assertEquals("098f6bcd4621d373cade4e832627b4f6", hash);
    }
}