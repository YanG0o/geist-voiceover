package com.kigi.https

import com.kigi.commonutil.SharePreferenceUtils


object HttpConstant {
    const val DEFAULT_TIMEOUT: Long = 15

    const val TestEnvKey = "TestEnvKey"

    val testEnvMap = mutableMapOf(
        pairs = arrayOf(
            Pair("develop", "https://mengmeng.meta1983.com"),
            Pair("develop-a", "https://mengmeng-a.meta1983.com"),
            Pair("develop-b", "https://mengmeng-b.meta1983.com"),
//            Pair("develop-c", "https://mengmeng-c.meta1983.com"),
            Pair("develop-stage", "https://mengmeng-stage.meta1983.com"),
        )
    )
    var nowTestEnv: String by SharePreferenceUtils(TestEnvKey, "develop")
    var BASE_URL: String = "https://api.mengmeng.app"

    const val COLLECTIONS_WEBSITE = ""
    const val UNCOLLECTIONS_WEBSITE = ""
    const val ARTICLE_WEBSITE = "article"
    const val TODO_WEBSITE = "lg/todo"
    const val COIN_WEBSITE = "lg/coin"
    const val TOKEN_KEY = "token"
    const val SHOP_ID = "SHOP_ID"
    const val SHOP_INFO = "SHOP_INFO"
    const val COOKIE_NAME = "Cookie"
    const val SET_COOKIE_KEY = "set-cookie"
    const val SAVE_USER_LOGIN_KEY = "user/login"
    const val SAVE_USER_REGISTER_KEY = "user/register"

    var UserLanguageHead = "zh_CN"


    fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
            .map { cookie ->
                cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            }
            .forEach {
                it.filterNot { set.contains(it) }.forEach { set.add(it) }
            }
        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }

    fun saveCookie(url: String?, domain: String?, cookies: String) {
        url ?: return
        var spUrl: String by SharePreferenceUtils(url, cookies)
        @Suppress("UNUSED_VALUE")
        spUrl = cookies
        domain ?: return
        var spDomain: String by SharePreferenceUtils(domain, cookies)
        @Suppress("UNUSED_VALUE")
        spDomain = cookies
    }
}