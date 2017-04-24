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

    open fun gte(i: Any) {}

    open fun lte(i: Any) {}

    open fun gt(i: Any) {}

    open fun lt(i: Any) {}

    open fun fields(vararg fields: Field) {}

    open fun elements(type: Element) {}

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