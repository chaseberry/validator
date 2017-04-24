package edu.csh.chase.validator

import edu.csh.chase.validator.types.Type
import edu.csh.chase.validator.validators.CommonValidator

class Field(required: Boolean, type: Type, val name: String, check: ((CommonValidator) -> Unit)?) : Element(required, type, check)