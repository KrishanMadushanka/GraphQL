package com.example.graphql.models

data class Node(
    val description: String,
    val name: String,
    val owner: Owner,
    val primaryLanguage: PrimaryLanguage,
    val stargazerCount: Int
)