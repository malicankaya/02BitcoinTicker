package com.malicankaya.a02bitcointicker.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.malicankaya.a02bitcointicker.BuildConfig
import kotlinx.coroutines.launch
import java.util.UUID

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    }
