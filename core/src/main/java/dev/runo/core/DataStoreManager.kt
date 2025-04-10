package dev.runo.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore("remediaAppPrefs")

// TODO: crypt api key
class DataStoreManager @Inject constructor(private val context: Context) {

    private val apiKeyPref = stringPreferencesKey("API_KEY")

    suspend fun updateApiKey(apiKey: String) {
        context.dataStore.edit {
            it[apiKeyPref] = apiKey
        }
    }

    fun getApiKey(): String {
        return runBlocking(Dispatchers.IO) {
            context.dataStore.data.map { it[apiKeyPref] }.firstOrNull() ?: ""
        }
    }

}