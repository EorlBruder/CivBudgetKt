package de.eorlbruder.civbudget.batch.rates.calculator

import de.eorlbruder.civbudget.batch.domain.Rate
import de.eorlbruder.civbudget.batch.enums.Cycle
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class RateCalculator(val rate: Rate, val now: LocalDate = LocalDate.now()) {

    fun calculate(): Double {
        if (now.isBefore(rate.startDate))
            return 0.0
        if (rate.cycle == Cycle.DAILY)
            return calculateDaily()
        if (rate.cycle == Cycle.WEEKLY)
            return calculateWeekly()
        return calculateMonthly(rate.cycle.value)
    }

    private fun calculateMonthly(cycleMonths: Int): Double {
        var currentStartDate = rate.startDate
        while (!now.isBefore(currentStartDate.plusMonths(cycleMonths.toLong()))) {
            currentStartDate = currentStartDate.plusMonths(cycleMonths.toLong())
        }
        val nextStartDate = currentStartDate.plusMonths(cycleMonths.toLong())
        val daysInCurrentCycle = ChronoUnit.DAYS.between(currentStartDate, nextStartDate)
        return rate.value / daysInCurrentCycle
    }


    private fun calculateWeekly(): Double = rate.value / 7.0

    private fun calculateDaily(): Double = rate.value

}