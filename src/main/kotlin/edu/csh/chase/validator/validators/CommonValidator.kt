package edu.csh.chase.validator.validators

import edu.csh.chase.validator.*
import edu.csh.chase.validator.types.Type
import java.util.*

open class CommonValidator(val required: Boolean, val type: Type, val value: Any?) {

    protected val problems = ArrayList<Problem>()

    init {
        if (value == null && required) {
            problems.add("null", "not null")
        }

        if (!type.isType(value) && !(required && this.value == null)) {
            problems.add("type", type.name)
        }
    }

    fun eq(value: Any?) {
        if (required && this.value == null) {
            return
        }

        if (this.value != value) {
            problems.add(Problem("eq", value))
        }
    }

    fun `in`(vararg elements: Any?) {
        if (required && value == null) {
            return
        }

        if (value !in elements) {
            problems.add(Problem("in", elements.toList()))
        }
    }

    fun notIn(vararg elements: Any?) {
        if (required && value == null) {
            return
        }

        if (value in elements) {
            problems.add(Problem("nin", elements.toList()))
        }
    }

    fun custom(check: (Any?) -> Problem?) {
        val i = check(value)
        if (i != null) {
            problems.add(i)
        }
    }

    open fun gte(i: Any) {
        inputError("calling gte on incomparable types")
    }

    open fun lte(i: Any) {
        inputError("calling lte on incomparable types")
    }

    open fun gt(i: Any) {
        inputError("calling gt on incomparable types")
    }

    open fun lt(i: Any) {
        inputError("calling lt on incomparable types")
    }

    open fun fields(vararg fields: Field) {
        inputError("calling fields on incomparable types")
    }

    open fun elements(type: Element) {
        inputError("calling elements on incomparable types")
    }

    fun hasProblems() = numProblems() != 0

    open fun numProblems() = problems.size

    open fun getResult(): ValidatorResult {
        return ValidatorResult(
                type = value.getType()?.name ?: "Unknown",
                value = value,
                status = when (hasProblems()) {
                    true -> ValidatorStatus.ERROR
                    false -> ValidatorStatus.OK
                },
                problems = problems
        )
    }
}