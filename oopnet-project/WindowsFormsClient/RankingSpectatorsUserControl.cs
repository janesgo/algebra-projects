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
    public partial class RankingSpectatorsUserControl : UserControl
    {
        public RankingSpectatorsUserControl(string place, string location, string attendance, string homeTeam, string awayTeam)
        {
            InitializeComponent();
            lblPlace.Text = place;
            lblLocation.Text = location;
            lblAttendance.Text = attendance;
            lblHome.Text = homeTeam;
            lblAway.Text = awayTeam;
        }
    }
}
