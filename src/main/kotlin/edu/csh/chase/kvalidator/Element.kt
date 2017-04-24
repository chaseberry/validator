package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.ValidatorResult

open class Element(val required: Boolean, val type: Type, private val check: ((CommonValidator) -> Unit)?) {

    fun checkAgainst(value: Any?): ValidatorResult {

        val result = type.getValidator(this, value)

        check?.invoke(result)

        return result.getResult()
    }

}
