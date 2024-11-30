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
import com.kigi.commonutil.CommonBaseApp.Companion.context
import com.kigi.commonutil.DataSaverPreference.DataSaverPreference.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.reflect.KProperty

class DataSaverPreference<T>(
    private val key: String,
    private val default: T,
) {
    private val scope by lazy { CoroutineScope(Dispatchers.IO) }

    object DataSaverPreference {
        val Context.prefs: DataStore<Preferences> by preferencesDataStore(name = Constant.pre_file_name)
    }

    /**
     * 保存数据
     * */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        scope.launch {
            with(context.prefs) {
                edit { prefs ->
                    when (value) {
                        is Long -> prefs[longPreferencesKey(key)] = value
                        is String -> prefs[stringPreferencesKey(key)] = value
                        is Int -> prefs[intPreferencesKey(key)] = value
                        is Boolean -> prefs[booleanPreferencesKey(key)] = value
                        is Float -> prefs[floatPreferencesKey(key)] = value
                        is Double -> prefs[doublePreferencesKey(key)] = value
                        else -> prefs[stringPreferencesKey(key)] = serialize(value)
                    }
                }
            }
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return runBlocking { getPreferences(key, default).first() }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getPreferences(key: String, default: T): Flow<T> = with(context.prefs) {
        return data.map {
            when (default) {
                is Long -> it[longPreferencesKey(key)] ?: default
                is String -> it[stringPreferencesKey(key)] ?: default
                is Int -> it[intPreferencesKey(key)] ?: default
                is Boolean -> it[booleanPreferencesKey(key)] ?: default
                is Float -> it[floatPreferencesKey(key)] ?: default
                is Double -> it[doublePreferencesKey(key)] ?: default
                else -> deSerialization(it[stringPreferencesKey(key)] ?: "")
            }
        } as Flow<T>
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