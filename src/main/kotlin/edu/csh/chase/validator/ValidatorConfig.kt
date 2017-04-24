package edu.csh.chase.validator

object ValidatorConfig {

    /**
     * When true any map with extra fields will fail validation
     */
    var extraFieldsCauseError = false

    /**
     * When true any bad input on the validators side will throw an exception
     *
     * example
     * required(Types.int, "num") {
     *    gte("hello")
     * }
     */
    var errorOnBadInput = false

    /**
     * Sets all config options back to their default values
     */
    fun reset() {
        extraFieldsCauseError = false
        errorOnBadInput = false
    }
}