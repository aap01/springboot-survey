package com.aap.springboot_skeleton.exception

import com.aap.springboot_skeleton.util.Resources
import java.lang.RuntimeException

class UnAuthorizedException (override val message: String = Resources.invalidToken): RuntimeException()