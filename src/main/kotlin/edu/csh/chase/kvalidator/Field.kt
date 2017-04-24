package edu.csh.chase.kvalidator

import edu.csh.chase.kvalidator.types.Type
import edu.csh.chase.kvalidator.validators.CommonValidator

class Field(required: Boolean, type: Type, val name: String, check: ((CommonValidator) -> Unit)?) : Element(required, type, check)