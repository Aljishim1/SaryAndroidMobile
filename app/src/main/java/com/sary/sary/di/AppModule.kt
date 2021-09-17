package com.sary.sary.di

import com.sary.sary.ui.main.categories.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var categoriesViewModule = module {
    viewModel { CategoriesViewModel() }
}
