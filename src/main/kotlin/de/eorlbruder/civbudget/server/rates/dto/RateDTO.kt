package de.eorlbruder.civbudget.server.rates.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class RateDTO @JsonCreator constructor(
        @JsonProperty("id")
        val id: Long,

        @JsonProperty("name")
        var name: String,

        @JsonProperty("value")
        var value: Double,

        @JsonProperty("cycle")
        var cycle: String,

        @JsonProperty("startDate")
        var startDate: String,

        @JsonProperty("dailyValue")
        var dailyValue: Double
)