package edu.csh.chase.validator

class Result(val extraFields: List<String>, val fields: List<ValidatorResult>) {

    val status = when {
        fields.map { it.problemCount() != 0 }.reduce { acc, b -> acc || b } -> ValidatorStatus.ERROR
        ValidatorConfig.extraFieldsCauseError && extraFields.isNotEmpty() -> ValidatorStatus.ERROR
        else -> ValidatorStatus.OK
    }

    val numProblems: Int by lazy {
        fields.sumBy { it.problemCount() } + (if (ValidatorConfig.extraFieldsCauseError) extraFields.size else 0)
    }

}