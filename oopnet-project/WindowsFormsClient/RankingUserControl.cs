using System.Windows.Forms;

namespace WindowsFormsClient
{
    public partial class RankingUserControl : UserControl
    {
        public RankingUserControl(string ranking, string name, string goals)
        {
            InitializeComponent();
            lblRanking.Text = ranking;
            lblName.Text = name;
            lblGoals.Text = goals;
        }
    }
}
