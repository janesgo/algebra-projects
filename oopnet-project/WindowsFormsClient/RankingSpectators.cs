using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsClient
{
    public partial class RankingSpectators : Form
    {
        private readonly Team team;
        private readonly List<Match> matches;

        public RankingSpectators(Team team, List<Match> matches)
        {
            InitializeComponent();
            this.team = team;
            this.matches = matches;
        }

        private void RankingSpectators_Load(object sender, EventArgs e)
        {
            flpSpectators.Controls.Clear();
            lblTeam.Text = team.Country;
            int i = 1;

            matches.ForEach(m => flpSpectators.Controls.Add(new RankingSpectatorsUserControl(i++.ToString(), m.Location, m.Attendance.ToString(), m.HomeTeamCountry, m.AwayTeamCountry)));
        }
    }
}
