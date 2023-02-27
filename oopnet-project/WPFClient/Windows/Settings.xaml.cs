using DataLayer.Dal;
using System;
using System.Windows;
using System.Windows.Controls;

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for Settings.xaml
    /// </summary>
    public partial class Settings : Window
    {
        public Settings()
        {
            InitializeComponent();
        }

        private string[] values = new string[2];

        public string[] GetValues() => values;

        private void btnOK_Click(object sender, RoutedEventArgs e)
        {
            string s;

            if (rbMen.IsChecked.Value)
            {
                s = rbMen.Tag.ToString();
            }
            else
            {
                s = rbWomen.Tag.ToString();
            }
            values[0] = s;

            if (rbCroatian.IsChecked.Value)
            {
                s = rbCroatian.Tag.ToString();
            }
            else
            {
                s = rbEnglish.Tag.ToString();
            }
            values[1] = s;

            if (MessageBox.Show(Properties.Resources.settingsSaveConfirm, Properties.Resources.settingsSaveConfirmTitle, MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                DialogResult = true;
            }
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = false;
        }
    }
}
