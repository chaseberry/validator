package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Element
import edu.csh.chase.kvalidator.validators.CommonValidator

class BooleanType : Type("Boolean") {

    override fun getValidator(element: Element, value: Any?): CommonValidator {
        return CommonValidator(element.required, element.type, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is BooleanType
    }

    override fun isType(value: Any?): Boolean {
        return value is Boolean
    }

}