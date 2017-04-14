package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.validators.CommonValidator

data class Field(val required: Boolean, val type: Type, val name: String, private val check: ((CommonValidator) -> Unit)?) {

    fun checkAgainst(value: Any?): CommonValidator {

        val result = type.getValidator(this, value)

        check?.invoke(result)

        return result
    }

}
