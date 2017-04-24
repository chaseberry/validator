package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.ValidatorResult

class Result(val extraFields: List<String>, val fields: List<ValidatorResult>) {

    val numProblems: Int by lazy {
        fields.sumBy { it.problemCount() } + (if (Config.extraFieldsCauseError) extraFields.size else 0)
    }

}