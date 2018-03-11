package de.eorlbruder.civbudget.server.rates.domain

enum class Cycle(val value: Int) {
    YEARLY(12),
    ELEVEN_MONTHLY(11),
    TEN_MONTHLY(10),
    NINE_MONTHLY(9),
    EIGHT_MONTHLY(8),
    SEVEN_MONTHLY(7),
    HALF_YEARLY(6),
    FIVE_MONTHLY(5),
    FOUR_MONTHLY(4),
    THREE_MONTHLY(3),
    TWO_MONTHLY(2),
    MONTHLY(1),
    WEEKLY(-1),
    DAILY(-1)
}