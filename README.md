Cordova плагин для Яндекс Метрики 2.4
===========


Две функции поддерживается, вызов:
- window.plugins.appMetrica.activate('key here');
- window.plugins.appMetrica.reportEvent('event name here');

Поддерживаются android и iOS

Порядок установки на ios
-----------
1. ionic platform add ios
2. ionic build ios
3. cordova plugin add https://github.com/MrFlox/AppMetricaCordovaPlugin.git
4. ionic build ios
5. добавить плагины в Xcode  (libc++, libsqlite3, lib)
полный списко плагинов тут https://tech.yandex.ru/metrica-mobile-sdk/doc/mobile-sdk-dg/tasks/ios-quickstart-docpage/
