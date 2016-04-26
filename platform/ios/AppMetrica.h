#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>
// #import "YandexMobileMetrica.h"

@interface AppMetrica : CDVPlugin <UIApplicationDelegate>
- (void)activate:(CDVInvokedUrlCommand*)command;
- (void)reportEvent:(CDVInvokedUrlCommand*)command;
@end
