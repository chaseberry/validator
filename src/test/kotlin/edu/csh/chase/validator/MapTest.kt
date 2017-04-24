package edu.csh.chase.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class MapTest {

    @Test fun validateMapTest() {
        val map = mapOf(
                "string" to "string",
                "int" to 1567,
                "double" to 15.67,
                "boolean" to true
        )

        val result = map.validate(
                required(Types.string, "string"),
                required(Types.int, "int"),
                required(Types.double, "double"),
                required(Types.boolean, "boolean")
        )

        assertEquals(ValidatorStatus.OK, result.status)
        assert(result.extraFields.isEmpty())

    }


}