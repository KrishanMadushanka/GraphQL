package com.example.graphql.models

data class RepositoryOwner(
    val avatarUrl: String,
    val email: String,
    val followers: Followers,
    val following: Following,
    val login: String,
    val name: String,
    val pinnedItems: PinnedItems,
    val starredRepositories: StarredRepositories,
    val topRepositories: TopRepositories
)