package eu.seijindemon.student_iee_ihu.utils

import com.tencent.mmkv.MMKV

class LoadLanguage {
    companion object {
        fun loadLanguage(): String {
            val kv = MMKV.mmkvWithID("languageMode")

            when (kv?.decodeString("string")) {
                "el" -> {
                    return "el"
                }
                "en" -> {
                    return "en"
                }
            }
            return "el"
        }
    }


}