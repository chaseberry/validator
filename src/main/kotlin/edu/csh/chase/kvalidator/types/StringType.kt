package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Field
import edu.csh.chase.kvalidator.validators.CommonValidator

class StringType : Type("String") {

    override fun getValidator(field: Field, value: Any?): CommonValidator {
        return CommonValidator(field.required, field.type, name, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is StringType
    }

    override fun isType(value: Any?): Boolean {
        return value is String
    }
}