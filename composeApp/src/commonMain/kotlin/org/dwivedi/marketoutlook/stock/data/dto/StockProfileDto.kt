package org.dwivedi.marketoutlook.stock.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockProfileDto(
    @SerialName("Symbol")
    val symbol: String,
    @SerialName("AssetType")
    val assetType: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Description")
    val description: String,
    @SerialName("CIK")
    val cik: String,
    @SerialName("Exchange")
    val exchange: String,
    @SerialName("Currency")
    val currency: String,
    @SerialName("Country")
    val country: String,
    @SerialName("Sector")
    val sector: String,
    @SerialName("Industry")
    val industry: String,
    @SerialName("Address")
    val address: String,
    @SerialName("OfficialSite")
    val officialSite: String,
    @SerialName("FiscalYearEnd")
    val fiscalYearEnd: String,
    @SerialName("LatestQuarter")
    val latestQuarter: String,
    @SerialName("MarketCapitalization")
    val marketCapitalization: String,
    @SerialName("EBITDA")
    val ebitda: String,
    @SerialName("PERatio")
    val peratio: String,
    @SerialName("PEGRatio")
    val pegratio: String,
    @SerialName("BookValue")
    val bookValue: String,
    @SerialName("DividendPerShare")
    val dividendPerShare: String,
    @SerialName("DividendYield")
    val dividendYield: String,
    @SerialName("EPS")
    val eps: String,
    @SerialName("RevenuePerShareTTM")
    val revenuePerShareTtm: String,
    @SerialName("ProfitMargin")
    val profitMargin: String,
    @SerialName("OperatingMarginTTM")
    val operatingMarginTtm: String,
    @SerialName("ReturnOnAssetsTTM")
    val returnOnAssetsTtm: String,
    @SerialName("ReturnOnEquityTTM")
    val returnOnEquityTtm: String,
    @SerialName("RevenueTTM")
    val revenueTtm: String,
    @SerialName("GrossProfitTTM")
    val grossProfitTtm: String,
    @SerialName("DilutedEPSTTM")
    val dilutedEpsttm: String,
    @SerialName("QuarterlyEarningsGrowthYOY")
    val quarterlyEarningsGrowthYoy: String,
    @SerialName("QuarterlyRevenueGrowthYOY")
    val quarterlyRevenueGrowthYoy: String,
    @SerialName("AnalystTargetPrice")
    val analystTargetPrice: String,
    @SerialName("AnalystRatingStrongBuy")
    val analystRatingStrongBuy: String,
    @SerialName("AnalystRatingBuy")
    val analystRatingBuy: String,
    @SerialName("AnalystRatingHold")
    val analystRatingHold: String,
    @SerialName("AnalystRatingSell")
    val analystRatingSell: String,
    @SerialName("AnalystRatingStrongSell")
    val analystRatingStrongSell: String,
    @SerialName("TrailingPE")
    val trailingPe: String,
    @SerialName("ForwardPE")
    val forwardPe: String,
    @SerialName("PriceToSalesRatioTTM")
    val priceToSalesRatioTtm: String,
    @SerialName("PriceToBookRatio")
    val priceToBookRatio: String,
    @SerialName("EVToRevenue")
    val evtoRevenue: String,
    @SerialName("EVToEBITDA")
    val evtoEbitda: String,
    @SerialName("Beta")
    val beta: String,
    @SerialName("52WeekHigh")
    val n52WeekHigh: String,
    @SerialName("52WeekLow")
    val n52WeekLow: String,
    @SerialName("50DayMovingAverage")
    val n50DayMovingAverage: String,
    @SerialName("200DayMovingAverage")
    val n200DayMovingAverage: String,
    @SerialName("SharesOutstanding")
    val sharesOutstanding: String,
    @SerialName("DividendDate")
    val dividendDate: String,
    @SerialName("ExDividendDate")
    val exDividendDate: String,
)
