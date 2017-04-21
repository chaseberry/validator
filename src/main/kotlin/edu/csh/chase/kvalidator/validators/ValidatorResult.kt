package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.Problem

open class ValidatorResult(val type: String,
                           val value: Any?,
                           val status: ValidatorStatus,
                           val problems: List<Problem>?) {

    fun problemCount(): Int = problems?.size ?: 0

}