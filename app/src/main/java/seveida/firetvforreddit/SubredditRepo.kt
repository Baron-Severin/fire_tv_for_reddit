package seveida.firetvforreddit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import seveida.firetvforreddit.domain.objects.SubredditDetails
import seveida.firetvforreddit.response.objects.SubredditResponse

class SubredditRepo internal constructor(retrofit: Retrofit) {

    private val api: SubredditApi

    init {
        api = retrofit.create(SubredditApi::class.java)
    }

    fun getSubreddit(subreddit: String): Observable<SubredditDetails> {
        return api.getSubreddit(subreddit)
            .map { response -> SubredditDetails.fromResponse(response) }
    }
}

internal interface SubredditApi {
    @GET("/r/{sub}.json")
    fun getSubreddit(@Path("sub") subreddit: String): Observable<SubredditResponse>
}

