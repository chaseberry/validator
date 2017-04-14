package edu.csh.chase.kvalidator

sealed class Validation(val result: Result)

class Valid(result: Result) : Validation(result)

class Invalid(result: Result) : Validation(result)