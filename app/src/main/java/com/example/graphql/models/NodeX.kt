package com.example.graphql.models

data class NodeX(
    val description: String,
    val name: String,
    val owner: OwnerX,
    val primaryLanguage: PrimaryLanguageX,
    val stargazerCount: Int
)