package com.example.favorites

import android.content.Context
import com.example.made.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}