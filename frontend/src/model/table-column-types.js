import {getGridNumericColumnOperators, getGridStringOperators} from "@mui/x-data-grid";

export const customNumberColumnType = {
    type: 'number',
    filterOperators: getGridNumericColumnOperators()
        .filter((operator) =>
            operator.value === '>' ||
            operator.value === '<' ||
            operator.value === '=' ||
            operator.value === '<=' ||
            operator.value === '>=')
        .map((operator) => {
            return {
                ...operator,
            };
        }),
};

export const customStringColumnType = {
    type: 'string',
    filterOperators: getGridStringOperators()
        .filter((operator) => operator.value === 'contains')
        .map((operator) => {
            return {
                ...operator,
            };
        }),
};