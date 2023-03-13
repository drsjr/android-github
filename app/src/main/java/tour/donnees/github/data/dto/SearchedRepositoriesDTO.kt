package tour.donnees.github.data.dto

import com.google.gson.annotations.SerializedName

data class SearchedRepositoriesDTO(
    @SerializedName("incomplete_results") val incompleteResults: Boolean?,
    @SerializedName("items") val items: List<ItemDTO>?,
    @SerializedName("total_count") val totalCount: Int?
)