package com.conceptic.andcourse.presentation.ext

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import kotlin.reflect.KClass

inline fun <reified T : Fragment> T.createScope(scopeId: String): Scope {
    return this.getKoin().createScope(scopeId, named<T>())
}