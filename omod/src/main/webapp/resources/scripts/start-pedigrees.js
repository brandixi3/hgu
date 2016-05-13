(function () {
    var timeout = 1000;

    waitForPedex();

    function waitForPedex() {
        if (typeof(pedex) !== "undefined" && pedex) {
            pedex.onInjectionDone("pedex");
        }
        else {
            setTimeout(function() { waitForPedex(); }, timeout);
        }
    }
    
    
    pedexIsReady = function() {
    	pedexNewPedigree();
     };
    
}).call(this);