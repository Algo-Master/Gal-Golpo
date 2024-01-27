package com.example.galgolpo.Hilt_Setup

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class HiltModule {
    @Provides
    fun provideAuthentication(): FirebaseAuth = Firebase.auth
}