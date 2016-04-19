#import "AppMetricaPlugin.h"
#import "YandexMobileMetrica.h"

@implementation AppMetricaPlugin

- (CDVPlugin *)initWithWebView:(UIWebView *)theWebView
{
    self = (AppMetricaPlugin *)[super initWithWebView:theWebView];
    return self;
}

- (void)activate:(CDVInvokedUrlCommand*)command
{
    if ([command.arguments count] < 1) {
        return;
    }

    NSString* devKey = [command.arguments objectAtIndex:0];

	 [YMMYandexMetrica activateWithApiKey:devKey];
};

- (void)reportEvent:(CDVInvokedUrlCommand*)command
{
  if ([command.arguments count] < 1) {
      return;
  }

  NSString* eventName = [command.arguments objectAtIndex:0];
  NSDictionary *params = @{@"test": @"test"};
  [YMMYandexMetrica reportEvent:eventName
    parameters:params
    onFailure:nil;
  }];
}

@end
