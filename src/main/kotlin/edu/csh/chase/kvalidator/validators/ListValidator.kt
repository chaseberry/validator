package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.Field
import edu.csh.chase.kvalidator.Types
import edu.csh.chase.kvalidator.getType
import java.util.*

open class ListValidator(required: Boolean, name: String, value: Any?) : CommonValidator(required, Types.list, name, value) {

    private val results = ArrayList<CommonValidator>(lst.size)

    private val lst
        get() = value as List<*>

    override fun numProblems(): Int {
        return super.numProblems() + results.sumBy { it.numProblems() }
    }

    override fun elements(type: Field) {
        if (value == null) {
            return
        }

        lst.forEach {
            results.add(type.checkAgainst(it))
        }
    }

    override fun getResult(): ValidatorResult {
        return ValidatorResult(
                type = value.getType()?.name ?: "Unknown",
                value = when {
                    results.isNotEmpty() -> results
                    else -> value
                },
                problems = problems,
                status = when {
                    hasProblems() -> "ERROR"
                    else -> "OK"
                }
        )
    }

}