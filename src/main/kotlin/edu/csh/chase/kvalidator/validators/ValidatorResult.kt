package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.Problem

open class ValidatorResult(val type: String,
                           val value: Any?,
                           val status: String,
                           val problems: List<Problem>?) {
}