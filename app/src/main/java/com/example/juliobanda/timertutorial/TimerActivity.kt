package com.example.juliobanda.timertutorial

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.juliobanda.timertutorial.Util.PrefUtil
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.android.synthetic.main.content_timer.*

class TimerActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 10
    private var timerState: TimerState = TimerState.Stopped

    private var secondsRemaining = 0L

    val startClickListener = View.OnClickListener {
        this.startTimer()
        timerState = TimerState.Running
        updateButtons()
    }

    val pauseClickListener = View.OnClickListener {
        this.timer.cancel()
        timerState = TimerState.Paused
        updateButtons()
    }

    val cancelClickListener = View.OnClickListener {
        this.timer.cancel()
        this.onTimerFinish()
        timerState = TimerState.Stopped
    }

    // MARK: - Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        this.configureActionBar()

        fab_start.setOnClickListener(startClickListener)
        fab_pause.setOnClickListener(pauseClickListener)
        fab_stop.setOnClickListener(cancelClickListener)

    }

    override fun onResume() {
        super.onResume()

        initTimer()
        // TODO: Remove background timer, hide notification

    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running) {
            timer.cancel()
            // TODO: Start background timer and show notification
        } else if (timerState == TimerState.Paused) {
            // TODO: Show notification
        }

        PrefUtil.setPreviousTimerLengthSeconds(this, timerLengthSeconds)
        PrefUtil.setSecondsRemainig(this, secondsRemaining)
        PrefUtil.setTimerState(this, timerState)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_timer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initTimer() {
//        timerState = PrefUtil.getTimerState(this)

        if (timerState == TimerState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }
        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemainig(this)
        else
            timerLengthSeconds

        // TODO: Change secondsRemaining according to where the background timer stopped

        // TODO: Resume where we left off
        if(timerState == TimerState.Running) {
            startTimer()
        }

        updateButtons()
        updateCountdownUI()

    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object: CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinish()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }

        } .start()

    }

    private fun setNewTimerLength(){
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLegthSeconds(this)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondInMinuteUntilFinish = secondsRemaining - minutesUntilFinished * 60
        val secondsString = secondInMinuteUntilFinish.toString()

        textView_countdown.text = "$minutesUntilFinished:${
        if(secondsString.length == 2) secondsString
        else "0" + secondsString}"

        progress_countdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons() {
        when(timerState) {
            TimerState.Running -> {
                fab_start.isEnabled = false
                fab_pause.isEnabled = true
                fab_stop.isEnabled = true
            }

            TimerState.Paused -> {
                fab_start.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = false
            }

            TimerState.Stopped -> {
                fab_start.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = true
            }
        }
    }

    private fun onTimerFinish() {

    }

    private fun configureActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer_white)
        supportActionBar?.title = resources.getString(R.string.timer)
    }

    enum class TimerState {
        Stopped, Paused, Running
    }
}
