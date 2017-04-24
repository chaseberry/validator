package edu.csh.chase.validator

import edu.csh.chase.validator.Problem
import edu.csh.chase.validator.ValidatorResult
import edu.csh.chase.validator.ValidatorStatus

class MapValidatorResult(type: String,
                         value: Any?,
                         status: ValidatorStatus,
                         problems: List<Problem>?,
                         val extraFields: List<String>?) : ValidatorResult(type, value, status, problems) {
}