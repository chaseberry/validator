package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.ListValidator
import edu.csh.chase.kvalidator.validators.MapValidator
import edu.csh.chase.kvalidator.validators.NumberValidator
import org.junit.Assert.assertEquals
import org.junit.Test

class CreationTest {

    @Test fun createRequiredField() {
        val field = required(Types.int, "fieldName")

        assertEquals(true, field.required)
        assertEquals(Types.int, field.type)
        assertEquals("fieldName", field.name)
    }

    @Test fun createOptionalField() {
        val field = optional(Types.double, "fieldName")

        assertEquals(false, field.required)
        assertEquals(Types.double, field.type)
        assertEquals("fieldName", field.name)
    }

    @Test fun createNumberValidatorFromInt() {
        val field = Field(true, Types.int, "fieldName", null)

        val validator = field.type.getValidator(field, 12)

        assert(validator is NumberValidator)
        assertEquals(12, validator.value)
    }

    @Test fun createNumberValidatorFromDouble() {
        val field = Field(true, Types.double, "fieldName", null)

        val validator = field.type.getValidator(field, 4.5)

        assert(validator is NumberValidator)
        assertEquals(4.5, validator.value)
    }

    @Test fun createNumberValidatorFromLong() {
        val field = Field(true, Types.long, "fieldName", null)

        val validator = field.type.getValidator(field, 145)

        assert(validator is NumberValidator)
        assertEquals(145, validator.value)
    }

    @Test fun createNumberValidatorFromFloat() {
        val field = Field(true, Types.float, "fieldName", null)

        val validator = field.type.getValidator(field, 18.6452)

        assert(validator is NumberValidator)
        assertEquals(18.6452, validator.value)
    }

    @Test fun createValidatorFromBoolean() {
        val field = Field(true, Types.boolean, "fieldName", null)

        val validator = field.type.getValidator(field, false)

        assert(validator is CommonValidator)
        assertEquals(false, validator.value)
    }

    @Test fun createValidatorFromString() {
        val field = Field(true, Types.string, "fieldName", null)

        val validator = field.type.getValidator(field, "testString")

        assert(validator is CommonValidator)
        assertEquals("testString", validator.value)
    }

    @Test fun createMapValidator() {
        val field = Field(true, Types.map, "fieldName", null)

        val validator = field.type.getValidator(field, mapOf<String, Any?>())

        assert(validator is MapValidator)
        assertEquals(mapOf<String, Any?>(), validator.value)
    }

    @Test fun createListValidator() {
        val field = Field(true, Types.list, "fieldName", null)

        val validator = field.type.getValidator(field, emptyList<String>())

        assert(validator is ListValidator)
        assertEquals(emptyList<String>(), validator.value)
    }

}