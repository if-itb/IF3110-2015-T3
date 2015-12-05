var Response = function(status, message, data) {
    if (typeof data === 'undefined') {
        data = {};
    }
    return {status: status, message: message, data: data};
}

module.exports = Response;