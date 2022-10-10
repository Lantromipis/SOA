const CATALOG_SERVICE = "http://localhost:8080/first-service"
const AGENCY_SERVICE = "http://localhost:8081/second_service_war"
const SECRET_SERVICE = "http://localhost:8080"


export const FLATS_API = `${CATALOG_SERVICE}/catalog/flats`
export const HEIGHT_SUM_API = `${CATALOG_SERVICE}/catalog/flats/height-sum`
export const COUNT_BY_NEW_API = `${CATALOG_SERVICE}/catalog/flats/count-by-new`
export const GET_WITH_MAX_ID_API = `${CATALOG_SERVICE}/catalog/flats/max-id`

export const CALCULATE_PRICES_SUM = `${AGENCY_SERVICE}/agency/get-total-cost`
export const GET_CHEAPEST = `${AGENCY_SERVICE}/agency/get-cheapest`