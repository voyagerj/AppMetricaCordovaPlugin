using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.Runtime.Serialization;
using System.Windows;
using System.Windows.Controls;
using Microsoft.Phone.Controls;
using WPCordovaClassLib;
using WPCordovaClassLib.Cordova;
using WPCordovaClassLib.Cordova.Commands;
using WPCordovaClassLib.Cordova.JSON;
using Yandex.Metrica;

namespace Cordova.Extension.Commands
{
	/// 
	/// Yandet Metrica wrapper
	/// 
	public sealed class AppMetrica : BaseCommand
	{
		
		/// <summary>
		/// Activate YandexMetrica
		/// </summary>
		/// <param name="options">1st parameter - activation key</param>
		public void activate(string options)
		{
			string[] optValues = JsonHelper.Deserialize<string[]>(options);
			if (optValues != null && optValues.Length > 0)
			{
				var apiKey = optValues[0];
				Deployment.Current.Dispatcher.BeginInvoke(() =>
				{
					try
					{
						YandexMetrica.Config.CrashTracking = true;
						YandexMetrica.Activate(apiKey);
						YandexMetrica.ReportEvent("activated");
					}
					catch (Exception ex)
					{
						Debug.WriteLine("Exception while activate YandexMetrica:" + ex); 
					}
				});
			}
		}		
		
		/// <summary>
		/// Report simple string event
		/// </summary>
		/// <param name="options">1st parameter - event name</param>
		public void reportEvent(string options)
		{
			string[] optValues = JsonHelper.Deserialize<string[]>(options);
			Deployment.Current.Dispatcher.BeginInvoke(() =>
			{
				try
				{
					YandexMetrica.ReportEvent(optValues[0], optValues[1]);
				}
				catch (Exception ex)
				{
					Debug.WriteLine("Exception while activate YandexMetrica:" + ex);
				}
			});
		}		
	}
}
