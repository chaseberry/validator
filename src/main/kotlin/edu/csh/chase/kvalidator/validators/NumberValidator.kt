package edu.csh.chase.kvalidator.validators

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.add
import edu.csh.chase.kvalidator.inputError

open class NumberValidator(required: Boolean, type: Type, value: Any?) : CommonValidator(required, type, value) {

    override fun gte(i: Any) {
        val comp = test("gte", i)

        if (comp == -1) {
            problems.add("gte", "$value >= $i")
        }
    }

    override fun gt(i: Any) {
        val comp = test("gt", i)

        if (comp == -1 || comp == 0) {
            problems.add("gt", "$value > $i")
        }
    }

    override fun lte(i: Any) {
        val comp = test("lte", i)
        if (comp == 1) {
            problems.add("lte", "$value <= $i")
        }
    }

    override fun lt(i: Any) {
        val comp = test("lt", i)
        if (comp == 1 || comp == 0) {
            problems.add("lt", "$value < $i")
        }
    }

    private fun test(test: String, i: Any): Int {
        if (value == null) {
            return -2
        }

        if (i !is Number) {
            inputError("$i is not a Number in test: $test")
            return -2
        }

        if (value !is Number) {
            problems.add(test, "Number")
        }

        val v = value as Number
        val comp = compare(v, i)
        if (comp == -2) {
            problems.add(test, "Number")
        }

        return comp
    }

    private fun compare(n1: Number, n2: Number): Int {
        return when (n1) {
            is Int -> n1.compareTo(n2.toInt())
            is Double -> n1.compareTo(n2.toDouble())
            is Float -> n1.compareTo(n2.toFloat())
            is Long -> n1.compareTo(n2.toLong())
            else -> -2
        }
    }


}