package edu.csh.chase.kvalidator


import edu.csh.chase.kvalidator.types.*
import java.util.*

object Types {

    val boolean = BooleanType()

    val int = IntType()

    val double = DoubleType()

    val list = ListType()

    val map = MapType()

    val long = LongType()

    val float = FloatType()

    val string = StringType()

    private val simpleTypes = listOf(
            boolean,
            int,
            double,
            list,
            map,
            long,
            float,
            string
    )

    private val types = ArrayList<Type>()

    fun find(value: Any?): Type? {
        return simpleTypes.find { it.isType(value) } ?: types.find { it.isType(value) }
    }

    fun register(type: Type) {
        //Prevent re-registering a simple type
        if (type in simpleTypes) {
            return
        }

        //Prevent registering the same type twice
        if (type in types) {
            return
        }

        types.add(type)
    }

}