package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.ValidatorResult

data class Field(val required: Boolean, val type: Type, val name: String, private val check: ((CommonValidator) -> Unit)?) {

    fun checkAgainst(value: Any?): ValidatorResult {

        val result = type.getValidator(this, value)

        check?.invoke(result)

        return result.getResult()
    }

}
