package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.Config
import edu.csh.chase.kvalidator.Field
import edu.csh.chase.kvalidator.Types
import edu.csh.chase.kvalidator.getType
import java.util.*

open class MapValidator(required: Boolean, name: String, value: Any?) : CommonValidator(required, Types.map, name, value) {

    private val results = ArrayList<CommonValidator>()

    private val extraFields = ArrayList<String>()

    override fun numProblems(): Int {
        return super.numProblems() + results.sumBy { it.numProblems() }
    }

    override fun fields(vararg fields: Field) {
        if (value == null) {
            return
        }

        //Make sure keys are strings
        val v = (value as Map<*, *>).mapKeys { it.key.toString() }

        fields.forEach {
            results.add(it.checkAgainst(v[it.name]))
        }

        val fieldNames = fields.map { it.name }

        extraFields.addAll(v.map { it.key }.filter { it !in fieldNames })
    }

    //This assumes fields is not an empty list (IE Empty Object)
    override fun getResult(): ValidatorResult {
        return MapValidatorResult(
                type = value.getType()?.name ?: "Unknown",
                value = when (results.isNotEmpty()) {
                    true -> results.associate { it.name to it }
                    false -> value
                },
                status = when {
                    hasProblems() -> "ERROR"
                    extraFields.isNotEmpty() && Config.extraFieldsCauseError -> "ERROR"
                    else -> "OK"
                },
                problems = problems,
                extraFields = extraFields
        )
    }

}