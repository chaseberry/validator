package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.Problem
import edu.csh.chase.kvalidator.ValidatorResult
import edu.csh.chase.kvalidator.ValidatorStatus

class MapValidatorResult(type: String,
                         value: Any?,
                         status: ValidatorStatus,
                         problems: List<Problem>?,
                         val extraFields: List<String>?) : ValidatorResult(type, value, status, problems) {
}