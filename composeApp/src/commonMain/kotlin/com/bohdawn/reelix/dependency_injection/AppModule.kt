package com.bohdawn.reelix.dependency_injection

import com.bohdawn.reelix.viewmodels.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
}