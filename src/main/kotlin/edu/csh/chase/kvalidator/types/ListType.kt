package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Element
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.ListValidator

class ListType : Type("List") {

    override fun getValidator(element: Element, value: Any?): CommonValidator {
        return ListValidator(element.required, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is ListType
    }

    override fun isType(value: Any?): Boolean {
        return value is List<*>
    }
}