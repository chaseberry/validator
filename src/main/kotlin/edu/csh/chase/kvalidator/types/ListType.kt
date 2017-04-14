package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Field
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.ListValidator

class ListType : Type("List") {
    override fun getValidator(field: Field, value: Any?): CommonValidator {
        return ListValidator(field.required, field.name, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is ListType
    }

    override fun isType(value: Any?): Boolean {
        return value is List<*>
    }
}