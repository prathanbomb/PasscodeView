package th.co.digio.passcodeview

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.TransitionDrawable
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import th.co.digio.passcodeview.databinding.PassCodeLayoutBinding

/**
* Created by prathanbomb on 3/30/2017 AD.
*/

class PassCodeView : LinearLayout, View.OnClickListener, View.OnTouchListener {

    private var binding: PassCodeLayoutBinding? = null
    private var rect: Rect? = null
    private var pin = ""
    var digitsSize: Int = 0
        private set
    private var digitsChangeListener: DigitsChangeListener? = null

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = PassCodeLayoutBinding.inflate(inflater, this, true)
        val values = context.theme.obtainStyledAttributes(attrs, R.styleable.PassCodeView, defStyleAttr, defStyleRes)
        try {
            digitsSize = values.getInteger(R.styleable.PassCodeView_digits, 4)
            setDigitSize()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

        setOnTouchListener()
        setOnClickListener()
    }

    private fun setOnTouchListener() {
        binding!!.btnNum1.setOnTouchListener(this)
        binding!!.btnNum2.setOnTouchListener(this)
        binding!!.btnNum3.setOnTouchListener(this)
        binding!!.btnNum4.setOnTouchListener(this)
        binding!!.btnNum5.setOnTouchListener(this)
        binding!!.btnNum6.setOnTouchListener(this)
        binding!!.btnNum7.setOnTouchListener(this)
        binding!!.btnNum8.setOnTouchListener(this)
        binding!!.btnNum9.setOnTouchListener(this)
        binding!!.btnFingerPrint.setOnTouchListener(this)
        binding!!.btnNum0.setOnTouchListener(this)
        binding!!.btnBackSpace.setOnTouchListener(this)
    }

    private fun setOnClickListener() {
        binding!!.btnNum1.setOnClickListener(this)
        binding!!.btnNum2.setOnClickListener(this)
        binding!!.btnNum3.setOnClickListener(this)
        binding!!.btnNum4.setOnClickListener(this)
        binding!!.btnNum5.setOnClickListener(this)
        binding!!.btnNum6.setOnClickListener(this)
        binding!!.btnNum7.setOnClickListener(this)
        binding!!.btnNum8.setOnClickListener(this)
        binding!!.btnNum9.setOnClickListener(this)
        binding!!.btnFingerPrint.setOnClickListener(this)
        binding!!.btnNum0.setOnClickListener(this)
        binding!!.btnBackSpace.setOnClickListener(this)
    }

    @SuppressLint("NewApi")
    private fun setButtonActionDown(button: Button) {
        rect = Rect(button.left, button.top, button.right, button.bottom)
        button.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        button.background = ContextCompat.getDrawable(context, R.drawable.bg_pin_button_pressed)
    }

    @SuppressLint("NewApi")
    private fun setButtonActionUp(button: Button) {
        val animator = ObjectAnimator.ofInt(button, "textColor", ContextCompat.getColor(context, R.color.colorWhite), ContextCompat.getColor(context, R.color.colorPrimaryLight)).setDuration(300)
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_action_up) as TransitionDrawable
        button.background = drawable
        drawable.startTransition(300)
        animator.setEvaluator(ArgbEvaluator())
        animator.start()
    }

    @SuppressLint("NewApi")
    private fun setImageButtonActionDown(imageButton: ImageButton) {
        rect = Rect(imageButton.left, imageButton.top, imageButton.right, imageButton.bottom)
        imageButton.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite))
        imageButton.background = ContextCompat.getDrawable(context, R.drawable.bg_pin_button_pressed)
    }

    @SuppressLint("NewApi")
    private fun setImageButtonActionUp(imageButton: ImageButton) {
        val animator = ObjectAnimator.ofInt(imageButton, "colorFilter", ContextCompat.getColor(context, R.color.colorWhite), ContextCompat.getColor(context, R.color.colorPrimaryLight)).setDuration(300)
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_action_up) as TransitionDrawable
        imageButton.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryLight))
        imageButton.background = drawable
        val scale = resources.displayMetrics.density
        val dp = (20 * scale + 0.5f).toInt()
        imageButton.setPadding(dp, dp, dp, dp)
        drawable.startTransition(300)
        animator.setEvaluator(ArgbEvaluator())
        animator.start()
    }

    private fun setDigitSize() {
        if (digitsSize > 0) {
            binding!!.dotPin1.visibility = View.VISIBLE
        } else {
            binding!!.dotPin1.visibility = View.GONE
        }
        if (digitsSize > 1) {
            binding!!.dotPin2.visibility = View.VISIBLE
        } else {
            binding!!.dotPin2.visibility = View.GONE
        }
        if (digitsSize > 2) {
            binding!!.dotPin3.visibility = View.VISIBLE
        } else {
            binding!!.dotPin3.visibility = View.GONE
        }
        if (digitsSize > 3) {
            binding!!.dotPin4.visibility = View.VISIBLE
        } else {
            binding!!.dotPin4.visibility = View.GONE
        }
        if (digitsSize > 4) {
            binding!!.dotPin5.visibility = View.VISIBLE
        } else {
            binding!!.dotPin5.visibility = View.GONE
        }
        if (digitsSize > 5) {
            binding!!.dotPin6.visibility = View.VISIBLE
        } else {
            binding!!.dotPin6.visibility = View.GONE
        }
    }

    private fun updateDrawable() {
        if (pin.isNotEmpty()) {
            binding!!.dotPin1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_filled))
        } else {
            binding!!.dotPin1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_not_filled))
        }
        if (pin.length > 1) {
            binding!!.dotPin2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_filled))
        } else {
            binding!!.dotPin2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_not_filled))
        }
        if (pin.length > 2) {
            binding!!.dotPin3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_filled))
        } else {
            binding!!.dotPin3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_not_filled))
        }
        if (pin.length > 3) {
            binding!!.dotPin4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_filled))
        } else {
            binding!!.dotPin4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_not_filled))
        }
        if (pin.length > 4) {
            binding!!.dotPin5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_filled))
        } else {
            binding!!.dotPin5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_not_filled))
        }
        if (pin.length > 5) {
            binding!!.dotPin6.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_filled))
        } else {
            binding!!.dotPin6.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_pin_not_filled))
        }
    }

    fun clear() {
        pin = ""
        updateDrawable()
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            handleTouchDown(v)
        } else if (event.action == MotionEvent.ACTION_UP && !rect!!.isEmpty) {
            handleRelease(v)
        }
        if (event.action == MotionEvent.ACTION_MOVE && !rect!!.contains(v.left + event.x.toInt(), v.top + event.y.toInt()) && !rect!!.isEmpty) {
            if (v == binding!!.btnFingerPrint || v == binding!!.btnBackSpace) {
                setImageButtonActionUp(v as ImageButton)
            } else {
                setButtonActionUp(v as Button)
            }
            rect!!.setEmpty()
        }
        return false
    }

    private fun handleRelease(v: View) {
        when (v) {
            binding!!.btnNum1 -> setButtonActionUp(binding!!.btnNum1)
            binding!!.btnNum2 -> setButtonActionUp(binding!!.btnNum2)
            binding!!.btnNum3 -> setButtonActionUp(binding!!.btnNum3)
            binding!!.btnNum4 -> setButtonActionUp(binding!!.btnNum4)
            binding!!.btnNum5 -> setButtonActionUp(binding!!.btnNum5)
            binding!!.btnNum6 -> setButtonActionUp(binding!!.btnNum6)
            binding!!.btnNum7 -> setButtonActionUp(binding!!.btnNum7)
            binding!!.btnNum8 -> setButtonActionUp(binding!!.btnNum8)
            binding!!.btnNum9 -> setButtonActionUp(binding!!.btnNum9)
            binding!!.btnFingerPrint -> setImageButtonActionUp(binding!!.btnFingerPrint)
            binding!!.btnNum0 -> setButtonActionUp(binding!!.btnNum0)
            binding!!.btnBackSpace -> setImageButtonActionUp(binding!!.btnBackSpace)
        }
    }

    private fun handleTouchDown(v: View) {
        when (v) {
            binding!!.btnNum1 -> setButtonActionDown(binding!!.btnNum1)
            binding!!.btnNum2 -> setButtonActionDown(binding!!.btnNum2)
            binding!!.btnNum3 -> setButtonActionDown(binding!!.btnNum3)
            binding!!.btnNum4 -> setButtonActionDown(binding!!.btnNum4)
            binding!!.btnNum5 -> setButtonActionDown(binding!!.btnNum5)
            binding!!.btnNum6 -> setButtonActionDown(binding!!.btnNum6)
            binding!!.btnNum7 -> setButtonActionDown(binding!!.btnNum7)
            binding!!.btnNum8 -> setButtonActionDown(binding!!.btnNum8)
            binding!!.btnNum9 -> setButtonActionDown(binding!!.btnNum9)
            binding!!.btnFingerPrint -> setImageButtonActionDown(binding!!.btnFingerPrint)
            binding!!.btnNum0 -> setButtonActionDown(binding!!.btnNum0)
            binding!!.btnBackSpace -> setImageButtonActionDown(binding!!.btnBackSpace)
        }
    }

    override fun onClick(v: View) {
        if (pin.length < digitsSize) {
            if (v == binding!!.btnNum1) {
                pin += "1"
            } else if (v == binding!!.btnNum2) {
                pin += "2"
            } else if (v == binding!!.btnNum3) {
                pin += "3"
            } else if (v == binding!!.btnNum4) {
                pin += "4"
            } else if (v == binding!!.btnNum5) {
                pin += "5"
            } else if (v == binding!!.btnNum6) {
                pin += "6"
            } else if (v == binding!!.btnNum7) {
                pin += "7"
            } else if (v == binding!!.btnNum8) {
                pin += "8"
            } else if (v == binding!!.btnNum9) {
                pin += "9"
            } else if (v == binding!!.btnNum0) {
                pin += "0"
            } else if (v == binding!!.btnBackSpace && !pin.isEmpty()) {
                pin = pin.substring(0, pin.length - 1)
            }
        }
        updateDrawable()
        notifyListener()
    }

    interface DigitsChangeListener {
        fun onDigitChangeListener(pin: String)
    }

    fun setOnDigitsChangeListener(listener: DigitsChangeListener) {
        this.digitsChangeListener = listener
    }

    private fun notifyListener() {
        if (digitsChangeListener != null) {
            digitsChangeListener!!.onDigitChangeListener(pin)
        }
    }

}
