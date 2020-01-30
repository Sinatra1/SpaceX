package com.vladislav.shumilov.core_data.widgets

import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.vladislav.shumilov.core_data.R
import com.vladislav.shumilov.core_domain.helpers.PhoneHelper
import com.vladislav.shumilov.core_domain.utils.hasFlag
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

private const val DELAY = 1000L

class FormattedEditText(
    context: Context,
    attrs: AttributeSet
) : AppCompatEditText(context, attrs) {

    private val phoneHelper = PhoneHelper()
    private val querySubject = PublishSubject.create<String>()

    init {
        if (inputType hasFlag InputType.TYPE_CLASS_PHONE) {
            initPhoneEditText()
        } else if (inputType hasFlag InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS) {

        }
    }

    private fun initPhoneEditText() {
        hint = context.getString(R.string.mobile_phone)

        addTextChangedListener(PhoneNumberFormattingTextWatcher())

        setPhoneValidation()

        setPhoneInputListener()
    }

    private fun setPhoneValidation() {
        querySubject
            .debounce(DELAY, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .switchMap {
                Observable.just(phoneHelper.isValid(it))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (!it) {
                    error = context.getString(R.string.error_phone_invalid)
                } else if (it) {
                    error = null
                }
            }
    }

    private fun setPhoneInputListener() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                querySubject.onNext(s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

}