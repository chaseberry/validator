package edu.csh.chase.validator.types

import edu.csh.chase.validator.Element
import edu.csh.chase.validator.validators.CommonValidator
import edu.csh.chase.validator.validators.ListValidator

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