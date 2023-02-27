using DataLayer;
using DataLayer.Dal;
using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using static DataLayer.Models.Enums;

namespace WindowsFormsClient
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            LoadConfig();
            SetCulture();
            LoadSavedData();
            InitializeComponent();
        }

        private IRepository repo;
        private List<Team> teams;
        private List<Match> teamMatches;
        private List<MatchPlayer> players = new List<MatchPlayer>();
        private List<MatchPlayer> favouritePlayers = new List<MatchPlayer>();
        private string[] savedData;
        private bool initialSaveDataLoaded;

        public AccessType AccessType { get; set; }
        public Gender Gender { get; set; }
        public Language Language { get; set; }

        private static void LoadInitialConfiguration(InitialConfiguration initialConfiguration)
        {
            if (initialConfiguration.ShowDialog() != DialogResult.OK)
            {
                Application.Exit();
            }
        }

        internal void AddToFavourites()
        {
            foreach (PlayerUserControl puc in flpPlayers.Controls)
            {
                if (puc.Player.Favourite)
                {
                    favouritePlayers.Add(puc.Player);
                }
                players.RemoveAll(p => p.Favourite);
            }
            AddPlayersToPanels();
        }

        internal void AddPlayersToPanels()
        {
            flpPlayers.Controls.Clear();
            flpFavouritePlayers.Controls.Clear();

            players.ForEach(p =>
            {
                p.Favourite = false;
                flpPlayers.Controls.Add(new PlayerUserControl(Gender, p, GetFavouritePlayersCount()));
            });
            favouritePlayers.ForEach(fp =>
            {
                fp.Favourite = true;
                flpFavouritePlayers.Controls.Add(new PlayerUserControl(Gender, fp, GetFavouritePlayersCount()));
            });
        }

        private void ClearAll()
        {
            players.Clear();
            favouritePlayers.Clear();
            ddlFavouriteTeam.Items.Clear();
            flpPlayers.Controls.Clear();
            flpFavouritePlayers.Controls.Clear();
        }

        private bool ConfirmExit()
        {
            if (MessageBox.Show("Do you want to exit?", "Confirm exit", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                return true;
            }
            return false;
        }

        internal int GetFavouritePlayersCount() => favouritePlayers.Count;

        private List<string> GenerateRankingGoals(Team team)
        {
            List<MatchTeamEvent> events = new List<MatchTeamEvent>();
            List<string> players = new List<string>();

            foreach (Match m in teamMatches)
            {
                if (m.HomeTeam.Code == team.FifaCode)
                {
                    events.AddRange(m.HomeTeamEvents);
                }
                else
                {
                    events.AddRange(m.AwayTeamEvents);
                }
            }
            events.ForEach(ev =>
            {
                if (ev.TypeOfEvent == TypeOfEvent.Goal || ev.TypeOfEvent == TypeOfEvent.GoalPenalty)
                {
                    players.Add(ev.Player);
                }
            });

            return players;
        }

        private List<string> GenerateRankingYellowCards(Team team)
        {
            List<MatchTeamEvent> events = new List<MatchTeamEvent>();
            List<string> players = new List<string>();

            foreach (Match m in teamMatches)
            {
                if (m.HomeTeam.Code == team.FifaCode)
                {
                    events.AddRange(m.HomeTeamEvents);
                }
                else
                {
                    events.AddRange(m.AwayTeamEvents);
                }
            }
            events.ForEach(ev =>
            {
                if (ev.TypeOfEvent == TypeOfEvent.YellowCard)
                {
                    players.Add(ev.Player);
                }
            });

            return players;
        }

        private void LoadConfig()
        {
            InitialConfiguration initialConfiguration = new InitialConfiguration();

            if (!Configuration.CheckConfiguration())
            {
                LoadInitialConfiguration(initialConfiguration);
            }

            try
            {
                Gender = Configuration.LoadConfiguration<Gender>();
                AccessType = Configuration.LoadConfiguration<AccessType>();
                Language = Configuration.LoadConfiguration<Language>();
            }
            catch (Exception)
            {
                MessageBox.Show("Cannot load configuration");
                LoadInitialConfiguration(initialConfiguration);
            }
        }

        private async void LoadPlayers(string fifaCode)
        {
            List<MatchPlayer> startingPlayers, subtitutionPlayers;
            favouritePlayers.Clear();
            try
            {
                if (AccessType == AccessType.File)
                {
                    teamMatches = new List<Match>(repo.GetTeamMatches(fifaCode));
                }
                else
                {
                    teamMatches = new List<Match>(await repo.GetTeamMatchesAsync(fifaCode));
                }

                Match match = teamMatches.First();
                if (match.HomeTeam.Code == fifaCode)
                {
                    startingPlayers = new List<MatchPlayer>(match.HomeTeamStatistics.StartingEleven);
                    subtitutionPlayers = new List<MatchPlayer>(match.HomeTeamStatistics.Substitutes);
                }
                else
                {
                    startingPlayers = new List<MatchPlayer>(match.AwayTeamStatistics.StartingEleven);
                    subtitutionPlayers = new List<MatchPlayer>(match.AwayTeamStatistics.Substitutes);
                }

                players = new List<MatchPlayer>(startingPlayers.Union(subtitutionPlayers));

                // Load saved players on initial run only if saved favourite players not empty
                if (!initialSaveDataLoaded)
                {
                    if (savedData != null && savedData.Length == 3 && savedData[2] != "")
                    {
                        LoadSavedPlayers();
                        initialSaveDataLoaded = true;
                    }
                }

                AddPlayersToPanels();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Cannot load players\n\n{ex.Message}");
            }
        }

        private void LoadSavedData()
        {
            string[] data = Utils.LoadPlayersFromFile();

            if (data == null) return;

            if (data.Length == 3)
            {
                savedData = data;
            }
        }

        private void LoadSavedPlayers()
        {
            string[] savedPlayers = savedData[1].Split(',');
            string[] savedFavouritePlayers = savedData[2].Split(',');

            List<string> allSavedPlayers = new List<string>();
            allSavedPlayers.AddRange(savedPlayers);
            allSavedPlayers.AddRange(savedFavouritePlayers);

            List<string> notFound = players.Select(p => p.Name).Except(allSavedPlayers).ToList();
            if (notFound.Count > 0) return;

            for (int i = 0; i < savedFavouritePlayers.Length; i++)
            {
                MatchPlayer mp = players.Find(p => p.Name == savedFavouritePlayers[i]);
                favouritePlayers.Add(mp);
                players.Remove(mp);
            }
        }

        private async void LoadTeams(string fifaCode)
        {
            ClearAll();
            rankingsToolStripMenuItem.Enabled = false;

            try
            {
                if (AccessType == AccessType.File)
                {
                    teams = new List<Team>(repo.GetTeams());
                }
                else
                {
                    teams = new List<Team>(await repo.GetTeamsAsync());
                }
                teams.Sort();
                teams.ForEach(t => ddlFavouriteTeam.Items.Add(t));

                // Load saved team
                if (fifaCode != "")
                {
                    foreach (Team team in ddlFavouriteTeam.Items)
                    {
                        if (team.FifaCode == fifaCode)
                        {
                            ddlFavouriteTeam.SelectedItem = team;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Cannot load teams\n\n{ex.Message}");
            }
        }

        internal void RemoveFromFavourites()
        {
            foreach (PlayerUserControl puc in flpFavouritePlayers.Controls)
            {
                if (!puc.Player.Favourite)
                {
                    players.Add(puc.Player);
                }
                favouritePlayers.RemoveAll(p => !p.Favourite);
            }
            AddPlayersToPanels();
        }

        private void SetCulture()
        {
            CultureInfo cultureInfo = new CultureInfo(Language == Language.Croatian ? "hr" : "en");

            Thread.CurrentThread.CurrentUICulture = cultureInfo;
            Thread.CurrentThread.CurrentCulture = cultureInfo;
        }

        private void UpdateUI()
        {
            List<MatchPlayer> tmpPlayers = new List<MatchPlayer>(players);
            List<MatchPlayer> tmpFavouritePlayers = new List<MatchPlayer>(favouritePlayers);
            int selectedTeamIndex = ddlFavouriteTeam.SelectedIndex;

            Controls.Clear();
            // Stop form closing called multiple time
            FormClosing -= new FormClosingEventHandler(MainForm_FormClosing);
            InitializeComponent();
            teams?.ForEach(t => ddlFavouriteTeam.Items.Add(t));
            ddlFavouriteTeam.SelectedIndex = selectedTeamIndex;
            players = tmpPlayers;
            favouritePlayers = tmpFavouritePlayers;
            AddPlayersToPanels();
        }

        private void ddlFavouriteTeam_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlFavouriteTeam.Items.Count > 0)
                LoadPlayers(((Team)ddlFavouriteTeam.SelectedItem).FifaCode);
            (new PlayerUserControl()).ResetCounter();
            rankingsToolStripMenuItem.Enabled = true;
        }

        private void flp_DragEnter(object sender, DragEventArgs e)
        {
            PlayerUserControl puc = (PlayerUserControl)e.Data.GetData(typeof(PlayerUserControl));
            FlowLayoutPanel source = sender as FlowLayoutPanel;

            if (e.Data.GetDataPresent(typeof(PlayerUserControl)) && source != puc.Parent)
            {
                e.Effect = DragDropEffects.Move;
            }
            else
            {
                e.Effect = DragDropEffects.None;
            }
        }

        private void flp_DragDrop(object sender, DragEventArgs e)
        {
            PlayerUserControl puc = (PlayerUserControl)e.Data.GetData(typeof(PlayerUserControl));
            FlowLayoutPanel source = sender as FlowLayoutPanel;

            if (source != puc.Parent)
            {
                if (puc.Parent.Name == "flpPlayers")
                {
                    AddToFavourites();
                }
                else
                {
                    RemoveFromFavourites();
                }
            }
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (!ConfirmExit())
            {
                e.Cancel = true;
            }
            else
            {
                if (ddlFavouriteTeam.SelectedItem is Team t && t != null)
                    Utils.SavePlayersToFile(((Team)ddlFavouriteTeam.SelectedItem)?.FifaCode, players, favouritePlayers);
                e.Cancel = false;
            }
        }

        private void MainForm_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Escape && ConfirmExit())
            {
                Close();
            }
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            repo = RepositoryFactory.GetRepository(Gender, AccessType);

            if (savedData != null)
            {
                LoadTeams(savedData[0]);
            }
            else
            {
                LoadTeams("");
            }
        }

        private void tsmiAttendance_Click(object sender, EventArgs e)
        {
            Team team = (Team)ddlFavouriteTeam.SelectedItem;
            List<Match> matches = new List<Match>(teamMatches);
            matches.Sort((m1, m2) => -m1.Attendance.CompareTo(m2.Attendance));
            RankingSpectators ranking = new RankingSpectators(team, matches);
            ranking.Text = "Project - Ranking - Spectators";
            ranking.Show();
        }

        private void tsmiGoals_Click(object sender, EventArgs e)
        {
            Team team = (Team)ddlFavouriteTeam.SelectedItem;
            if (team != null)
            {
                RankingPlayers ranking = new RankingPlayers(team, GenerateRankingGoals(team));
                ranking.Text = "Project - Ranking - Scored goals";
                (ranking.Controls.Find("lblValue", true).First() as Label).Text = "Goals";
                ranking.Show();
            }
        }

        private void tsmiSettings_Click(object sender, EventArgs e)
        {
            bool loadData = false;
            Settings settings = new Settings(Gender, AccessType, Language);

            if (settings.ShowDialog() == DialogResult.OK)
            {
                string[] s = (settings.Controls.Find("btnOK", true).First() as Button).Tag.ToString().Split(',');
                try
                {
                    Gender g = (Gender)Enum.Parse(typeof(Gender), s[0]);
                    if (Gender != g)
                    {
                        Gender = g;
                        loadData = true;
                    }

                    AccessType a = (AccessType)Enum.Parse(typeof(AccessType), s[1]);
                    if (AccessType != a)
                    {
                        AccessType = a;
                        loadData = true;
                    }

                    Language l = (Language)Enum.Parse(typeof(Language), s[2]);
                    if (Language != l)
                    {
                        Language = l;
                        SetCulture();
                        UpdateUI();
                    }

                    if (loadData)
                    {
                        repo = RepositoryFactory.GetRepository(Gender, AccessType);
                        LoadTeams("");
                    }
                }
                catch (Exception)
                {
                    rankingsToolStripMenuItem.Enabled = false;
                }
            }
        }

        private void tsmiYellowCards_Click(object sender, EventArgs e)
        {
            Team team = (Team)ddlFavouriteTeam.SelectedItem;
            RankingPlayers ranking = new RankingPlayers(team, GenerateRankingYellowCards(team));
            ranking.Text = "Project - Ranking - Yellow cards";
            (ranking.Controls.Find("lblValue", true).First() as Label).Text = "Cards";
            ranking.Show();
        }
    }
}
