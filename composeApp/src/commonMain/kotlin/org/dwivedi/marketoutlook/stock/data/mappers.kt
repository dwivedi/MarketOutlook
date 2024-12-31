package org.dwivedi.marketoutlook.stock.data

import org.dwivedi.marketoutlook.stock.data.dto.ExchangeDto
import org.dwivedi.marketoutlook.stock.data.dto.StockDto
import org.dwivedi.marketoutlook.stock.data.dto.StockProfileDto
import org.dwivedi.marketoutlook.stock.domain.Exchange
import org.dwivedi.marketoutlook.stock.domain.Stock
import org.dwivedi.marketoutlook.stock.domain.StockProfile


fun ExchangeDto.toExchange(): Exchange = Exchange(
    name = name,
    code = code,
    country = country,
    timezone = timezone
)

fun StockDto.toStock(): Stock = Stock(
    symbol = symbol,
    name = name,
    exchange = exchange,
    currency = currency,
    micCode = micCode,
    figiCode = figiCode,
    country = country
)



fun StockProfileDto.toStockProfile(): StockProfile = StockProfile(
    assetType = assetType,
    name = name,
    symbol = symbol,
    description = description,
    cik = cik,
    exchange = exchange,
    currency = currency,
    country = country,
    sector = sector,
    industry = industry,
    address = address,
    officialSite = officialSite,
    fiscalYearEnd = fiscalYearEnd,
    latestQuarter = latestQuarter,
    marketCapitalization = marketCapitalization,
    ebitda = ebitda,
    peratio = peratio,
    pegratio = pegratio,
    bookValue = bookValue,
    dividendPerShare = dividendPerShare,
    dividendYield = dividendYield,
    eps = eps,
    revenuePerShareTtm = revenuePerShareTtm,
    profitMargin = profitMargin,
    operatingMarginTtm = operatingMarginTtm,
    returnOnAssetsTtm = returnOnAssetsTtm,
    returnOnEquityTtm = returnOnEquityTtm,
    revenueTtm = revenueTtm,
    grossProfitTtm = grossProfitTtm,
    dilutedEpsttm = dilutedEpsttm  ,
    quarterlyEarningsGrowthYoy = quarterlyEarningsGrowthYoy ,
    quarterlyRevenueGrowthYoy = quarterlyRevenueGrowthYoy ,
    analystTargetPrice  = analystTargetPrice,
    analystRatingStrongBuy  = analystRatingStrongBuy,
    analystRatingBuy  = analystRatingBuy,
    analystRatingHold  = analystRatingHold,
    analystRatingSell  = analystRatingSell ,
    analystRatingStrongSell  = analystRatingStrongSell,
    trailingPe  = trailingPe,
    forwardPe  = forwardPe,
    priceToSalesRatioTtm  = priceToSalesRatioTtm,
    priceToBookRatio  = priceToBookRatio,
    evtoRevenue  = evtoRevenue,
    evtoEbitda  = evtoEbitda,
    beta  = beta,
    n52WeekHigh  = n52WeekHigh,
    n52WeekLow  = n52WeekLow,
    n50DayMovingAverage  = n50DayMovingAverage,
    n200DayMovingAverage  = n200DayMovingAverage,
    sharesOutstanding  = sharesOutstanding,
    dividendDate  = dividendDate,
    exDividendDate  = exDividendDate

)


