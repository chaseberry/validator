package edu.csh.chase.validator.types

import edu.csh.chase.validator.Element
import edu.csh.chase.validator.validators.CommonValidator
import edu.csh.chase.validator.validators.NumberValidator

class LongType : Type("Long") {

    override fun getValidator(element: Element, value: Any?): CommonValidator {
        return NumberValidator(element.required, element.type, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is LongType
    }

    override fun isType(value: Any?): Boolean {
        return value is Long
    }
}