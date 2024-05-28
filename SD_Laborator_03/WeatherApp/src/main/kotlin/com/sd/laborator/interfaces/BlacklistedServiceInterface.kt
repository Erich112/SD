package com.sd.laborator.interfaces

import com.sd.laborator.services.LocationSearchService

interface BlacklistedServiceInterface {
    var nextService : LocationSearchInterface
    fun isBlacklisted(locationName: String): String
}