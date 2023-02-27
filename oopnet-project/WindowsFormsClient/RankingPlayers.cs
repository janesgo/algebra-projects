using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Windows.Forms;

namespace WindowsFormsClient
{
    public partial class RankingPlayers : Form
    {
        private readonly Team team;
        private readonly List<string> list;

        public RankingPlayers(Team team, List<string> ls)
        {
            InitializeComponent();
            this.team = team;
            list = ls;
        }

        private void Ranking_Load(object sender, EventArgs e)
        {
            flpRanking.Controls.Clear();
            lblTeam.Text = team.Country;

            var ranking = list.GroupBy(p => p).Select(g => new { Name = g.Key, Goals = g.Count() }).OrderByDescending(go => go.Goals);

            int i = 1;

            foreach (var r in ranking)
            {
                flpRanking.Controls.Add(new RankingUserControl(i++.ToString(), r.Name, r.Goals.ToString()));
            }
        }
    }
}
