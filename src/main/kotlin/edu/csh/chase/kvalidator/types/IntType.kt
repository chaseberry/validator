package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Field
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.NumberValidator

class IntType : Type("Int") {
    override fun getValidator(field: Field, value: Any?): CommonValidator {
        return NumberValidator(field.required, field.type, name, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is IntType
    }

    override fun isType(value: Any?): Boolean {
        return value is Int
    }
}