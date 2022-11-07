const CATALOG_SERVICE = "/first-service"
const AGENCY_SERVICE = "/second-service"
const SECRET_SERVICE = "/third-service"


export const FLATS_API = `${CATALOG_SERVICE}/catalog/flats`
export const HEIGHT_SUM_API = `${CATALOG_SERVICE}/catalog/flats/height-sum`
export const COUNT_BY_NEW_API = `${CATALOG_SERVICE}/catalog/flats/count-by-new`
export const GET_WITH_MAX_ID_API = `${CATALOG_SERVICE}/catalog/flats/max-id`

export const CALCULATE_PRICES_SUM = `${AGENCY_SERVICE}/agency/get-total-cost`
export const GET_CHEAPEST = `${AGENCY_SERVICE}/agency/get-cheapest`

export const COUNTRIES_API = `${SECRET_SERVICE}/secret/countries`
export const HOUSES_API = `${SECRET_SERVICE}/secret`
export const SPONSOR_API = `${SECRET_SERVICE}/secret/donate`