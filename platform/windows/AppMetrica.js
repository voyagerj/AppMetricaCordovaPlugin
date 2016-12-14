(function (global) {
    var AppMetrica;
    AppMetrica = function () {
    };

    AppMetrica.prototype.activate = function (args) {
        console.log("AppMetrica.prototype.activate:" + args);
        var csharpclass = new AppMetricaWinJSProxy.MetricaProxy();
        csharpclass.activate(args);
        /*
        cordova.exec(function(winParam) {},
            function(error) {
                console.warn('cordova.exec act ' + error)
            },
            "AppMetrica",
            "activate",
            [args]);
            */
    };

    AppMetrica.prototype.reportEvent = function (event, params) {
        console.log("AppMetrica.prototype.reportEvent :" + event + "," + params);
        console.log("js:AppMetrica:reportEvent:" + event + ";" + params);
        var csharpclass = new AppMetricaWinJSProxy.MetricaProxy();
        csharpclass.reportEvent(event, params);
        console.log("js:AppMetrica:reportEvent - end");

        /*
        cordova.exec(function(winParam) {},
            function(error) {
                console.warn('cordova.exec rep' + error)
            },
            "AppMetrica",
            "reportEvent",
            [event, params]);
            */
    };


    global.cordova.addConstructor(function() {
        if (!global.Cordova) {
            global.Cordova = global.cordova;
        };

        if (!global.plugins) {
            global.plugins = {};
        }

        global.plugins.appMetrica = new AppMetrica();
    });
}(window));
