using DataLayer;
using DataLayer.Dal;
using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Threading;
using System.Windows;
using System.Windows.Controls;
using WPFClient.UserControls;
using static DataLayer.Models.Enums;
using static WPFClient.Utility.ScreenSizeExtensions;

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private const char SEPARATOR = 'x';

        private IRepository repository;

        public Gender Gender { get; set; }
        public Language AppLanguage { get; set; }
        public AccessType AccessType { get; set; }
        public ScreenSize ScreenSize { get; set; }

        public string SavedTeam { get; set; }
        public List<TeamResults> Teams { get; set; }
        public List<Match> TeamMatches { get; set; }
        public TeamResults FirstTeam { get; set; }
        public TeamResults SecondTeam { get; set; }
        public Match Match { get; set; }
        public List<MatchPlayer> FirstTeamStartingPlayers { get; set; }
        public List<MatchPlayer> SecondTeamStartingPlayers { get; set; }

        public MainWindow()
        {
            LoadConfiguration();
            SetLanguage();
            InitializeComponent();
            SetScreenSize();
            LoadSavedTeam();
            GetFirstTeams();
        }

        private void ClearPlayers()
        {
            foreach (StackPanel panel in pitch.Children)
            {
                panel.Children.Clear();
            }
        }

        private async void GetFirstTeams()
        {
            repository = RepositoryFactory.GetRepository(Gender, AccessType);

            try
            {
                if (AccessType == AccessType.File)
                {
                    Teams = new List<TeamResults>(repository.GetTeamResults());
                }
                else
                {
                    Teams = new List<TeamResults>(await repository.GetTeamResultsAsync());
                }
                Teams.Sort();
                Teams.ForEach(t => cboxFirstTeam.Items.Add(t));

                // Load saved team
                if (SavedTeam != "")
                {
                    foreach (TeamResults t in cboxFirstTeam.Items)
                    {
                        if (t.FifaCode == SavedTeam)
                        {
                            cboxFirstTeam.SelectedItem = t;
                        }
                    }
                }
            }
            catch (Exception)
            {
                MessageBox.Show(Properties.Resources.errLoadTeams);
            }
        }

        private void GetSecondTeams(string country)
        {
            foreach (Match m in TeamMatches)
            {
                if (m.AwayTeamCountry != country)
                {
                    cboxSecondTeam.Items.Add(Teams.First(t => t.Country == m.AwayTeam.Country));
                }
                else
                {
                    cboxSecondTeam.Items.Add(Teams.First(t => t.Country == m.HomeTeam.Country));
                }
            }
        }

        private async void GetTeamMatches(string fifaCode)
        {
            try
            {
                if (AccessType == AccessType.File)
                {
                    TeamMatches = new List<Match>(repository.GetTeamMatches(fifaCode));
                }
                else
                {
                    TeamMatches = new List<Match>(await repository.GetTeamMatchesAsync(fifaCode));
                }

                GetSecondTeams((cboxFirstTeam.SelectedItem as TeamResults).Country);

            }
            catch (Exception)
            {
                MessageBox.Show(Application.Current.FindResource("errLoadTeam") as string);
            }
        }

        private void LoadConfiguration()
        {
            if (!Configuration.CheckConfiguration())
            {
                Utility.Utils.OpenInitialSettings();
            }

            try
            {
                Gender = Configuration.LoadConfiguration<Gender>();
                AppLanguage = Configuration.LoadConfiguration<Language>();
                AccessType = Configuration.LoadConfiguration<AccessType>();
                ScreenSize = Configuration.LoadConfiguration<ScreenSize>();
            }
            catch (Exception)
            {
                MessageBox.Show($"{Properties.Resources.errLoadConfig}");

                // Set default values
                Gender = Gender.Men;
                AppLanguage = DataLayer.Models.Enums.Language.English;
                AccessType = AccessType.File;
                ScreenSize = ScreenSize.Small;
            }
        }

        private void LoadImages()
        {
            string path = AppDomain.CurrentDomain.BaseDirectory;

            foreach (MatchPlayer player in FirstTeamStartingPlayers)
            {
                player.Picture = (Bitmap)Utils.LoadImageFromFile(path, player.Name.ToLower().Trim().Replace(" ", ""));

                if (player.Picture == null)
                {
                    player.Picture = Properties.PlayersBack.first_background;
                }
            }
            foreach (MatchPlayer player in SecondTeamStartingPlayers)
            {
                player.Picture = (Bitmap)Utils.LoadImageFromFile(path, player.Name.ToLower().Trim().Replace(" ", ""));

                if (player.Picture == null)
                {
                    player.Picture = Properties.PlayersBack.second_background;
                }
            }
        }

        private void LoadPlayersToPitch()
        {
            double height = pitch.ActualHeight / 60;

            ClearPlayers();

            foreach (MatchPlayer player in FirstTeamStartingPlayers)
            {
                if (player.Position == Position.Goalie)
                {
                    f1.Children.Add(new PlayerUC(player, Match));
                }
                else if (player.Position == Position.Defender)
                {
                    f2.Children.Add(new Separator { Height = height, Opacity = 0 });
                    f2.Children.Add(new PlayerUC(player, Match));
                    f2.Children.Add(new Separator { Height = height, Opacity = 0 });
                }
                else if (player.Position == Position.Midfield)
                {
                    f3.Children.Add(new Separator { Height = height, Opacity = 0 });
                    f3.Children.Add(new PlayerUC(player, Match));
                    f3.Children.Add(new Separator { Height = height, Opacity = 0 });
                }
                else
                {
                    f4.Children.Add(new Separator { Height = height, Opacity = 0 });
                    f4.Children.Add(new PlayerUC(player, Match));
                    f4.Children.Add(new Separator { Height = height, Opacity = 0 });
                }
            }

            foreach (MatchPlayer player in SecondTeamStartingPlayers)
            {
                if (player.Position == Position.Goalie)
                {
                    s1.Children.Add(new PlayerUC(player, Match));
                }
                else if (player.Position == Position.Defender)
                {
                    s2.Children.Add(new Separator { Height = height, Opacity = 0 });
                    s2.Children.Add(new PlayerUC(player, Match));
                    s2.Children.Add(new Separator { Height = height, Opacity = 0 });
                }
                else if (player.Position == Position.Midfield)
                {
                    s3.Children.Add(new Separator { Height = height, Opacity = 0 });
                    s3.Children.Add(new PlayerUC(player, Match));
                    s3.Children.Add(new Separator { Height = height, Opacity = 0 });
                }
                else
                {
                    s4.Children.Add(new Separator { Height = height, Opacity = 0 });
                    s4.Children.Add(new PlayerUC(player, Match));
                    s4.Children.Add(new Separator { Height = height, Opacity = 0 });
                }
            }
        }

        private void LoadSavedTeam()
        {
            string[] data = Utils.LoadPlayersFromFile();

            if (data == null)
            {
                return;
            }

            if (data.Length > 0)
            {
                SavedTeam = data[0];
            }
        }

        private void SetLanguage()
        {
            string culture = AppLanguage == DataLayer.Models.Enums.Language.Croatian ? "hr" : "en";
            Thread.CurrentThread.CurrentUICulture = new CultureInfo(culture);
        }

        private void SetScreenSize()
        {
            string s = ScreenSize.ScreenSizeToValues();
            if (s != "Fullscreen")
            {
                Width = double.Parse(ScreenSize.ScreenSizeToValues().Split(SEPARATOR)[0]);
                Height = double.Parse(ScreenSize.ScreenSizeToValues().Split(SEPARATOR)[1]);
            }
            else
            {
                WindowState = WindowState.Maximized;
            }
        }

        private void btnInfo_Click(object sender, RoutedEventArgs e)
        {
            TeamInfo teamInfo;

            if ((sender as Button).Name.Contains("First"))
            {
                teamInfo = new TeamInfo(FirstTeam);
            }
            else
            {
                teamInfo = new TeamInfo(SecondTeam);
            }

            teamInfo.Show();
        }

        private void cboxFirstTeam_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            ClearPlayers();
            lblFirstTeamScore.Content = "";
            lblSecondTeamScore.Content = "";
            lblFirstTeamScorePenalties.Content = "";
            lblSecondTeamScorePenalties.Content = "";
            lblSecondTeamName.Content = "";
            lblScoreMiddle.Visibility = Visibility.Hidden;
            lblScoreMiddlePenalties.Visibility = Visibility.Hidden;
            btnSecondTeamInfo.Visibility = Visibility.Hidden;

            if (cboxFirstTeam.SelectedItem != null)
            {
                FirstTeam = cboxFirstTeam.SelectedItem as TeamResults;
                lblFirstTeamName.Content = FirstTeam.Country;
                btnFirstTeamInfo.Visibility = Visibility.Visible;
                cboxSecondTeam.Items.Clear();
                GetTeamMatches((cboxFirstTeam.SelectedItem as TeamResults).FifaCode);
            }
        }

        private void cboxSecondTeam_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            bool penalties = false;
            SecondTeam = cboxSecondTeam.SelectedItem as TeamResults;

            lblFirstTeamScorePenalties.Content = "";
            lblSecondTeamScorePenalties.Content = "";
            lblScoreMiddlePenalties.Visibility = Visibility.Hidden;

            if (e.AddedItems.Count > 0)
            {
                lblSecondTeamName.Content = SecondTeam.Country;
                btnSecondTeamInfo.Visibility = Visibility.Visible;
                Match = TeamMatches.First(m => m.HomeTeamCountry == SecondTeam.Country || m.AwayTeamCountry == SecondTeam.Country);

                if (Match.AwayTeam.Penalties > 0)
                {
                    penalties = true;
                    lblScoreMiddlePenalties.Visibility = Visibility.Visible;
                }

                if (Match.AwayTeamCountry == FirstTeam.Country)
                {
                    lblFirstTeamScore.Content = Match.AwayTeam.Goals.ToString();
                    lblSecondTeamScore.Content = Match.HomeTeam.Goals.ToString();

                    if (penalties)
                    {
                        lblFirstTeamScorePenalties.Content = Match.AwayTeam.Penalties.ToString();
                        lblSecondTeamScorePenalties.Content = Match.HomeTeam.Penalties.ToString();

                    }

                    FirstTeamStartingPlayers = Match.AwayTeamStatistics.StartingEleven.ToList();
                    SecondTeamStartingPlayers = Match.HomeTeamStatistics.StartingEleven.ToList();
                }
                else
                {
                    lblFirstTeamScore.Content = Match.HomeTeam.Goals.ToString();
                    lblSecondTeamScore.Content = Match.AwayTeam.Goals.ToString();

                    if (penalties)
                    {
                        lblFirstTeamScorePenalties.Content = Match.HomeTeam.Penalties.ToString();
                        lblSecondTeamScorePenalties.Content = Match.AwayTeam.Penalties.ToString();
                    }

                    FirstTeamStartingPlayers = Match.HomeTeamStatistics.StartingEleven.ToList();
                    SecondTeamStartingPlayers = Match.AwayTeamStatistics.StartingEleven.ToList();
                }

                lblScoreMiddle.Visibility = Visibility.Visible;

                LoadImages();
                LoadPlayersToPitch();
            }
        }

        private void menuSettings_Click(object sender, RoutedEventArgs e)
        {
            Settings settings = new Settings();

            if (settings.ShowDialog() == true)
            {
                try
                {
                    Gender = (Gender)Enum.Parse(typeof(Gender), settings.GetValues()[0]);
                    AppLanguage = (Language)Enum.Parse(typeof(Language), settings.GetValues()[1]);
                    cboxFirstTeam.Items.Clear();
                    GetFirstTeams();
                }
                catch (Exception)
                {
                    MessageBox.Show(Properties.Resources.errSettings);
                }
            }
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            if (MessageBox.Show(Properties.Resources.exitConfirm, Properties.Resources.exitConfirmTitle, MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                try
                {
                    Configuration.SaveConfiguration(Gender, AccessType, AppLanguage, ScreenSize);
                    e.Cancel = false;
                }
                catch (Exception)
                {
                    MessageBox.Show(Properties.Resources.errSaveConfig);
                }
            }
            else
            {
                e.Cancel = true;
            }
        }
    }
}
