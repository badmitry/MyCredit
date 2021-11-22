package com.badmitry.vtbhackaton

class AccessToken {
    val accessToken: String? = null
    private var tokenType: String? = null
    fun getTokenType(): String? {
        tokenType?.let{
            if (!Character.isUpperCase(it[0])) {
                tokenType = Character
                    .toString(it[0])
                    .toUpperCase() + it.substring(1)
            }
        }
        return tokenType
    }
}