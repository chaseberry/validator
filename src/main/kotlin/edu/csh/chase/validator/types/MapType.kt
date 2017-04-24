package edu.csh.chase.validator.types

import edu.csh.chase.validator.Element
import edu.csh.chase.validator.validators.CommonValidator
import edu.csh.chase.validator.validators.MapValidator

class MapType : Type("Map") {

    override fun getValidator(element: Element, value: Any?): CommonValidator {
        return MapValidator(element.required, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is MapType
    }

    override fun isType(value: Any?): Boolean {
        return value is Map<*, *>
    }
}