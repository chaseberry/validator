package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.ValidatorStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorTest {

    @Test
    fun validateBooleanTest() {
        val field = required(Types.boolean, "")

        val result = field.checkAgainst(true)

        assertEquals(ValidatorStatus.OK, result.status)
        assertEquals(Types.boolean.name, result.type)
        assertEquals(true, result.value)
    }

    @Test
    fun invalidBooleanTest() {
        val field = required(Types.boolean, "")

        val result = field.checkAgainst(15)

        assertEquals(ValidatorStatus.ERROR, result.status)
        assertEquals(15, result.value)
        assertEquals(Types.int.name, result.type)
    }

    @Test
    fun validateStringTest() {
        val field = required(Types.string, "")

        val result = field.checkAgainst("Hello World!")

        assertEquals(ValidatorStatus.OK, result.status)
        assertEquals(Types.string.name, result.type)
        assertEquals("Hello World!", result.value)
    }

    @Test
    fun invalidStringTest() {
        val field = required(Types.string, "")

        val result = field.checkAgainst(true)

        assertEquals(ValidatorStatus.ERROR, result.status)
        assertEquals(Types.boolean.name, result.type)
        assertEquals(true, result.value)
    }
}