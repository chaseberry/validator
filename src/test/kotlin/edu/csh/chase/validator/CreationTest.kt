package edu.csh.chase.validator

import edu.csh.chase.validator.validators.CommonValidator
import edu.csh.chase.validator.validators.ListValidator
import edu.csh.chase.validator.validators.MapValidator
import edu.csh.chase.validator.validators.NumberValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class CreationTest {

    @Test fun createRequiredField() {
        val field = required(Types.int, "fieldName")

        assertEquals(true, field.required)
        assertEquals(Types.int, field.type)
    }

    @Test fun createOptionalField() {
        val field = optional(Types.double, "fieldName")

        assertEquals(false, field.required)
        assertEquals(Types.double, field.type)
    }

    @Test fun createNumberValidatorFromInt() {
        val field = Element(true, Types.int, null)

        val validator = field.type.getValidator(field, 12)

        assert(validator is NumberValidator)
        assertEquals(12, validator.value)
    }

    @Test fun createNumberValidatorFromDouble() {
        val field = Element(true, Types.double, null)

        val validator = field.type.getValidator(field, 4.5)

        assert(validator is NumberValidator)
        assertEquals(4.5, validator.value)
    }

    @Test fun createNumberValidatorFromLong() {
        val field = Element(true, Types.long, null)

        val validator = field.type.getValidator(field, 145)

        assert(validator is NumberValidator)
        assertEquals(145, validator.value)
    }

    @Test fun createNumberValidatorFromFloat() {
        val field = Element(true, Types.float, null)

        val validator = field.type.getValidator(field, 18.6452)

        assert(validator is NumberValidator)
        assertEquals(18.6452, validator.value)
    }

    @Test fun createValidatorFromBoolean() {
        val field = Element(true, Types.boolean, null)

        val validator = field.type.getValidator(field, false)

        assert(validator is CommonValidator)
        assertEquals(false, validator.value)
    }

    @Test fun createValidatorFromString() {
        val field = Element(true, Types.string, null)

        val validator = field.type.getValidator(field, "testString")

        assert(validator is CommonValidator)
        assertEquals("testString", validator.value)
    }

    @Test fun createMapValidator() {
        val field = Element(true, Types.map, null)

        val validator = field.type.getValidator(field, mapOf<String, Any?>())

        assert(validator is MapValidator)
        assertEquals(mapOf<String, Any?>(), validator.value)
    }

    @Test fun createListValidator() {
        val field = Element(true, Types.list, null)

        val validator = field.type.getValidator(field, emptyList<String>())

        assert(validator is ListValidator)
        assertEquals(emptyList<String>(), validator.value)
    }

}