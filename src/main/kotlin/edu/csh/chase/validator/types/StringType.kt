package edu.csh.chase.validator.types

import edu.csh.chase.validator.Element
import edu.csh.chase.validator.validators.CommonValidator

class StringType : Type("String") {

    override fun getValidator(element: Element, value: Any?): CommonValidator {
        return CommonValidator(element.required, element.type, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is StringType
    }

    override fun isType(value: Any?): Boolean {
        return value is String
    }
}