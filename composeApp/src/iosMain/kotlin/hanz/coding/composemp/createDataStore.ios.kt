package hanz.coding.composemp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import hanz.coding.composemp.datastore.DATA_STORE_FILE_NAME

fun createDataStore(): DataStore<Preferences> {
    return hanz.coding.composemp.datastore.createDataStore {
        DATA_STORE_FILE_NAME
    }
}