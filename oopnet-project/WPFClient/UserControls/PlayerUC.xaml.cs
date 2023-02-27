using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using WPFClient.Windows;

namespace WPFClient.UserControls
{
    /// <summary>
    /// Interaction logic for PlayerUsesControl.xaml
    /// </summary>
    public partial class PlayerUC : UserControl
    {
        public Match Match { get; set; }
        public MatchPlayer MatchPlayer { get; set; }

        public PlayerUC(MatchPlayer matchPlayer, Match match)
        {
            InitializeComponent();
            MatchPlayer = matchPlayer;
            Match = match;
            LoadData();
        }

        private void LoadData()
        {
            playerInfo.Text = MatchPlayer.Name;
            playerInfoNumber.Text = MatchPlayer.ShirtNumber.ToString();
            playerPicture.ImageSource = Utility.Utils.BitmapToImageSource(MatchPlayer.Picture);
        }

        private void UserControl_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            PlayerInfo playerInfoWindow = new PlayerInfo(MatchPlayer, Match);
            playerInfoWindow.Show();
        }
    }
}
