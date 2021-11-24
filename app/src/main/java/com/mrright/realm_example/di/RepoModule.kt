package com.mrright.realm_example.di

import com.mrright.realm_example.data.realm.repositories.RealmRepoImpl
import com.mrright.realm_example.domain.realm_repos.RealmRepository
import com.mrright.realm_example.models.Daos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.realm.Realm

@Module
@InstallIn(ViewModelComponent::class)
class RepoModule {

    @Provides
    @ViewModelScoped
    fun provideRealRepo(dao:Daos,realm: Realm) : RealmRepository = RealmRepoImpl(dao)

}