#import "AppMetrica.h"
#import "YandexMobileMetrica/YandexMobileMetrica.h"

@implementation AppMetrica

- (void)activate:(CDVInvokedUrlCommand*)command
{
    if ([command.arguments count] < 1) {
        return;
    }

    NSString* devKey = [command.arguments objectAtIndex:0];
    [YMMYandexMetrica activateWithApiKey:devKey];
    
    NSLog(@"--> Activate");
}

- (void)reportEvent:(CDVInvokedUrlCommand*)command
{
  if ([command.arguments count] < 1) {
      return;
  }

  NSString* eventName = [command.arguments objectAtIndex:0];
    NSMutableDictionary *params = [command argumentAtIndex:1 withDefault:nil andClass:[NSMutableDictionary class]];
    if (params == nil) {
        [YMMYandexMetrica reportEvent:eventName onFailure:^(NSError *error) {NSLog(@"error: %@", [error localizedDescription]);}];
    }
    else {
  [YMMYandexMetrica reportEvent:eventName parameters:params onFailure:^(NSError *error) {NSLog(@"error: %@", [error localizedDescription]);}];
    }
    NSLog(@"reportEvent --> %@", eventName);
}

@end