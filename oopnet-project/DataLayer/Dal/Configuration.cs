using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Dal
{
    public static class Configuration
    {
        private const string CONF = "conf.txt";

        public static bool CheckConfiguration() => File.Exists(CONF);

        public static T LoadConfiguration<T>()
        {
            string[] conf = File.ReadAllLines(CONF);
            string s = "";

            if (typeof(Gender) == typeof(T))
            {
                s = conf[0];
            }
            else if (typeof(AccessType) == typeof(T))
            {
                s = conf[1];
            }
            else if (typeof(Language) == typeof(T))
            {
                s = conf[2];
            }
            else if (typeof(ScreenSize) == typeof(T))
            {
                if (conf.Length == 4)
                {
                    s = conf[3];
                }
                else
                {
                    s = "Small";
                }
            }

            return (T)Enum.Parse(typeof(T), s);
        }

        public static void SaveConfiguration(Gender gender, AccessType accessType, Language language)
        {
            string[] contents = { Enum.GetName(typeof(Gender), gender), Enum.GetName(typeof(AccessType), accessType), Enum.GetName(typeof(Language), language) };
            File.WriteAllLines(CONF, contents);
        }

        public static void SaveConfiguration(Gender gender, AccessType accessType, Language language, ScreenSize screenSize)
        {
            string[] contents = {
                Enum.GetName(typeof(Gender), gender),
                Enum.GetName(typeof(AccessType), accessType),
                Enum.GetName(typeof(Language), language),
                Enum.GetName(typeof(ScreenSize), screenSize)
            };

            File.WriteAllLines(CONF, contents);
        }

    }
}
