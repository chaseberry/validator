package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.Problem

class MapValidatorResult(type: String,
                         value: Any?,
                         status: ValidatorStatus,
                         problems: List<Problem>?,
                         val extraFields: List<String>?) : ValidatorResult(type, value, status, problems) {
}