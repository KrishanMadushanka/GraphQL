package com.example.graphql

object Constants {
    const val TOKEN = "Bearer 959873295ac82b7ae44647d0838865264d88f7bc"
    const val QUERY =
        "query{repositoryOwner(login: \"sindresorhus\") { login ... on User { pinnedItems(first: 3) { nodes { ... on Repository { name description stargazerCount primaryLanguage { name } owner { avatarUrl login } } } } topRepositories(first: 10, orderBy: {field: NAME, direction: ASC}) { edges { node { name description stargazerCount primaryLanguage { name } owner { avatarUrl login } } } } email name avatarUrl starredRepositories { totalCount } followers { totalCount } following { totalCount } } }}"
}