package com.jogayed.currencyconverter.app_core.ext

import kotlin.reflect.full.memberProperties

/**
 * Convert the object to map of it's properties
 */
inline fun <reified T : Any> T.asMap(): Map<String, Any?> {
    val props = T::class.memberProperties.associateBy { it.name }
    return props.keys.associateWith { props[it]?.get(this) }
}