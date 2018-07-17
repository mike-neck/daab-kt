// module daab-kt-js-binding
module.exports = (() => {
    "use strict";
    let object = {};
    object.receive = object.receive || {};
    let receive = object.receive;
    ["stamp", "yesno", "select", "task", "file", "files", "map"]
        .forEach(item => receive[item] = item);
    return object;
})();
