package com.project.fintrack
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context, name: String, mode: Int) {
        sharedPreferences = context.getSharedPreferences(name, mode)
    }
    var incomeList: List<String>?
        get() = Key.INCOME.getString()?.split(',')
        set(value) = Key.INCOME.setString("".plus(value?.get(0)))

    var expenseList: List<String>?
        get() = Key.EXPENSE.getString()?.split(',')
        set(value) = Key.EXPENSE.setString("".plus(value?.get(0)))

    private enum class Key {
        INCOME, EXPENSE;

        fun getBoolean(): Boolean? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getBoolean(
                name,
                false
            ) else null

        fun getFloat(): Float? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getFloat(name, 0f) else null

        fun getInt(): Int? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getInt(name, 0) else null

        fun getLong(): Long? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getLong(name, 0) else null

        fun getString(): String? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(
                name,
                ""
            ) else null

        fun setBoolean(value: Boolean?) =
            value?.let { sharedPreferences!!.edit { putBoolean(name, value) } } ?: remove()

        fun setFloat(value: Float?) =
            value?.let { sharedPreferences!!.edit { putFloat(name, value) } } ?: remove()

        fun setInt(value: Int?) =
            value?.let { sharedPreferences!!.edit { putInt(name, value) } } ?: remove()

        fun setLong(value: Long?) =
            value?.let { sharedPreferences!!.edit { putLong(name, value) } } ?: remove()

        fun setString(value: String?) =
            value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }
}