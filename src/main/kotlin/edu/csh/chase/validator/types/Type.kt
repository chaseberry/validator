package edu.csh.chase.validator.types

import edu.csh.chase.validator.Element
import edu.csh.chase.validator.validators.CommonValidator

abstract class Type(val name: String) {

    abstract fun getValidator(element: Element, value: Any?): CommonValidator

    abstract fun matchesType(other: Type): Boolean

    abstract fun isType(value: Any?): Boolean
}