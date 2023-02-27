using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;
using static DataLayer.Models.Enums;

namespace WPFClient.Utility
{
    public static class Utils
    {
        public static void OpenInitialSettings()
        {
            InitialSettings initialSettings = new InitialSettings();
            Nullable<bool> result = initialSettings.ShowDialog();

            if (!result.Value)
            {
                Environment.Exit(-1);
            }
        }

        public static BitmapImage BitmapToImageSource(Bitmap bitmap)
        {
            using (MemoryStream memory = new MemoryStream())
            {
                bitmap.Save(memory, System.Drawing.Imaging.ImageFormat.Bmp);
                memory.Position = 0;
                BitmapImage bitmapimage = new BitmapImage();
                bitmapimage.BeginInit();
                bitmapimage.StreamSource = memory;
                bitmapimage.CacheOption = BitmapCacheOption.OnLoad;
                bitmapimage.EndInit();

                return bitmapimage;
            }
        }
    }

    public static class ScreenSizeExtensions
    {
        public static string ScreenSizeToValues(this ScreenSize screenSize)
        {
            switch (screenSize)
            {
                case ScreenSize.Fullscreen:
                    return "Fullscreen";
                case ScreenSize.Small:
                    return "800x600";
                case ScreenSize.Medium:
                    return "1024x768";
                case ScreenSize.Large:
                    return "1280x960";
                default:
                    return "";
            }
        }
    }
}
