using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer
{
    public static class Utils
    {
        private const string PLAYERS = "players.txt";
        private const string LIST_DELIMITER = ",";
        private const char DATA_DELIMITER = ';';
        private const string IMAGEDIR = @"\images\";

        public static bool SavedPlayersFileExists() => File.Exists(PLAYERS);

        public static string[] LoadPlayersFromFile()
        {
            if (!SavedPlayersFileExists()) return null;

            try
            {
                return File.ReadAllText(PLAYERS).Split(DATA_DELIMITER);
            }
            catch (Exception)
            {
                return null;
            }
        }

        public static bool SavePlayersToFile(string fifaCode, IList<MatchPlayer> players, IList<MatchPlayer> favouritePlayers)
        {
            string p = string.Join(LIST_DELIMITER, players);
            string fp = string.Join(LIST_DELIMITER, favouritePlayers);

            try
            {
                File.WriteAllText(PLAYERS, $"{fifaCode}{DATA_DELIMITER}{p}{DATA_DELIMITER}{fp}");
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }

        public static bool ImagesDirExists(string path)
        {
            return File.Exists(path + IMAGEDIR);
        }

        public static Image LoadImageFromFile(string path, string filename)
        {
            Image image = null;

            try
            {
                using (var bmpTemp = new Bitmap(path + IMAGEDIR + filename + ".jpg"))
                {
                    image = new Bitmap(bmpTemp);
                }
            }
            catch (Exception)
            {
            }

            return image;
        }

        public static bool SaveImageToFile(Image image, string path, string filename)
        {
            try
            {
                if (!ImagesDirExists(path))
                {
                    Directory.CreateDirectory(path + IMAGEDIR);
                }

                if (LoadImageFromFile(path, filename) != null)
                {
                    File.Delete(path + IMAGEDIR + filename + ".jpg");
                }

                image.Save(path + IMAGEDIR + filename + ".jpg", ImageFormat.Jpeg);
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }
    }
}
