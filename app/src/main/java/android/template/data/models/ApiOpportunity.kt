package android.template.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiOpportunity(
    @Json(name="id") val id: Long = 0L,
    @Json(name="duration") val duration: Long = 0L,
    @Json(name="title") val title: String = "",
    @Json(name="degree") val degree: String = "",
    @Json(name="image") val thumbnailLink: String = "",
    @Json(name="country") val country: String = "",
    @Json(name="category") val category: String = "",
    @Json(name="content") val content: String = "",
    @Json(name="deadline") val deadline: String = "",
    @Json(name="requirements") val requirements: List<String> = emptyList() ,
)


@JsonClass(generateAdapter = true)
data class ApiOpportunities(
    @Json(name="id") val body: List<ApiOpportunity>,
)