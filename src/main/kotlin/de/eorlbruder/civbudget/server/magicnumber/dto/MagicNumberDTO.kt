package de.eorlbruder.civbudget.server.magicnumber.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class MagicNumberDTO @JsonCreator constructor(

        @JsonProperty("id")
        val id: Long,

        @JsonProperty("value")
        var value: Double,

        @JsonProperty("calculatedDate")
        var calculatedDate: String
)