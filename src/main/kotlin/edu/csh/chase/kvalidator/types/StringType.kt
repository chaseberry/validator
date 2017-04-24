package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Element
import edu.csh.chase.kvalidator.validators.CommonValidator

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