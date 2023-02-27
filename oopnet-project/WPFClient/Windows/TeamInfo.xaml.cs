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

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for TeamInfo.xaml
    /// </summary>
    public partial class TeamInfo : Window
    {
        public TeamResults TeamResults { get; set; }

        public TeamInfo(TeamResults teamResults)
        {
            InitializeComponent();
            TeamResults = teamResults;
            FillValues();
        }

        private void FillValues()
        {
            lblInfoTeam.Content = $"{TeamResults.Country} ({TeamResults.FifaCode})";
            lblInfoGroupLetter.Content = TeamResults.GroupLetter;
            lblInfoGamesPlayed.Content = TeamResults.GamesPlayed;
            lblInfoWins.Content = TeamResults.Wins;
            lblInfoDraws.Content = TeamResults.Draws;
            lblInfoLosses.Content = TeamResults.Losses;
            lblInfoPoints.Content = TeamResults.Points;
            lblInfoGoalsFor.Content = TeamResults.GoalsFor;
            lblInfoGoalsAgainst.Content = TeamResults.GoalsAgainst;
            lblInfoGoalsDifferential.Content = TeamResults.GoalDifferential;
        }
    }
}
