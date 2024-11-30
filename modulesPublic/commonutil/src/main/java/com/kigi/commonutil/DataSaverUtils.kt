package com.kigi.commonutil

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.funny.data_saver.core.DataSaverInterface
import com.funny.data_saver.core.DataSaverLogger
import com.kigi.commonutil.DataSaverUtils.DataSaverUtils.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

 class DataSaverUtils(
    private val dataStore: DataStore<Preferences> = CommonBaseApp.context.prefs,
    senseExternalDataChange: Boolean = false
) : DataSaverInterface(senseExternalDataChange) {
    private val scope by lazy { CoroutineScope(Dispatchers.IO) }
    private val logger by lazy { DataSaverLogger("DataStorePreferences") }

    object DataSaverUtils {
        val Context.prefs: DataStore<Preferences> by preferencesDataStore(name = Constant.pre_file_name)
    }

    init {
        if (senseExternalDataChange) {
            scope.launch {
                dataStore.data.distinctUntilChanged().collect {
                    it.asMap().forEach { (key, value) ->
                        logger.d("$key -> $value is emitted")
                        externalDataChangedFlow?.tryEmit(key.name to value)
                    }
                }
            }
        }
    }

    override fun <T> readData(key: String, default: T): T {
        return runBlocking { get(dataStore, key, default) }
    }

    override fun <T> saveData(key: String, data: T) {
        runBlocking { put(dataStore, key, data) }
    }

    override suspend fun <T> saveDataAsync(key: String, data: T) = put(dataStore, key, data)

    override fun remove(key: String) {
        runBlocking {
            dataStore.edit {
                it.remove(intPreferencesKey(key))
            }
        }
    }

    private suspend fun <T> get(dataStore: DataStore<Preferences>, key: String, default: T): T {
        return when (default) {
            is Int -> {
                dataStore.data.map { setting ->
                    setting[intPreferencesKey(key)] ?: default
                }.first() as T
            }

            is Long -> {
                dataStore.data.map { setting ->
                    setting[longPreferencesKey(key)] ?: default
                }.first() as T
            }

            is Double -> {
                dataStore.data.map { setting ->
                    setting[doublePreferencesKey(key)] ?: default
                }.first() as T
            }

            is Float -> {
                dataStore.data.map { setting ->
                    setting[floatPreferencesKey(key)] ?: default
                }.first() as T
            }

            is Boolean -> {
                dataStore.data.map { setting ->
                    setting[booleanPreferencesKey(key)] ?: default
                }.first() as T
            }

            is String -> {
                dataStore.data.map { setting ->
                    setting[stringPreferencesKey(key)] ?: default
                }.first() as T
            }

            else -> deSerialization(dataStore.data.map { setting ->
                setting[stringPreferencesKey(key)] ?: ""
            }.first()) as T
        }
    }

    private suspend fun <T> put(dataStore: DataStore<Preferences>, key: String, value: T) {
        if (value == null) {
            remove(key)
            return
        }
        dataStore.edit { setting ->
            when (value) {
                is Int -> setting[intPreferencesKey(key)] = value
                is Long -> setting[longPreferencesKey(key)] = value
                is Double -> setting[doublePreferencesKey(key)] = value
                is Float -> setting[floatPreferencesKey(key)] = value
                is Boolean -> setting[booleanPreferencesKey(key)] = value
                is String -> setting[stringPreferencesKey(key)] = value
                else -> setting[stringPreferencesKey(key)] = serialize(value)
            }
        }
    }

    override fun contains(key: String): Boolean {
        return runBlocking {
            dataStore.data.map { setting ->
                setting.contains(intPreferencesKey(key))
            }.first()
        }
    }

    /**
     * 序列化对象
     * *
     * @return
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun <A> serialize(obj: A): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(
            byteArrayOutputStream
        )
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }

    /**
     * 反序列化对象

     * @param str
     * *
     * @return
     * *
     * @throws IOException
     * *
     * @throws ClassNotFoundException
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun <A> deSerialization(str: String): A {
        val redStr = java.net.URLDecoder.decode(str, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(
            redStr.toByteArray(charset("ISO-8859-1"))
        )
        val objectInputStream = ObjectInputStream(
            byteArrayInputStream
        )
        val obj = objectInputStream.readObject() as A
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }
}
val DefaultDataSaverPreferences by lazy(LazyThreadSafetyMode.PUBLICATION) {
    DataSaverUtils()
}