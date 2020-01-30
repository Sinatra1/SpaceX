package com.vladislav.shumilov.core_domain.helpers

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

private const val MIN_PHONE_LENGTH = 11
private const val ONLY_DIGITS_REGEXP = "[^\\d]"

class PhoneHelper {

    private val onlyDigits = Regex(ONLY_DIGITS_REGEXP)
    private val phoneUtil = PhoneNumberUtil.getInstance()

    fun isValid(phoneNumber: String): Boolean {
        if (phoneNumber.length < MIN_PHONE_LENGTH) {
            return false
        }

        val phone = onlyDigits.replace(phoneNumber, "")

        val phoneNumber = Phonenumber.PhoneNumber()
        phoneNumber.countryCode = phone[0].toInt()
        phoneNumber.nationalNumber = phone.substring(1).toLong()

        return phoneUtil.isValidNumber(phoneNumber)
    }
}