package baron.severin.domain_objects

import android.net.Uri

data class SubredditMetadata(val urlPath: Uri, val avatarImage: Uri,
                        val subredditId: String, val displayName: String)
