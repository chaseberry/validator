package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.validators.CommonValidator

class NamedField(required: Boolean, type: Type, val name: String, check: ((CommonValidator) -> Unit)?) : Field(required, type, check)