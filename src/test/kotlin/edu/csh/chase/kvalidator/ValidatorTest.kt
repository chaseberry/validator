package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.validators.ValidatorStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorTest {


    @Test
    fun validatingBooleanTest() {
        val field = required(Types.boolean, "")

        val result = field.checkAgainst(true)

        assertEquals(ValidatorStatus.OK, result.status)
    }


}