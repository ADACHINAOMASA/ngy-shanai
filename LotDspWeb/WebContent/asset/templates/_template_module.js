var _Module = (function() {

    // =============================== private function 
    var _say = function(msg) {
        return msg;
    };
    // =============================== private function 

    // =============================== public function 
    var greeting = function() {
        return _say("Hi!");
    };
    
    // =============================== public function 
    return {
        greeting: greeting,
    };
    
})();