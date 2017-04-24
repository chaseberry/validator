package edu.csh.chase.validator

import edu.csh.chase.validator.Problem

open class ValidatorResult(val type: String,
                           val value: Any?,
                           val status: ValidatorStatus,
                           val problems: List<Problem>?) {

    fun problemCount(): Int = problems?.size ?: 0

}