package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.*
import java.util.*

open class ListValidator(required: Boolean, value: Any?) : CommonValidator(required, Types.list, value) {

    private val results = ArrayList<ValidatorResult>(lst.size)

    private val lst
        get() = value as List<*>

    override fun numProblems(): Int {
        return super.numProblems() + results.sumBy { it.problemCount() }
    }

    override fun elements(type: Element) {
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
                    hasProblems() -> ValidatorStatus.ERROR
                    else -> ValidatorStatus.OK
                }
        )
    }

}