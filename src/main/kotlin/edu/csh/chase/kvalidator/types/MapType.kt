package edu.csh.chase.kvalidator.types

import edu.csh.chase.kvalidator.Field
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.MapValidator

class MapType : Type("Map") {

    override fun getValidator(field: Field, value: Any?): CommonValidator {
        return MapValidator(field.required, field.name, value)
    }

    override fun matchesType(other: Type): Boolean {
        return other is MapType
    }

    override fun isType(value: Any?): Boolean {
        return value is Map<*, *>
    }
}