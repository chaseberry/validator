package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Element
import edu.csh.chase.kvalidator.validators.CommonValidator

abstract class Type(val name: String) {

    abstract fun getValidator(element: Element, value: Any?): CommonValidator

    abstract fun matchesType(other: Type): Boolean

    abstract fun isType(value: Any?): Boolean
}