package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.validators.CommonValidator
import edu.csh.chase.kvalidator.validators.ValidatorResult
import java.util.*

fun Map<String, *>.validate(vararg fields: Field): Validation {

    val fieldKeys = fields.map { it.name }

    val fieldResults = ArrayList<ValidatorResult>()

    for (it in fields) {
        val v = this[it.name]

        val result = it.checkAgainst(v)

        fieldResults.add(result)
    }

    val extraKeys = ArrayList<String>(this.keys.filter { it !in fieldKeys })

    val result = Result(extraKeys, fieldResults)

    return when {
        result.numProblems == 0 -> Valid(result)
        else -> Invalid(result)
    }
}

fun Map<String, *>.isValid(vararg fields: Field): Boolean {
    return validate(*fields) is Valid
}

fun Any?.getType(): Type? {

    return Types.find(this)

}

fun ArrayList<Problem>.add(test: String, expected: Any?) {
    add(Problem(test, expected))
}

fun required(type: Type, name: String, check: (CommonValidator.() -> Unit)? = null): Field {
    return Field(true, type, name, check)
}

fun optional(type: Type, name: String, check: (CommonValidator.() -> Unit)? = null): Field {
    return Field(false, type, name, check)
}

fun inputError(msg: String) {
    if (Config.errorOnBadInput) {
        throw edu.csh.chase.kvalidator.BadInputException(msg)
    }
}
