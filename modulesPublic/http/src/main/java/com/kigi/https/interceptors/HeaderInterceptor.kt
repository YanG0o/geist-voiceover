
import android.util.Log
import com.kigi.commonutil.SharePreferenceUtils
import com.kigi.https.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response
//todo
class HeaderInterceptor : Interceptor {

    private var token: String by SharePreferenceUtils(HttpConstant.TOKEN_KEY, "")
//    private val testToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOjUsImlhdCI6MTY3MDg1OTQ0OCwiZXhwIjoxNzMzOTMxNDQ4fQ.90h4m_wamVvmrRNLei_2etPnK2ezJLG3sOVQdneuV5c"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json; charset=utf-8")
            .addHeader("token",token).addHeader("Accept-Language", HttpConstant.UserLanguageHead)

        Log.d("addToken","${token}")


        val domain = request.url.host
        val url = request.url.toString()
        if (domain.isNotEmpty() && (url.contains(HttpConstant.COLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.UNCOLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.ARTICLE_WEBSITE)
                    || url.contains(HttpConstant.TODO_WEBSITE)
                    || url.contains(HttpConstant.COIN_WEBSITE))
        ) {
            val spDomain: String by SharePreferenceUtils(domain, "")
            val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()) {
                // 将 Cookie 添加到请求头
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }
        return chain.proceed(builder.build())
    }

}