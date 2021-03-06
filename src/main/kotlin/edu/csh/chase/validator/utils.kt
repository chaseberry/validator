package edu.csh.chase.validator

import edu.csh.chase.validator.types.Type
import edu.csh.chase.validator.validators.CommonValidator
import edu.csh.chase.validator.ValidatorResult
import java.util.*

fun Map<String, *>.validate(vararg fields: Field): Result {

    val fieldKeys = fields.map { it.name }

    val results = fields.associate {
        val v = this[it.name]

        it.name to it.checkAgainst(v)
    }

    val extraKeys = ArrayList<String>(this.keys.filter { it !in fieldKeys })

    val result = Result(extraKeys, results)

    return result
}

fun Map<String, *>.validate(vararg fields: Pair<String, Element>): Result {
    return this.validate(fields.associate { it.first to it.second })
}

fun Map<String, *>.validate(map: Map<String, Element>): Result {
    val fieldKeys = map.keys

    val results = map.mapValues {
        val v = this[it.key]

        it.value.checkAgainst(v)
    }

    val extraKeys = ArrayList<String>(this.keys.filter { it !in fieldKeys })

    return Result(extraKeys, results)
}

fun Map<String, *>.isValid(vararg fields: Field): Boolean {
    return validate(*fields).status == ValidatorStatus.OK
}

fun Any?.getType(): Type? {
    return Types.find(this)
}

fun ArrayList<Problem>.add(test: String, expected: Any?) {
    add(Problem(test, expected))
}

fun required(type: Type, check: (CommonValidator.() -> Unit)? = null): Element {
    return Element(true, type, check)
}

fun optional(type: Type, check: (CommonValidator.() -> Unit)? = null): Element {
    return Element(false, type, check)
}

fun required(type: Type, name: String, check: (CommonValidator.() -> Unit)? = null): Field {
    return Field(true, type, name, check)
}

fun optional(type: Type, name: String, check: (CommonValidator.() -> Unit)? = null): Field {
    return Field(false, type, name, check)
}

fun inputError(msg: String) {
    if (ValidatorConfig.errorOnBadInput) {
        throw edu.csh.chase.validator.BadInputException(msg)
    }
}
