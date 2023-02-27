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
using System.Windows.Shapes;
using static DataLayer.Models.Enums;

namespace WPFClient.Windows
{
    /// <summary>
    /// Interaction logic for PlayerInfo.xaml
    /// </summary>
    public partial class PlayerInfo : Window
    {
        public Match Match { get; set; }
        public MatchPlayer MatchPlayer { get; set; }

        public PlayerInfo(MatchPlayer matchPlayer, Match match)
        {
            InitializeComponent();
            Match = match;
            MatchPlayer = matchPlayer;
            LoadData();
        }

        private void LoadData()
        {
            playerName.Content = MatchPlayer.Name;
            shirtNumber.Content = MatchPlayer.ShirtNumber.ToString();
            position.Content = MatchPlayer.Position.ToString();
            captain.Content = MatchPlayer.Captain ? $"{Properties.Resources.yes}" : $"{Properties.Resources.no}";
            goals.Content = GetGoalsCount();
            yellowCards.Content = GetYellowCardsCount();
            playerImage.Source = Utility.Utils.BitmapToImageSource(MatchPlayer.Picture);
        }

        private int GetYellowCardsCount()
        {
            int count = 0;

            try
            {
                count += Match.AwayTeamEvents.ToList().FindAll(e => e.Player == MatchPlayer.Name && e.TypeOfEvent == TypeOfEvent.YellowCard).Count();
                count += Match.HomeTeamEvents.ToList().FindAll(e => e.Player == MatchPlayer.Name && e.TypeOfEvent == TypeOfEvent.YellowCard).Count();

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

            return count;
        }

        private object GetGoalsCount()
        {
            int count = 0;

            try
            {
                count += Match.AwayTeamEvents.ToList().FindAll(e => e.Player == MatchPlayer.Name && (e.TypeOfEvent == TypeOfEvent.Goal || e.TypeOfEvent == TypeOfEvent.GoalPenalty)).Count();
                count += Match.HomeTeamEvents.ToList().FindAll(e => e.Player == MatchPlayer.Name && (e.TypeOfEvent == TypeOfEvent.Goal || e.TypeOfEvent == TypeOfEvent.GoalPenalty)).Count();

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

            return count;
        }
    }
}
