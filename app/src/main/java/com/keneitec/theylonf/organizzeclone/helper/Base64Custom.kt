package com.keneitec.theylonf.organizzeclone.helper

import android.util.Base64

class Base64Custom {
    companion object {
        fun codifyBase64(str: String): String {
            return Base64.encodeToString(str.toByteArray(), Base64.DEFAULT)
                .replace("\n", "")
                .replace("\r", "")
        }

        fun decodifyBase64(str: String): String {
            return Base64.decode(str, Base64.DEFAULT).toString()
        }
    }
}
