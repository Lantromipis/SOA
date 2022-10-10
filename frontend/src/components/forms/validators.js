export const validateNumberGreaterThanZero = (_, value) => {
    if (value > 0) {
        return Promise.resolve();
    }
    return Promise.reject(new Error('Must be greater than zero!'));
};

export const validateIntegerGreaterThanZero = (_, value) => {
    if (Number.isInteger(value) && value > 0) {
        return Promise.resolve();
    }
    return Promise.reject(new Error('Must be integer greater than zero!'));
};