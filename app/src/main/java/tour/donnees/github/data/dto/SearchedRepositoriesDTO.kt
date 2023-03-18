package tour.donnees.github.data.dto

import com.google.gson.annotations.SerializedName

data class SearchedRepositoriesDTO(
    @SerializedName("incomplete_results") val incompleteResults: Boolean? = false,
    @SerializedName("items") val items: List<ItemDTO>? = emptyList(),
    @SerializedName("total_count") val totalCount: Int? = 0
)