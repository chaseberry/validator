package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Element
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.NumberValidator

class FloatType : Type("Float") {
    override fun getValidator(element: Element, value: Any?): CommonValidator {
        return NumberValidator(element.required, element.type, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is FloatType
    }

    override fun isType(value: Any?): Boolean {
        return value is Float
    }
}