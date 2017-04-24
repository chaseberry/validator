package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.*
import java.util.*

open class MapValidator(required: Boolean, value: Any?) : CommonValidator(required, Types.map, value) {

    private val results = HashMap<String, ValidatorResult>()

    private val extraFields = ArrayList<String>()

    override fun numProblems(): Int {
        return super.numProblems() + results.values.sumBy { it.problemCount() }
    }

    override fun fields(vararg fields: Field) {
        if (value == null) {
            return
        }

        //Make sure keys are strings
        val v = (value as Map<*, *>).mapKeys { it.key.toString() }

        fields.forEach {
            results[it.name] = it.checkAgainst(v[it.name])
        }

        val fieldNames = fields.map { it.name }

        extraFields.addAll(v.map { it.key }.filter { it !in fieldNames })
    }

    override fun getResult(): ValidatorResult {
        return MapValidatorResult(
                type = value.getType()?.name ?: "Unknown",
                value = when (results.isNotEmpty()) {
                    true -> results
                    false -> value
                },
                status = when {
                    hasProblems() -> ValidatorStatus.ERROR
                    Config.extraFieldsCauseError && extraFields.isNotEmpty() -> ValidatorStatus.ERROR
                    else -> ValidatorStatus.OK
                },
                problems = problems,
                extraFields = extraFields
        )
    }

}