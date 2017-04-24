package edu.csh.chase.validator

import edu.csh.chase.validator.types.Type
import edu.csh.chase.validator.validators.CommonValidator
import edu.csh.chase.validator.ValidatorResult

open class Element(val required: Boolean, val type: Type, private val check: ((CommonValidator) -> Unit)?) {

    fun checkAgainst(value: Any?): ValidatorResult {

        val validator = type.getValidator(this, value)

        check?.invoke(validator)

        return validator.getResult()
    }

}
