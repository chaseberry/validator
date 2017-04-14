package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.validators.CommonValidator

class Result(val extraFields: List<String>, val fields: List<CommonValidator>) {

    val issueCount: Int by lazy {
        fields.sumBy { it.numProblems() } + (if (Config.extraFieldsCauseError) extraFields.size else 0)
    }

    val map = fields.associate { it.name to it.getResult() }
}