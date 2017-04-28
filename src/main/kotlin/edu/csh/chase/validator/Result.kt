package edu.csh.chase.validator

class Result(val extraFields: List<String>, val fields: Map<String, ValidatorResult>) {

    val status = when {
        fields.map { it.value.problemCount() != 0 }.reduce { acc, b -> acc || b } -> ValidatorStatus.ERROR
        ValidatorConfig.extraFieldsCauseError && extraFields.isNotEmpty() -> ValidatorStatus.ERROR
        else -> ValidatorStatus.OK
    }

    val numProblems: Int by lazy {
        fields.values.sumBy { it.problemCount() } + (if (ValidatorConfig.extraFieldsCauseError) extraFields.size else 0)
    }

}