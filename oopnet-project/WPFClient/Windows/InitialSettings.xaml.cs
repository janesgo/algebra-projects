using DataLayer.Dal;
using System;
using System.Windows;
using static DataLayer.Models.Enums;

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for InitialSettings.xaml
    /// </summary>
    public partial class InitialSettings : Window
    {
        public InitialSettings()
        {
            InitializeComponent();
        }

        private void btnStartApp_Click(object sender, RoutedEventArgs e)
        {
            string s;
            Gender gender;
            AccessType accessType;
            Language language;
            ScreenSize screenSize;

            if (rbMen.IsChecked.Value)
            {
                s = rbMen.Tag.ToString();
            }
            else
            {
                s = rbWomen.Tag.ToString();
            }
            gender = (Gender)Enum.Parse(typeof(Gender), s);

            if (rbCroatian.IsChecked.Value)
            {
                s = rbCroatian.Tag.ToString();
            }
            else
            {
                s = rbEnglish.Tag.ToString();
            }
            language = (Language)Enum.Parse(typeof(Language), s);

            if (rbFile.IsChecked.Value)
            {
                s = rbFile.Tag.ToString();
            }
            else
            {
                s = rbWeb.Tag.ToString();
            }
            accessType = (AccessType)Enum.Parse(typeof(AccessType), s);

            if (rbFullscreen.IsChecked.Value)
            {
                s = rbFullscreen.Tag.ToString();
            }
            else if (rbSmall.IsChecked.Value)
            {
                s = rbSmall.Tag.ToString();
            }
            else if (rbMedium.IsChecked.Value)
            {
                s = rbMedium.Tag.ToString();
            }
            else
            {
                s = rbLarge.Tag.ToString();
            }
            screenSize = (ScreenSize)Enum.Parse(typeof(ScreenSize), s);

            try
            {
                Configuration.SaveConfiguration(gender, accessType, language, screenSize);
            }
            catch (Exception)
            {
                MessageBox.Show(Properties.Resources.errSaveConfig);
                Environment.Exit(-1);
            }

            DialogResult = true;
        }
    }
}
