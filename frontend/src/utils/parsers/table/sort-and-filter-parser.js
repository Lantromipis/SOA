export function filtersToLHSBrackets(filters) {
    return Object.keys(filters).map((key) => {
            if (key && filters[key]) {
                return String.prototype.concat(key, "[", filters[key][0], "]=", filters[key][1])
            }
        }
    ).filter((element) => {
        return element !== undefined
    })
}

export function filtersMapToLHSBrackets(filtersMap) {
    return Array.from(filtersMap.keys()).map((key) => {
            if (key && filtersMap.get(key)) {
                return String.prototype.concat(key, "[", filtersMap.get(key)[0], "]=", filtersMap.get(key)[1])
            }
        }
    ).filter((element) => {
        return element !== undefined
    })
}

export function parseSorterToQueryParam(sort) {
    return String.prototype.concat((sort.order === 'ascend' ? '' : '-'), sort.columnKey);
}