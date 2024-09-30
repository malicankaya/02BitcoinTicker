package com.malicankaya.a02bitcointicker.firebase

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.malicankaya.a02bitcointicker.BuildConfig
import java.util.UUID

class AuthManager {

    private fun googleSignIn(context: Context): GetCredentialRequest {
        val signInWithGoogleOption: GetSignInWithGoogleOption =
            GetSignInWithGoogleOption.Builder(
                BuildConfig.WEB_CLIENT_ID
            )
                .setNonce(UUID.randomUUID().toString())
                .build()

        val getCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        return getCredentialRequest
    }

    suspend fun googleSignInRequest(
        context: Context
    ): GoogleIdTokenCredential? {
        val getCredentialResponse = CredentialManager.create(context)
            .getCredential(context, googleSignIn(context))

        return handleSignIn(getCredentialResponse)
    }


    private fun handleSignIn(result: GetCredentialResponse): GoogleIdTokenCredential? {
        // Handle the successfully returned credential.
        val credential = result.credential

        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract id to validate and
                        // authenticate on your server.
                        return GoogleIdTokenCredential
                            .createFrom(credential.data)

                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized credential type here.
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }
        return null
    }
}