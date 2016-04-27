#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>

@interface AppMetrica : CDVPlugin <UIApplicationDelegate>
- (void)activate:(CDVInvokedUrlCommand*)command;
- (void)reportEvent:(CDVInvokedUrlCommand*)command;
@end
