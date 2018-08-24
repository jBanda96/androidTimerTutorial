package com.example.juliobanda.timertutorial.Util

import android.content.Context
import android.preference.PreferenceManager
import com.example.juliobanda.timertutorial.TimerActivity

class PrefUtil {

    companion object {

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "com.example.juliobanda.previus_timer_length"
        private const val TIMER_STATE_ID = "com.example.juliobanda.timer_state"
        private const val SECONDS_REMAINING = "com.example.juliobanda.seconds_remaining"

        fun getTimerLength(context: Context): Int {
            // Placeholder
            return 1
        }

        fun getPreviousTimerLegthSeconds(context: Context): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(context: Context, seconds: Long) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        fun getTimerState(context: Context): TimerActivity.TimerState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return TimerActivity.TimerState.values()[ordinal]
        }

        fun setTimerState(context: Context, state: TimerActivity.TimerState) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal= state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        fun getSecondsRemainig(context: Context): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING, 0)
        }

        fun setSecondsRemainig(context: Context, seconds: Long) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING, seconds)
            editor.apply()
        }

    }

}